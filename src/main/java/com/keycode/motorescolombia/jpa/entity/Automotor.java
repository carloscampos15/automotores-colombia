package com.keycode.motorescolombia.jpa.entity;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "gama_id", referencedColumnName = "id")
    private Gama gama;

}
