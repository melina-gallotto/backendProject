package com.example.ClinicaProyecto.service;

import com.example.ClinicaProyecto.entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void guardarOdontologo() {
        Odontologo odontologo = new Odontologo("Agustin", "Mondati", "MN-123");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
        assertEquals(1, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    void buscarOdontologo() {
        Long id = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
        assertTrue(odontologoBuscado.isPresent());
    }

    @Test
    @Order(3)
    void actualizarOdontologo() {
        Odontologo odontologoActualizado = new Odontologo(1L, "Agustin", "Rueda", "MN-123");
        odontologoService.actualizarOdontologo(odontologoActualizado);
        assertEquals("Rueda", odontologoService.buscarOdontologoPorId(1L).get().getApellido());
    }

    @Test
    @Order(4)
    void buscarTodosLosOdontologos() {
        List<Odontologo> listaOdontologos = odontologoService.listarOdontologos();
        assertEquals(1, listaOdontologos.size());
    }

    @Test
    @Order(5)
    void buscarOdontologoPorMatricula() {
        String matricula = "MN-123";
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorMatricula(matricula);
        assertTrue(odontologoBuscado.isPresent());
    }

    @Test
    @Order(6)
    void eliminarOdontologo() {
        Long id = 1L;
        odontologoService.eliminarOdontologo(id);
        Optional<Odontologo> odontologoEliminado = odontologoService.buscarOdontologoPorId(id);
        assertFalse(odontologoEliminado.isPresent());
    }

}