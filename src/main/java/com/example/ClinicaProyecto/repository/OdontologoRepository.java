package com.example.ClinicaProyecto.repository;


import com.example.ClinicaProyecto.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    // Método de consulta personalizado que busca un odontólogo por su matrícula en la base de datos y devuelve un Optional que puede contener el odontólogo encontrado o estar vacío.
    Optional<Odontologo> findByMatricula(String matricula);
}
