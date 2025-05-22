package com.votos.v2.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.votos.v2.model.Centro;

public interface CentroRepository extends PagingAndSortingRepository<Centro, Long> {
    // Additional query methods can be defined here if needed

}
