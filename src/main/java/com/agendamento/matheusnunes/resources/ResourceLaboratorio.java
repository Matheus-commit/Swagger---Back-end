package com.agendamento.matheusnunes.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendamento.matheusnunes.models.Laboratorio;
import com.agendamento.matheusnunes.services.ServiceLaboratorio;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/laboratorios")
@SecurityRequirement(name = "security_auth")
public class ResourceLaboratorio {
	
	@Autowired
	private ServiceLaboratorio serviceLaboratorio;
	
	@GetMapping("")
	public ResponseEntity<List<Laboratorio>> listAll(){
		return new ResponseEntity<List<Laboratorio>>(serviceLaboratorio.listAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Laboratorio> get(@PathVariable("id") int id){
		Optional<Laboratorio> laboratorio = serviceLaboratorio.getOne(id);
		
		if(laboratorio.isPresent()) {
			return new ResponseEntity<Laboratorio>(laboratorio.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Laboratorio>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Laboratorio> add(@RequestBody Laboratorio laboratorio){
		serviceLaboratorio.salvar(laboratorio);
		return new ResponseEntity<Laboratorio>(laboratorio, HttpStatus.OK);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Laboratorio> edit(@RequestBody Laboratorio laboratorio, @PathVariable("id") int id) {
		Optional<Laboratorio> opLaboratorio = serviceLaboratorio.getOne(id);
		
		if(opLaboratorio.isPresent()) {
			laboratorio.setId(id);
			serviceLaboratorio.salvar(laboratorio);
			return new ResponseEntity<Laboratorio>(laboratorio, HttpStatus.OK);
		}
		
		return new ResponseEntity<Laboratorio>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{id}")
	  public ResponseEntity<Laboratorio> delete(@PathVariable("id") int id) {
		  Optional<Laboratorio> opLaboratorio = serviceLaboratorio.getOne(id);
		  
		  if(opLaboratorio.isPresent()) {
			  serviceLaboratorio.excluir(opLaboratorio.get());
			  return new ResponseEntity<>(null, HttpStatus.OK);
		  }
		  
		  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  
	  }
	
}
