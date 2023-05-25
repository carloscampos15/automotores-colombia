package com.keycode.motorescolombia.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "reserva")
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "automotor_id", referencedColumnName = "id", nullable = false, unique = true)
    private Automotor automotor;

    @Column(nullable = false)
    private String usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agente_id", referencedColumnName = "id", nullable = false)
    private Agente agente;

    @CreationTimestamp
    @Column(nullable = false)
    private Date createdAt = new Date();

}
