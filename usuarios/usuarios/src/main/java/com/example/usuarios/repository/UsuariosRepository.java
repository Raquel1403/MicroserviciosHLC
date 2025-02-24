package com.example.usuarios.repository;

import com.example.usuarios.model.UsuariosVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends MongoRepository<UsuariosVO, String> {

    Optional<UsuariosVO> findByCorreoAndContraseña(String correo, String contraseña);

    Optional<UsuariosVO> findByCorreo(String correo);

    List<UsuariosVO> findByNombreContaining(String nombre);

    List<UsuariosVO> findByApellidosContaining(String apellidos);

    List<UsuariosVO> findByRol(String rol);

    Optional<UsuariosVO> findById(String id);
}
