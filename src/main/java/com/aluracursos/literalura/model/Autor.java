package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Autor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  // private String name;
  // private Integer birth_year;
  // private Integer death_year;
  @JsonAlias("name") String nombre;
  @JsonAlias("birth_year") String fechaNac;
  @JsonAlias("death_year") String fechaDef;
  @ManyToOne
  private Libro libro;

  public Autor() {}   // Constructor predeterminado requerido por JPA

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Libro getLibro() {
    return libro;
  }
  public void setLibro(Libro libro) {
    this.libro = libro;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getFechaNac() {
    return fechaNac;
  }

  public void setFechaNac(String fechaNac) {
    this.fechaNac = fechaNac;
  }

  public String getFechaDef() {
    return fechaDef;
  }

  public void setFechaDef(String fechaDef) {
    this.fechaDef = fechaDef;
  }
  
  // public String getName() {
  //   return name;
  // }
  // public void setName(String name) {
  //   this.name = name;
  // }
  // public Integer getBirth_year() {
  //   return birth_year;
  // }
  // public void setBirth_year(Integer birth_year) {
  //   this.birth_year = birth_year;
  // }
  // public Integer getDeath_year() {
  //   return death_year;
  // }
  // public void setDeath_year(Integer death_year) {
  //   this.death_year = death_year;
  // }
  
}
