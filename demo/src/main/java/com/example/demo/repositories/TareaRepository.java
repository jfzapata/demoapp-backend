package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
	List<Tarea> findByUsuarioUsuarioId(Integer usuarioId);
}
