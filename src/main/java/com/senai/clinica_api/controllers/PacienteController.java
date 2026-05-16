package com.senai.clinica_api.controllers;

import com.senai.clinica_api.dtos.PacienteDto;
import com.senai.clinica_api.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("clinica")
public class PacienteController {


    //OBSERVAÇÃO- Notei que em todos end-points tem o mesmo caminho ("/paciente")
    // e que só alguns são adicionados algumas entradas expecificas
    // entao decidi usar a anotação em nivel de classe ("/paciente")
    // e só adiciona o caminho expecifico quando precisar.

    public PacienteService pacienteService;
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @GetMapping
    public ResponseEntity<?> lista() {

        List<PacienteDto> RetornoLista = pacienteService.obterPacientes();

        if (RetornoLista.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vazia de pacientes");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(RetornoLista);
        }
    }

    @PostMapping
    public ResponseEntity<String> inserirPaciente(@RequestBody @Valid PacienteDto pacienteDto) {
        boolean cadastrado = pacienteService.inserirPaciente(pacienteDto);
        if (cadastrado) {
            return ResponseEntity.status(HttpStatus.OK).body("Paciente inserido com sucesso");
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe paciente");
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> atualizarPaciente(@RequestBody PacienteDto pacienteDto, @PathVariable String email){

        try {

            boolean retorno =   pacienteService.atualizarPaciente(pacienteDto, email);
            if (retorno){
                return ResponseEntity.status(HttpStatus.OK).body("Paciente atualizado com sucesso");
            }

        }catch (RuntimeException e){
            if (e.getMessage().equals("email ja existe")){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe paciente");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
    }

    @GetMapping("/{Email}")
    public ResponseEntity<?> pesquisarPorEmail(@PathVariable @Valid String Email) {
        List<PacienteDto> retorno = pacienteService.obterPaciente(Email);
        if (retorno.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não existe");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(retorno);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> excluiurPaciente(@PathVariable String email) {
        try {

            boolean retorno = pacienteService.excluirPaciente(email);
            if (retorno){
                return ResponseEntity.status(HttpStatus.OK).body("Paciente excluido com sucesso");
            }

        }catch (RuntimeException e){
            if (e.getMessage().equals("consulta em aberta")){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Paciente vinculado em consultas");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não existe");
    }

}
