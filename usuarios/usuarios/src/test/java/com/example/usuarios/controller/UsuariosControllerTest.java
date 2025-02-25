package com.example.usuarios.controller;

import com.example.usuarios.controller.impl.UsuariosController;
import com.example.usuarios.model.UsuariosDto;
import com.example.usuarios.service.UsuariosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuariosController.class)
public class UsuariosControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsuariosService usuariosService;

    @InjectMocks
    private UsuariosController usuariosController;

    private UsuariosDto usuario;

    @BeforeEach
    public void setUp() {
        usuario = new UsuariosDto("1", "correo@dominio.com", "contraseña123", "psicologo",
                "Juan", "Pérez", "123456789",
                LocalDate.of(1985, 10, 15), false, "Psicología", null);
    }

    @Test
    public void testGetAllUsuarios() throws Exception {
        when(usuariosService.getAllUsuarios()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }

    @Test
    public void testGetUsuarioById() throws Exception {
        when(usuariosService.getUsuarioById("1")).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    public void testAddUsuario() throws Exception {
        when(usuariosService.addUsuario(Mockito.any(UsuariosDto.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"correo\":\"correo@dominio.com\", \"contraseña\":\"contraseña123\", \"rol\":\"psicologo\", \"nombre\":\"Juan\", \"apellidos\":\"Pérez\", \"telefono\":\"123456789\", \"fechaNacimiento\":\"1985-10-15\", \"administrador\":false, \"especialidad\":\"Psicología\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    public void testUpdateUsuario() throws Exception {
        when(usuariosService.updateUsuario(Mockito.any(UsuariosDto.class), Mockito.eq("1"))).thenReturn(usuario);

        mockMvc.perform(put("/api/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"correo\":\"nuevo@dominio.com\", \"contraseña\":\"nueva123\", \"rol\":\"psicologo\", \"nombre\":\"Juan\", \"apellidos\":\"Pérez\", \"telefono\":\"123456789\", \"fechaNacimiento\":\"1985-10-15\", \"administrador\":false, \"especialidad\":\"Psicología\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    public void testDeleteUsuario() throws Exception {
        when(usuariosService.deleteUsuario("1")).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(delete("/api/v1/usuarios/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAllUsuarios() throws Exception {
        when(usuariosService.deleteAllUsuarios()).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(delete("/api/v1/usuarios/"))
                .andExpect(status().isOk());
    }

    // Additional tests can be written for other methods, such as filtering by nombre, apellidos, etc.

}
