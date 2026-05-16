package com.senai.clinica_api.dtos;

import com.senai.clinica_api.entitys.PacienteEntity;

public class PacienteDto {

    private String nome;
    private String email;

    public PacienteDto() {
    }

    public PacienteDto(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public PacienteDto(PacienteEntity paciente) {
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
