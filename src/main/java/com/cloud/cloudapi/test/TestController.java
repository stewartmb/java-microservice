package com.cloud.cloudapi.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String getTestDocumentation() {
        return "<html>" +
                "<head><title>Documentación de Microservicio</title></head>" +
                "<body>" +
                "<h1>Documentación de Microservicio en Java</h1>" +
                "<h2>Entidad: Estudiante</h2>" +
                "<p>La entidad <strong>Estudiante</strong> representa a los estudiantes del sistema de gestión de inscripciones de UTEC, identificados por un código único. Está relacionada con la entidad <strong>Inscripcion</strong> mediante el código del estudiante.</p>" +
                "<h2>Entidad: Inscripcion</h2>" +
                "<p>La entidad <strong>Inscripcion</strong> representa la inscripción de un estudiante en un curso, vinculada a través del código del estudiante. Contiene información como el curso y profesor asignados.</p>" +
                "<p>Al eliminar un estudiante, las inscripciones asociadas a este también se eliminan para mantener la integridad referencial.</p>" +
                "</body>" +
                "</html>";
    }
}