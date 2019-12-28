package com.ajeff.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajeff.course.domain.Cidade;
import com.ajeff.course.repositories.CidadeRepository;
import com.ajeff.course.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;
	
	public Cidade busca(Long id) {
		Optional<Cidade> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Cidade.class.getName()));
	}
	
	public List<Cidade> lista(){
		return repository.findAll();
	}
}
