package com.libreria.demo.service;

import com.libreria.demo.entidades.Editorial;
import com.libreria.demo.excepciones.MiExcepcion;
import com.libreria.demo.repositorios.EditorialRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Transactional
    public Editorial crearEditorial(String editorial) {
        Editorial nuevaEditorial = new Editorial();

        nuevaEditorial.setNombre(editorial);
        nuevaEditorial.setAlta(true);

        return editorialRepository.save(nuevaEditorial);
    }

    @Transactional(readOnly = true)
    public Editorial buscarEditorialPorNombre(String autor) {
        return editorialRepository.buscarEditorialPorNombre(autor);
    }

    @Transactional
    public Editorial buscarEditorialPorId(String id) throws MiExcepcion {

        Optional<Editorial> respuesta = editorialRepository.findById(id);

        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            return editorial;
        } else {
            throw new MiExcepcion("No se encuentra la editorial buscada");
        }

    }

    @Transactional
    public Editorial alta(String id) throws MiExcepcion {
        Optional<Editorial> respuesta = editorialRepository.findById(id);

        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(true);
            return editorialRepository.save(editorial);
        } else {
            throw new MiExcepcion("No Existe este autor.");
        }
    }

    @Transactional
    public Editorial baja(String id) throws MiExcepcion {
        Optional<Editorial> respuesta = editorialRepository.findById(id);

        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(false);
            return editorialRepository.save(editorial);
        } else {
            throw new MiExcepcion("No Existe este autor.");
        }
    }

    @Transactional
    public Editorial modificar(String id, String nombreEditorial) throws MiExcepcion {
        Optional<Editorial> respuesta = editorialRepository.findById(id);

        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombreEditorial);
            return editorialRepository.save(editorial);

        } else {
            throw new MiExcepcion("No se encontro el autor que se desea modificar");
        }
    }

    @Transactional(readOnly = true)
    public List<Editorial> listarEditoriales() {
        return editorialRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Editorial> listarActivos() {
        return editorialRepository.buscarActivos();
    }

}
