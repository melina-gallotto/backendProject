package com.example.ClinicaProyecto.controller;


import com.example.ClinicaProyecto.entity.Odontologo;
import com.example.ClinicaProyecto.exception.ResoucerNotFoundException;
import com.example.ClinicaProyecto.service.OdontologoService;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    private static final Logger LOGGER = Logger.getLogger(OdontologoController.class);

    // Método para listar todos los odontólogos. Retorna una lista de objetos Odontologo.
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos() {
        LOGGER.info("Listando todos los odontologos");
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    // Método para buscar un odontólogo por su ID. Retorna el odontólogo encontrado o una respuesta 404 si no se encuentra.
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable("id") Long id) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
        if (odontologoBuscado.isPresent()) {
            LOGGER.info("Buscando odontologo por ID con exito");
            return ResponseEntity.ok(odontologoBuscado.get());
        } else {
            LOGGER.info("No se encontro odontologo por ID");
            return ResponseEntity.notFound().build();
        }
    }

    // Método para buscar un odontólogo por su matrícula. Retorna el odontólogo encontrado o una respuesta 404 si no se encuentra.
    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Odontologo> buscarOdontolodoPorMatricula(@PathVariable String matricula) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorMatricula(matricula);
        if (odontologoBuscado.isPresent()) {
            LOGGER.info("Buscando odontologo por matricula con exito");
            return ResponseEntity.ok(odontologoBuscado.get());
        } else {
            LOGGER.info("No se encontro odontologo por matricula");
            return ResponseEntity.notFound().build();
        }
    }

    // Método para registrar un nuevo odon tólogo. Recibe un objeto Odontologo en el cuerpo de la solicitud y retorna el odontólogo registrado.
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologos(@RequestBody Odontologo odontologo) {
        LOGGER.info("Registrando nuevo odontologo");
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    // Método para actualizar un odontólogo existente. Recibe un ID y un objeto Odontologo en el cuerpo de la solicitud. Retorna un mensaje de éxito si se actualiza correctamente o un mensaje de error si no se encuentra el odontólogo.
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            LOGGER.info("Se actualizo correctmente el odontologo");
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo actualizado " + odontologo.getNombre());
        } else {
            LOGGER.info("No se pudo actualizar odontologo por id");
            return ResponseEntity.badRequest().body("Odontologo no encontrado " + odontologo.getNombre());
        }
    }

    // Método para eliminar un odontólogo por su ID. Realiza la eliminación y retorna un mensaje de éxito.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResoucerNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
        if (odontologoBuscado.isPresent()) {
            odontologoService.eliminarOdontologo(id);
            LOGGER.info("Se elimino correctamente el odontologo");
            return ResponseEntity.ok("Odontologo eliminado con éxito: " + id);
        } else {
            LOGGER.info("No se pudo eliminar el odontologo por id");
            throw new ResoucerNotFoundException("No existe el id asociado a un odontologo en la base de datos " + id);
        }

    }

}