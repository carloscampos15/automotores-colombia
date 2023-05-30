package com.keycode.motorescolombia.service;

import java.util.List;

import com.keycode.motorescolombia.dto.AutomotorDTO;
import com.keycode.motorescolombia.dto.in.AutomotorDtoIn;
import com.keycode.motorescolombia.dto.out.AutomotorDtoOut;
import com.keycode.motorescolombia.exception.NotFoundException;

public interface IAutomotorService {

    List<AutomotorDtoOut> obtenerAutomotores(int page);

    AutomotorDtoOut obtenerAutomotorById(Long id) throws NotFoundException;

    void crearAutomotor(AutomotorDtoIn automotor);

    void actualizarAutomotor(Long idAutomotor, AutomotorDtoIn automotor) throws NotFoundException;
}
