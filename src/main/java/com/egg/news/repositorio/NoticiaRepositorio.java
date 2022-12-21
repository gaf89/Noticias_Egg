
package com.egg.news.repositorio;

import com.egg.news.entidades.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String>{
    
    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    public Noticia buscarPorTitulo(@Param("titulo") String titulo);
    
    @Query("SELECT n FROM Noticia n WHERE n.cuerpo LIKE :cuerpo")
    public List<Noticia> buscarPorContenido(@Param("cuerpo") String cuerpo);
    
    @Query("select n from Noticia n order by n.alta desc")
    public List<Noticia> ordenarPorAlta();
}
