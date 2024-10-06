package com.cloud.cloudapi.inscripcion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByCodigoEstudiante(String codigoEstudiante);
    void deleteByCodigoEstudiante(String codigoEstudiante);
}