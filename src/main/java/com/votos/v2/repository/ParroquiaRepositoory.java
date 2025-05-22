package com.votos.v2.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.votos.v2.model.Parroquia;
public interface ParroquiaRepositoory extends PagingAndSortingRepository<Parroquia, Long> {
    // Additional query methods can be defined here if needed

}
