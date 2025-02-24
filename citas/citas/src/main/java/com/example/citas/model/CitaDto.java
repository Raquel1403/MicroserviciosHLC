package com.example.citas.model;


import lombok.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitaDto {

    private String id;
    private String pacienteID, psicologoID;
    private Date fecha;
    private LocalTime hora;
    private String estado, mensaje, especialidad;
    private List<String> notificacionesEnviadas;

}
