package com.farmarcia.FarmaciaGen.controller;
/*
 * @author Darllan Lopes Pinto
 * @since 25/01/2022
 * @version 0.01
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.farmarcia.FarmaciaGen.Model.Categoria;
import com.farmarcia.FarmaciaGen.Repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>>GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Categoria>FindByCategoria(@PathVariable(value = "id")long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.status(200).body(resp))
	 			.orElseGet(() -> {
	 				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "ID não encontrado");
	 			});
	}
	
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity<List<Categoria>> getByCategoria(@PathVariable String categoria){
		return ResponseEntity.ok(repository.findAllByCategoriaContainingIgnoreCase(categoria));
    }
	@PostMapping("/save")
	public ResponseEntity<Categoria>saveCategoria(@RequestBody Categoria newCategoria){
		return ResponseEntity.status(201).body(repository.save(newCategoria));
	}
	@PutMapping("/update")
	public ResponseEntity<Categoria>updateCategoria(@RequestBody Categoria categoria){
		return ResponseEntity.status(200).body(repository.save(categoria));
	}
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable long id) {
		repository.deleteById(id);

	}
}
