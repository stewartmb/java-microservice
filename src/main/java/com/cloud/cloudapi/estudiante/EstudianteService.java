package com.cloud.cloudapi.estudiante;


import com.cloud.cloudapi.inscripcion.InscripcionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Estudiante getEstudianteByCodigo(String codigo) {
        return estudianteRepository.findById(codigo).orElse(null);
    }

    public Estudiante createEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Estudiante updateEstudiante(String codigo, Estudiante estudiante) {
        Estudiante existingEstudiante = estudianteRepository.findById(codigo).orElse(null);
        if (existingEstudiante != null) {
            existingEstudiante.setNombre(estudiante.getNombre());
            existingEstudiante.setApellido(estudiante.getApellido());
            existingEstudiante.setDni(estudiante.getDni());
            existingEstudiante.setTelefono(estudiante.getTelefono());
            existingEstudiante.setSexo(estudiante.getSexo());
            existingEstudiante.setEdad(estudiante.getEdad());
            existingEstudiante.setCarrera(estudiante.getCarrera());
            existingEstudiante.setFechaNacimiento(estudiante.getFechaNacimiento());
            return estudianteRepository.save(existingEstudiante);
        }
        return null;
    }

    // eliminar un estudiante junto con sus inscripciones
    @Transactional
    public boolean deleteEstudiante(String codigo) {
        if (estudianteRepository.existsById(codigo)) {
            inscripcionRepository.deleteByCodigoEstudiante(codigo);
            estudianteRepository.deleteById(codigo);
            return true;
        } else {
            return false;
        }
    }
}