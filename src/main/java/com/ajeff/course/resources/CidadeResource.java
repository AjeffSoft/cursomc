package com.ajeff.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajeff.course.domain.Cidade;
import com.ajeff.course.services.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService service;
	
	@GetMapping
	public List<Cidade> lista(){
		return service.lista();
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cidade> findOne(@PathVariable Long id){
		Cidade obj = service.busca(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
