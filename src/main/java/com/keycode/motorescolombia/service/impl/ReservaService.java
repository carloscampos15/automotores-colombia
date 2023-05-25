package com.keycode.motorescolombia.service.impl;

import com.keycode.motorescolombia.dto.request.ReservaRqDTO;
import com.keycode.motorescolombia.exception.NotFoundException;
import com.keycode.motorescolombia.jpa.entity.Agente;
import com.keycode.motorescolombia.jpa.entity.Automotor;
import com.keycode.motorescolombia.jpa.entity.Reserva;
import com.keycode.motorescolombia.jpa.repository.AgenteRepository;
import com.keycode.motorescolombia.jpa.repository.ReservaRepository;
import com.keycode.motorescolombia.jpa.repository.ReservaViewRepository;
import com.keycode.motorescolombia.service.IReservaService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservaService implements IReservaService {

    private final ReservaRepository reservaRepository;
    private final AutomotorService automotorService;
    private final AgenteRepository agenteRepository;
    private final ReservaViewRepository reservaViewRepository;

    @Override
    public void crearReserva(ReservaRqDTO reservaRqDTO) throws NotFoundException {
        Automotor automotor = automotorService.obtenerAutomotorById(reservaRqDTO.getIdAutomotor());
        Agente agente = agenteRepository
                .findById(reservaRqDTO.getAgenteId())
                .orElseThrow(() -> new NotFoundException(String.format("El agente de id %s no existe.", reservaRqDTO.getAgenteId())));

        Reserva reserva = new Reserva();
        reserva.setAutomotor(automotor);
        reserva.setUsuario(reservaRqDTO.getUsuario());
        reserva.setAgente(agente);

        reservaRepository.save(reserva);
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public void actualizarReserva(ReservaRqDTO reservaRqDTO) throws NotFoundException {
        Reserva reserva = reservaRepository
                .findById(reservaRqDTO.getIdReserva())
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("La reserva con id %s no fue encontrada", reservaRqDTO.getIdReserva())));

        automotorService.asignarReserva(reserva.getAutomotor(), null);

        Automotor automotor = automotorService.obtenerAutomotorById(reservaRqDTO.getIdAutomotor());

        reserva.setUsuario(reservaRqDTO.getUsuario());
        reserva.setAutomotor(automotor);

        reservaRepository.save(reserva);

        automotorService.asignarReserva(automotor, reserva);
        // automotorService.asignarReserva(automotor, new Reserva());
    }

    @Override
    public List<Reserva> getReservasByCiudad(Long ciudad) {
        reservaViewRepository.getAllReservasView();
        return reservaRepository.getReservasByCiudad(ciudad);
        //return reservaRepository.getReservasByCiudadNative(ciudad);
    }

}
