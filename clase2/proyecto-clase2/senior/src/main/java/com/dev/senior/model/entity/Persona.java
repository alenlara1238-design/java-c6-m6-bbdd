package com.dev.senior.model.entity;

import jakarta.persistence.*;

@Entity // Indica que la clase es una entidad JPA (tabla en BD)
@Table(name="personas") //nombre exacto de la tabla en PostgreSQL
public class Persona {
    
    @Id //marca la llave primaria
    @Column(name="cedula") //mapea con la columna en la BD
    private Long cedula;

    @Column(name="nombre", lenght = 150, nullable = false)
    private String nombre;

    @Column(name="telefono", length = 20)
    private String telefono;

    @Column(name="email", lenght= 150)
    private String email;

    //constructor vacío obligatorio para JPA
    public Persona() {}

    public Persona(Long cedula, String nombre, String telefono, String email){
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    


}
