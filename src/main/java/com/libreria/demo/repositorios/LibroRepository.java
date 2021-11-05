package com.libreria.demo.repositorios;

import com.libreria.demo.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, String>{

    @Query("SELECT l FROM Libro l WHERE l.nombre = :nombre")
    public Libro buscarLibroPorNombre(@Param("nombre") String nombre);
    
}
