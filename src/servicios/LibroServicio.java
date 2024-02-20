
package servicios;

import entidades.Libro;
import java.util.List;
import java.util.Scanner;
import persistencia.LibroDAO;
import utilidades.Utilidades;

public class LibroServicio {

    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    public LibroDAO dao = new LibroDAO();
    private static LibroServicio instance = new LibroServicio();
    private Utilidades utilidades = new Utilidades();
    private EditorialServicio es = EditorialServicio.getInstance();
    private AutorServicio as = AutorServicio.getInstance();

    private LibroServicio() {
    }

    public static LibroServicio getInstance() {
        return instance;
    }

    public void crearLibro() {
        Libro libro = new Libro();
        do {
            System.out.println("Ingrese el ISBN del libro:");
            libro.setISBN(utilidades.ingresarLong(leer));
            Libro libroBuscar = dao.buscarLibroISBN(libro.getISBN());
            if(libroBuscar != null){
                System.out.println("El ISBN ingresado, ya esta registrado. Ingrese  otro.");
            }
            else{
                break;
            }
        } while (true);
        
        System.out.println("Ingrese el titulo del libro:");
        libro.setTitulo(leer.next());
        System.out.println("Ingrese el año de publicacion;");
        libro.setAnio(utilidades.ingresarEnteros(leer));
        System.out.println("Ingrese la cantidad de ejemplares;");
        libro.setEjemplares(utilidades.ingresarEnteros(leer));
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(libro.getEjemplares());
        libro.setAutor(as.crearAutor());
        libro.setEditorial(es.crearEditorial());
        libro.setAlta(true);
        dao.actualizarEntidad(libro);
    }
    public void darBaja(){
        System.out.println("Ingrese el ISBN del libro a dar BAJA:");
        long isbn = utilidades.ingresarLong(leer);
        Libro libro = dao.buscarLibroISBN(isbn);
        if(libro != null){
            libro.setAlta(false);
            dao.actualizarEntidad(libro);
            System.out.println("Libro dado de BAJA, exitosamente!");
        }
        else{
            System.out.println("No hay libro con el ISBN: " + isbn);
        }
    }
     public void darAlta(){
        System.out.println("Ingrese el ISBN del libro a dar ALTA:");
        long isbn = utilidades.ingresarLong(leer);
        Libro libro = dao.buscarLibroISBN(isbn);
        if(libro != null){
            libro.setAlta(true);
            dao.actualizarEntidad(libro);
            System.out.println("Libro dado de ALTA, exitosamente!");
        }
        else{
            System.out.println("No hay libro con el ISBN: " + isbn);
        }
    }
    public void mostrarLibroISBN(){
        System.out.println("Ingrese  el ISBN del libro a buscar: ");
        long isbn = utilidades.ingresarLong(leer);
        Libro libro = dao.buscarLibroISBN(isbn);
        if(libro != null){
            System.out.println(libro.toString());
        }
        else{
            System.out.println("No hay libro con el ISBN: " + isbn);
        }
    }
    public void mostrarLibroTitulo(){
        System.out.println("Ingrese el titulo del libro a buscar: ");
        String titulo = leer.next();
        List<Libro> libros = dao.buscarLibroPorTitulo(titulo);
        if(libros.isEmpty()){
            System.out.println("No hay libro con el titulo ingresado.");
        }
        else{
            for(Libro libro : libros){
                System.out.println(libro.toString());
            }
        }
    }
    public void mostrarLibroAutor(){
        System.out.println("Ingrese el nombre del autor del libro:");
        String nombre = leer.next();
        List<Libro> libros = dao.buscarLibrosPorAutor(nombre);
        if(libros.isEmpty()){
            System.out.println("No hay libros registrados para el autor " + nombre);
        }
        else{
            System.out.println("Libro/s del autor " + nombre);
            for(Libro libro : libros){
                System.out.println(libro.toString());
                System.out.println("\n");
            }
        }
    }
    public void mostrarLibroEditorial(){
        System.out.println("Ingrese el nombre de la editorial del libro:");
        String nombre = leer.next();
        List<Libro> libros = dao.buscarLibrosPorEditorial(nombre);
        if(libros.isEmpty()){
            System.out.println("No hay libros registrados para la editorial " + nombre);
        }
        else{
            System.out.println("Libro/s de la editorial " + nombre);
            for(Libro libro : libros){
                System.out.println(libro.toString());
                System.out.println("\n");
            }
        }
    }
    public void modificarLibro(){
        boolean validar = true;
        System.out.println("Ingrese el ISBN del libro a modificar:");
        long isbn = utilidades.ingresarLong(leer);
        Libro libro = dao.buscarLibroISBN(isbn);
        if(libro == null){
            System.out.println("No hay libro con el ISBN: " + isbn);
        }
        else{
            do {                
                System.out.println("1-Modificar titulo.");
                System.out.println("2-Modificar año de publicación.");
                System.out.println("3-Modificar autor.");
                System.out.println("4-Modificar editorial.");
                System.out.println("5-Resetear ejemplares.");
                System.out.println("6-Salir.");
                int op = utilidades.ingresarEnteros(leer);
                switch(op){
                    case 1: System.out.println("Ingrese el nuevo titulo.");
                            libro.setTitulo(leer.next());
                            dao.actualizarEntidad(libro);
                            System.out.println("Titulo modificado, exitosamente!");
                            break;
                    case 2: System.out.println("Ingrese el nuevo año de publicación.");   
                            int anio = utilidades.ingresarEnteros(leer);
                            libro.setAnio(anio);
                            dao.actualizarEntidad(libro);
                            System.out.println("Año modificado, exitosamente!");
                            break;
                    case 3: libro.setAutor(as.crearAutor());
                            dao.actualizarEntidad(libro);
                            System.out.println("Autor modificado, exitosamente!");
                            break;
                    case 4: libro.setEditorial(es.crearEditorial());
                            dao.actualizarEntidad(libro);
                            System.out.println("Editorial modificada, exitosamente!");
                            break;
                    case 5: libro.setEjemplaresPrestados(0);
                            libro.setEjemplaresRestantes(libro.getEjemplares());
                            dao.actualizarEntidad(libro);
                            System.out.println("Cantidad de ejemplares reseteada, correctamente!");
                    case 6: validar = false;
                            break;
                    default: System.out.println("Opcion incorrecta, intentalo de nuevo.");
                }
            } while (validar);
        }
    }
    public List<Libro> buscarLibroTitulo(String nombre){
        return dao.buscarLibroPorTitulo(nombre);
    }
}
