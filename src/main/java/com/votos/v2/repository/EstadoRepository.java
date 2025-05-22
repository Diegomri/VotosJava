package com.votos.v2.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.votos.v2.model.Estado;

public interface EstadoRepository extends PagingAndSortingRepository<Estado, Long> {
}
