package com.keycode.motorescolombia.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "automotor")
@Getter
@Setter
public class Automotor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;

    private Long modelo;

    private String linea;

    @OneToOne
    @JoinColumn(name = "reserva_id", referencedColumnName = "id", nullable = true)
    private Reserva reserva;

}
