package com.senai.clinica_api.services;

import com.senai.clinica_api.dtos.PacienteDto;
import com.senai.clinica_api.entitys.PacienteEntity;
import com.senai.clinica_api.repositorys.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private PacienteRepository pacienteRepository;
    public PacienteService(PacienteRepository pacienteRepository) {this.pacienteRepository = pacienteRepository;}


    //Listar Pacientes
    public List<PacienteDto> obterPacientes() {
        List<PacienteDto> lista = new ArrayList<>();
        for (PacienteEntity pacienteEntity : pacienteRepository.findAll()) {
            PacienteDto pacienteDto = new PacienteDto(pacienteEntity);
            lista.add(pacienteDto);
        }
        return lista;
    }


    // Cadastrando paciente
    public boolean inserirPaciente(PacienteDto pacienteDto) {

        if (!pacienteRepository.existsByEmail(pacienteDto.getEmail())) {

            PacienteEntity pacienteEntity = new PacienteEntity(pacienteDto);
            pacienteRepository.save(pacienteEntity);
            return true;
        }
        return false;
    }


    //Atualizar Paciente
    public boolean atualizarPaciente(PacienteDto pacienteDto, String email) {

        Optional<PacienteEntity> paciente = pacienteRepository.findByEmail(email);

        if (paciente.isEmpty()){
            return false;
        }

        PacienteEntity capituraPaciente = paciente.get();

        boolean existe = pacienteRepository.existsByEmail(pacienteDto.getEmail());

        if (existe){ // validei se o email passado no jason ja é existente no banco se for gera erro.
            throw new RuntimeException("email ja existe");
        }

        capituraPaciente.setNome(pacienteDto.getNome());
        capituraPaciente.setEmail(pacienteDto.getEmail());
        pacienteRepository.save(capituraPaciente);
        return true;

    }


    //Obter o paciente a partir de um e-mail.
    public List<PacienteDto> obterPaciente(String email) {
        List<PacienteDto> lista = new ArrayList<>();

        Optional<PacienteEntity> pacienteEntity = pacienteRepository.findByEmail(email);

        if (pacienteEntity.isPresent()) {

            PacienteEntity paciente = pacienteEntity.get();
            lista.add(new PacienteDto(paciente));
        }
        return lista;
    }



}
