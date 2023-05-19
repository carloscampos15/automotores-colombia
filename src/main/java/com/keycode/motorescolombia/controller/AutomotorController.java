package com.keycode.motorescolombia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keycode.motorescolombia.dto.AutomotorDTO;
import com.keycode.motorescolombia.service.IAutomotorService;

@RestController
@RequestMapping("/automotor")
public class AutomotorController {

    @Autowired
    private IAutomotorService automotorService;

    @GetMapping()
    public List<AutomotorDTO> obtenerTodosAutomotores() {
        return automotorService.obtenerAutomotoresAll();
    }

    @GetMapping("/sin-reservar")
    public List<AutomotorDTO> obtenerAutomotores(@RequestParam("page") Integer page) {
        return automotorService.obtenerAutomotores(page);
    }

}
