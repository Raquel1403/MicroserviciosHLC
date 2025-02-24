package com.example.usuarios.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class UsuariosDto {
    private String id;
    private String correo;
    private String contraseña;
    private String rol;  // Rol puede ser "psicologo" o "paciente"
    private String nombre;
    private String apellidos;
    private String telefono;
    private LocalDate fechaNacimiento;
    private boolean administrador;
    private String especialidad;  // Solo se asigna si el rol es "psicologo"
    private List<String> historialCitas;  // Solo se asigna si el rol es "paciente". Se usarán los ids (tipo string) de las citas
}