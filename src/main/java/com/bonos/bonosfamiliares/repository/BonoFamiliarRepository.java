package com.bonos.bonosfamiliares.repository;

import com.bonos.bonosfamiliares.model.BonoFamiliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonoFamiliarRepository extends JpaRepository<BonoFamiliar, Long> {
}
