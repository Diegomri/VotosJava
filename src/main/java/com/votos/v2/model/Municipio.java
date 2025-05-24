package com.votos.v2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "municipio")
public class Municipio {
    @Id
    private String id;

    private int cod_mun;
    private String name;
    private int cod_edo;

    public String getName() {
        return name;
    }
    public Municipio() {
    }
    public Municipio(String id, int cod_mun, String name, int cod_edo) {
        this.cod_edo = cod_edo;
        this.id = id;
        this.cod_mun = cod_mun;
        this.name = name;
    }

    public int get_cod() {
        return cod_mun;
    }

    public String getId() {
        return id;
    }
    
}
