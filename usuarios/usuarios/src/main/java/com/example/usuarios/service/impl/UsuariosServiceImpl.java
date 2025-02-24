package com.example.usuarios.service.impl;

import com.example.usuarios.model.UsuariosDto;
import com.example.usuarios.model.UsuariosVO;
import com.example.usuarios.repository.UsuariosRepository;
import com.example.usuarios.service.UsuariosService;
import com.example.usuarios.util.UsuariosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public List<UsuariosDto> getAllUsuarios() {
        List<UsuariosVO> usuariosVO = usuariosRepository.findAll();
        return UsuariosMapper.usuarioVOListMapperEntityToDto(usuariosVO);
    }

    @Override
    public Optional<UsuariosDto> getUsuarioById(String id) {
        Optional<UsuariosVO> usuarioOptional = usuariosRepository.findById(id);
        return usuarioOptional.map(UsuariosMapper::usuarioMapperEntityToDto);
    }

    @Override
    public List<UsuariosDto> findByNombreContaining(String nombre) {
        List<UsuariosVO> usuariosVO = usuariosRepository.findByNombreContaining(nombre);
        return UsuariosMapper.usuarioVOListMapperEntityToDto(usuariosVO);
    }

    @Override
    public List<UsuariosDto> findByApellidosContaining(String apellidos) {
        List<UsuariosVO> usuariosVO = usuariosRepository.findByApellidosContaining(apellidos);
        return UsuariosMapper.usuarioVOListMapperEntityToDto(usuariosVO);
    }

    @Override
    public List<UsuariosDto> findByRol(String rol) {
        List<UsuariosVO> usuariosVO = usuariosRepository.findByRol(rol);
        return UsuariosMapper.usuarioVOListMapperEntityToDto(usuariosVO);
    }

    @Override
    public UsuariosDto updateUsuario(UsuariosDto usuarioDto, String id) {
        Optional<UsuariosVO> usuarioOptional = usuariosRepository.findById(usuarioDto.getId());
        if (usuarioOptional.isPresent()) {
            UsuariosVO usuarioVO = usuarioOptional.get();
            usuarioVO.setCorreo(usuarioDto.getCorreo());
            usuarioVO.setContraseña(usuarioDto.getContraseña());
            usuarioVO.setRol(usuarioDto.getRol());
            usuarioVO.setNombre(usuarioDto.getNombre());
            usuarioVO.setApellidos(usuarioDto.getApellidos());
            usuarioVO.setTelefono(usuarioDto.getTelefono());
            usuarioVO.setFechaNacimiento(usuarioDto.getFechaNacimiento());
            usuarioVO.setAdministrador(usuarioDto.isAdministrador());
            usuarioVO.setEspecialidad(usuarioDto.getEspecialidad());
            usuarioVO.setHistorialCitas(usuarioDto.getHistorialCitas());
            UsuariosVO updatedUsuario = usuariosRepository.save(usuarioVO);
            return UsuariosMapper.usuarioMapperEntityToDto(updatedUsuario);
        } else {
            return null;
        }
    }

    @Override
    public UsuariosDto addUsuario(UsuariosDto usuarioDto) {
        UsuariosVO usuarioVO = UsuariosMapper.usuarioMapperDtoToEntity(usuarioDto);
        UsuariosVO createdUsuario = usuariosRepository.save(usuarioVO);
        return UsuariosMapper.usuarioMapperEntityToDto(createdUsuario);
    }

    @Override
    public ResponseEntity deleteUsuario(String id) {
        try {
            Optional<UsuariosVO> usuarioOptional = usuariosRepository.findById(id);
            if (usuarioOptional.isPresent()) {
                usuariosRepository.deleteById(id);
                return ResponseEntity.ok("Usuario eliminado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario");
        }
    }

    @Override
    public ResponseEntity deleteAllUsuarios() {
        usuariosRepository.deleteAll();
        return ResponseEntity.ok("Todos los usuarios han sido eliminados exitosamente");
    }

    @Override
    public Optional<UsuariosDto> login(String correo, String contraseña) {
        Optional<UsuariosVO> usuarioOptional = usuariosRepository.findByCorreoAndContraseña(correo, contraseña);
        return usuarioOptional.map(UsuariosMapper::usuarioMapperEntityToDto);
    }

    @Override
    public Optional<String> getEspecialidadByUsuarioId(String id) {
        Optional<UsuariosVO> usuarioOptional = usuariosRepository.findById(id);
        return usuarioOptional.map(UsuariosVO::getEspecialidad);
    }

    @Override
    public List<String> getHistorialCitasByUsuarioId(String id) {
        Optional<UsuariosVO> usuarioOptional = usuariosRepository.findById(id);
        return usuarioOptional.map(UsuariosVO::getHistorialCitas).orElse(new ArrayList<>());
    }

    @Override
    public UsuariosDto asignarEspecialidad(String id, String especialidad) {
        Optional<UsuariosVO> usuarioOptional = usuariosRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            UsuariosVO usuarioVO = usuarioOptional.get();
            usuarioVO.setEspecialidad(especialidad);
            UsuariosVO updatedUsuario = usuariosRepository.save(usuarioVO);
            return UsuariosMapper.usuarioMapperEntityToDto(updatedUsuario);
        }
        return null;
    }

    @Override
    public ResponseEntity actualizarContraseña(String id, String nuevaContraseña) {
        Optional<UsuariosVO> usuarioOptional = usuariosRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            UsuariosVO usuarioVO = usuarioOptional.get();
            usuarioVO.setContraseña(nuevaContraseña);
            usuariosRepository.save(usuarioVO);
            return ResponseEntity.ok("Contraseña actualizada exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }

    @Override
    public ResponseEntity solicitarCita(String usuarioId, String citaId) {
        // Implementación de la solicitud de cita (relacionado con pacientes)
        return ResponseEntity.ok("Cita solicitada");
    }

    @Override
    public ResponseEntity reprogramarCita(String usuarioId, String citaId, String nuevaFecha) {
        // Implementación de la reprogramación de cita (relacionado con pacientes)
        return ResponseEntity.ok("Cita reprogramada");
    }

    @Override
    public ResponseEntity cancelarCita(String usuarioId, String citaId) {
        // Implementación de la cancelación de cita (relacionado con pacientes)
        return ResponseEntity.ok("Cita cancelada");
    }

    @Override
    public List<String> visualizarAgenda(String psicologoId) {
        // Implementación para visualizar agenda de citas de un psicólogo
        return new ArrayList<>();
    }

    @Override
    public String verificarRol(String id) {
        Optional<UsuariosVO> usuarioOptional = usuariosRepository.findById(id);
        return usuarioOptional.map(UsuariosVO::getRol).orElse("ROL_NO_ENCONTRADO");
    }
}
