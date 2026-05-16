package com.senai.clinica_api.dtos;

import com.senai.clinica_api.entitys.ConsultaEntity;
import com.senai.clinica_api.entitys.StatusConsulta;

import java.sql.Date;

public class ConsultaSaidaDto {

    private String titulo;
    private Date dataConsulta;
    private StatusConsulta statusConsulta;

    private String nomePaciente;
    private String emailPaciente;

    public ConsultaSaidaDto() {
    }

    public ConsultaSaidaDto(ConsultaEntity consultaEntity) {
        this.titulo = consultaEntity.getTitulo();
        this.dataConsulta = consultaEntity.getDataConsulta();
        this.statusConsulta = consultaEntity.getStatusConsulta();

        // dados do paciente
        this.nomePaciente = consultaEntity.getPaciente().getNome();
        this.emailPaciente = consultaEntity.getPaciente().getEmail();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public StatusConsulta getStatusConsulta() {
        return statusConsulta;
    }

    public void setStatusConsulta(StatusConsulta statusConsulta) {
        this.statusConsulta = statusConsulta;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }
}
