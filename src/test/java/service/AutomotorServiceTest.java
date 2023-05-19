package service;

import com.keycode.motorescolombia.dto.AutomotorDTO;
import com.keycode.motorescolombia.exception.NotFoundException;
import com.keycode.motorescolombia.jpa.entity.Automotor;
import com.keycode.motorescolombia.jpa.entity.Reserva;
import com.keycode.motorescolombia.jpa.repository.AutomotorRepository;
import com.keycode.motorescolombia.service.impl.AutomotorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.keycode.motorescolombia.util.Contants.MSG_ERROR_NOT_FOUND;

@ExtendWith(MockitoExtension.class)
public class AutomotorServiceTest {

    @Mock
    private AutomotorRepository automotorRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private AutomotorService automotorService;

    @Test
    void obtenerAutomotores() {
        List<Automotor> automotors = new ArrayList<>() {{
            add(new Automotor());
        }};

        Mockito.when(automotorRepository.findAllByReserva(Mockito.any(), Mockito.nullable(Reserva.class)))
                .thenReturn(new PageImpl<>(automotors));

        List<AutomotorDTO> result = automotorService.obtenerAutomotores(1);

        Assertions.assertNotNull(result);
        Mockito.verify(automotorRepository, Mockito.times(1)).findAllByReserva(Mockito.any(), Mockito.nullable(Reserva.class));
    }

    @ParameterizedTest
    @CsvSource({"1","2"})
    void obtenerAutomotorByIdSuccess(Long id) throws NotFoundException {
        Mockito.when(automotorRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(new Automotor()));

        Automotor result = automotorService.obtenerAutomotorById(id);

        Assertions.assertNotNull(result);
        Mockito.verify(automotorRepository, Mockito.times(1)) .findById(Mockito.anyLong());
    }

    @ParameterizedTest
    @CsvSource({"1","2"})
    void obtenerAutomotorByIdException(Long id) {
        Mockito.when(automotorRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        NotFoundException ex = Assertions.assertThrows(
                NotFoundException.class,
                () -> automotorService.obtenerAutomotorById(id)
        );

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(ex.getMessage(), String.format(MSG_ERROR_NOT_FOUND, id));

        Mockito.verify(automotorRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }
}
