package com.votos.v2.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
public class GetApiService {

    private final EstadoRepository estadoRepository;
    private final MunicipioRepository municipioRepository;
    private final ParroquiaRepository parroquiaRepository;
    private final CentroRepository centroRepository;
    private final MesaRepository mesaRepository;

    public GetApiService(EstadoRepository estadoRepository, 
                         MunicipioRepository municipioRepository, 
                         ParroquiaRepository parroquiaRepository,
                         CentroRepository centroRepository, 
                         MesaRepository mesaRepository) {
        this.estadoRepository = estadoRepository;
        this.municipioRepository = municipioRepository;
        this.parroquiaRepository = parroquiaRepository;
        this.centroRepository = centroRepository;
        this.mesaRepository = mesaRepository;
    }

    public List<Estado> getAllEstados() {
        return estadoRepository.findAll();
    }
    public List<Municipio> getAllMunicipios() {
        return municipioRepository.findAll();
    }
    public List<Parroquia> getParroquias() {
        return parroquiaRepository.findAll();
    }
    public List<Centro> getAllCentros() {
        return centroRepository.findAll();
    }
    public List<Mesa> getAllMesas() {
        return mesaRepository.findAll();
    }


}