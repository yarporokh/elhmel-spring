package com.example.elhmel.repository;

import com.example.elhmel.model.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerTypeRepository extends JpaRepository<BeerType, Long> {
}
