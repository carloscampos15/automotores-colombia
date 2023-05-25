package com.keycode.motorescolombia.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.keycode.motorescolombia.jpa.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query(
            value = "SELECT R FROM Reserva R WHERE R.usuario = :usuario"
    )
    List<Reserva> obtenerReservasByUsuario(String usuario);

    @Query(
            value = "SELECT * FROM reserva WHERE usuario = :usuario",
            nativeQuery = true
    )
    List<Reserva> obtenerReservasByUsuarioNative(String usuario);

}
