package com.aluracursos.literalura.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aluracursos.literalura.model.Libro;
import java.util.List;


public interface LibroRepository extends JpaRepository<Libro, Long> {
  List<Libro> findByIdiomasContaining(String idioma);
  Libro findByTitulo(String titulo);
}
