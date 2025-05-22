package com.votos.v2.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.votos.v2.model.Municipio;

public interface MunicipioRepository extends PagingAndSortingRepository<Municipio, Long> {
    

}
