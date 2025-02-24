package com.example.citas.repository;

import com.example.citas.model.CitaVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitaRepository extends MongoRepository<CitaVO, String> {

    List<CitaVO> findAll();  // Obtener todas las citas

    Optional<CitaVO> findById(String id);  // Buscar cita por ID

    CitaVO save(CitaVO citaVO);  // Guardar una nueva cita
    //CitaVO update(CitaVO citaVO);  // Actualizar una cita existente
    //void deleteById(String id);  // Eliminar cita por ID

    /*List<CitaVO> findByPacienteId(String pacienteId);  // Buscar citas por paciente
    List<CitaVO> findByPsicologoId(String psicologoId);  // Buscar citas por psicólogo

    CitaVO confirmarCita(String id);  // Confirmar una cita (Cambia el estado de la cita a "CONFIRMADA")
    CitaVO cancelarCita(String id);  // Cancelar una cita (Cambia el estado de la cita a "CANCELADA")
    CitaVO reprogramarCita(String id, CitaVO nuevaCita);  // Reprogramar cita (Cambia fecha y hora de la cita)*/
}
