package com.example.ClinicaProyecto.service;


import com.example.ClinicaProyecto.dto.TurnoDTO;
import com.example.ClinicaProyecto.entity.Odontologo;
import com.example.ClinicaProyecto.entity.Paciente;
import com.example.ClinicaProyecto.entity.Turno;
import com.example.ClinicaProyecto.repository.OdontologoRepository;
import com.example.ClinicaProyecto.repository.PacienteRepository;
import com.example.ClinicaProyecto.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    // Método para guardar un nuevo turno en la base de datos.
    public TurnoDTO guardarTurno(Turno turno) {
        Turno turnoGuardado=turnoRepository.save(turno);
        return turnoATurnoDTO(turnoGuardado);
    }


    // Método para obtener una lista de todos los turnos almacenados en la base de datos.
    public List<TurnoDTO> listarTurnos() {
        // Obtiene una lista de todos los turnos almacenados en la base de datos utilizando el repositorio
        List<Turno> turnosEncontrados = turnoRepository.findAll();
        List<TurnoDTO> turnoDTOList = new ArrayList<>();
        for (Turno turno : turnosEncontrados) {
            turnoDTOList.add(turnoATurnoDTO(turno));
        }
        return turnoDTOList;
    }

    // Método para eliminar un turno de la base de datos por su ID.
    public void eliminarTurno(Long id) {
        // Elimina un turno de la base de datos utilizando el repositorio y el ID proporcionado
        turnoRepository.deleteById(id);
    }

    // Método para buscar un turno en la base de datos por su ID.
    public Optional<TurnoDTO> buscarTurno(Long id) {
        // Busca un turno en la base de datos utilizando el repositorio y el ID proporcionado
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        } else {
            return Optional.empty();
        }
    }

    // Método para actualizar un turno existente en la base de datos.
    public void actualizarTurno(Turno turno) {
        // Actualiza un turno en la base de datos utilizando el repositorio
        turnoRepository.save(turno);
    }

    //convertir de turno a turnoDTO
    private Turno turnoDTOaTurno(TurnoDTO turnoDTO) {
        Turno turno = new Turno();
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        turno.setOdontologo(odontologoRepository.findById(turnoDTO.getOdontologo_id()).get());
        turno.setPaciente(pacienteRepository.findById(turnoDTO.getPaciente_id()).get());

        return turno;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno) {
        TurnoDTO turnoDTO = new TurnoDTO();

        turnoDTO.setId(turno.getId());
        turnoDTO.setOdontologo_id(turno.getOdontologo().getId());
        turnoDTO.setPaciente_id(turno.getPaciente().getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setNombre_odontologo(turno.getOdontologo().getNombre());
        turnoDTO.setNombre_paciente(turno.getPaciente().getNombre());

        return turnoDTO;
    }
}