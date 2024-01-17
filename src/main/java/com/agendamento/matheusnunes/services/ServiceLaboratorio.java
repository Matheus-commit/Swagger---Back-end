package com.agendamento.matheusnunes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agendamento.matheusnunes.models.Laboratorio;
import com.agendamento.matheusnunes.repositories.RepositoryLaboratorio;


@Service
public class ServiceLaboratorio {
	
	@Autowired
	private RepositoryLaboratorio repositoryLaboratorio;
	
	public void salvar(Laboratorio laboratorio) {
		repositoryLaboratorio.save(laboratorio);
	}
	
	public void excluir(Laboratorio laboratorio) {
		repositoryLaboratorio.delete(laboratorio);
	}
	
	public List<Laboratorio> listAll(){
		return repositoryLaboratorio.findAll();
	}
	
	public Optional<Laboratorio> getOne(int id){
		return repositoryLaboratorio.findById(id);
	}
	
	

}
