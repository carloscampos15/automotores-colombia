package com.keycode.motorescolombia.dto;

import lombok.Data;

@Data
public class ReservaDTO {

    private Long id;
    private Long idAutomotor;
    private String lineaAutomotor;
    private String automotorCompleto;
    private String usuario;
    
}
