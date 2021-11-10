package com.libreria.demo.service;

import com.libreria.demo.entidades.Autor;
import com.libreria.demo.excepciones.MiExcepcion;
import com.libreria.demo.repositorios.AutorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public Autor crearAutor(String autor) {
        Autor nuevoAutor = new Autor();

        nuevoAutor.setNombre(autor);
        nuevoAutor.setAlta(true);

        return autorRepository.save(nuevoAutor);
    }

    @Transactional(readOnly = true)
    public Autor buscarAutorPorNombre(String autor) {
        return autorRepository.buscarAutorPorNombre(autor);
    }

    public Autor buscarAutorPorId(String id) throws MiExcepcion {

        Optional<Autor> respuesta = autorRepository.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            return autor;
        } else {
            throw new MiExcepcion("No se encuentra el autor buscado");
        }

    }

    @Transactional
    public Autor alta(String id) throws MiExcepcion {
        Optional<Autor> respuesta = autorRepository.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(true);
            return autorRepository.save(autor);
        } else {
            throw new MiExcepcion("No Existe este autor.");
        }
    }

    @Transactional
    public Autor baja(String id) throws MiExcepcion {
        Optional<Autor> respuesta = autorRepository.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(false);
            return autorRepository.save(autor);
        } else {
            throw new MiExcepcion("No Existe este autor.");
        }
    }

    @Transactional
    public Autor modificar(String id, String nombreAutor) throws MiExcepcion {
        Optional<Autor> respuesta = autorRepository.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombreAutor);
            return autorRepository.save(autor);

        } else {
            throw new MiExcepcion("No se encontro el autor que se desea modificar");
        }
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Autor> listarActivos() {
        return autorRepository.buscarActivos();
    }

}
