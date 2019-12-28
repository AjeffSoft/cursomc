package com.ajeff.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajeff.course.domain.Estado;
import com.ajeff.course.repositories.EstadoRepository;
import com.ajeff.course.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	
	@Autowired
	private EstadoRepository repository;
	
	public Estado buscar(Long id) {
		Optional<Estado> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Estado.class.getName()));
	}
	
	public List<Estado> lista(){
		return repository.findAll();
	}
	
	
}
