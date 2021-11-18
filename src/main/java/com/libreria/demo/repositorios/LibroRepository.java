package com.libreria.demo.repositorios;

import com.libreria.demo.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, String> {

    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public Libro buscarLibroPorNombre(@Param("titulo") String titulo);

    @Query("SELECT a from Libro a WHERE a.alta = true ")
    public List<Libro> buscarActivos();
    
    @Query("SELECT l FROM Libro l WHERE l.id = :id")
    public Libro buscarLibroPorId(@Param("id") String id);
    
    @Query("SELECT l FROM Libro l WHERE l.autor.id = :id")
    public List<Libro> buscarLibroPorAutor(@Param("id") String id);

}
