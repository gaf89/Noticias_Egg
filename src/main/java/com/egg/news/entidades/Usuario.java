
package com.egg.news.entidades;

import com.egg.news.enumeradores.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter @Setter
@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")    
    private String id;
    
    private String nombre;
    private String email;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Usuario() {
    }    
    
}
