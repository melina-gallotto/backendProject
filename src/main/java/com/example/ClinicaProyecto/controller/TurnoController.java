package com.example.ClinicaProyecto.controller;


import com.example.ClinicaProyecto.dto.TurnoDTO;
import com.example.ClinicaProyecto.entity.Odontologo;
import com.example.ClinicaProyecto.entity.Paciente;
import com.example.ClinicaProyecto.entity.Turno;
import com.example.ClinicaProyecto.exception.BadRequestException;
import com.example.ClinicaProyecto.exception.ResoucerNotFoundException;
import com.example.ClinicaProyecto.service.OdontologoService;
import com.example.ClinicaProyecto.service.PacienteService;
import com.example.ClinicaProyecto.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    private static final Logger LOGGER = Logger.getLogger(TurnoController.class);

    // Método para guardar un nuevo turno. Verifica si existen el odontólogo y el paciente asociados antes de guardar el turno.
    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody Turno turno) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            LOGGER.info("No se pudo registrar el turno");
            throw new BadRequestException("No existe odontologo/paciente asociado al turno a guardar");
        }
    }


    // Método para listar todos los turnos.
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos() {
        LOGGER.info("Listando todos los turnos");
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    // Método para buscar un turno por su ID.
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurnoPorId(@PathVariable Long id) {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        if (turnoBuscado.isPresent()) {
            LOGGER.info("Buscando turno por id con exito");
            return ResponseEntity.ok(turnoBuscado.get());
        } else {
            LOGGER.info("No se pudo obtener el turno");
            return ResponseEntity.notFound().build();
        }
    }

    // Método para eliminar un turno por su ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarturno(@PathVariable Long id) throws ResoucerNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        if (turnoBuscado.isPresent()) {
            turnoService.eliminarTurno(id);
            LOGGER.info("Eliminando turno con exito");
            return ResponseEntity.ok("Turno eliminado con éxito: " + id);
        } else {
            LOGGER.info("No fue posible eliminar el turno");
            throw new ResoucerNotFoundException("No existe el id asociado a un turno en la base de datos " + id);
        }
    }

    // Método para actualizar un turno existente.
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(turno.getId());
        if (turnoBuscado.isPresent()) {
            turnoService.actualizarTurno(turno);
            LOGGER.info("Actualizando turno con éxito");
            return ResponseEntity.ok("Se actualizo el turno correctamente: " + turno.getId());
        } else {
            LOGGER.info("No fue posible actualizar el turno");
            return ResponseEntity.badRequest().body("No se pudo actualizar el turno solicitado.");
        }
    }

}
