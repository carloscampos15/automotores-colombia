package com.keycode.motorescolombia.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.keycode.motorescolombia.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.keycode.motorescolombia.dto.AutomotorDTO;
import com.keycode.motorescolombia.dto.ReservaDTO;
import com.keycode.motorescolombia.jpa.entity.Automotor;
import com.keycode.motorescolombia.jpa.entity.Reserva;
import com.keycode.motorescolombia.jpa.repository.AutomotorRepository;
import com.keycode.motorescolombia.service.IAutomotorService;

import lombok.AllArgsConstructor;

import static com.keycode.motorescolombia.util.Contants.MSG_ERROR_NOT_FOUND;

@Service
@AllArgsConstructor
public class AutomotorService implements IAutomotorService {

    private final AutomotorRepository automotorRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<AutomotorDTO> obtenerAutomotores(int page) {
        Pageable paginacion = PageRequest.of(page, 10, Sort.by("linea"));

        return Stream.of(automotorRepository
                .findAllByReserva(paginacion, null)
                .get()).collect(Collectors.toList())
                .stream()
                .map(automotor -> mapper.map(automotor, AutomotorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AutomotorDTO> obtenerAutomotoresAll() {
        return StreamSupport
                .stream(automotorRepository.findAll(Sort.by("linea")).spliterator(), false)
                .collect(Collectors.toList())
                .stream()
                .map(automotor -> {
                    AutomotorDTO automotorDTO = mapper.map(automotor, AutomotorDTO.class);

                    if (Objects.nonNull(automotorDTO.getReserva())) {
                        ReservaDTO reservaDTO = automotorDTO.getReserva();
                        reservaDTO.setAutomotorCompleto(
                                automotor.getMarca() + " " + automotor.getLinea() + " " + automotor.getModelo());
                        automotorDTO.setReserva(reservaDTO);
                    }

                    return automotorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Automotor obtenerAutomotorById(Long id) throws NotFoundException {
        return automotorRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format(MSG_ERROR_NOT_FOUND, id)));
    }

    @Override
    public void asignarReserva(Automotor automotor, Reserva reserva) {
        automotor.setReserva(reserva);
        automotorRepository.save(automotor);
    }

}
