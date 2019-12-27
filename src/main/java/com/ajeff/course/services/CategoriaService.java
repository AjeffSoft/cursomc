package com.ajeff.course.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajeff.course.domain.Categoria;
import com.ajeff.course.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(Long id) {
		Optional<Categoria> cat = repository.findById(id);
		return cat.orElse(null);
	}
}
