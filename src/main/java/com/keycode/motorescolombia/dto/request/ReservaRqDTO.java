package com.keycode.motorescolombia.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReservaRqDTO {

    private Long idReserva;

    @NotNull(message = "El id del automotor es requerido")
    private Long idAutomotor;

    @NotNull(message = "El usuario es requerido")
    private String usuario;

}
