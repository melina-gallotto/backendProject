package com.example.ClinicaProyecto.service;


import com.example.ClinicaProyecto.entity.Paciente;
import com.example.ClinicaProyecto.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    // Método para guardar un nuevo paciente en la base de datos.
    public Paciente guardarPaciente(Paciente paciente) {
        // Guarda un paciente en la base de datos utilizando el repositorio
        return pacienteRepository.save(paciente);
    }

    // Método para buscar un paciente en la base de datos por su ID.
    public Optional<Paciente> buscarPacientePorID(Long id) {
        // Busca un paciente en la base de datos por su ID utilizando el repositorio
        return pacienteRepository.findById(id);
    }

    // Método para eliminar un paciente de la base de datos por su ID.
    public void eliminarPaciente(Long id) {
        // Elimina un paciente de la base de datos por su ID utilizando el repositorio
        pacienteRepository.deleteById(id);
    }

    // Método para actualizar un paciente existente en la base de datos.
    public void actualizarPaciente(Paciente paciente) {
        // Actualiza un paciente en la base de datos utilizando el repositorio
        pacienteRepository.save(paciente);
    }

    // Método para obtener una lista de todos los pacientes almacenados en la base de datos.
    public List<Paciente> listarPacientes() {
        // Obtiene una lista de todos los pacientes almacenados en la base de datos utilizando el repositorio
        return pacienteRepository.findAll();
    }

    // Método para buscar un paciente en la base de datos por su email.
    public Optional<Paciente> buscarPacientePorEmail(String email) {
        // Busca un paciente en la base de datos por su email utilizando el repositorio
        return pacienteRepository.findByEmail(email);
    }

}
