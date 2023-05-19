package com.keycode.motorescolombia.jpa.repository;

import org.springframework.stereotype.Repository;
import com.keycode.motorescolombia.jpa.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
