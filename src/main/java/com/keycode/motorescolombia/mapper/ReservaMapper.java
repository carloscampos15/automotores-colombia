package com.keycode.motorescolombia.mapper;

import com.keycode.motorescolombia.dto.out.ReservaDtoOut;
import com.keycode.motorescolombia.jpa.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReservaMapper {

    ReservaMapper MAPPER = Mappers.getMapper(ReservaMapper.class);

    @Mapping(source = "reserva.automotor", target = "automotorInfo")
    ReservaDtoOut map(Reserva reserva);

    List<ReservaDtoOut> map(List<Reserva> reservas);

}
