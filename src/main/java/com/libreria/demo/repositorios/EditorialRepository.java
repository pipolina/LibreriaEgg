package com.libreria.demo.repositorios;

import com.libreria.demo.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, String> {

    @Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
    public Editorial buscarEditorialPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT a from Editorial a WHERE a.alta = true ")
    public List<Editorial> buscarActivos();
}
