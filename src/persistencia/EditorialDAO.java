
package persistencia;

import entidades.Editorial;
import java.util.List;
import javax.persistence.Query;

public class EditorialDAO extends DAO<Editorial> {
    public Editorial buscarEditorialID(Integer id){
        conectarBase();
        Editorial editorial = em.find(Editorial.class, id);
        desconectarBase();
        return editorial;
    }
    public List<Editorial> buscarEditorialNombre(String nombre){
        conectarBase();
        String jpql = "SELECT e FROM Editorial e WHERE e.nombre = :name";
        Query query = em.createQuery(jpql);
        query.setParameter("name", nombre);
        List<Editorial> editoriales = query.getResultList();
        desconectarBase();
        return editoriales;
    }
    
}
