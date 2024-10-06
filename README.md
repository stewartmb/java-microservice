## Documentación de Microservicio en Java

### Entidad: `Estudiante`

La entidad `Estudiante` representa a los estudiantes que forman parte de un sistema de gestión de inscripciones a cursos de UTEC. Cada estudiante tiene información personal, académica, y está identificado por un código único.

#### Atributos

| Atributo          | Tipo         | Descripción                                                    |
|-------------------|--------------|----------------------------------------------------------------|
| `codigo`          | `String`     | Clave primaria del estudiante. Código único que lo identifica. |
| `nombre`          | `String`     | Nombre del estudiante.                                         |
| `apellido`        | `String`     | Apellido del estudiante.                                       |
| `dni`             | `String`     | Documento Nacional de Identidad (DNI) del estudiante.          |
| `telefono`        | `String`     | Número de teléfono del estudiante.                             |
| `sexo`            | `String`     | Género del estudiante (ej. Masculino, Femenino).               |
| `edad`            | `int`        | Edad del estudiante.                                           |
| `carrera`         | `String`     | Carrera universitaria del estudiante.                          |
| `fechaNacimiento` | `LocalDate`  | Fecha de nacimiento del estudiante.                            |

#### Relación

La entidad `Estudiante` está relacionada con la entidad `Inscripcion` a través de la llave foránea `codigoEstudiante` que asigna un único estudiante a cada inscripción.

#### Ejemplo JSON

```json
{
    "codigo": "202310123",
    "nombre": "Carlos",
    "apellido": "Ramirez",
    "dni": "12345678",
    "telefono": "987654321",
    "sexo": "Masculino",
    "edad": 20,
    "carrera": "Ciencias de la Computación",
    "fechaNacimiento": "2004-01-15"
}
```

---

### Entidad: `Inscripcion`

La entidad `Inscripcion` representa la inscripción de un estudiante en un curso. Cada inscripción está vinculada a un estudiante y contiene información sobre el curso y el profesor involucrado.

#### Atributos

| Atributo           | Tipo         | Descripción                                                                   |
|--------------------|--------------|-------------------------------------------------------------------------------|
| `idInscripcion`    | `Long`       | Clave primaria autogenerada de la inscripción.                                |
| `fecha`            | `LocalDate`  | Fecha en la que se realizó la inscripción.                                    |
| `idCurso`          | `Long`       | Identificador único del curso en el que se inscribe el estudiante.            |
| `idProfesor`       | `Long`       | Identificador único del profesor que imparte el curso.                        |
| `codigoEstudiante` | `String`     | Código del estudiante que se inscribe (relación con la entidad `Estudiante`). |

#### Relación

La entidad `Inscripcion` tiene una relación `ManyToOne` con la entidad `Estudiante`, pero en este caso solo se almacena la llave foránea `codigoEstudiante`, que corresponde al código del estudiante.

#### Ejemplo JSON

```json
{
    "idInscripcion": 1,
    "fecha": "2024-10-02",
    "idCurso": 101,
    "idProfesor": 5001,
    "codigoEstudiante": "202310123"
}
```

#### Consideraciones

- Al eliminar un estudiante, todas las inscripciones asociadas a dicho estudiante se eliminan para evitar errores de integridad referencial.


- Se debe ejecutar el siguiente comando en la maquina del `db.host` para crear la base de datos
```shell
docker run -d --rm --name postgres_java_microservice -e POSTGRES_DB=javadb -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=utec -p 5432:5432 -d postgres
```
