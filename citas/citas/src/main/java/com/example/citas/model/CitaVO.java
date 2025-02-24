package com.example.citas.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "citas")
public class CitaVO {
    @Id

    private String id;
    private String pacienteID, psicologoID;
    private Date fecha;
    private LocalTime hora;
    private String estado, mensaje, especialidad;
    private List<String> notificacionesEnviadas;
}
