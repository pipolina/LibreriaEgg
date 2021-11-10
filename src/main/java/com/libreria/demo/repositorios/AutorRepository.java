package com.libreria.demo.repositorios;

import com.libreria.demo.entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, String> {

    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    public Autor buscarAutorPorNombre(@Param("nombre") String nombre);

    @Query("SELECT a from Autor a WHERE a.alta = true ")
    public List<Autor> buscarActivos();

    @Query("SELECT l FROM Autor l WHERE l.id = :id")
    public Autor buscarLibroPorId(@Param("id") String id);

}
