package com.keycode.motorescolombia.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reserva")
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "automotor_id", referencedColumnName = "id", nullable = false)
    private Automotor automotor;

    @Column(nullable = false)
    private String usuario;

}
