package com.votos.v2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado {
    @Id
    private int cod_edo;
    
    private String name;

    public String getName() {
        return name;
    }
    public Estado() {
    }
    public Estado(int cod_edo, String name) {
        this.cod_edo = cod_edo;
        this.name = name;
    }

    public int get_cod() {
        return cod_edo;
    }
    
}
