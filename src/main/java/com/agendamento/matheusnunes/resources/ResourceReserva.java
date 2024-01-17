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

import com.agendamento.matheusnunes.models.Reserva;
import com.agendamento.matheusnunes.services.ServiceReserva;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/reservas")
@SecurityRequirement(name = "security_auth")
public class ResourceReserva {
	
	
	@Autowired
	private ServiceReserva serviceReserva;
	
	@GetMapping("")
	public ResponseEntity<List<Reserva>> listAll(){
		return new ResponseEntity<List<Reserva>>(serviceReserva.listAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Reserva> get(@PathVariable("id") int id){
		Optional<Reserva> reserva = serviceReserva.getOne(id);
		
		if(reserva.isPresent()) {
			return new ResponseEntity<Reserva>(reserva.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Reserva> add(@RequestBody Reserva reserva){
		serviceReserva.salvar(reserva);
		return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Reserva> edit(@RequestBody Reserva reserva, @PathVariable("id") int id) {
		Optional<Reserva> opReserva = serviceReserva.getOne(id);
		
		if(opReserva.isPresent()) {
			reserva.setId(id);
			serviceReserva.salvar(reserva);
			return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
		}
		
		return new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{id}")
	  public ResponseEntity<Reserva> delete(@PathVariable("id") int id) {
		  Optional<Reserva> opReserva = serviceReserva.getOne(id);
		  
		  if(opReserva.isPresent()) {
			  serviceReserva.excluir(opReserva.get());
			  return new ResponseEntity<>(null, HttpStatus.OK);
		  }
		  
		  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  
	  }
	
}
