package com.keycode.motorescolombia.mapper;

import com.keycode.motorescolombia.dto.out.AutomotorDtoOut;
import com.keycode.motorescolombia.jpa.entity.Automotor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;

import java.util.Iterator;
import java.util.List;

@Mapper
public interface AutomotorMapper {

    AutomotorMapper MAPPER = Mappers.getMapper(AutomotorMapper.class);

    AutomotorDtoOut map(Automotor automotor);

    List<AutomotorDtoOut> map(List<Automotor> automotors);

}
