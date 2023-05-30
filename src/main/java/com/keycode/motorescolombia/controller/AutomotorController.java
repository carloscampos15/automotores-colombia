package com.keycode.motorescolombia.controller;

import com.keycode.motorescolombia.dto.in.AutomotorDtoIn;
import com.keycode.motorescolombia.dto.out.AutomotorDtoOut;
import com.keycode.motorescolombia.exception.NotFoundException;
import com.keycode.motorescolombia.service.IAutomotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/automotor")
public class AutomotorController {

    @Autowired
    private IAutomotorService automotorService;

    @GetMapping
    public ResponseEntity<List<AutomotorDtoOut>> obtenerAutomotores(@RequestParam("page") Integer page) {
        return ResponseEntity.status(HttpStatus.OK).body(automotorService.obtenerAutomotores(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutomotorDtoOut> obtenerAutomotorById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(automotorService.obtenerAutomotorById(id));
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> crearAutomotor(@RequestBody AutomotorDtoIn automotor) {
        automotorService.crearAutomotor(automotor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarAutomotor(@PathVariable("id") Long idAutomotor, @RequestBody AutomotorDtoIn automotor) {
        try {
            automotorService.actualizarAutomotor(idAutomotor, automotor);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
