
package persistencia;

import entidades.Libro;
import java.util.List;
import javax.persistence.Query;

public class LibroDAO extends DAO<Libro>{
    public Libro buscarLibroISBN(long isbn){
        conectarBase();
        Libro libro = em.find(Libro.class, isbn);
        desconectarBase();
        return libro;
    }
    public List<Libro> buscarLibroPorTitulo(String titulo){
        conectarBase();
        String jpql = "SELECT l FROM Libro l WHERE l.titulo = :title";
        Query query = em.createQuery(jpql);
        query.setParameter("title", titulo);
        List<Libro> libros = query.getResultList();
        desconectarBase();
        return libros;
    }
    public List<Libro> buscarLibrosPorAutor(String nombre){
        conectarBase();
        String jpql = "SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre = :nombre";
        Query query = em.createQuery(jpql);
        query.setParameter("nombre", nombre);
        List<Libro> lista = query.getResultList();
        desconectarBase();
        return lista;
    }
    public List<Libro> buscarLibrosPorEditorial(String nombre){
        conectarBase();
        String jpql = "SELECT l FROM Libro l JOIN l.editorial e WHERE e.nombre = :nombre";
        Query query = em.createQuery(jpql);
        query.setParameter("nombre", nombre);
        List<Libro> libros = query.getResultList();
        desconectarBase();
        return libros;
    }
}
