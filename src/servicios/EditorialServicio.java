
package servicios;

import entidades.Editorial;
import java.util.List;
import java.util.Scanner;
import persistencia.EditorialDAO;
import utilidades.Utilidades;

public class EditorialServicio {

    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private EditorialDAO dao = new EditorialDAO();
    Utilidades utilidades = new Utilidades();
    private static EditorialServicio instance = new EditorialServicio();

    private EditorialServicio() {

    }

    public static EditorialServicio getInstance() {
        return instance;
    }

    public Editorial crearEditorial() {
        boolean validar = true;
        Editorial editorial = new Editorial();
        do {
            System.out.println("Ingrese el nombre de la editorial:");
            String nombre = leer.next();
            List<Editorial> editoriales = dao.buscarEditorialNombre(nombre);
            if (!editoriales.isEmpty()) {
                System.out.println("La editorial ya está registrada!");
                editorial = editoriales.get(0);
                System.out.println("¿Desea ingresar otra? S/N");
                if (leer.next().equalsIgnoreCase("n")) {
                    validar = false;
                }
            }else {
                editorial.setNombre(nombre);
                editorial.setAlta(true);
                dao.persistirEntidad(editorial);
                System.out.println("Se registro una editorial nueva!");
                validar = false;
            }
        } while(validar);
        return editorial;
    }

    public void darBajaEditorial() {
        System.out.println("Ingrese el ID de la editorial a dar de BAJA.");
        int id = utilidades.ingresarEnteros(leer);
        Editorial editorial = dao.buscarEditorialID(id);
        if (editorial != null) {
            editorial.setAlta(false);
            dao.actualizarEntidad(editorial);
            System.out.println("Editorial dada de BAJA, exitosamente!");
        } else {
            System.out.println("No hay editorial con el ID " + id);
        }
    }

    public void darAltaEditorial() {
        System.out.println("Ingrese el ID de la editorial a dar de ALTA.");
        int id = utilidades.ingresarEnteros(leer);
        Editorial editorial = dao.buscarEditorialID(id);
        if (editorial != null) {
            editorial.setAlta(true);
            dao.actualizarEntidad(editorial);
            System.out.println("Editorial dada de ALTA, exitosamente!");
        } else {
            System.out.println("No hay editorial con el ID " + id);
        }
    }
      public void modificarEditorial(){
        System.out.println("Ingrese el ID del autor a modificar:");
        int id = utilidades.ingresarEnteros(leer);
        Editorial editorial = dao.buscarEditorialID(id);
        if(editorial == null){
            System.out.println("No se encuentra la editoiral con el ID: " + id);
        }
        else{
            System.out.println("Ingrese el nuevo nombre:");
            String nombre = leer.next();
            editorial.setNombre(nombre);
            dao.actualizarEntidad(editorial);
        }
    }
}
