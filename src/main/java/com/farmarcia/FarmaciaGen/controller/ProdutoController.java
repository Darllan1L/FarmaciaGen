package com.farmarcia.FarmaciaGen.controller;

/*
 * @author Guilherme Barbosa Rodrigues
 * @since 24/01/2022
 * @version 0.01
 */

import java.util.List;

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
import org.springframework.web.server.ResponseStatusException;

import com.farmarcia.FarmaciaGen.Model.Produto;
import com.farmarcia.FarmaciaGen.Repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	@GetMapping
	public ResponseEntity<List<Produto>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/produto/{id}")
	public ResponseEntity<Produto> findByNome(@PathVariable(value = "id") long id) {
		return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp)).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "ID não encontrado");
		});
	}

	@GetMapping("/produto/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping("/save")
	public ResponseEntity<Produto> saveNome(@RequestBody Produto newProduto) {
		return ResponseEntity.status(201).body(repository.save(newProduto));
	}

	@PutMapping("/update")
	public ResponseEntity<Produto> updateNome(@RequestBody Produto nome) {
		return ResponseEntity.status(200).body(repository.save(nome));
	}

	@DeleteMapping("/id")
	public void deleteNome(@PathVariable long id) {
		repository.deleteById(id);
	}

}
