package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query("SELECT u FROM Usuario u WHERE (:nombres IS NOT NULL AND :apellidos IS NOT NULL AND u.nombres LIKE CONCAT('%',:nombres,'%') AND u.apellidos LIKE CONCAT('%',:apellidos,'%')) OR (:nombres IS NOT NULL AND :apellidos IS NULL AND u.nombres LIKE CONCAT('%',:nombres,'%')) OR (:apellidos IS NOT NULL AND nombres IS NULL AND u.apellidos LIKE CONCAT('%',:apellidos,'%')) OR (:apellidos IS NULL AND :nombres IS NULL)")
	List<Usuario> getUsuariosByNombresAndApellidos(@Param("nombres") String nombres, @Param("apellidos") String apellidos);
}
