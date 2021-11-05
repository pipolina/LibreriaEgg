package com.libreria.demo.service;

import com.libreria.demo.entidades.Autor;
import com.libreria.demo.entidades.Editorial;
import com.libreria.demo.entidades.Libro;
import com.libreria.demo.excepciones.MiExcepcion;
import com.libreria.demo.repositorios.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;
    
    private final AutorService as = new AutorService();
    private final EditorialService es = new EditorialService();

    @Transactional
    public void guardarLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, String autor, String editorial) throws MiExcepcion {

        Libro nuevoLibro = new Libro();
        
        validar(isbn,titulo,autor,editorial);

        nuevoLibro.setIsbn(isbn);
        nuevoLibro.setTitulo(titulo);
        nuevoLibro.setAnio(anio);
        nuevoLibro.setEjemplares(ejemplares);

        //tengo q crear los metodos en autor y editoral para buscarlos por nombre y ver si existen antes de crearlos
        
        if(as.buscarAutorPorNombre(autor)== null){
            nuevoLibro.setAutor(as.crearAutor(autor));
        } else if(as.buscarAutorPorNombre(autor)!= null){
            Autor autorViejo = as.buscarAutorPorNombre(autor);
            nuevoLibro.setAutor(autorViejo);
        }
        
        if(es.buscarEditorialPorNombre(editorial)==null){
            nuevoLibro.setEditorial(es.crearEditorial(editorial));
        }else if(es.buscarEditorialPorNombre(editorial)!= null){
            Editorial editorialVieja = es.buscarEditorialPorNombre(editorial);
            nuevoLibro.setEditorial(editorialVieja);
        }
        
        if(ejemplares>0){
            nuevoLibro.setAlta(true);
        }
        
        libroRepository.save(nuevoLibro);
    }
    
    private void validar(Long isbn, String titulo, String autor, String editorial) throws MiExcepcion{
        
        if(isbn == null){
            throw new MiExcepcion("El numero de ISBN no puede ser nulo");
        }
        
        if(titulo == null || titulo.isEmpty() || titulo.contains("  ")){
            throw new MiExcepcion("El titulo del libro no puede ser nulo");
        }
        
        if(autor == null || autor.isEmpty() || autor.contains("  ")){
            throw new MiExcepcion("El nombre del autor no puede ser nulo");
        }
        
        if(editorial == null || editorial.isEmpty() || editorial.contains("  ")){
            throw new MiExcepcion("La editorial no puede ser nula");
        }
        
    }

}
