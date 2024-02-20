
package persistencia;

import entidades.Autor;

import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class AutorDAO extends DAO<Autor> {
    public Autor buscarAutorID(Integer id){
        Autor autor = new Autor();
        try {
            conectarBase();
            autor = em.find(Autor.class, id);
        } catch (PersistenceException e) {
            System.out.println("Error al buscar autor por ID.");
        } finally {
            desconectarBase();
        }
        return autor;
    }
     public List<Autor> buscarAutorNombre(String nombre){
        conectarBase();
        String jpql = "SELECT e FROM Autor e WHERE e.nombre = :name";
        Query query = em.createQuery(jpql);
        query.setParameter("name", nombre);
        List<Autor> autores = query.getResultList();
        desconectarBase();
        return autores;
    } 
    public List<Autor> buscarAutoresNombre(String nombre){
        conectarBase();
        String jpql = "SELECT e FROM Autor e WHERE e.nombre LIKE :name";
        Query query = em.createQuery(jpql);
        query.setParameter("name", "%" + nombre + "%");
        List<Autor> autores = query.getResultList();
        desconectarBase();
        return autores;
       }
} 
