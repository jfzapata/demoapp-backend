package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Tarea;
import com.example.demo.repositories.TareaRepository;
import com.example.demo.exceptions.ResourceNotFoundException;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TareaController {
	@Autowired
	TareaRepository tareaRepository;
	

	@GetMapping("/tareas")
	public List<Tarea> getTareas() {
	    return tareaRepository.findAll();
	}
	
	@GetMapping("/tareas/find-by-user/{id}")
	public List<Tarea> getTareasByUsuarioId(@PathVariable(value = "id") Integer usuarioId) {
	    return tareaRepository.findByUsuarioUsuarioId(usuarioId);
	}
	
	@GetMapping("/tareas/{id}")
	public Tarea getTareaById(@PathVariable(value = "id") Integer tareaId) {
	    return tareaRepository.findById(tareaId)
	            .orElseThrow(() -> new ResourceNotFoundException("Tareas", "id", tareaId));
	}

	@PostMapping("/tareas")
	public Tarea createTarea(@Valid @RequestBody Tarea tarea) {
	    return tareaRepository.save(tarea);
	}


	@PutMapping("/tareas/{id}")
	public Tarea updateTarea(@PathVariable(value = "id") Integer tareaId,
	                                        @Valid @RequestBody Tarea nuevaTarea) {

		Tarea tarea = tareaRepository.findById(tareaId)
	            .orElseThrow(() -> new ResourceNotFoundException("Tareas", "id", tareaId));

		tarea.setFechaEjecucion(nuevaTarea.getFechaEjecucion());
		tarea.setEstado(nuevaTarea.getEstado());
		tarea.setUsuario(nuevaTarea.getUsuario());
		
	    Tarea tareaActualizada = tareaRepository.save(tarea);
	    return tareaActualizada;
	}

	@DeleteMapping("/tareas/{id}")
	public ResponseEntity<?> deleteTarea(@PathVariable(value = "id") Integer tareaId) {
		Tarea tarea = tareaRepository.findById(tareaId)
	            .orElseThrow(() -> new ResourceNotFoundException("Tareas", "id", tareaId));

		tareaRepository.delete(tarea);

	    return ResponseEntity.ok().build();
	}
}
