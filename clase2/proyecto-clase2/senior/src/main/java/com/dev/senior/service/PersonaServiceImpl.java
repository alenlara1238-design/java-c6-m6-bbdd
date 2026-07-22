package com.dev.senior.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.senior.model.dto.PersonaDTO;
import com.dev.senior.model.entity.Persona;
import com.dev.senior.repository.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaDTO crear(PersonaDTO dto) {
        Persona persona = mapToEntity(dto);
        Persona guardada = personaRepository.save(persona);
        return mapToDTO(guardada);
    }

    @Override
    public List<PersonaDTO> listar() {
        return personaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public PersonaDTO buscarPorId(Long cedula) {
      Persona persona = personaRepository.findById(cedula)
            .orElseThrow(()-> new RuntimeException("Persona no encontrada"));
        return mapToDTO(persona);
    }

    @Override
    public void eliminar(Long cedula) {
        personaRepository.deleteById(cedula);
    }


    private PersonaDTO mapToDTO(Persona persona){
        return new PersonaDTO (
            persona.getCedula(),
            persona.getNombre(),
            persona.getTelefono(),
            persona.getEmail()
        );
    }

    private Persona mapToEntity(PersonaDTO dto){
        return new Persona(
            dto.getCedula(),
            dto.getNombre(),
            dto.getTelefono(),
            dto.getEmail()
        );
    }

    public List<PersonaDTO> buscarPorNombre(String nombre){
        return personaRepository.findByNombre(nombre)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    

}
