package com.example.citas.controller.impl;

import com.example.citas.controller.CitaAPI;
import com.example.citas.model.CitaDto;
import com.example.citas.repository.CitaRepository;
import com.example.citas.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@Tag(name = "Citas", description = "Operaciones sobre citas psicológicas")
@RestController
@RequestMapping("api/v1")
public class CitaController implements CitaAPI {

    @Autowired
    private CitaService citaService;

    @Autowired
    private CitaRepository citaRepository;

    @Operation(summary = "Obtener todas las citas")
    @Override
    @GetMapping("/citas")
    public List<CitaDto> findAll() {return citaService.findAll();}

    @Operation(summary = "Obtener una cita por ID")
    @Override
    @GetMapping("/citas/{id}")
    public Optional<CitaDto> findById(@PathVariable String id) {return citaService.findById(id);}

    @Operation(summary = "Crear una nueva cita")
    @Override
    @PostMapping("/citas")
    public CitaDto save(@RequestBody CitaDto citaDto) {return citaService.save(citaDto);}

    @Operation(summary = "Modificar una cita existente")
    @Override
    @PutMapping("citas/{id}")
    public CitaDto updateCita(@RequestBody CitaDto citaDto, @PathVariable String id) {
        return citaService.update(citaDto);
    }

    @Operation(summary = "Eliminar una cita existente")
    @Override
    @DeleteMapping("/citas/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {return citaService.deleteById(id);
    }

    /*@Override
    public List<CitaDto> findByPacienteId(String pacienteId) {
        return List.of();
    }

    @Override
    public List<CitaDto> findByPsicologoId(String psicologoId) {
        return List.of();
    }

    @Override
    public CitaDto confirmarCita(String id) {
        return null;
    }

    @Override
    public CitaDto cancelarCita(String id) {
        return null;
    }

    @Override
    public CitaDto reprogramarCita(String id, CitaDto nuevaCita) {
        return null;
    }*/
}
