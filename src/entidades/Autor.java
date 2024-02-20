
package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Autor {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private boolean alta;
    
    public Autor(){
    }
    
    public Autor(String nombre, boolean alta){
        this.nombre = nombre;
        this.alta = alta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }
    public Integer getId(){
        return id;
    }
    @Override
    public String toString(){
        String[] estados  = {"Alta","Baja"}; 
        String  estado = estados[1];
        if(alta){
            estado = estados[0];
        }
        return "-Registro Autor-\nID: " + id + "\nNombre: " + nombre + "\nEstado: " + estado; 
    }
}
