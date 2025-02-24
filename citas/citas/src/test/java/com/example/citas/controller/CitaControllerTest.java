package com.example.citas.controller;

import com.example.citas.controller.impl.CitaController;
import com.example.citas.model.CitaDto;
import com.example.citas.service.CitaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CitaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CitaService citaService;

    @InjectMocks
    private CitaController citaController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(citaController).build();
    }

    @Test
    void testObtenerTodasLasCitas() throws Exception {
        when(citaService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/citas"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(citaService, times(1)).findAll();
    }

    @Test
    void testObtenerCitaPorId() throws Exception {
        CitaDto cita = new CitaDto("1", "123", "456", new Date(), LocalTime.of(14, 30), "pendiente", "Consulta", "Terapia", Collections.emptyList());
        when(citaService.findById("1")).thenReturn(Optional.of(cita));

        mockMvc.perform(get("/api/v1/citas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));

        verify(citaService, times(1)).findById("1");
    }

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

    @Test
    void testEliminarCita() throws Exception {
        when(citaService.deleteById("1")).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(delete("/api/v1/citas/1"))
                .andExpect(status().isOk());

        verify(citaService, times(1)).deleteById("1");
    }
}
