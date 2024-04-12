package com.example.ClinicaProyecto.service;

import com.example.ClinicaProyecto.dto.TurnoDTO;
import com.example.ClinicaProyecto.entity.Domicilio;
import com.example.ClinicaProyecto.entity.Odontologo;
import com.example.ClinicaProyecto.entity.Paciente;
import com.example.ClinicaProyecto.entity.Turno;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void guardarTurno() {
        Domicilio domicilio = new Domicilio("cambiaggi", "9021", "Guaymallen", "Mendoza");
        Paciente paciente = pacienteService.guardarPaciente(new Paciente("Agustin", "Mondati", "40221375", LocalDate.of(1997, 3, 22), "agustinmoati@gmail.com", domicilio));
        Odontologo odontologo = odontologoService.guardarOdontologo(new Odontologo("Agustin", "Mondati", "MN-1423"));
        Turno turno = new Turno(LocalDate.of(2024, 03, 22), paciente, odontologo);
        TurnoDTO turno1 = turnoService.guardarTurno(turno);
        assertEquals(1, turno1.getId());
    }

    @Test
    @Order(2)
    void buscarTurno() {
        Long id = 1L;
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        assertTrue(turnoBuscado.isPresent());
    }

    @Test
    @Order(3)
    void actualizarTurno() {
        Domicilio domicilio = new Domicilio("cambiaggi", "9021", "Guaymallen", "Mendoza");
        Paciente paciente = pacienteService.guardarPaciente(new Paciente("Agustin", "Mondati", "4021375", LocalDate.of(1997, 3, 22), "agustinmondati@gmail.com", domicilio));
        Odontologo odontologo = odontologoService.guardarOdontologo(new Odontologo("Agustin", "Mondati", "MN-123"));
        Turno turno = new Turno(LocalDate.of(2025, 03, 22), paciente, odontologo);
        turnoService.actualizarTurno(turno);
        assertEquals(LocalDate.of(2025, 03, 22), turnoService.buscarTurno(2L).get().getFecha());
    }

    @Test
    @Order(4)
    void buscarTodosLosTurnos() {
        List<TurnoDTO> listaTurnos = turnoService.listarTurnos();
        assertEquals(2, listaTurnos.size());
    }

    @Test
    @Order(5)
    void eliminarTurno() {
        Long id = 2L;
        turnoService.eliminarTurno(id);
        Optional<TurnoDTO> turnoEliminado = turnoService.buscarTurno(id);
        assertFalse(turnoEliminado.isPresent());
    }

}