package com.keycode.motorescolombia.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservaRqDTO {

    private Long idReserva;

    @NotNull(message = "El id del automotor es requerido")
    private Long idAutomotor;

    @NotNull(message = "El usuario es requerido")
    private String usuario;

}
