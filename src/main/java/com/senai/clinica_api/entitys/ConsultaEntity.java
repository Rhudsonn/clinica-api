package com.senai.clinica_api.entitys;

import com.senai.clinica_api.dtos.ConsultaDto;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class ConsultaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private Date dataConsulta; //Date- Pode armazenar (Data e Hora juntos)

    @Enumerated(EnumType.STRING) // salva como texto.
    private StatusConsulta statusConsulta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PacienteEntity paciente;

    public ConsultaEntity() {
    }

    public ConsultaEntity(Long id, String titulo, Date dataConsulta, StatusConsulta statusConsulta, PacienteEntity paciente) {
        this.id = id;
        this.titulo = titulo;
        this.dataConsulta = dataConsulta;
        this.statusConsulta = statusConsulta;
        this.paciente = paciente;
    }

    public ConsultaEntity(ConsultaDto consultaDto) {
        this.titulo = consultaDto.getTitulo();
        this.dataConsulta = consultaDto.getDataConsulta();
        this.statusConsulta = consultaDto.getStatusConsulta();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }
}
