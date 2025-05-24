package com.votos.v2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "mesa")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mesa_seq_gen")
    @SequenceGenerator(name = "mesa_seq_gen", sequenceName = "mesa_seq", initialValue = 1, allocationSize = 1000)
    private long id;

    private int name;
    private int centro;
    private String mun_id;
    private int cod_edo;
    private int cod_par;
    
    private int votos_validos;
    private int votos_nulos;
    private int EG;
    private int NM;
    private int LM;
    private int JABE;
    private int JOBR;
    private int AE;
    private int CF;
    private int DC;
    private int EM;
    private int BERA;
    
    public Mesa() {
    }
    public Mesa(int name, int centro, String mun_id, int cod_edo, int cod_par,
            int votos_validos, int votos_nulos, int EG, int NM, int LM, int JABE,
            int JOBR, int AE, int CF, int DC, int EM, int BERA) {
        this.name = name;
        this.centro = centro;
        this.mun_id = mun_id;
        this.cod_edo = cod_edo;
        this.cod_par = cod_par;
        this.votos_validos = votos_validos;
        this.votos_nulos = votos_nulos;
        this.EG = EG;
        this.NM = NM;
        this.LM = LM;
        this.JABE = JABE;
        this.JOBR = JOBR;
        this.AE = AE;
        this.CF = CF;
        this.DC = DC;
        this.EM = EM;
        this.BERA = BERA;
    }

    public long get_cod() {
        return id;
    }

}
