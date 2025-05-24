package com.votos.v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votos.v2.model.Parroquia;
public interface ParroquiaRepository extends JpaRepository<Parroquia, Integer> {
    

}
