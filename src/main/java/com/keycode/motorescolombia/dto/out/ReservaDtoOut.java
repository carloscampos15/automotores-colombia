package com.keycode.motorescolombia.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReservaDtoOut {

    private Long id;

    @JsonProperty("automotor_info")
    private AutomotorDtoOut automotorInfo;

    private String usuario;

}
