package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
	Page<Tarea> findByUsuarioId(Integer usuarioId, Pageable pageable);
}
