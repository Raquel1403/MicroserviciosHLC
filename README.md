# MicroserviciosHLC

## Microservicio de Citas
Este microservicio gestiona las citas de pacientes con psicólogos. Permite realizar operaciones CRUD sobre las citas almacenadas en MongoDB.

## 💽 Endpoints de la API
La API corre en `http://localhost:8086/api/v1/citas`

### ➕ Crear nuevas citas
**Método:** `POST`

**URL:** `http://localhost:8086/api/v1/citas/add`

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

