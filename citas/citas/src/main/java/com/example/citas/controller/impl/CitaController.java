package com.example.citas.controller.impl;

import com.example.citas.controller.CitaAPI;
import com.example.citas.model.CitaDto;
import com.example.citas.repository.CitaRepository;
import com.example.citas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class CitaController implements CitaAPI {

    @Autowired
    private CitaService citaService;

    @Autowired
    private CitaRepository citaRepository;

    @Override
    @GetMapping("/citas")
    public List<CitaDto> findAll() {return citaService.findAll();}

    @Override
    @GetMapping("/citas/{id}")
    public Optional<CitaDto> findById(@PathVariable String id) {return citaService.findById(id);}

    @Override
    @PostMapping("/citas/add")
    public CitaDto save(@RequestBody CitaDto citaDto) {return citaService.save(citaDto);}

    @Override
    @PutMapping("citas/{id}")
    public CitaDto updateCita(@RequestBody CitaDto citaDto, @PathVariable String id) {
        return citaService.update(citaDto);
    }

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
