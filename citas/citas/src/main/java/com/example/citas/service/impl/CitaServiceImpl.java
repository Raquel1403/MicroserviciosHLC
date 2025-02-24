package com.example.citas.service.impl;

import com.example.citas.model.CitaDto;
import com.example.citas.model.CitaVO;
import com.example.citas.repository.CitaRepository;
import com.example.citas.service.CitaService;
import com.example.citas.util.CitaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public List<CitaDto> findAll() {
        List<CitaVO> citaVOList = citaRepository.findAll();
        return citaVOList.stream()
                .map(CitaMapper::citaDtoMapperEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CitaDto> findById(String id) {
        Optional<CitaVO> citaVOOptional = citaRepository.findById(id);
        return citaVOOptional.map(CitaMapper::citaDtoMapperEntityToDto);
    }

    @Override
    public CitaDto save(CitaDto citaDto) {
        CitaVO citaVO = CitaMapper.citaVOMapperDtoToEntity(citaDto);
        CitaVO savedCitaEntity = citaRepository.save(citaVO);
        return CitaMapper.citaDtoMapperEntityToDto(savedCitaEntity);
    }

    @Override
    public CitaDto update(CitaDto citaDto) {
        Optional <CitaVO> existingCitaVOOptional = citaRepository.findById(citaDto.getId());
        if(existingCitaVOOptional.isPresent()){
            CitaVO existingCitaVO = existingCitaVOOptional.get();
            existingCitaVO.setPacienteID(citaDto.getPacienteID());
            existingCitaVO.setPsicologoID(citaDto.getPsicologoID());
            existingCitaVO.setFecha(citaDto.getFecha());
            existingCitaVO.setHora(citaDto.getHora());
            existingCitaVO.setEstado(citaDto.getEstado());
            existingCitaVO.setMensaje(citaDto.getMensaje());
            existingCitaVO.setEspecialidad(citaDto.getEspecialidad());
            existingCitaVO.setNotificacionesEnviadas(citaDto.getNotificacionesEnviadas());

            CitaVO updatedCitaVO = citaRepository.save(existingCitaVO);
            return  CitaMapper.citaDtoMapperEntityToDto(updatedCitaVO);
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity deleteById(String id) {
        try{
            Optional<CitaVO> existingCitaVOOptional = citaRepository.findById(id);
            if (existingCitaVOOptional.isPresent()){
                citaRepository.deleteById(id);
                return ResponseEntity.ok("Cita eliminada correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar Cita");
        }
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
