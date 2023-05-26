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
            value =
            """
                SELECT
                	re
                FROM
                    Reserva re
                WHERE
                    re.agente.ciudad.id = :ciudad
            """
    )
    List<Reserva> getReservasByCiudad(Long ciudad);

    @Query(
            value =
            """
                SELECT
                	re.*
                FROM reserva re
                INNER 
                    JOIN agente ag ON ag.id = re.agente_id
                INNER 
                    JOIN ciudad ci ON ci.id = ag.ciudad_id
                WHERE 
                    ag.ciudad_id = :ciudad
            """,
            nativeQuery = true
    )
    List<Reserva> getReservasByCiudadNative(@Param("ciudad") Long ciudad);

}
