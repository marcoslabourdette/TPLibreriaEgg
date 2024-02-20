/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import entidades.Cliente;
import entidades.Libro;
import entidades.Prestamo;
import static java.lang.Integer.parseInt;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import persistencia.LibroDAO;
import persistencia.PrestamoDAO;
import utilidades.Utilidades;

/**
 *
 * @author marco
 */
public class PrestamoServicio {

    private LibroServicio ls = LibroServicio.getInstance();
    private PrestamoDAO dao = new PrestamoDAO();
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private ClienteServicio cs = ClienteServicio.getInstance();
    private Utilidades utilidades = new Utilidades();

    public void crearPrestamo() {
        boolean validar = true;
        boolean validarFecha = true;
        Prestamo prestamo = new Prestamo();
        do {
            boolean hayLibro = true;
            boolean continuarPrestamo = true;
            System.out.println("Ingrese el nombre del libro a prestar.");
            String nombreLibro = leer.next();
            List<Libro> libros = ls.buscarLibroTitulo(nombreLibro);
            if (libros.isEmpty()) {
                System.out.println("No hay libro registrado, con el titulo ingresado.");
                hayLibro = false;
            } else if (libros.get(0).getEjemplaresRestantes() > 0) {
                Libro libro = libros.get(0);
                libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
                libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
                do {
                    System.out.println("Ingrese el dni del cliente.");
                    long dni = utilidades.ingresarLong(leer);
                    List<Cliente> clientes = cs.dao.buscarClienteDNI(dni);
                    if (clientes.isEmpty()) {
                        System.out.println("No existe cliente registrado con el DNI, ingresado.");
                        System.out.println("¿Desea registarlo? S/N");
                        if (leer.next().equalsIgnoreCase("s")) {
                            Cliente cliente = cs.crearCliente();
                            prestamo.setCliente(cliente);
                            prestamo.setLibro(libro);
                            ls.dao.actualizarEntidad(libro);
                            break;
                        } else {
                            continuarPrestamo = false;
                            break;
                        }
                    } else {
                        prestamo.setLibro(libro);
                        prestamo.setCliente(clientes.get(0));
                        ls.dao.actualizarEntidad(libro);
                        break;
                    }
                } while (true);
                if (continuarPrestamo) {
                    Date fechaPrestamo = new Date();
                    prestamo.setFechaPrestamo(fechaPrestamo);
                    do {
                        try {
                            System.out.println("Ingrese la fecha de devolución, en este formato: dd/mm/aaaa");
                            String fecha = leer.next();
                            int dia = parseInt(fecha.substring(0, 2));
                            int mes = parseInt(fecha.substring(3, 5));
                            int anio = parseInt(fecha.substring(6, 10));
                            Date fechaDevolucion = new Date(anio - 1900, mes - 1, dia);
                            if (fechaDevolucion.before(fechaPrestamo)) {
                                System.out.println("La fecha ingresada debe ser posterior a la fecha del prestamo. Intente otra vez.");
                            } else {
                                prestamo.setFechaDevolucion(fechaDevolucion);
                                validarFecha = false;
                            }
                        } catch (Exception e) {
                            System.out.println("Error al ingresar la fecha.");
                        }
                    } while (validarFecha);
                    System.out.println("¡Prestamo realizado exitosamente!");
                    System.out.println("\n");
                    System.out.println(">> INFORMACIÓN DEL PRESTAMO <<<: "); 
                    System.out.println("-Titulo del libro : " + libro.getTitulo()); 
                    System.out.println("-Cliente: " + prestamo.getCliente().getApellido() + ", " + prestamo.getCliente().getNombre());
                    System.out.println("-Fecha del prestamo: " + prestamo.getFechaPrestamo().toString());
                    System.out.println("-Fecha límite de devolucion: " + prestamo.getFechaDevolucion().toString());
                    System.out.println("\n");
                }
            } else {
                System.out.println("No hay mas ejemplares de este titulo.");
                hayLibro = false;
            }
            if (hayLibro) {
                if(continuarPrestamo){
                 prestamo.setAlta(true);
                 dao.persistirEntidad(prestamo);
                }
                System.out.println("¿Quiere realizar otro prestamo?");
                if (leer.next().equalsIgnoreCase("n")) {
                    validar = false;
                }
            } else {
                System.out.println("¿Quiere ingresar otro libro? S/N");
                if (leer.next().equalsIgnoreCase("n")) {
                    validar = false;
                }
            }
        } while (validar);
    }
}
