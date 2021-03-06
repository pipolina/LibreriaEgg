package com.libreria.demo.controladores;

import com.libreria.demo.entidades.Libro;
import com.libreria.demo.excepciones.MiExcepcion;
import com.libreria.demo.service.LibroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/guardar")
    public String formularioLibro() {
        return "form-libro";
    }

    @PostMapping("/guardar")
    public String guardarLibro(ModelMap modelo, @RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam String autor, @RequestParam String editorial) throws MiExcepcion {

        try {
            libroService.guardarLibro(isbn, titulo, anio, ejemplares, autor, editorial);

            modelo.put("exito", "Libro guardado con Éxito");
            return "form-libro";

        } catch (Exception e) {

            modelo.put("error", "Hubo un error al guardar el libro");
            return "form-libro";
        }

    }

    @GetMapping("/lista")
    public String listaLibros(ModelMap modelo) {
        List<Libro> libros = libroService.listarLibros();

        modelo.addAttribute("libros", libros);

        return "list-libros";
    }

    @GetMapping("/alta/{id}")
    public String altaLibro(@PathVariable String id) throws MiExcepcion {

        try {
            libroService.alta(id);

            return "redirect:/libro/lista";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    @GetMapping("/baja/{id}")
    public String bajaLibro(@PathVariable String id) throws MiExcepcion {

        try {
            libroService.baja(id);

            return "redirect:/libro/lista";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    @GetMapping("/modificar/{id}")
    public String modificarLibro(@PathVariable String id, ModelMap modelo) {
        Libro libro = libroService.buscarLibroPorId(id);
        modelo.addAttribute("libro", libro);
        return "modif-libro";
    }

    @PostMapping("/modificar")
    public String modificarLibro(ModelMap modelo, String id, @RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam String autor, @RequestParam String editorial) throws MiExcepcion {

        try {
            libroService.modificarLibro(id, isbn, titulo, anio, ejemplares, autor, editorial);

            List<Libro> libros = libroService.listarLibros();
            modelo.addAttribute("libros", libros);

            modelo.put("exito", "Libro modificado con Éxito");
            return "list-libros";

        } catch (MiExcepcion e) {

            List<Libro> libros = libroService.listarLibros();
            modelo.addAttribute("libros", libros);

            modelo.put("error", "Hubo un error al modificar el libro");
            return "list-libros";
        }

//        try {
//            libroService.modificarLibro(id, isbn, titulo, anio, ejemplares, autor, editorial);
//
//            Libro libro = libroService.buscarLibroPorId(id);
//            modelo.addAttribute("libro", libro);
//            modelo.put("exito", "Libro modificado con Éxito");
//            return "modif-libro";
//
//        } catch (Exception e) {
//
//            Libro libro = libroService.buscarLibroPorId(id);
//            modelo.addAttribute("libro", libro);
//            modelo.put("error", "Hubo un error al modificar el libro");
//            return "modif-libro";
//
//    }
    }

}
