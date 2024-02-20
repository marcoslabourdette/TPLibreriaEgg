/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import entidades.Cliente;
import java.util.Scanner;
import persistencia.ClienteDAO;
import utilidades.Utilidades;

/**
 *
 * @author marco
 */
public class ClienteServicio {

    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    ClienteDAO dao = new ClienteDAO();
    private Utilidades utilidades = new Utilidades();
    private static ClienteServicio instance = new ClienteServicio();

    private ClienteServicio() {

    }

    public static ClienteServicio getInstance() {
        return instance;
    }

    public Cliente crearCliente() {
        boolean validar = true;
        Cliente cliente = new Cliente();
        System.out.println("Ingrese el nombre del cliente.");
        cliente.setNombre(leer.next());
        System.out.println("Ingrese el apellido del cliente.");
        cliente.setApellido(leer.next());
        do {
            System.out.println("Ingrese el DNI del cliente.");
            long dni = utilidades.ingresarLong(leer);
            cliente.setDni(dni);
            if (!dao.buscarClienteDNI(cliente.getDni()).isEmpty()) {
                System.out.println("Ya existe un usuario con el DNI ingresado.");
                System.out.println("¿Desea ingresar otro? S/N");
                if (leer.next().equalsIgnoreCase("n")) {
                    validar = false;
                }
            } else {
                System.out.println("Ingrese el numero de telefono celular del cliente. ");
                cliente.setTelefono(leer.next());
                cliente.setAlta(true);
                dao.persistirEntidad(cliente);
                validar = false;
            }
        } while (validar);
        return cliente;
    }

    public void darAltaCliente() {
        System.out.println("Ingrese el id del cliente a dar de ALTA.");
        int id = utilidades.ingresarEnteros(leer);
        Cliente cliente = dao.buscarClienteID(id);
        if (cliente == null) {
            System.out.println("No hay cliente registrado con el ID: " + id);
        } else {
            cliente.setAlta(true);
            dao.actualizarEntidad(cliente);
            System.out.println("Cliente dado de ALTA, exitosamente!");
        }
    }
    public void darBajaCliente() {
        System.out.println("Ingrese el id del cliente a dar de BAJA.");
        int id = utilidades.ingresarEnteros(leer);
        Cliente cliente = dao.buscarClienteID(id);
        if (cliente == null) {
            System.out.println("No hay cliente registrado con el ID: " + id);
        } else {
            cliente.setAlta(false);
            dao.actualizarEntidad(cliente);
            System.out.println("Cliente dado de baja, exitosamente!");
        }
    }
    public void buscarClienteNombre(String nombre){
        
    }
}
