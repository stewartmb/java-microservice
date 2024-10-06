package com.cloud.cloudapi.inscripcion;

import com.cloud.cloudapi.estudiante.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    // RestTemplate para realizar llamadas HTTP al microservicio Python
    private final RestTemplate restTemplate = new RestTemplate();

    // URL del microservicio Python para la verificación del curso
    @Value("${python.api.url}")
    private String PYTHON_API_URL;

    public List<Inscripcion> getAllInscripciones() {
        return inscripcionRepository.findAll();
    }

    public List<Inscripcion> getInscripcionesByEstudiante(String codigo) {
        return inscripcionRepository.findByCodigoEstudiante(codigo);
    }

    public Inscripcion createInscripcion(String codigo, Inscripcion inscripcion) {
        System.out.println("ejecutando inscripcion");
        // Verificar si el estudiante existe en la base de datos
        if (!estudianteRepository.existsById(codigo)) {
            System.out.println("no hay estudiante");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado.");
        }

        // Verificar si el curso existe en el sistema externo (microservicio Python)
        System.out.println(inscripcion.getIdCurso());
        boolean cursoExiste = verificarCurso(inscripcion.getIdCurso());

        if (!cursoExiste) {
            // Si el curso no existe, lanzamos una excepción
            System.out.println("no hay curso");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El curso con id " + inscripcion.getIdCurso() + " no existe en la API de Python.");
        }

        // Si tanto el estudiante como el curso existen, procedemos a guardar la inscripción
        inscripcion.setCodigoEstudiante(codigo);
        System.out.println("se logró");
        return inscripcionRepository.save(inscripcion);
    }

    public void deleteInscripcion(Long idInscripcion) {
        inscripcionRepository.deleteById(idInscripcion);
    }

    private boolean verificarCurso(Long idCurso) {
        System.out.println(idCurso);
        try {
            // Construir la URL del microservicio (versión 2 del endpoint)
            String url = PYTHON_API_URL + idCurso + "/version2";
            System.out.println(url);
            CursoResponse response = restTemplate.getForObject(url, CursoResponse.class);
            System.out.println(response);

            if (response != null) {
                System.out.println(response.getIdCurso());
                if (response.getIdCurso() != null) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error al verificar el curso");
            e.printStackTrace();
            return false;
        }
    }


    // Clase interna para manejar la respuesta del microservicio de Python
    public static class CursoResponse {
        private Long idCurso;
        private Long idProfesor;
        private String message;

        public Long getIdCurso() {
            return idCurso;
        }

        public void setIdCurso(Long idCurso) {
            this.idCurso = idCurso;
        }

        public Long getIdProfesor() {
            return idProfesor;
        }

        public void setIdProfesor(Long idProfesor) {
            this.idProfesor = idProfesor;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
