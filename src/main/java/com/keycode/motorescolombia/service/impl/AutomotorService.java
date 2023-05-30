package com.keycode.motorescolombia.service.impl;

import java.util.List;

import com.keycode.motorescolombia.dto.in.AutomotorDtoIn;
import com.keycode.motorescolombia.dto.out.AutomotorDtoOut;
import com.keycode.motorescolombia.exception.NotFoundException;
import com.keycode.motorescolombia.mapper.AutomotorMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.keycode.motorescolombia.dto.AutomotorDTO;
import com.keycode.motorescolombia.jpa.entity.Automotor;
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
    public List<AutomotorDtoOut> obtenerAutomotores(int page) {
        Pageable paginacion = PageRequest.of(page, 10, Sort.by("linea"));

        return AutomotorMapper.MAPPER.map(automotorRepository.findAll(paginacion).get().toList());

//        return Stream.of(automotorRepository
//                .findAllByReserva(paginacion, null)
//                .get()).collect(Collectors.toList())
//                .stream()
//                .map(automotor -> mapper.map(automotor, AutomotorDTO.class))
//                .collect(Collectors.toList());
    }

    @Override
    public AutomotorDtoOut obtenerAutomotorById(Long id) throws NotFoundException {
        Automotor automotor = automotorRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format(MSG_ERROR_NOT_FOUND, id)));
        return AutomotorMapper.MAPPER.map(automotor);
    }

    @Override
    public void crearAutomotor(AutomotorDtoIn automotor) {
        Automotor automotorEntity = new Automotor();
        automotorEntity.setMarca(automotor.getMarca());
        automotorEntity.setLinea(automotor.getLinea());
        automotorEntity.setModelo(automotor.getModelo());

        automotorRepository.save(automotorEntity);
    }

    public void actualizarAutomotor(Long idAutomotor, AutomotorDtoIn automotor) throws NotFoundException {
        Automotor automotorEntity = automotorRepository.findById(idAutomotor)
                .orElseThrow(() -> new NotFoundException(String.format(MSG_ERROR_NOT_FOUND, idAutomotor)));

        automotorEntity.setMarca(automotor.getMarca());
        automotorEntity.setLinea(automotor.getLinea());
        automotorEntity.setModelo(automotor.getModelo());

        automotorRepository.save(automotorEntity);
    }

}
