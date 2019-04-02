package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.exceptions.ResourceNotFoundException;

import javax.validation.Valid;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
    UsuarioRepository usuarioRepository;
	

	@GetMapping("/usuarios")
	public List<Usuario> getUsuarios() {
	    return usuarioRepository.findAll();
	}
	
	@GetMapping("/usuarios/{id}")
	public Usuario getUsuarioById(@PathVariable(value = "id") Integer usuarioId) {
	    return usuarioRepository.findById(usuarioId)
	            .orElseThrow(() -> new ResourceNotFoundException("Usuarios", "id", usuarioId));
	}

	@PostMapping("/usuarios")
	public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
		usuario.setFechaCreacion(new Date());
	    return usuarioRepository.save(usuario);
	}


	@PutMapping("/usuarios/{id}")
	public Usuario updateUsuario(@PathVariable(value = "id") Integer usuarioId,
	                                        @Valid @RequestBody Usuario nuevoUsuario) {

		Usuario usuario = usuarioRepository.findById(usuarioId)
	            .orElseThrow(() -> new ResourceNotFoundException("Usuarios", "id", usuarioId));

		usuario.setNombres(nuevoUsuario.getNombres());
		usuario.setApellidos(nuevoUsuario.getApellidos());
		usuario.setEstado(nuevoUsuario.getEstado());

	    Usuario usuarioActualizado = usuarioRepository.save(usuario);
	    return usuarioActualizado;
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Integer usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
	            .orElseThrow(() -> new ResourceNotFoundException("Usuarios", "id", usuarioId));

		usuarioRepository.delete(usuario);

	    return ResponseEntity.ok().build();
	}
}
