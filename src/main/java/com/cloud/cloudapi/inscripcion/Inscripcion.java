package com.cloud.cloudapi.inscripcion;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "inscripciones")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscripcion;

    private LocalDate fecha;

    private Long idCurso;

    private Long idProfesor;

    @Column(name = "codigo_estudiante", nullable = false)
    private String codigoEstudiante; // Solo se almacena la llave primaria del estudiante
}