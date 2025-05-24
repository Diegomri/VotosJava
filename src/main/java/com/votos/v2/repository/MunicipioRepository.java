package com.votos.v2.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.votos.v2.model.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, String> {
    
}
