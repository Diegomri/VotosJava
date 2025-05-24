package com.votos.v2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parroquia")
public class Parroquia {
    @Id
    private int cod_par;

    private String mun_id;
    private int cod_edo;
    private String name;

    public String getName() {
        return name;
    }
    public Parroquia() {
    }
    public Parroquia(int cod_par, String mun_id, int cod_edo, String name) {
        this.cod_edo = cod_edo;
        this.name = name;
        this.mun_id = mun_id;
        this.cod_par = cod_par;
    }

    public int get_cod() {
        return cod_par;
    }

}
