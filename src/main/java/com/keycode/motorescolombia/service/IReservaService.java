package com.keycode.motorescolombia.service;

import com.keycode.motorescolombia.dto.request.ReservaRqDTO;
import com.keycode.motorescolombia.exception.NotFoundException;
import com.keycode.motorescolombia.jpa.entity.Reserva;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReservaService {

    public void crearReserva(ReservaRqDTO reservaRqDTO) throws NotFoundException;

    public void actualizarReserva(ReservaRqDTO reservaRqDTO) throws NotFoundException;

    public List<Reserva> getReservasByCiudad(Long ciudad);

    List<Reserva> getReservasByMarca(String marca);

}
