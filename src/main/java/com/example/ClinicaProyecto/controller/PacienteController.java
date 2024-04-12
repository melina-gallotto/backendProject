package com.example.ClinicaProyecto.controller;


import com.example.ClinicaProyecto.entity.Paciente;
import com.example.ClinicaProyecto.exception.BadRequestException;
import com.example.ClinicaProyecto.exception.ResoucerNotFoundException;
import com.example.ClinicaProyecto.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")

public class PacienteController {
    @Autowired
    PacienteService pacienteService;

    private static final Logger LOGGER = Logger.getLogger(PacienteController.class);

    // Método para obtener todos los pacientes. Retorna una lista de objetos Paciente.
    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        LOGGER.info("Buscando lista de pacientes");
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    // Método para buscar un paciente por su ID. Retorna el paciente encontrado o una respuesta 404 si no se encuentra.
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable("id") Long id) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(id);
        if (pacienteBuscado.isPresent()) {
            LOGGER.info("Paciente encontrado");
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            LOGGER.info("Paciente no encontrado");
            throw new BadRequestException("El ID ingresado es incorrecto. No existe paciente asociado al id");
        }
    }

    // Método para buscar un paciente por su correo electrónico. Retorna el paciente encontrado o una respuesta 404 si no se encuentra.
    @GetMapping("/email/{email}")
    public ResponseEntity<Paciente> buscarPacientePorEmail(@PathVariable String email) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorEmail(email);
        if (pacienteBuscado.isPresent()) {
            LOGGER.info("Paciente encontrado");
            return ResponseEntity.ok(pacienteBuscado.get());

        } else {
            LOGGER.info("Paciente  no encontrado");
            return ResponseEntity.notFound().build();
        }
    }

    // Método para registrar un nuevo paciente. Recibe un objeto Paciente en el cuerpo de la solicitud y retorna el paciente registrado
    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        LOGGER.info("Registrando paciente");
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    // Método para actualizar un paciente existente. Recibe un ID y un objeto Paciente en el cuerpo de la solicitud. Retorna un mensaje de éxito si se actualiza correctamente o un mensaje de error si no se encuentra el paciente.
    @PutMapping
    public ResponseEntity<String> actualizarPaciente( @RequestBody Paciente paciente) throws ResoucerNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            LOGGER.info("Actualizando paciente correctamente");
            return ResponseEntity.ok("Paciente actualizado " + paciente.getNombre());
        } else {
            LOGGER.info("No se pudo actualizar paciente");
            throw new ResoucerNotFoundException("Paciente no encontrado: " + paciente.getId() + " " + paciente.getNombre());
        }
    }

    // Método para eliminar un paciente por su ID. Realiza la eliminación y retorna un mensaje de éxito.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResoucerNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(id);
        if (pacienteBuscado.isPresent()) {
            pacienteService.eliminarPaciente(id);
            LOGGER.info("Paciente eliminado con exito");
            return ResponseEntity.ok("Paciente eliminado con éxito: " + id);
        } else {
            LOGGER.info("No se pudo eliminar el paciente por id");
            throw new ResoucerNotFoundException("No existe el id asociado a un paciente en la base de datos " + id);
        }
    }
}

