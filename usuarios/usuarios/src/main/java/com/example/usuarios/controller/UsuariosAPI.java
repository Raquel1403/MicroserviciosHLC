package com.example.usuarios.controller;

import com.example.usuarios.model.UsuariosDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UsuariosAPI {
    List<UsuariosDto> getAllUsuarios();
    Optional<UsuariosDto> getUsuarioById(String id);
    List<UsuariosDto> findByNombreContaining(String nombre);
    List<UsuariosDto> findByApellidosContaining(String apellidos);
    List<UsuariosDto> findByRol(String rol);

    UsuariosDto updateUsuario(UsuariosDto usuarioDto, String id);
    UsuariosDto addUsuario(UsuariosDto usuarioDto);
    ResponseEntity deleteUsuario(String id);
    ResponseEntity deleteAllUsuarios();

    Optional<UsuariosDto> login(String correo, String contraseña);

    Optional<String> getEspecialidadByUsuarioId(String id);
    List<String> getHistorialCitasByUsuarioId(String id);

    // Métodos adicionales
    UsuariosDto asignarEspecialidad(String id, String especialidad);
    ResponseEntity actualizarContraseña(String id, String nuevaContraseña);

    // Métodos de gestión de citas
    ResponseEntity solicitarCita(String usuarioId, String citaId);
    ResponseEntity reprogramarCita(String usuarioId, String citaId, String nuevaFecha);
    ResponseEntity cancelarCita(String usuarioId, String citaId);
    List<String> visualizarAgenda(String psicologoId);

    // Verificar rol del usuario
    String verificarRol(String id);
}
