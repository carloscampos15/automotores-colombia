package com.keycode.motorescolombia.service;

import java.util.List;

import com.keycode.motorescolombia.dto.AutomotorDTO;
import com.keycode.motorescolombia.exception.NotFoundException;
import com.keycode.motorescolombia.jpa.entity.Automotor;
import com.keycode.motorescolombia.jpa.entity.Reserva;

public interface IAutomotorService {

    List<AutomotorDTO> obtenerAutomotores(int page);

    List<AutomotorDTO> obtenerAutomotoresAll();

    Automotor obtenerAutomotorById(Long id) throws NotFoundException;

    void asignarReserva(Automotor automotor, Reserva reserva);
}
