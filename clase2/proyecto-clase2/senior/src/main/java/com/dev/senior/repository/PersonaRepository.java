package com.dev.senior.repository;

import java.util.List;

import com.dev.senior.model.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//aqui ya trae un CRUD completo sin escribir nada ni SQL
public interface PersonaRepository extends JpaRepository<Persona, Long>{

        // Consultas derivadas: métodos traen todos los campos del resultado
    
    //buscar por nombre exacto
    List<Persona> findByNombre(String nombre);

    //buscar por nombre que contenga "texto"
    List<Persona> findByNombreContaining(String nombre);

    // buscar por email
    Persona findByEmail(String email);

    // buscar por telefono que empiece con 
    List<Persona> findByNombreStartingWith(String telefono);

    // Bienvenidos a JPQL
    // JPQL usa "Persona" (clase), no "personas" (tabla)
    @Query("SELECT p FROM Persona p WHERE p.nombre = :nombre")
    List<Persona> buscarPorNombreJPQL(@Param("nombre") String nombre);

    @Query("SELECT p FROM Persona p WHERE p.nombre LIKE %:nombre%")
    List<Persona> buscarPorNombreParcial(@Param("nombre") String nombre);

    // Ejemplo mas interesante
    @Query("SELECT p FROM Persona p WHERE LENGTH(p.telefono) > :longitud")
    List<Persona> buscarTelefonosLargos(@Param("longitud") int longitud);

}
