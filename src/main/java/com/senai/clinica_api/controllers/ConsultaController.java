package com.senai.clinica_api.controllers;

import com.senai.clinica_api.dtos.AtualizarConsultaDto;
import com.senai.clinica_api.dtos.ConsultaDto;
import com.senai.clinica_api.dtos.ConsultaSaidaDto;
import com.senai.clinica_api.services.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("consulta")
public class ConsultaController {


    private ConsultaService consultaService;
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping("/consultas")
    public ResponseEntity<?> obterConsultas() {
        List<ConsultaSaidaDto> retorno  = consultaService.obterConsultas();

        if (retorno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("sem Consultas");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(retorno);
        }
    }


    @PostMapping
    public ResponseEntity<?> criarConsulta(@RequestBody @Valid ConsultaDto consultaDto) {

        try {
            consultaService.inserirConsulta(consultaDto);

            return ResponseEntity.status(HttpStatus.OK).body("Consulta inserida com sucesso");

        } catch (RuntimeException e) {

            if (e.getMessage().equals("nao encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente da consulta não encontrado");
            }

            if (e.getMessage().equals("ja tem consulta agendada")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Paciente já possui consulta agendada para a data e horário informados");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar Consulta");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarConsulta(@PathVariable Long id,@RequestBody @Valid AtualizarConsultaDto atualizarConsultaDto) {
        try {
            boolean retorno =consultaService.atualizarConsulta(atualizarConsultaDto,id);
            if (retorno){
                return ResponseEntity.status(HttpStatus.OK).body("Consulta atualizada com sucesso");
            }
        }catch (RuntimeException e) {
            if (e.getMessage().equals("Paciente nao encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente da consulta não encontrado");
            }
            if (e.getMessage().equals("ja tem consulta agendada")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Paciente já possui consulta agendada para a data informada");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirConsulta(@PathVariable Long id) {
        boolean retorno = consultaService.excluirConsulta(id);
        if (retorno){
            return ResponseEntity.status(HttpStatus.OK).body("Consulta excluída com sucesso");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada");
        }
    }
}
