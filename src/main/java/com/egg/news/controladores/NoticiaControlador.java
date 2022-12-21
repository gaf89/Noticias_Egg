
package com.egg.news.controladores;

import com.egg.news.entidades.Noticia;
import com.egg.news.excepciones.MiException;
import com.egg.news.servicio.NoticiaServicio;
import java.util.ArrayList;
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
@RequestMapping("/noticia")
public class NoticiaControlador {
    
    @Autowired
    private NoticiaServicio noticiaServicio;
    
    @GetMapping("/guardar")
    public String guardar(){
        
        return "noticia_form";
    }
    
    @PostMapping("/guardado")
    public String guardado(@RequestParam String titulo, String cuerpo, ModelMap modelo){
        try{
            noticiaServicio.crearNoticia(titulo, cuerpo);
                        
            modelo.put("exito", "La Noticia fue cargada exitosamente");
        } catch(MiException ex){
            modelo.put("error", ex.getMessage());
            
            return "noticia_form.html";
        }
        return "index.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        
        modelo.addAttribute("noticias", noticias);
        
        return "noticia_list.html";
    }
    
    @GetMapping("/mostrar/{id}")
    public String mostrarNoticia(@PathVariable String id, ModelMap modelo){
        
        modelo.put("noticia", noticiaServicio.getOne(id));
        
        return "noticia.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        
        modelo.put("noticia", noticiaServicio.getOne(id));
        
        return "noticia_modificar.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String titulo, String cuerpo, ModelMap modelo) {
        try{
            noticiaServicio.modificarNoticia(id, titulo, cuerpo);
            return "redirect:../lista";
        } catch(MiException ex){
            modelo.put("error", ex.getMessage());
            return "noticia_modificar.html";
        }
        
    }
      
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
                
        noticiaServicio.eliminarNoticia(id);
        return "redirect:/inicio/panelAdmin";       
    }
}
