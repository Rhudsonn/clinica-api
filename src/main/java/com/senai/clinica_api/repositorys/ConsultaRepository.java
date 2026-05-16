package com.senai.clinica_api.repositorys;

import com.senai.clinica_api.entitys.ConsultaEntity;
import com.senai.clinica_api.entitys.StatusConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    boolean existsByDataConsulta(Date dataConsulta);

    boolean existsByPaciente_EmailAndStatusConsulta(String email, StatusConsulta statusConsulta);

    boolean existsByPaciente_IdAndDataConsultaAndIdNot(Long pacienteId, Date dataConsulta, Long id);
}
