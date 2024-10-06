package com.cloud.cloudapi.inscripcion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    // Obtener todas las inscripciones
    @GetMapping
    public List<Inscripcion> getAllInscripciones() {
        return inscripcionService.getAllInscripciones();
    }

    // Obtener inscripciones de un estudiante por su código
    @GetMapping("/estudiante/{codigo}")
    public List<Inscripcion> getInscripcionesByEstudiante(@PathVariable String codigo) {
        return inscripcionService.getInscripcionesByEstudiante(codigo);
    }

    // Crear una inscripción para un estudiante
    @PostMapping("/estudiante/{codigo}")
    public Inscripcion createInscripcion(@PathVariable String codigo, @RequestBody Inscripcion inscripcion) {
        return inscripcionService.createInscripcion(codigo, inscripcion);
    }

    // Eliminar una inscripción por su id
    @DeleteMapping("/{idInscripcion}")
    public void deleteInscripcion(@PathVariable Long idInscripcion) {
        inscripcionService.deleteInscripcion(idInscripcion);
    }
}