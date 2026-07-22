package com.dev.senior.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.senior.model.dto.PersonaDTO;
import com.dev.senior.service.PersonaService;

@RestController
@RequestMapping("/api/personas") //URL base
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService){
        this.personaService = personaService;
    }

    @PostMapping
    public PersonaDTO crear(@RequestBody PersonaDTO personaDTO){
        return personaService.crear(personaDTO);
    }

    @GetMapping
    public List<PersonaDTO> listar(){
        return personaService.listar();
    }

    @GetMapping("/cedula")
    public PersonaDTO obtener(@PathVariable Long cedula){
        return personaService.buscarPorId(cedula);
    }

    @DeleteMapping("/cedula")
    public void eliminar(@PathVariable Long cedula){
        personaService.eliminar(cedula);
    }
}
