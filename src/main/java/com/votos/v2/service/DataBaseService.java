package com.votos.v2.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.votos.v2.model.Centro;
import com.votos.v2.model.Estado;
import com.votos.v2.model.Mesa;
import com.votos.v2.model.Municipio;
import com.votos.v2.model.Parroquia;
import com.votos.v2.repository.CentroRepository;
import com.votos.v2.repository.EstadoRepository;
import com.votos.v2.repository.MesaRepository;
import com.votos.v2.repository.MunicipioRepository;
import com.votos.v2.repository.ParroquiaRepository;

@Service
public class DataBaseService {
    private static final int BATCH_SIZE = 1000;
    
    private final ReadCSVService csvService;
    private final EstadoRepository estadoRepository;
    private final MunicipioRepository municipioRepository;
    private final ParroquiaRepository parroquiaRepository;
    private final CentroRepository centroRepository;
    private final MesaRepository mesaRepository;

    public DataBaseService(ReadCSVService csvService, EstadoRepository estadoRepository, 
                           MunicipioRepository municipioRepository, ParroquiaRepository parroquiaRepository,
                           CentroRepository centroRepository, MesaRepository mesaRepository) {
        this.csvService = csvService;
        this.estadoRepository = estadoRepository;
        this.municipioRepository = municipioRepository;
        this.parroquiaRepository = parroquiaRepository;
        this.centroRepository = centroRepository;
        this.mesaRepository = mesaRepository;
    }

    @Transactional
    public void saveDataToDatabase() {
        List<Map<String, String>> csvContent = csvService.readCsvContent();
        if (csvContent.isEmpty()) {
            System.out.println("No data found in CSV file.");
            return;
        }

        // Prefetch existing data to avoid N+1 queries
        Set<Integer> estadosExistentes = new HashSet<>(estadoRepository.findAll().stream()
                .map(Estado::get_cod).collect(Collectors.toSet()));
        Set<String> municipiosExistentes = new HashSet<>(municipioRepository.findAll().stream()
                .map(Municipio::getId).collect(Collectors.toSet()));
        Set<Integer> parroquiasExistentes = new HashSet<>(parroquiaRepository.findAll().stream()
                .map(Parroquia::get_cod).collect(Collectors.toSet()));
        Set<Integer> centrosExistentes = new HashSet<>(centroRepository.findAll().stream()
                .map(Centro::get_cod).collect(Collectors.toSet()));

        List<Estado> estadosNuevos = new ArrayList<>();
        List<Municipio> municipiosNuevos = new ArrayList<>();
        List<Parroquia> parroquiasNuevas = new ArrayList<>();
        List<Centro> centrosNuevos = new ArrayList<>();
        List<Mesa> mesasNuevas = new ArrayList<>();

        // Process records and collect entities
        for (Map<String, String> row : csvContent) {
            processRow(row, estadosExistentes, municipiosExistentes, parroquiasExistentes, 
                      centrosExistentes, estadosNuevos, municipiosNuevos, parroquiasNuevas, 
                      centrosNuevos, mesasNuevas);

            // Save in batches when reaching batch size
            if (mesasNuevas.size() >= BATCH_SIZE) {
                saveEntitiesBatch(estadosNuevos, municipiosNuevos, parroquiasNuevas, centrosNuevos, mesasNuevas);
                clearLists(estadosNuevos, municipiosNuevos, parroquiasNuevas, centrosNuevos, mesasNuevas);
            }
        }

        // Save remaining entities
        if (!mesasNuevas.isEmpty()) {
            saveEntitiesBatch(estadosNuevos, municipiosNuevos, parroquiasNuevas, centrosNuevos, mesasNuevas);
        }
    }

    private void processRow(Map<String, String> row, 
                          Set<Integer> estadosExistentes, Set<String> municipiosExistentes,
                          Set<Integer> parroquiasExistentes, Set<Integer> centrosExistentes,
                          List<Estado> estadosNuevos, List<Municipio> municipiosNuevos,
                          List<Parroquia> parroquiasNuevas, List<Centro> centrosNuevos,
                          List<Mesa> mesasNuevas) {
        int cod_edo = Integer.parseInt(row.get("COD_EDO"));
        String edo = row.get("EDO");
        int cod_mun = Integer.parseInt(row.get("COD_MUN"));
        String mun = row.get("MUN");
        int cod_par = Integer.parseInt(row.get("COD_PAR"));
        String par = row.get("PAR");
        int centroId = Integer.parseInt(row.get("CENTRO"));
        String munId = "M" + cod_mun + "E" + cod_edo;

        // Add new entities only if they don't exist
        if (estadosExistentes.add(cod_edo)) {
            estadosNuevos.add(new Estado(cod_edo, edo));
        }
        if (municipiosExistentes.add(munId)) {
            municipiosNuevos.add(new Municipio(munId, cod_mun, mun, cod_edo));
        }
        if (parroquiasExistentes.add(cod_par)) {
            parroquiasNuevas.add(new Parroquia(cod_par, munId, cod_edo, par));
        }
        if (centrosExistentes.add(centroId)) {
            centrosNuevos.add(new Centro(centroId, munId, cod_edo, cod_par));
        }

        mesasNuevas.add(createMesa(row, centroId, munId, cod_edo, cod_par));
    }

    private Mesa createMesa(Map<String, String> row, int centroId, String munId, int cod_edo, int cod_par) {
        return new Mesa(
            Integer.parseInt(row.get("MESA")),
            centroId,
            munId,
            cod_edo,
            cod_par,
            Integer.parseInt(row.get("VOTOS_VALIDOS")),
            Integer.parseInt(row.get("VOTOS_NULOS")),
            Integer.parseInt(row.get("EG")),
            Integer.parseInt(row.get("NM")),
            Integer.parseInt(row.get("LM")),
            Integer.parseInt(row.get("JABE")),
            Integer.parseInt(row.get("JOBR")),
            Integer.parseInt(row.get("AE")),
            Integer.parseInt(row.get("CF")),
            Integer.parseInt(row.get("DC")),
            Integer.parseInt(row.get("EM")),
            Integer.parseInt(row.get("BERA"))
        );
    }

    @Transactional
    private void saveEntitiesBatch(List<Estado> estados, List<Municipio> municipios,
                                 List<Parroquia> parroquias, List<Centro> centros,
                                 List<Mesa> mesas) {
        if (!estados.isEmpty()) estadoRepository.saveAll(estados);
        if (!municipios.isEmpty()) municipioRepository.saveAll(municipios);
        if (!parroquias.isEmpty()) parroquiaRepository.saveAll(parroquias);
        if (!centros.isEmpty()) centroRepository.saveAll(centros);
        if (!mesas.isEmpty()) mesaRepository.saveAll(mesas);
    }

    private void clearLists(List<Estado> estados, List<Municipio> municipios,
                          List<Parroquia> parroquias, List<Centro> centros,
                          List<Mesa> mesas) {
        estados.clear();
        municipios.clear();
        parroquias.clear();
        centros.clear();
        mesas.clear();
    }
}
