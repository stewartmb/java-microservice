package com.cloud.cloudapi.estudiante;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    private String codigo; // Primary key

    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String sexo;
    private int edad;
    private String carrera;
    private LocalDate fechaNacimiento;
}