package com.keycode.motorescolombia.controller;

import com.keycode.motorescolombia.dto.AutomotorDTO;
import com.keycode.motorescolombia.service.impl.AutomotorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AutomotorControllerTest {

    @Mock
    private AutomotorService automotorService;

    @InjectMocks
    private AutomotorController automotorController;

    @Test
    void obtenerAutomotoresSuccess(){
        //Arrange

        //Act
        Mockito.when(automotorService.obtenerAutomotores(Mockito.anyInt()))
                .thenReturn(new ArrayList<>());

        List<AutomotorDTO> result = automotorController.obtenerAutomotores(1);

        //Assert
        Assert.notNull(result, "empty list");
        Mockito.verify(automotorService, Mockito.times(1))
                .obtenerAutomotores(Mockito.anyInt());
    }

    @Test
    void obtenerTodosAutomotoresSuccess() {
        //Arrange

        //Act
        Mockito.when(automotorService.obtenerAutomotoresAll())
                .thenReturn(new ArrayList<>());

        List<AutomotorDTO> result = automotorController.obtenerTodosAutomotores();

        //Assert
        Assert.notNull(result, "empty list");
        Mockito.verify(automotorService, Mockito.times(1))
                .obtenerAutomotoresAll();
    }

}
