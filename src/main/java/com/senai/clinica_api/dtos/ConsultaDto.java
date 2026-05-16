package com.senai.clinica_api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senai.clinica_api.entitys.ConsultaEntity;
import com.senai.clinica_api.entitys.StatusConsulta;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class ConsultaDto {

    @NotBlank
    private String titulo;

    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dataConsulta;

    @NotBlank
    @Email
    private String email;

    private StatusConsulta statusConsulta;

    public ConsultaDto() {
    }

    public ConsultaDto(String titulo, Date dataConsulta, String email, StatusConsulta statusConsulta) {
        this.titulo = titulo;
        this.dataConsulta = dataConsulta;
        this.email = email;
        this.statusConsulta = statusConsulta;
    }

    public ConsultaDto(ConsultaEntity consultaEntity) {
        this.titulo = consultaEntity.getTitulo();
        this.dataConsulta = consultaEntity.getDataConsulta();
        this.statusConsulta = consultaEntity.getStatusConsulta();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusConsulta getStatusConsulta() {
        return statusConsulta;
    }

    public void setStatusConsulta(StatusConsulta statusConsulta) {
        this.statusConsulta = statusConsulta;
    }
}
