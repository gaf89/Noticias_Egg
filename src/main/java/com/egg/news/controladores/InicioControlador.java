
package com.egg.news.controladores;

import com.egg.news.entidades.Noticia;
import com.egg.news.servicio.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
public class InicioControlador {
    
    @Autowired
    NoticiaServicio noticiaServicio;
    
    @GetMapping
    public String inicio(ModelMap modelo){
        
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        
        modelo.addAttribute("noticias", noticias);
        
        return "inicio.html";
    }
    
    @GetMapping("/panelAdmin")
    public String panelAdmin(ModelMap modelo){
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        
        modelo.addAttribute("noticias", noticias);
        
        return "panelAdmin.html";
    }
    
  
    
}
