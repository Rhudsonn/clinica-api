package com.senai.clinica_api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senai.clinica_api.entitys.StatusConsulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class AtualizarConsultaDto {

    //Esta classe DTO fiz para caregar o LongId
    // vou utilizar esta classe para atualizar Consulta

    @NotBlank
    private String titulo;

    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dataConsulta;

    @NotNull
    private Long idPaciente;

    private StatusConsulta statusConsulta;


    public AtualizarConsultaDto() {
    }

    public AtualizarConsultaDto(String titulo, Date dataConsulta, Long id, StatusConsulta statusConsulta) {
        this.titulo = titulo;
        this.dataConsulta = dataConsulta;
        this.idPaciente = id;
        this.statusConsulta = statusConsulta;
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


    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public StatusConsulta getStatusConsulta() {
        return statusConsulta;
    }

    public void setStatusConsulta(StatusConsulta statusConsulta) {
        this.statusConsulta = statusConsulta;
    }
}
