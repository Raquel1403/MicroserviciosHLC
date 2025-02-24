package com.example.citas.controller;

import com.example.citas.model.CitaDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CitaAPI {

    List<CitaDto> findAll();  // Obtener todas las citas
    Optional<CitaDto> findById(String id);  // Buscar cita por ID
    CitaDto save(CitaDto citaDto);  // Guardar una nueva cita
    CitaDto updateCita(CitaDto citaDto, String id);  // Actualizar una cita existente
    ResponseEntity deleteById(String id);  // Eliminar cita por ID

    /*List<CitaDto> findByPacienteId(String pacienteId);  // Buscar citas por paciente
    List<CitaDto> findByPsicologoId(String psicologoId);  // Buscar citas por psicólogo

    CitaDto confirmarCita(String id);  // Confirmar una cita (Cambia el estado de la cita a "CONFIRMADA")
    CitaDto cancelarCita(String id);  // Cancelar una cita (Cambia el estado de la cita a "CANCELADA")
    CitaDto reprogramarCita(String id, CitaDto nuevaCita);  // Reprogramar cita (Cambia fecha y hora de la cita)*/

}
