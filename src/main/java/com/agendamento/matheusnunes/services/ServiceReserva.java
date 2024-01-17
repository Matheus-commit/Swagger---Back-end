package com.agendamento.matheusnunes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agendamento.matheusnunes.models.Reserva;
import com.agendamento.matheusnunes.repositories.RepositoryReserva;


@Service
public class ServiceReserva {
	
	@Autowired
	private RepositoryReserva repositoryReserva;
	
	public void salvar(Reserva reserva) {
		repositoryReserva.save(reserva);
	}
	
	public void excluir(Reserva reserva) {
		repositoryReserva.delete(reserva);
	}
	
	public List<Reserva> listAll(){
		return repositoryReserva.findAll();
	}
	
	public Optional<Reserva> getOne(int id){
		return repositoryReserva.findById(id);
	}
}
