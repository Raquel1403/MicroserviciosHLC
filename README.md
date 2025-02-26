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

### 📌 Swagger

Puedes acceder a la interfaz de Swagger desde:  
[`http://localhost:8086/swagger-ui/index.html`](http://localhost:8086/swagger-ui/index.html)  

Para habilitar Swagger en nuestro microservicio, hemos realizado las siguientes configuraciones:

---

**⚙️ 1. Agregar dependencias**  

En el archivo `pom.xml`, añadimos la dependencia:  

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

---

**🛠️ 2. Modificación de `application.yml`**  

Configuramos Swagger en el archivo `application.yml` para habilitar la UI y los endpoints de la API:

```yaml
springdoc:
  swagger-ui:
    enabled: true
    path: /swagger-ui.html
  api-docs:
    enabled: true
    path: /v3/api-docs
```

---

**☕ 3. Asegurar versión de Java**  

Es recomendable usar **Java 21**, ya que Java 23 presenta errores con algunas dependencias.  
Configuramos la versión en `pom.xml`:

```xml
<properties>
    <java.version>21</java.version>
</properties>
```

---

**📁 4. Añadir configuración en el paquete `config`**  

Creamos la clase **SwaggerConfig** en el paquete `config` para una configuración adicional.

---

**📝 5. Anotaciones en `CitaController`**  

Para mejorar la documentación en Swagger, añadimos anotaciones en nuestra clase **CitaController**:

- **Encima de la clase** `CitaController`, agregamos:  

  ```java
  @Tag(name = "Citas", description = "Operaciones sobre citas psicológicas")
  ```

- **Encima de cada método**, usamos:  

  ```java
  @Operation(summary = "Descripción del método...")
  ```

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
📍 **Base URL:** `http://localhost:8085/api/v1/usuarios`

### ➕ **Crear un nuevo usuario**
- **Método:** `POST`
- **URL:** `http://localhost:8085/api/v1/usuarios`
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
- **URL:** `http://localhost:8085/api/v1/usuarios`

📌 **Ejemplo de respuesta (JSON):**
```json
[
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
    },
    {
        "id": "654",
        "correo": "ana.psicologo@mail.com",
        "contraseña": "securePass456",
        "rol": "psicologo",
        "nombre": "Ana",
        "apellidos": "Gómez",
        "telefono": "623456789",
        "fechaNacimiento": "1985-10-22",
        "administrador": false,
        "especialidad": "Terapia Cognitiva",
        "historialCitas": null
    },
    {
        "id": "123",
        "correo": "admin@empresa.com",
        "contraseña": "admin123",
        "rol": "administrador",
        "nombre": "Carlos",
        "apellidos": "Martínez",
        "telefono": "612345001",
        "fechaNacimiento": "1980-02-15",
        "administrador": true,
        "especialidad": null,
        "historialCitas": null
    }
]
```

---

### 🔍 **Obtener un usuario por ID**
- **Método:** `GET`
- **URL:** `http://localhost:8085/api/v1/usuarios/{id}`

📌 **Ejemplo:** `http://localhost:8085/api/v1/usuarios/789`

📌 **Ejemplo de respuesta (JSON):**
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

### ✏️ **Actualizar un usuario**
- **Método:** `PUT`
- **URL:** `http://localhost:8085/api/v1/usuarios/{id}`
- **Headers:** `Content-Type: application/json`

📌 **Ejemplo:** `http://localhost:8085/api/v1/usuarios/789`

📌 **Ejemplo de solicitud (JSON):**
```json
{
        "id": "789",
        "correo": "juan.paciente@mail.com",
        "contraseña": "password123",
        "rol": "paciente",
        "nombre": "María",
        "apellidos": "Pérez",
        "telefono": "612345678",
        "fechaNacimiento": "1990-05-10",
        "administrador": false,
        "especialidad": null,
        "historialCitas": []
    }
```

---

### 🗑️ **Eliminar un usuario**
- **Método:** `DELETE`
- **URL:** `http://localhost:8085/api/v1/usuarios/{id}`

📌 **Ejemplo:** `http://localhost:8085/api/v1/usuarios/789`

---

### 🗑️ **Eliminar todos los usuarios**
- **Método:** `DELETE`
- **URL:** `http://localhost:8085/api/v1/usuarios`

📌 **Ejemplo:** `http://localhost:8085/api/v1/usuarios`

---

## 🔎 **Documentación con Swagger**

Este microservicio expone una **documentación interactiva** con **Swagger** para facilitar su exploración y prueba.
📍 **URL:** [http://localhost:8085/swagger-ui/index.html](http://localhost:8085/swagger-ui/index.html)

---


