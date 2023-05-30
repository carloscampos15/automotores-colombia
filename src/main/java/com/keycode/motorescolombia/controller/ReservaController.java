package com.keycode.motorescolombia.controller;

import com.keycode.motorescolombia.dto.out.ReservaDtoOut;
import com.keycode.motorescolombia.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDtoOut> getReservaById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservaService.getReservaById(id));
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
