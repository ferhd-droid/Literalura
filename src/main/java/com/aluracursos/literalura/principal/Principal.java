package com.aluracursos.literalura.principal;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Datos;
import com.aluracursos.literalura.model.DatosLibro;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

public class Principal {
  private final String URL_BASE = "https://gutendex.com/books/";
  private Scanner teclado = new Scanner(System.in);
  private ConsumoAPI consumoAPI = new ConsumoAPI();
  private ConvierteDatos conversor = new ConvierteDatos();
  private List<Libro> libros;
  private LibroRepository repository;
  // private List<Libro> datosLibro = new ArrayList<>();
  private List<Autor> autores;
  private AutorRepository autorRepository;

  public Principal(LibroRepository repository, AutorRepository autorRepository) {
    this.repository = repository;
    this.autorRepository = autorRepository;
  }

  public void muestraMenu() {
    var opcion = -1;
    var menu = """
              1 - Buscar libro por título
              2 - Listar libros registrados
              3 - Listar autores registrados
              4 - Listar autores vivos en un determinado año
              5 - Listar libros por idioma
              
              0 - Salir
              """;
    while (opcion != 0) {
        System.out.println(menu);
        opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion) {
            case 1:
                buscarLibroPorTitulo();
                break;
            case 2:
                listarLibrosRegistrados();
                break;
            case 3:
                listarAutoresRegistrados();
                break;
            case 4:
                autoresVivosEnDeterminadoAnio();
                break;
            case 5:
                librosPorIdioma();
                break;
            case 0:
                System.out.println("Cerrando la aplicación...");
                break;
            default:
                System.out.println("Opción inválida");
        }
    }
  }

  private void buscarLibroPorTitulo() {
    // Búsqueda de libros por nombre
    System.out.println("Ingrese el nombre del libro que desea buscar");
    var tituloLibro = teclado.nextLine();
    var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + 
                                  tituloLibro.replaceAll("\s+", "+"));
    var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
    Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                          .filter(l -> l.titulo()
                                        .toUpperCase()
                                        .contains(tituloLibro.toUpperCase())
                                  )
                          .findFirst();
    if(libroBuscado.isPresent()) {
      Libro libro = new Libro(libroBuscado.get());
      libro.setAutores(libroBuscado.get().autores());
      var libroEnBD = repository.findByTitulo(libro.getTitulo());
      if (libroEnBD != null) {
        System.out.println("Este libro ya está registrado\n");
      } else {
        repository.save(libro);
        System.out.println("Libro encontrado\n");
        System.out.println(libro);
      }
    } else {
      System.out.println("Libro no encontrado\n");
    }

  }
  
  private void listarLibrosRegistrados() {
    libros = repository.findAll();
    if(libros.isEmpty()) {
      System.out.println("No hay libros registrados");
    } else {
      libros.stream().forEach(System.out::println);
      System.out.println("\n");
    }
  }

  private void listarAutoresRegistrados() {
    autores = autorRepository.findAll();
    if (autores.isEmpty()) {
      System.out.println("No hay autores registrados\n");
    } else {
      autores.stream().forEach(System.out::println);
      System.out.println("\n");
    }
  }

  private void autoresVivosEnDeterminadoAnio() {
    System.out.println("Ingrese el año donde loa autores están vivos");
    var anio = teclado.nextLine();
    autores = autorRepository.findByFechaNacLessThanEqualAndFechaDefGreaterThanEqual(anio, anio);
    if (autores.isEmpty()) {
      System.out.println("No hay autores vivos en éste año\n");
    } else {
      autores.stream().forEach(System.out::println);
      System.out.println("\n");
    }
  }

  private void librosPorIdioma() {
    System.out.println("Ingrese las dos letras del idioma para buscar los libros\n");
    var menu = """
              es - Español
              en - Inglés
              fr - Francés
              pt - Portugués
              """;
    System.out.println(menu);
    var idioma = teclado.nextLine();
    libros = repository.findByIdiomasContaining(idioma);
    if (libros.isEmpty()) {
      System.out.println("No hay libros en éste idioma\n");
    } else {
      libros.stream().forEach(System.out::println);
      System.out.println("\n");
    }
  }
}
