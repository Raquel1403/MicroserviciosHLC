package com.example.usuarios.util;

import com.example.usuarios.model.UsuariosDto;
import com.example.usuarios.model.UsuariosVO;

import java.util.List;
import java.util.stream.Collectors;

public class UsuariosMapper {

    public static UsuariosVO usuarioMapperDtoToEntity(UsuariosDto usuarioDto){
        UsuariosVO.UsuariosVOBuilder usuarioVOBuilder = UsuariosVO.builder()
                .id(usuarioDto.getId())
                .correo(usuarioDto.getCorreo())
                .contraseña(usuarioDto.getContraseña())
                .rol(usuarioDto.getRol())
                .nombre(usuarioDto.getNombre())
                .apellidos(usuarioDto.getApellidos())
                .telefono(usuarioDto.getTelefono())
                .fechaNacimiento(usuarioDto.getFechaNacimiento())
                .administrador(usuarioDto.isAdministrador());

        // Solo asignar especialidad si el rol es "psicologo"
        if ("psicologo".equals(usuarioDto.getRol())) {
            usuarioVOBuilder.especialidad(usuarioDto.getEspecialidad());
        }

        // Solo asignar historialCitas si el rol es "paciente"
        if ("paciente".equals(usuarioDto.getRol())) {
            usuarioVOBuilder.historialCitas(usuarioDto.getHistorialCitas());
        }

        return usuarioVOBuilder.build();
    }

    public static UsuariosDto usuarioMapperEntityToDto(UsuariosVO usuarioVO){
        UsuariosDto.UsuariosDtoBuilder usuarioDtoBuilder = UsuariosDto.builder()
                .id(usuarioVO.getId())
                .correo(usuarioVO.getCorreo())
                .contraseña(usuarioVO.getContraseña())
                .rol(usuarioVO.getRol())
                .nombre(usuarioVO.getNombre())
                .apellidos(usuarioVO.getApellidos())
                .telefono(usuarioVO.getTelefono())
                .fechaNacimiento(usuarioVO.getFechaNacimiento())
                .administrador(usuarioVO.isAdministrador());

        // Solo asignar especialidad si el rol es "psicologo"
        if ("psicologo".equals(usuarioVO.getRol())) {
            usuarioDtoBuilder.especialidad(usuarioVO.getEspecialidad());
        }

        // Solo asignar historialCitas si el rol es "paciente"
        if ("paciente".equals(usuarioVO.getRol())) {
            usuarioDtoBuilder.historialCitas(usuarioVO.getHistorialCitas());
        }

        return usuarioDtoBuilder.build();
    }

    public static List<UsuariosVO> usuarioVOListMapperDtoToEntity(List<UsuariosDto> usuarioDtoList) {
        return usuarioDtoList.stream()
                .map(UsuariosMapper::usuarioMapperDtoToEntity)
                .collect(Collectors.toList());
    }

    public static List<UsuariosDto> usuarioVOListMapperEntityToDto(List<UsuariosVO> usuarioVOList) {
        return usuarioVOList.stream()
                .map(UsuariosMapper::usuarioMapperEntityToDto)
                .collect(Collectors.toList());
    }
}
