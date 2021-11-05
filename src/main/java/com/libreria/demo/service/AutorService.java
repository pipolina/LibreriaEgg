package com.libreria.demo.service;

import com.libreria.demo.entidades.Autor;
import com.libreria.demo.repositorios.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    //List<Autor> listaAutores = new ArrayList();
    @Transactional
    public Autor crearAutor(String autor) {
        Autor nuevoAutor = new Autor();

        nuevoAutor.setNombre(autor);
        nuevoAutor.setAlta(true);

        autorRepository.save(nuevoAutor);
        //listaAutores.add(nuevoAutor);

        return nuevoAutor;
    }

    @Transactional(readOnly = true)
    public Autor buscarAutorPorNombre(String autor) {
        return autorRepository.buscarAutorPorNombre(autor);
    }

}
