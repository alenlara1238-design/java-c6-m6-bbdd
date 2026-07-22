package com.dev.senior.service;

import java.util.List;

import com.dev.senior.model.dto.PersonaDTO;

public interface PersonaService {

   PersonaDTO crear(PersonaDTO personaDTO);
   
   List<PersonaDTO> listar();

   PersonaDTO buscarPorId(Long cedula);

   void eliminar(Long cedula);

   
}
