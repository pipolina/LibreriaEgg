package com.libreria.demo.service;

import com.libreria.demo.entidades.Autor;
import com.libreria.demo.entidades.Editorial;
import com.libreria.demo.entidades.Libro;
import com.libreria.demo.excepciones.MiExcepcion;
import com.libreria.demo.repositorios.LibroRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;
    
    @Autowired
    private AutorService as;
    @Autowired
    private EditorialService es;

    @Transactional
    public Libro guardarLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, String autor, String editorial) throws MiExcepcion {
//enel ejemplo de perro este metodo devuelve un perro
        Libro nuevoLibro = new Libro();

        validar(isbn, titulo, autor, editorial);

        nuevoLibro.setIsbn(isbn);
        nuevoLibro.setTitulo(titulo);
        nuevoLibro.setAnio(anio);
        nuevoLibro.setEjemplares(ejemplares);

        //tengo q crear los metodos en autor y editoral para buscarlos por nombre y ver si existen antes de crearlos
        if (as.buscarAutorPorNombre(autor) == null) {
            nuevoLibro.setAutor(as.crearAutor(autor));
        } else if (as.buscarAutorPorNombre(autor) != null) {
            Autor autorViejo = as.buscarAutorPorNombre(autor);
            nuevoLibro.setAutor(autorViejo);
        }

        if (es.buscarEditorialPorNombre(editorial) == null) {
            nuevoLibro.setEditorial(es.crearEditorial(editorial));
        } else if (es.buscarEditorialPorNombre(editorial) != null) {
            Editorial editorialVieja = es.buscarEditorialPorNombre(editorial);
            nuevoLibro.setEditorial(editorialVieja);
        }

        if (ejemplares > 0) {
            nuevoLibro.setAlta(true);
        }

        return libroRepository.save(nuevoLibro);
    }

    @Transactional
    public Libro alta(String id) throws MiExcepcion { //enel ejemplo de perro este metodo devuelve un perro
        Optional<Libro> respuesta = libroRepository.findById(id);

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(true);
            return libroRepository.save(libro);
        } else {
            throw new MiExcepcion("No Existe este libro.");
        }
    }

    @Transactional
    public Libro baja(String id) throws MiExcepcion { //enel ejemplo de perro este metodo devuelve un perro
        Optional<Libro> respuesta = libroRepository.findById(id);

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(false);
            return libroRepository.save(libro);
        } else {
            throw new MiExcepcion("No Existe este libro.");
        }
    }

    @Transactional
    public Libro modificarLibro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, String autor, String editorial) throws MiExcepcion {

        Optional<Libro> respuesta = libroRepository.findById(id);

        validar(isbn, titulo, autor, editorial);

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();

            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);

            if (as.buscarAutorPorNombre(autor) == null) {
                libro.setAutor(as.crearAutor(autor));
            } else if (as.buscarAutorPorNombre(autor) != null) {
                Autor autorViejo = as.buscarAutorPorNombre(autor);
                libro.setAutor(autorViejo);
            }

            if (es.buscarEditorialPorNombre(editorial) == null) {
                libro.setEditorial(es.crearEditorial(editorial));
            } else if (es.buscarEditorialPorNombre(editorial) != null) {
                Editorial editorialVieja = es.buscarEditorialPorNombre(editorial);
                libro.setEditorial(editorialVieja);
            }

            if (ejemplares > 0) {
                libro.setAlta(true);
            } else {
                libro.setAlta(false);
            }
            return libroRepository.save(libro);
        } else {
            throw new MiExcepcion("No se encuentra el libro seleccionado.");
        }

    }

    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Libro> listarActivos() {
        return libroRepository.buscarActivos();
    }

    private void validar(Long isbn, String titulo, String autor, String editorial) throws MiExcepcion {

        if (isbn == null) {
            throw new MiExcepcion("El numero de ISBN no puede ser nulo");
        }

        if (titulo == null || titulo.isEmpty() || titulo.contains("  ")) {
            throw new MiExcepcion("El titulo del libro no puede ser nulo");
        }

        if (autor == null || autor.isEmpty() || autor.contains("  ")) {
            throw new MiExcepcion("El nombre del autor no puede ser nulo");
        }

        if (editorial == null || editorial.isEmpty() || editorial.contains("  ")) {
            throw new MiExcepcion("La editorial no puede ser nula");
        }

    }

}
