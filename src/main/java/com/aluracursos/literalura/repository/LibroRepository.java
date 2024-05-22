package com.aluracursos.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aluracursos.literalura.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
