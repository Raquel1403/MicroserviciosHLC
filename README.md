# MicroserviciosHLC

## Microservicio de Citas
Este microservicio gestiona las citas de pacientes con psicólogos. Permite realizar operaciones CRUD sobre las citas almacenadas en MongoDB.

## 💽 Endpoints de la API
La API corre en `http://localhost:8086/api/v1/citas`

### ➕ Crear nuevas citas
**Método:** `POST`

**URL:** `http://localhost:8086/api/v1/citas`

**Headers:** `Content-Type: application/json`

#### 📌 Ejemplos de cuerpo (JSON):
```json
{
    "id": 1,
    "pacienteID": "789",
    "psicologoID": "654",
    "fecha": "2025-03-01",
    "hora": "14:30",
    "estado": "pendiente",
    "mensaje": "Sesión de seguimiento",
    "especialidad": "Terapia Cognitiva",
    "notificacionesEnviadas": []
}
```

```json
{
    "id": 2,
    "pacienteID": "111",
    "psicologoID": "222",
    "fecha": "2025-04-10",
    "hora": "09:00",
    "estado": "pendiente",
    "mensaje": "Primera consulta psicológica",
    "especialidad": "Psicoterapia Infantil",
    "notificacionesEnviadas": []
}
```

```json
{
    "id": "3",
    "pacienteID": "555",
    "psicologoID": "333",
    "fecha": "2025-05-20",
    "hora": "15:45",
    "estado": "pendiente",
    "mensaje": "Consulta de terapia de pareja",
    "especialidad": "Terapia de Pareja",
    "notificacionesEnviadas": []
}
```

---

### 🔍 Obtener todas las citas
**Método:** `GET`

**URL:** `http://localhost:8086/api/v1/citas`

---

### 🔍 Obtener una cita por ID
**Método:** `GET`

**URL:** `http://localhost:8086/api/v1/citas/{id}`

📌 **Ejemplo:** `http://localhost:8086/api/v1/citas/1`

---

### ✏️ Actualizar una cita
**Método:** `PUT`

**URL:** `http://localhost:8086/api/v1/citas/{id}`

📌 **Ejemplo:** `http://localhost:8086/api/v1/citas/1`

**Headers:** `Content-Type: application/json`

#### 📌 Ejemplo de cuerpo (JSON):
```json
{
    "id": "1",
    "pacienteID": "789",
    "psicologoID": "654",
    "fecha": "2025-03-05",
    "hora": "16:00",
    "estado": "confirmada",
    "mensaje": "Cambio de horario",
    "especialidad": "Terapia Cognitiva",
    "notificacionesEnviadas": ["Email enviado"]
}
```

---

### 🗑️ Eliminar una cita
**Método:** `DELETE`
**URL:** `http://localhost:8086/api/v1/citas/{id}`

📌 **Ejemplo:** `http://localhost:8086/api/v1/citas/2`

---

### Swagger
`http://localhost:8086/swagger-ui/index.html`

---

### Test
Añadido tests en CitaControllerTest. Se usa JUnit 5, Mockito y Spring MockMvc para simular peticiones HTTP y validar respuestas sin necesidad de un servidor real.

📌 **¿Qué hacen estas pruebas?**
Cada prueba verifica un endpoint específico del controlador CitaController:

**`testObtenerTodasLasCitas()`**

Simula una petición `GET /api/v1/citas`.
Mockea citaService.findAll() para devolver una lista vacía.
Verifica que el estado HTTP es 200 OK y la respuesta es [] (JSON vacío).

**`testObtenerCitaPorId()`**

Simula `GET /api/v1/citas/1`.
Mockea citaService.findById("1") para devolver una cita específica.
Comprueba que el estado es 200 OK y que la cita tiene el ID correcto.

**`testCrearCita()`**

Simula `POST /api/v1/citas` enviando un JSON con los datos de la cita.
Mockea citaService.save() para devolver la cita creada.
Verifica que la respuesta es 200 OK y el JSON de respuesta tiene el ID esperado.

**`testEliminarCita()`**

Simula `DELETE /api/v1/citas/1`.
Mockea citaService.deleteById("1") para devolver ResponseEntity.ok().
Comprueba que la respuesta es 200 OK.


## Microservicio de Usuarios

Este microservicio gestiona la información de los usuarios dentro del sistema. Permite realizar operaciones **CRUD** sobre los usuarios almacenados en **MongoDB**.

---

## 💽 **Endpoints de la API**
📍 **Base URL:** `http://localhost:8080/api/v1/usuarios`

### ➕ **Crear un nuevo usuario**
- **Método:** `POST`
- **URL:** `http://localhost:8080/api/v1/usuarios`
- **Headers:** `Content-Type: application/json`

📌 **Ejemplo de solicitud (JSON):**
```json
{
    "id": "789",
    "correo": "juan.paciente@mail.com",
    "contraseña": "password123",
    "rol": "paciente",
    "nombre": "Juan",
    "apellidos": "Pérez",
    "telefono": "612345678",
    "fechaNacimiento": "1990-05-10",
    "administrador": false,
    "especialidad": null,
    "historialCitas": []
}
```


---

### 🔍 **Obtener todos los usuarios**
- **Método:** `GET`
- **URL:** `http://localhost:8080/api/v1/usuarios`

📌 **Ejemplo de respuesta (JSON):**
```json
[
    {
        "id": "1",
        "nombre": "Juan Pérez",
        "correo": "juan.perez@example.com",
        "roles": ["paciente"]
    },
    {
        "id": "2",
        "nombre": "María López",
        "correo": "maria.lopez@example.com",
        "roles": ["admin"]
    }
]
```

---

### 🔍 **Obtener un usuario por ID**
- **Método:** `GET`
- **URL:** `http://localhost:8080/api/v1/usuarios/{id}`

📌 **Ejemplo:** `http://localhost:8080/api/v1/usuarios/1`

📌 **Ejemplo de respuesta (JSON):**
```json
{
    "id": "1",
    "nombre": "Juan Pérez",
    "correo": "juan.perez@example.com",
    "roles": ["paciente"]
}
```

---

### ✏️ **Actualizar un usuario**
- **Método:** `PUT`
- **URL:** `http://localhost:8080/api/v1/usuarios/{id}`
- **Headers:** `Content-Type: application/json`

📌 **Ejemplo:** `http://localhost:8080/api/v1/usuarios/1`

📌 **Ejemplo de solicitud (JSON):**
```json
{
    "id": "1",
    "nombre": "Juan Pérez Actualizado",
    "correo": "juan.perez@example.com",
    "contraseña": "NuevaContraseñaSegura456",
    "roles": ["admin"]
}
```

---

### 🗑️ **Eliminar un usuario**
- **Método:** `DELETE`
- **URL:** `http://localhost:8080/api/v1/usuarios/{id}`

📌 **Ejemplo:** `http://localhost:8080/api/v1/usuarios/1`

---

## 🔎 **Documentación con Swagger**

Este microservicio expone una **documentación interactiva** con **Swagger** para facilitar su exploración y prueba.
📍 **URL:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---


