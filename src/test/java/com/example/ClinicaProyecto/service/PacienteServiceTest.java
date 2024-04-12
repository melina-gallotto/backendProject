package com.example.ClinicaProyecto.service;

import com.example.ClinicaProyecto.entity.Domicilio;
import com.example.ClinicaProyecto.entity.Paciente;
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


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void guardarPaciente() {
        Domicilio domicilio = new Domicilio("cambiaggi", "9021", "Guaymallen", "Mendoza");
        Paciente paciente = new Paciente("Agustin", "Mondati", "40221375", LocalDate.of(1997, 3, 22), "agustinmondati@gmail.com", domicilio);
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
        assertEquals(1, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    void buscarPaciente() {
        Long id = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(id);
        assertTrue(pacienteBuscado.isPresent());
    }

    @Test
    @Order(3)
    void actualizarPaciente() {
        Domicilio domicilio = new Domicilio("cambiaggi", "9021", "Guaymallen", "Mendoza");
        Paciente pacienteActualizado = new Paciente(1L,"Agustin", "Mon", "40221375", LocalDate.of(1997, 3, 22), "agustinmondati@gmail.com", domicilio);
        pacienteService.actualizarPaciente(pacienteActualizado);
        assertEquals("Mon", pacienteService.buscarPacientePorID(1L).get().getApellido());
    }

    @Test
    @Order(4)
    void buscarTodosLosPacientes() {
        List<Paciente> listaPacientes = pacienteService.listarPacientes();
        assertEquals(1, listaPacientes.size());
    }

    @Test
    @Order(5)
    void buscarPacientePorEmail() {
        String email = "agustinmondati@gmail.com";
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorEmail(email);
        assertTrue(pacienteBuscado.isPresent());
    }

    @Test
    @Order(6)
    void eliminarPaciente() {
        Long id = 1L;
        pacienteService.eliminarPaciente(id);
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPacientePorID(id);
        assertFalse(pacienteEliminado.isPresent());
    }
}