package com.keycode.motorescolombia.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gama")
@Getter
@Setter
public class Gama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "cilindraje_id", referencedColumnName = "id")
    private Cilindraje cilindraje;

}
