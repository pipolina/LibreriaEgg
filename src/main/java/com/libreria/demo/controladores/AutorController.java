package com.libreria.demo.controladores;

import com.libreria.demo.entidades.Autor;
import com.libreria.demo.service.AutorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;
    
    @GetMapping("/lista")
    public String listaAutores(ModelMap modelo){
        List<Autor> autores = autorService.listarAutores();
        
        modelo.addAttribute("autores", autores);
        
        return "list-autores";
    }
    
}
