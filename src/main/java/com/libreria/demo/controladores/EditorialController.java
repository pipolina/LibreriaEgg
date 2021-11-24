package com.libreria.demo.controladores;

import com.libreria.demo.entidades.Editorial;
import com.libreria.demo.entidades.Libro;
import com.libreria.demo.service.EditorialService;
import com.libreria.demo.service.LibroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editorial")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;
    
    @Autowired
    private LibroService libroService;

    @GetMapping("/lista")
    public String listaEditoriales(ModelMap modelo) {
        List<Editorial> editoriales = editorialService.listarEditoriales();

        modelo.addAttribute("editoriales", editoriales);

        return "list-editoriales";
    }
    
    @GetMapping("/libro/lista/{id}")
    public String listaLibros(ModelMap modelo, @PathVariable String id) {
        List<Libro> libros = libroService.listarLibrosPorEditorial(id);

        modelo.addAttribute("libros", libros);

        return "list-libros";
    }

}
