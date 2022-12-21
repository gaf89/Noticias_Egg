
package com.egg.news.servicio;

import com.egg.news.entidades.Noticia;
import com.egg.news.excepciones.MiException;
import com.egg.news.repositorio.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicio {
    
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    
    
    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MiException{
    
        validar(titulo, cuerpo);
        
        Noticia noticia = new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        
        noticia.setAlta(new Date());
     
        noticiaRepositorio.save(noticia);
    }
    
    @Transactional
    public void modificarNoticia(String id, String titulo, String cuerpo) throws MiException{
        
        validar(titulo, cuerpo);
        
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            Noticia noticia = respuesta.get();
            
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            
            noticiaRepositorio.save(noticia);
        }
    }
    
    @Transactional
    public void eliminarNoticia(String id){
                      
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            Noticia noticia = respuesta.get();
            
            noticiaRepositorio.delete(noticia);
        }
    }
    
    public List<Noticia> listarNoticias(){
        
        List<Noticia> noticias = new ArrayList();
        
        noticias = noticiaRepositorio.findAll();
        
        noticias = noticiaRepositorio.ordenarPorAlta();
        
        return noticias;
    }
    
    private void validar(String titulo, String cuerpo) throws MiException{
        
       if(titulo.isEmpty() || titulo == null){
           throw new MiException("El título no puede estar vacío ni ser nulo");
       }
       
       if(cuerpo.isEmpty() || cuerpo == null){
           throw new MiException("El cuerpo de la noticia no puede estar vacío ni ser nulo");
       }
    }
    
    public Noticia getOne(String id){
        return noticiaRepositorio.getOne(id);
    }
}
