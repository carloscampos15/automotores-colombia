package com.keycode.motorescolombia.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.keycode.motorescolombia.jpa.entity.Automotor;
import com.keycode.motorescolombia.jpa.entity.Reserva;

@Repository
public interface AutomotorRepository extends PagingAndSortingRepository<Automotor, Long> {

//    Page<Automotor> findAllByReserva(Pageable pageable, Reserva reserva);

    Optional<Automotor> findById(Long id);

    Automotor save(Automotor automotor);

}
