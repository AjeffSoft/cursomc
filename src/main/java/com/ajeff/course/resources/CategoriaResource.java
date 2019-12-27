package com.ajeff.course.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajeff.course.domain.Categoria;
import com.ajeff.course.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {


	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1L, "Informática");
		Categoria cat2 = new Categoria(2L, "Escritório");
		List<Categoria> list = new ArrayList<>();
		list.add(cat1);
		list.add(cat2);
		return list;
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Long id){
		Categoria cat = service.buscar(id);
		return ResponseEntity.ok().body(cat);
	}
	
}
