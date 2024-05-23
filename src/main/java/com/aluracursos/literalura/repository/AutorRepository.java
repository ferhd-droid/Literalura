package com.aluracursos.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aluracursos.literalura.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
  List<Autor> findByFechaNacLessThanEqualAndFechaDefGreaterThanEqual(String anio1, String anio2);
}
