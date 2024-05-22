package com.aluracursos.literalura.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Libro {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String titulo;
  @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Autor> autores;
  private String idiomas;
  private Integer numDescargas;

  public Libro() {}   // Constructor predeterminado requerido por JPA

  public Libro(DatosLibro datosLibro) {
    this.titulo = datosLibro.titulo();
    this.autores = datosLibro.autores();
    this.idiomas = String.join(",",datosLibro.idiomas());
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

  public List<Autor> getAutores() {
    return autores;
  }

  public void setAutores(List<Autor> autores) {
    autores.forEach(a -> a.setLibro(this));
    this.autores = autores;
  }

  public String getIdiomas() {
    return idiomas;
  }

  public void setIdiomas(String idiomas) {
    this.idiomas = idiomas;
  }

  public Integer getNumDescargas() {
    return numDescargas;
  }

  public void setNumDescargas(Integer numDescargas) {
    this.numDescargas = numDescargas;
  }
}
