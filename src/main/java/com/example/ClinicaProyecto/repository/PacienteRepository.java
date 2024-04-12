package com.example.ClinicaProyecto.repository;


import com.example.ClinicaProyecto.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Método de consulta personalizado que busca un paciente por su email en la base de datos y devuelve un Optional que puede contener el paciente encontrado o estar vacío.
    Optional<Paciente> findByEmail(String email);
}
