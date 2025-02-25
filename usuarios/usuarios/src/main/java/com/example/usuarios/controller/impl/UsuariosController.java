package com.example.usuarios.controller.impl;

import com.example.usuarios.controller.UsuariosAPI;
import com.example.usuarios.model.UsuariosDto;
import com.example.usuarios.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UsuariosController implements UsuariosAPI {

    @Autowired
    private UsuariosService usuariosService;

    @Override
    @GetMapping("/usuarios")
    public List<UsuariosDto> getAllUsuarios() {
        return usuariosService.getAllUsuarios();
    }

    @Override
    @GetMapping("/usuarios/{id}")
    public Optional<UsuariosDto> getUsuarioById(@PathVariable String id) {
        return usuariosService.getUsuarioById(id);
    }

    @Override
    @GetMapping("/usuarios/nombre/{nombre}")
    public List<UsuariosDto> findByNombreContaining(@PathVariable String nombre) {
        return usuariosService.findByNombreContaining(nombre);
    }

    @Override
    @GetMapping("/usuarios/apellidos/{apellidos}")
    public List<UsuariosDto> findByApellidosContaining(@PathVariable String apellidos) {
        return usuariosService.findByApellidosContaining(apellidos);
    }

    @Override
    @GetMapping("/usuarios/rol/{rol}")
    public List<UsuariosDto> findByRol(@PathVariable String rol) {
        return usuariosService.findByRol(rol);
    }

    @Override
    @PutMapping("/usuarios/{id}")
    public UsuariosDto updateUsuario(@RequestBody UsuariosDto usuarioDto, @PathVariable String id) {
        return usuariosService.updateUsuario(usuarioDto, id);
    }

    @Override
    @PostMapping("/usuarios")
    public UsuariosDto addUsuario(@RequestBody UsuariosDto usuarioDto) {
        return usuariosService.addUsuario(usuarioDto);
    }

    @Override
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity deleteUsuario(@PathVariable String id) {
        return usuariosService.deleteUsuario(id);
    }

    @Override
    @DeleteMapping("/usuarios/")
    public ResponseEntity deleteAllUsuarios() {
        return usuariosService.deleteAllUsuarios();
    }

    /*@Override
    @PostMapping("/usuarios/login")
    public Optional<UsuariosDto> login(@RequestParam String correo, @RequestParam String contraseña) {
        return usuariosService.login(correo, contraseña);
    }*/

    /*@Override
    @GetMapping("/usuarios/especialidad/{id}")
    public Optional<String> getEspecialidadByUsuarioId(@PathVariable String id) {
        return usuariosService.getEspecialidadByUsuarioId(id);
    }

    @Override
    @GetMapping("/usuarios/historial-citas/{id}")
    public List<String> getHistorialCitasByUsuarioId(@PathVariable String id) {
        return usuariosService.getHistorialCitasByUsuarioId(id);
    }

    @Override
    @GetMapping("/usuarios/rol/verificar/{id}")
    public String verificarRol(@PathVariable String id) {
        return usuariosService.verificarRol(id);
    }

    @Override
    @PutMapping("/usuarios/{id}/asignar-especialidad")
    public UsuariosDto asignarEspecialidad(@PathVariable String id, @RequestParam String especialidad) {
        return usuariosService.asignarEspecialidad(id, especialidad);
    }

    @Override
    @PutMapping("/usuarios/{id}/actualizar-contraseña")
    public ResponseEntity actualizarContraseña(@PathVariable String id, @RequestParam String nuevaContraseña) {
        return usuariosService.actualizarContraseña(id, nuevaContraseña);
    }

    @Override
    @PostMapping("/usuarios/{usuarioId}/solicitar-cita")
    public ResponseEntity solicitarCita(@PathVariable String usuarioId, @RequestParam String citaId) {
        return usuariosService.solicitarCita(usuarioId, citaId);
    }

    @Override
    @PutMapping("/usuarios/{usuarioId}/reprogramar-cita")
    public ResponseEntity reprogramarCita(@PathVariable String usuarioId, @RequestParam String citaId, @RequestParam String nuevaFecha) {
        return usuariosService.reprogramarCita(usuarioId, citaId, nuevaFecha);
    }

    @Override
    @DeleteMapping("/usuarios/{usuarioId}/cancelar-cita")
    public ResponseEntity cancelarCita(@PathVariable String usuarioId, @RequestParam String citaId) {
        return usuariosService.cancelarCita(usuarioId, citaId);
    }

    @Override
    @GetMapping("/usuarios/{psicologoId}/visualizar-agenda")
    public List<String> visualizarAgenda(@PathVariable String psicologoId) {
        return usuariosService.visualizarAgenda(psicologoId);
    }*/
}
