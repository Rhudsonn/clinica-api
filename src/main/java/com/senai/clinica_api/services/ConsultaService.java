package com.senai.clinica_api.services;

import com.senai.clinica_api.dtos.AtualizarConsultaDto;
import com.senai.clinica_api.dtos.ConsultaDto;
import com.senai.clinica_api.dtos.ConsultaSaidaDto;
import com.senai.clinica_api.entitys.ConsultaEntity;
import com.senai.clinica_api.entitys.PacienteEntity;
import com.senai.clinica_api.entitys.StatusConsulta;
import com.senai.clinica_api.repositorys.ConsultaRepository;
import com.senai.clinica_api.repositorys.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private ConsultaRepository consultaRepository;
    private PacienteRepository pacienteRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    //Listar todas consultas
    public List<ConsultaSaidaDto> obterConsultas() {
        List<ConsultaSaidaDto> consultas = new ArrayList<>();
        for (ConsultaEntity consultaEntity : consultaRepository.findAll()) {
            ConsultaSaidaDto consultaSaidaDto = new ConsultaSaidaDto(consultaEntity);
            consultas.add(consultaSaidaDto);
        }
        return consultas;
    }


    // Cadastrando Consulta
    public boolean inserirConsulta(ConsultaDto consultaDto) {

        //sempre que for criar consulta ele inicia com status EM_ANDAMENTO
        // para que eu nao precise ficar informando sempre no Json o status, ja que esta sendo criado a consulta.
        if (consultaDto.getStatusConsulta() == null){
            consultaDto.setStatusConsulta(StatusConsulta.EM_ANDAMENTO);
        }

        PacienteEntity paciente = pacienteRepository.findByEmail(consultaDto.getEmail())
                .orElseThrow(() -> new RuntimeException("nao encontrado"));

        // validando se tem paciente com agendamento para esta data e horario, assim evito comflito de horarios.
        boolean existeDataConsulta = consultaRepository.existsByDataConsulta(consultaDto.getDataConsulta());
        if (existeDataConsulta){
            throw new RuntimeException("ja tem consulta agendada");
        }

        ConsultaEntity consultaEntity = new ConsultaEntity(consultaDto);

        consultaEntity.setPaciente(paciente); // aqui estou associando o paciente a consulta porque no banco tava dando (id_paciente = nullo)

        consultaRepository.save(consultaEntity);
        return true;
    }


    //Atualizar consulta
    public boolean atualizarConsulta(@RequestBody @Valid AtualizarConsultaDto atualizarConsultaDto, Long id) {

        Optional<ConsultaEntity> existeConsultaId = consultaRepository.findById(id);

        if (existeConsultaId.isEmpty()){
            return false;
        }

        // já existe outra consulta, verifica
        PacienteEntity paciente = pacienteRepository.findById(atualizarConsultaDto.getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente nao encontrado"));

        boolean existeConsulta = consultaRepository.existsByPaciente_IdAndDataConsultaAndIdNot(
                atualizarConsultaDto.getIdPaciente(),
                atualizarConsultaDto.getDataConsulta(), id);
        if (existeConsulta){
            throw new RuntimeException("ja tem consulta agendada");
        }

        ConsultaEntity consulta = existeConsultaId.get();

        consulta.setTitulo(atualizarConsultaDto.getTitulo());
        consulta.setDataConsulta(atualizarConsultaDto.getDataConsulta());
        consulta.setStatusConsulta(atualizarConsultaDto.getStatusConsulta());
        consulta.setPaciente(paciente);
        consultaRepository.save(consulta);
        return true;
    }

    //Excluir consulta
    public boolean excluirConsulta(Long id) {
        if (consultaRepository.existsById(id)){
            consultaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
