package com.aluracursos.literalura.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.aluracursos.literalura.model.Datos;
import com.aluracursos.literalura.model.DatosLibro;
import com.aluracursos.literalura.model.Libro;
// import com.aluracursos.literalura.repository.ILibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

public class Principal {
  private final String URL_BASE = "https://gutendex.com/books/";
  private Scanner teclado = new Scanner(System.in);
  private ConsumoAPI consumoAPI = new ConsumoAPI();
  private ConvierteDatos conversor = new ConvierteDatos();
  // private List<DatosLibro> datosLibro = new ArrayList<>();
  // private ILibroRepository repository;

  // public Principal(ILibroRepository repository) {
  //   this.repository = repository;
  // }

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
                // buscarEpisodioPorSerie();
                break;
            case 3:
                // mostrarSeriesBuscadas();
                break;
            case 4:
                // buscarSeriesPorTitulo();
                break;
            case 5:
                // buscarTop3Series();
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
      // Libro libro = new Libro(libroBuscado.get());
      // repository.save(libro);
      System.out.println("Libro encontrado ");
      System.out.println(libroBuscado.get());
    } else {
      System.out.println("Libro no encontrado");
    }

  }
  
}
