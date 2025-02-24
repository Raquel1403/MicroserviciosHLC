package com.example.citas.util;

import com.example.citas.model.CitaDto;
import com.example.citas.model.CitaVO;

import java.util.List;
import java.util.stream.Collectors;

public class CitaMapper {

    public static CitaVO citaVOMapperDtoToEntity(CitaDto citaDto){
        return CitaVO.builder()
                .id(citaDto.getId())
                .pacienteID(citaDto.getPacienteID())
                .psicologoID(citaDto.getPsicologoID())
                .fecha(citaDto.getFecha())
                .hora(citaDto.getHora())
                .estado(citaDto.getEstado())
                .mensaje(citaDto.getMensaje())
                .especialidad(citaDto.getEspecialidad())
                .notificacionesEnviadas(citaDto.getNotificacionesEnviadas())
                .build();
    }

    public static CitaDto citaDtoMapperEntityToDto(CitaVO citaVO){
        return CitaDto.builder()
                .id(citaVO.getId())
                .pacienteID(citaVO.getPacienteID())
                .psicologoID(citaVO.getPsicologoID())
                .fecha(citaVO.getFecha())
                .hora(citaVO.getHora())
                .estado(citaVO.getEstado())
                .mensaje(citaVO.getMensaje())
                .especialidad(citaVO.getEspecialidad())
                .notificacionesEnviadas(citaVO.getNotificacionesEnviadas())
                .build();
    }

    public static List<CitaVO> citaVOListMapperDtoToEntity(List<CitaDto> citaDtoList){
        return citaDtoList.stream()
                .map(CitaMapper::citaVOMapperDtoToEntity)
                .collect(Collectors.toList());
    }

    public static List<CitaDto> citaDtoListMapperEntityToDto(List<CitaVO> citaVOList){
        return citaVOList.stream()
                .map(CitaMapper::citaDtoMapperEntityToDto)
                .collect(Collectors.toList());
    }
}
