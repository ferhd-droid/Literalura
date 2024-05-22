package com.aluracursos.literalura.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Libro {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String titulo;
  private List<DatosAutor> autores;
  private List<String> idiomas;
  private Integer numDescargas;

  public Libro() {}   // Constructor predeterminado requerido por JPA

  public Libro(DatosLibro datosLibro) {
    this.titulo = datosLibro.titulo();
    this.autores = datosLibro.autores();
    this.idiomas = datosLibro.idiomas();
    this.numDescargas = datosLibro.numDescargas();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public List<DatosAutor> getAutores() {
    return autores;
  }

  public void setAutores(List<DatosAutor> autores) {
    this.autores = autores;
  }

  public List<String> getIdiomas() {
    return idiomas;
  }

  public void setIdiomas(List<String> idiomas) {
    this.idiomas = idiomas;
  }

  public Integer getNumDescargas() {
    return numDescargas;
  }

  public void setNumDescargas(Integer numDescargas) {
    this.numDescargas = numDescargas;
  }
}
