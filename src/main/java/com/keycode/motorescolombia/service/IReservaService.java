package com.keycode.motorescolombia.service;

import com.keycode.motorescolombia.dto.out.ReservaDtoOut;
import com.keycode.motorescolombia.dto.request.ReservaRqDTO;
import com.keycode.motorescolombia.exception.NotFoundException;

public interface IReservaService {

    public void crearReserva(ReservaRqDTO reservaRqDTO) throws NotFoundException;

    public void actualizarReserva(ReservaRqDTO reservaRqDTO) throws NotFoundException;

    public ReservaDtoOut getReservaById(Long id) throws NotFoundException;

}
