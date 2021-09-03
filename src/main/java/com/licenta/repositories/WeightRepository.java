package com.licenta.repositories;

import com.licenta.models.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {


}
