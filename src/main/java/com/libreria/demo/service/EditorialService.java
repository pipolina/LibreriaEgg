package com.libreria.demo.service;

import com.libreria.demo.entidades.Editorial;
import com.libreria.demo.repositorios.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;
    
    @Transactional
    public Editorial crearEditorial(String editorial){
        Editorial nuevaEditorial = new Editorial();
        
        nuevaEditorial.setNombre(editorial);
        nuevaEditorial.setAlta(true);
        
        editorialRepository.save(nuevaEditorial);
        
        return nuevaEditorial;
    }
    
    @Transactional(readOnly=true)
    public Editorial buscarEditorialPorNombre(String autor){
        return editorialRepository.buscarEditorialPorNombre(autor);
    }
    
}
