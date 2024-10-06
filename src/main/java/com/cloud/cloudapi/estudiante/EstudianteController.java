package com.cloud.cloudapi.estudiante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // Obtener todos los estudiantes
    @GetMapping
    public List<Estudiante> getAllEstudiantes() {
        return estudianteService.getAllEstudiantes();
    }

    // Obtener un estudiante por código
    @GetMapping("/{codigo}")
    public Estudiante getEstudianteByCodigo(@PathVariable String codigo) {
        return estudianteService.getEstudianteByCodigo(codigo);
    }

    // Crear un nuevo estudiante
    @PostMapping
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.createEstudiante(estudiante);
    }

    // Actualizar un estudiante existente
    @PutMapping("/{codigo}")
    public Estudiante updateEstudiante(@PathVariable String codigo, @RequestBody Estudiante estudiante) {
        return estudianteService.updateEstudiante(codigo, estudiante);
    }

    // Eliminar un estudiante
    @DeleteMapping("/{codigo}")
    public String deleteEstudiante(@PathVariable String codigo) {
        boolean eliminado = estudianteService.deleteEstudiante(codigo);

        if (eliminado) {
            return "Estudiante eliminado";
        } else {
            return "Estudiante con código " + codigo + " no encontrado";
        }
    }
}