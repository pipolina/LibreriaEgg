package com.libreria.demo.controladores;

import com.libreria.demo.entidades.Editorial;
import com.libreria.demo.service.EditorialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editorial")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/lista")
    public String listaEditoriales(ModelMap modelo) {
        List<Editorial> editoriales = editorialService.listarEditoriales();

        modelo.addAttribute("editoriales", editoriales);

        return "list-editoriales";
    }

}
