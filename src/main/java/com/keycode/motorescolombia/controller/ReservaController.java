package com.keycode.motorescolombia.controller;

import com.keycode.motorescolombia.dto.ReservaDTO;
import com.keycode.motorescolombia.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.keycode.motorescolombia.dto.request.ReservaRqDTO;
import com.keycode.motorescolombia.service.IReservaService;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;

    @GetMapping
    public List<ReservaDTO> getReservas(@RequestParam("usuario") String usuario){
        return reservaService.findReservasByUsuario(usuario);
    }

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
