package com.keycode.motorescolombia.service.impl;

import com.keycode.motorescolombia.dto.ReservaDTO;
import com.keycode.motorescolombia.dto.request.ReservaRqDTO;
import com.keycode.motorescolombia.exception.NotFoundException;
import com.keycode.motorescolombia.jpa.entity.Automotor;
import com.keycode.motorescolombia.jpa.entity.Reserva;
import com.keycode.motorescolombia.jpa.repository.ReservaRepository;
import com.keycode.motorescolombia.service.IReservaService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservaService implements IReservaService {

    private final ReservaRepository reservaRepository;
    private final AutomotorService automotorService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void crearReserva(ReservaRqDTO reservaRqDTO) throws NotFoundException {
        Automotor automotor = automotorService.obtenerAutomotorById(reservaRqDTO.getIdAutomotor());

        if (Objects.isNull(automotor.getReserva())) {
            Reserva reserva = new Reserva();
            reserva.setAutomotor(automotor);
            reserva.setUsuario(reservaRqDTO.getUsuario());

            reservaRepository.save(reserva);

            automotorService.asignarReserva(automotor, reserva);
        } else {
            throw new NotFoundException(
                    String.format("El vehículo con id %s ya se encuentra reservado", reservaRqDTO.getIdAutomotor()));
        }
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
    public List<ReservaDTO> findReservasByUsuario(String usuario) {
        return reservaRepository
                .obtenerReservasByUsuarioNative(usuario)
                .stream()
                .map(reserva -> {
                    ReservaDTO reservaDTO = mapper.map(reserva, ReservaDTO.class);
                    reservaDTO.setAutomotorCompleto(
                            reserva.getAutomotor().getMarca() + " " + reserva.getAutomotor().getLinea() + " " + reserva.getAutomotor().getModelo());
                    return reservaDTO;
                })
                .collect(Collectors.toList());
    }

}
