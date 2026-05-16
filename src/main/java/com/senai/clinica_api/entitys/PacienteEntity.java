package com.senai.clinica_api.entitys;

import com.senai.clinica_api.dtos.PacienteDto;
import jakarta.persistence.*;

@Entity
@Table(name = "clinica")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String nome;

    private String email;

    public PacienteEntity() {
    }

    public PacienteEntity(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public PacienteEntity(PacienteDto pacienteDto) {
        this.nome = pacienteDto.getNome();
        this.email = pacienteDto.getEmail();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
