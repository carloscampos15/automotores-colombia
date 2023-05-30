package com.keycode.motorescolombia.dto.in;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AutomotorDtoIn {

    @NotNull
    @NotEmpty
    private String marca;

    @NotNull
    private Long modelo;

    @NotNull
    @NotEmpty
    private String linea;

}
