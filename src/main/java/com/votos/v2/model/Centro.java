package com.votos.v2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "centro")
public class Centro {
    @Id
    private int id;

    private String mun_id;
    private int cod_edo;
    private int cod_par;

    public Centro() {
    }
    public Centro(int id, String mun_id, int cod_edo, int cod_par) {
        this.id = id;
        this.mun_id = mun_id;
        this.cod_edo = cod_edo;
        this.cod_par = cod_par;

    }

    public int get_cod() {
        return id;
    }

}
