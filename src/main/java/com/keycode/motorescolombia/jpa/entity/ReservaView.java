package com.keycode.motorescolombia.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@NamedNativeQuery(
        name = "ReservaView.getAllReservasView",
        query =
        """
        SELECT
        	re.id,
        	au.linea AS automotor,
        	ga.descripcion AS gama,
        	cil.descripcion AS cilindraje,
        	re.usuario,
        	ag.nombre AS agente,
        	ci.nombre AS ciudad
        FROM reserva re
        INNER JOIN automotor au ON au.id = re.automotor_id
        INNER JOIN agente ag ON ag.id = re.agente_id
        INNER JOIN ciudad ci ON ci.id = ag.ciudad_id
        INNER JOIN gama ga ON ga.id = au.gama_id
        INNER JOIN cilindraje cil ON cil.id = ga.cilindraje_id
        """,
        resultClass = ReservaView.class
)
public class ReservaView {

    @Id
    private Long id;

    private String automotor;

    private String gama;

    private String cilindraje;

    private String usuario;

    private String agente;

    private String ciudad;

}
