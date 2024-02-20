package servicios;

import entidades.Autor;
import java.util.List;
import java.util.Scanner;
import persistencia.AutorDAO;
import utilidades.Utilidades;

public class AutorServicio {

    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private AutorDAO dao = new AutorDAO();
    Utilidades utilidades = new Utilidades();
    private static AutorServicio instance = new AutorServicio();

    private AutorServicio() {

    }

    public static AutorServicio getInstance() {
        return instance;
    }

    public Autor crearAutor() {
        boolean validar = true;
        Autor autor = new Autor();
        do {
            System.out.println("Ingrese el nombre del autor:");
            String nombre = leer.next();
            List<Autor> autores = dao.buscarAutorNombre(nombre);
            if (!autores.isEmpty()) {
                System.out.println("El autor ya está registrado!");
                autor = autores.get(0);
                System.out.println("¿Desea ingresar otro? S/N");
                if (leer.next().equalsIgnoreCase("n")) {
                    validar = false;
                }
            } else {
                autor.setNombre(nombre);
                autor.setAlta(true);
                dao.persistirEntidad(autor);
                System.out.println("Se registro un autor nuevo!");
                validar = false;
            }
        } while (validar);
        return autor;
    }

    public void darBajaAutor() {
        System.out.println("Ingrese el ID del autor a dar de BAJA.");
        int id = utilidades.ingresarEnteros(leer);
        Autor autor = dao.buscarAutorID(id);
        if (autor != null) {
            autor.setAlta(false);
            dao.actualizarEntidad(autor);
            System.out.println("Autor dado de BAJA, exitosamente!");
        } else {
            System.out.println("No se encuentra autor con el ID: " + id);
        }
    }

    public void darAltaAutor() {
        System.out.println("Ingrese el ID del autor a dar de ALTA.");
        int id = utilidades.ingresarEnteros(leer);
        Autor autor = dao.buscarAutorID(id);
        if (autor != null) {
            autor.setAlta(true);
            dao.actualizarEntidad(autor);
            System.out.println("Autor dado de ALTA, exitosamente!");
        } else {
            System.out.println("No se encuentra autor con el ID: " + id);
        }
    }

    public void mostrarAutorNombre() {
        System.out.println("Ingrese el nombre del autor que desea buscar:");
        String nombre = leer.next();
        List<Autor> autores = dao.buscarAutorNombre(nombre);
        if (autores.isEmpty()) {
            System.out.println("No se encuentra un autor con el nombre ingresado.");
        } else {
            for(Autor autor : autores){
            System.out.println(autor.toString());
            System.out.println("\n");
            }
        }
    }
    public void mostrarAutoresNombre(){
        System.out.println("Ingrese el nombre de autor o autores a buscar:");
        String nombre = leer.next();
        List<Autor> autores = dao.buscarAutoresNombre(nombre);
        if(autores.isEmpty()){
            System.out.println("No se encuentra autores con el nombre ingresado.");
        }
        else{
            for(Autor autor : autores){
                System.out.println(autor.toString());
                System.out.println("\n");
            }
        }
    }
    public void modificarAutor(){
        System.out.println("Ingrese el ID del autor a modificar:");
        int id = utilidades.ingresarEnteros(leer);
        Autor autor = dao.buscarAutorID(id);
        if(autor == null){
            System.out.println("No se encuentra el autor con el ID: " + id);
        }
        else{
            System.out.println("Ingrese el nuevo nombre:");
            String nombre = leer.next();
            autor.setNombre(nombre);
            dao.actualizarEntidad(autor);
        }
    }
}
