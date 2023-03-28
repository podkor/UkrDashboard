package com.podkor.ukrdashboard.repository;

import com.podkor.ukrdashboard.dto.CountryCoefficients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CountryCoefficientsRepository extends JpaRepository<CountryCoefficients, Long> {

    Set<CountryCoefficients> findAllBySeason(String season);
}
