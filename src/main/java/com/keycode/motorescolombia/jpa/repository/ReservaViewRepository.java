package com.keycode.motorescolombia.jpa.repository;

import com.keycode.motorescolombia.jpa.entity.ReservaView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaViewRepository extends JpaRepository<ReservaView, Long> {

    List<ReservaView> getAllReservasView();

}
