package com.agendamento.matheusnunes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agendamento.matheusnunes.models.Reserva;

@Repository
public interface RepositoryReserva extends JpaRepository<Reserva, Integer>{

}