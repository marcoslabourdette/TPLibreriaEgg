package menu;

import java.util.Scanner;
import servicios.AutorServicio;
import servicios.ClienteServicio;
import servicios.EditorialServicio;
import servicios.LibroServicio;
import servicios.PrestamoServicio;
import utilidades.Utilidades;

public class Menu {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    EditorialServicio es = EditorialServicio.getInstance();
    AutorServicio as = AutorServicio.getInstance();
    LibroServicio ls = LibroServicio.getInstance();
    ClienteServicio cs = ClienteServicio.getInstance();
    Utilidades util = new Utilidades();
    PrestamoServicio ps = new PrestamoServicio();
    public void mainMenu() {
        boolean validar = true;
        do {
            System.out.println("1-CREAR ENTIDAD.");
            System.out.println("2-DAR ALTA ENTIDAD.");
            System.out.println("3-DAR BAJA ENTIDAD.");
            System.out.println("4-EDITAR ENTIDAD.");
            System.out.println("5-CONSULTAS.");
            System.out.println("6-PEDIR PRESTAMO.");
            System.out.println("7-SALIR.");
            int op = util.ingresarEnteros(leer);
            switch (op) {
                case 1:
                    menuCrear();
                    break;
                case 2:
                    menuAlta();
                    break;
                case 3:
                    menuBaja();
                    break;
                case 4:
                    modificarEntidad();
                    break;
                case 5:
                    consultas();
                    break;
                case 6: ps.crearPrestamo();
                    break;
                case 7:
                    validar = salir();
                    break;
                default:
                    System.out.println("Opcion incorrecta, intente otra vez.");
            }
        } while (validar);
        System.out.println("Gracias por utilizar nuestro programa! Hasta luego :)");
    }

    private void menuCrear() {
        boolean validar = true;
        do {
            System.out.println("1-CREAR LIBRO.\n2-CREAR AUTOR.\n3-CREAR EDITORIAL. \n4-CREAR CLIENTE.");
            System.out.println("5-SALIR.");
            int op = util.ingresarEnteros(leer);
            switch (op) {
                case 1:
                    ls.crearLibro();
                    break;
                case 2:
                    as.crearAutor();
                    break;
                case 3:
                    es.crearEditorial();
                    break;
                case 4:
                    cs.crearCliente();
                    break;        
                case 5:
                    validar = salir();
                    break;
                default:
                    System.out.println("Opcion incorrecta. Intente otra vez.");
            }
        } while (validar);
    }

    private void menuAlta() {
        boolean validar = true;
        do {
            System.out.println("1-DAR ALTA LIBRO.");
            System.out.println("2-DAR ALTA AUTOR.");
            System.out.println("3-DAR ALTA EDITORIAL.");
            System.out.println("4-DAR ALTA CLIENTE.");
            System.out.println("5-SALIR.");
            int op = util.ingresarEnteros(leer);
            switch (op) {
                case 1:
                    ls.darAlta();
                    break;
                case 2:
                    as.darAltaAutor();
                    break;
                case 3:
                    es.darAltaEditorial();
                    break;
                case 4:
                    cs.darAltaCliente();
                    break;
                case 5:
                    validar = salir();
                    break;
                default:
                    System.out.println("Opcion incorreta. Intente otra vez.");
            }
        } while (validar);
    }

    private void menuBaja() {
        boolean validar = true;
        do {
            System.out.println("1-DAR BAJA LIBRO.");
            System.out.println("2-DAR BAJA AUTOR.");
            System.out.println("3-DAR BAJA EDITORIAL.");
            System.out.println("4-DAR BAJA CLIENTE.");
            System.out.println("5-SALIR.");
            int op = util.ingresarEnteros(leer);
            switch (op) {
                case 1:
                    ls.darBaja();
                    break;
                case 2:
                    as.darBajaAutor();
                    break;
                case 3:
                    es.darBajaEditorial();
                    break;
                case 4:
                    cs.darBajaCliente();
                    break;
                case 5:
                    validar = salir();
                    break;
                default:
                    System.out.println("Opcion incorreta. Intente otra vez.");
            }
        } while (validar);
    }
    private void modificarEntidad(){
        boolean validar = true;
        do {            
            System.out.println("1-EDITAR LIBRO.");
            System.out.println("2-EDITAR AUTOR.");
            System.out.println("3-EDITAR EDITORIAL.");
            System.out.println("4-SALIR.");
            int op = util.ingresarEnteros(leer);
            switch(op){
                case 1: ls.modificarLibro();
                    break;
                case 2: as.modificarAutor();
                    break;
                case 3: es.modificarEditorial();
                    break;
                case 4: validar = salir();
                    break;
                default: System.out.println("Opcion incorrecta. Intente otra vez.");
            }
        } while (validar);
    }
    private void consultas() {
        boolean validar = true;
        do {
            System.out.println("1-BUSCAR AUTOR POR NOMBRE INDIVIDUAL.");
            System.out.println("2-BUSCAR AUTORES POR NOMBRE.");
            System.out.println("3-BUSCAR LIBRO POR ISBN."); 
            System.out.println("4-BUSCAR LIBRO POR TITULO.");
            System.out.println("5-BUSCAR LIBRO/S POR AUTOR.");
            System.out.println("6-BUSCAR LIBRO/S POR EDITORIAL");
            System.out.println("7-SALIR.");
            int op = util.ingresarEnteros(leer);
            switch(op){
                case 1: as.mostrarAutorNombre();
                        break;
                case 2: as.mostrarAutoresNombre();
                        break;
                case 3: ls.mostrarLibroISBN();
                        break;
                case 4: ls.mostrarLibroTitulo();
                        break;
                case 5: ls.mostrarLibroAutor();
                        break;
                case 6: ls.mostrarLibroEditorial();
                        break;
                case 7: validar = salir();
                        break; 
                default: System.out.println("Opcion incorrecta, intente de nuevo.");
            }
        } while (validar);
    }

    public boolean salir() {
        System.out.println("¿Esta seguro de salir? S/N");
        if (leer.next().equalsIgnoreCase("s")) {
            return false;
        }
        return true;
    }
}
