package com.keycode.motorescolombia.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

}
