package com.keycode.motorescolombia.controller;

import com.keycode.motorescolombia.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keycode.motorescolombia.dto.request.ReservaRqDTO;
import com.keycode.motorescolombia.service.IReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;

    @PostMapping
    public void crearReserva(@RequestBody ReservaRqDTO request) {
        try {
            reservaService.crearReserva(request);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public void actualizarReserva(@RequestBody ReservaRqDTO request) {
        try {
            reservaService.actualizarReserva(request);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
