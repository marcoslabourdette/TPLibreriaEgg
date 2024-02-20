
package utilidades;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilidades {
    public int ingresarEnteros(Scanner leer){
        int numero;
        do {            
        try {
            numero = leer.nextInt();
            break;
        } catch (InputMismatchException e) {
            System.out.println("Ha ingresado un dato incorrecto, inténtelo de nuevo.");
            leer.next();
        }    
        } while (true);
        
        return numero;
    }
     public long ingresarLong(Scanner leer){
        long numero;
        do {            
        try {
            numero = leer.nextLong();
            break;
        } catch (InputMismatchException e) {
            System.out.println("Ha ingresado un dato incorrecto, inténtelo de nuevo.");
            leer.next();
        }    
        } while (true);
        return numero;
    }
}
