package com.senai.clinica_api.repositorys;

import com.senai.clinica_api.entitys.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    boolean existsByEmail(String email);

    Optional<PacienteEntity> findByEmail(String email);
}
