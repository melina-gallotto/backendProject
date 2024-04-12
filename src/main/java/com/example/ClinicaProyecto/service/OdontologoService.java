
package com.example.ClinicaProyecto.service;


import com.example.ClinicaProyecto.entity.Odontologo;
import com.example.ClinicaProyecto.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;

    // Método para guardar un nuevo odontólogo en la base de datos.
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        // Guarda un odontólogo en la base de datos utilizando el repositorio
        return odontologoRepository.save(odontologo);
    }

    // Método para eliminar un odontólogo de la base de datos por su ID.
    public void eliminarOdontologo(Long id) {
        // Elimina un odontólogo de la base de datos por su ID utilizando el repositorio
        odontologoRepository.deleteById(id);
    }

    // Método para actualizar un odontólogo existente en la base de datos.
    public void actualizarOdontologo(Odontologo odontologo) {
        // Actualiza un odontólogo en la base de datos utilizando el repositorio
        odontologoRepository.save(odontologo);
    }

    // Busca un odontólogo en la base de datos por su matrícula utilizando el repositorio
    public Optional<Odontologo> buscarOdontologoPorMatricula(String matricula) {
        // Método para buscar un odontólogo en la base de datos por su matrícula.
        return odontologoRepository.findByMatricula(matricula);
    }

    // Método para buscar un odontólogo en la base de datos por su ID.
    public Optional<Odontologo> buscarOdontologoPorId(Long id) {
        // Busca un odontólogo en la base de datos por su ID utilizando el repositorio
        return odontologoRepository.findById(id);
    }

    // Método para obtener una lista de todos los odontólogos almacenados en la base de datos.
    public List<Odontologo> listarOdontologos() {
        // Obtiene una lista de todos los odontólogos almacenados en la base de datos utilizando el repositorio
        return odontologoRepository.findAll();
    }

}


