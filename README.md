# MicroserviciosHLC

**📚 Descripción del Proyecto**
Este proyecto se compone de dos microservicios diseñados para una gestión eficiente de citas psicológicas y la información de los usuarios:
- Microservicio de Citas: Se encarga de la administración de las citas entre pacientes y psicólogos, permitiendo operaciones como la creación, modificación y cancelación de citas.
- Microservicio de Usuarios: Gestiona la información de los usuarios dentro del sistema, incluyendo pacientes, psicólogos y administradores, asegurando un manejo seguro y organizado de sus datos.


# Microservicio de Citas

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

**⚙️ 1. Agregar dependencias**  

En el archivo `pom.xml`, añadimos la dependencia:  

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

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

**☕ 3. Asegurar versión de Java**  

Es recomendable usar **Java 21**, ya que Java 23 presenta errores con algunas dependencias.  
Configuramos la versión en `pom.xml`:

```xml
<properties>
    <java.version>21</java.version>
</properties>
```

**📁 4. Añadir configuración en el paquete `config`**  

Creamos la clase **SwaggerConfig** en el paquete `config` para una configuración adicional.

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

📌 **Configuración Inicial** (@BeforeEach)

Antes de ejecutar cada prueba, inicializamos MockMvc, que nos permite simular peticiones HTTP hacia nuestro controlador sin necesidad de levantar la aplicación completa.

@BeforeEach

void setUp() {

    mockMvc = MockMvcBuilders.standaloneSetup(citaController).build();
    
}

📝 **Pruebas Implementadas**

1️⃣ Obtener Todas las Citas

📍 Endpoint: GET /api/v1/citas

@Test
void testObtenerTodasLasCitas() throws Exception {
    when(citaService.findAll()).thenReturn(Collections.emptyList());

    mockMvc.perform(get("/api/v1/citas"))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));

    verify(citaService, times(1)).findAll();
}

✅ Simulamos una llamada al servicio findAll() que devuelve una lista vacía.

✅ Verificamos que la respuesta tiene código 200 OK y devuelve un JSON vacío ([]).

✅ Comprobamos que el servicio fue llamado una sola vez.

2️⃣ Obtener una Cita por ID

📍 Endpoint: GET /api/v1/citas/{id}

@Test
void testObtenerCitaPorId() throws Exception {
    CitaDto cita = new CitaDto("1", "123", "456", new Date(), LocalTime.of(14, 30), "pendiente", "Consulta", "Terapia", Collections.emptyList());
    when(citaService.findById("1")).thenReturn(Optional.of(cita));

    mockMvc.perform(get("/api/v1/citas/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"));

    verify(citaService, times(1)).findById("1");
}

✅ Simulamos que findById("1") devuelve una cita existente.

✅ Verificamos que la respuesta es 200 OK y que el JSON contiene el id: "1".

✅ Confirmamos que el servicio fue llamado una sola vez.

3️⃣ Crear una Nueva Cita

📍 Endpoint: POST /api/v1/citas

@Test
void testCrearCita() throws Exception {
    String citaJson = """
            {
                "id": "1",
                "pacienteID": "123",
                "psicologoID": "456",
                "fecha": "2025-03-01",
                "hora": "14:30",
                "estado": "pendiente",
                "mensaje": "Consulta",
                "especialidad": "Terapia",
                "notificacionesEnviadas": []
            }
            """;

    CitaDto cita = new CitaDto("1", "123", "456", new Date(), LocalTime.of(14, 30), "pendiente", "Consulta", "Terapia", Collections.emptyList());

    when(citaService.save(any())).thenReturn(cita);

    mockMvc.perform(post("/api/v1/citas")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(citaJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"));

    verify(citaService, times(1)).save(any());
}

✅ Simulamos que el servicio guarda la cita correctamente.

✅ Enviamos un JSON con los datos de la cita.

✅ Verificamos que la respuesta es 200 OK y el JSON contiene el id: "1".

✅ Confirmamos que save(any()) se llamó una sola vez.

4️⃣ Eliminar una Cita

📍 Endpoint: DELETE /api/v1/citas/{id}

@Test
void testEliminarCita() throws Exception {
    when(citaService.deleteById("1")).thenReturn(ResponseEntity.ok().build());

    mockMvc.perform(delete("/api/v1/citas/1"))
            .andExpect(status().isOk());

    verify(citaService, times(1)).deleteById("1");
}

✅ Simulamos que la cita se elimina correctamente.

✅ Enviamos una petición DELETE y verificamos que la respuesta es 200 OK.

✅ Confirmamos que deleteById("1") se llamó solo una vez.


📌 Ejecución de Pruebas:
Para ejecutar las pruebas, usa el siguiente comando en el terminal:

```mvn test```

# Microservicio de Usuarios

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


