/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Cliente;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author marco
 */
public class ClienteDAO extends DAO<Cliente> {
    public List<Cliente> buscarClienteDNI(long dni){
        conectarBase();
        String jpql = "SELECT c FROM Cliente c WHERE c.dni = :dni";
        Query consulta = em.createQuery(jpql);
        consulta.setParameter("dni", dni);
        List<Cliente> cliente = consulta.getResultList();
        desconectarBase();
        return cliente;
    }
    public Cliente buscarClienteID(Integer id){
        conectarBase();
        Cliente cliente = em.find(Cliente.class, id);
        desconectarBase();
        return cliente;
    }
    public List<Cliente> buscarClienteNombre(String nombre){
        conectarBase();
        String jpql = "SELECT c FROM Cliente c WHERE c.nombre = :name";
        Query query = em.createQuery(jpql);
        query.setParameter("name", nombre);
        List<Cliente> clientes = query.getResultList();
        desconectarBase();
        return clientes;
    
    }
}
