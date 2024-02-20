
package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public abstract class DAO<T> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpafinalPU");
    EntityManager em = emf.createEntityManager();

    public void conectarBase() {
        if (!em.isOpen() || em == null) {
            em = emf.createEntityManager();
        }
    }

    public void desconectarBase() {
        if (em.isOpen()) {
            em.close();
        }
    }

    public void persistirEntidad(T entidad) {
        try {
            conectarBase();
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Error al persistir " + entidad.getClass().toString());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            desconectarBase();
        }
    }

    public void actualizarEntidad(T entidad) {
        try {
            conectarBase();
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Error al actualizar " + entidad.getClass().toString());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            desconectarBase();
        }
    }
    public void eliminarEntidad(T entidad) {
        try {
            conectarBase();
            em.getTransaction().begin();
            em.remove(entidad);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Error al eliminar " + entidad.getClass().toString());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            desconectarBase();
        }
    }

}
