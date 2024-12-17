import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListadoPersonasApp {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        //Definimos la lista fuera del ciclo while
        List<Persona> personas = new ArrayList<>();
        //Empezamos con el menu
        var salir = false;
        while (!salir){
            mostrarMenu();
            try {
                salir = ejecutarOperacion(entrada, personas);
            } catch (Exception e){
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
            System.out.println();
        }
    }
    private static void mostrarMenu(){
        //Mostramos las opciones
        System.out.print("""
                **** Listado Personas App ****
                1. Agregar
                2. Listar
                3. Salir
                """);
        System.out.print("Elija una opcion:");
    }

    private static boolean ejecutarOperacion(Scanner entrada, List<Persona> personas){
        var opcion = Integer.parseInt(entrada.nextLine());
        var salir = false;
        //Revisamos la opcion proporcionada
        switch (opcion){
            case 1 ->{ //Agregar persona a la lista
                System.out.print("Proporcione el nomre: ");
                var nombre = entrada.nextLine();
                System.out.print("Proporcione el telefono: ");
                var tel = entrada.nextLine();
                System.out.print("Proporcione el email: ");
                var email = entrada.nextLine();
                //Crear el objeto persona
                var persona = new Persona(nombre, tel, email);
                //Lo agregamos a la lista de personas
                personas.add(persona);
                System.out.println("La lista tiene: " + personas.size() + " elementos");
            }//Fin caso 1
            case 2 ->{//Listar las personas
                System.out.println("Listado de personas: ");
                // Mejora usando lambda y metodo de referencia
                //personas.forEach((persona) -> System.out.println(persona));
                personas.forEach(System.out::println);
            }
            case 3 ->{//Salimos del ciclo
                System.out.println("Hasta pronto...");
                salir = true;
            }
            default -> System.out.println("Opcion erronea: " + opcion);
        }//Fin switch
        return salir;
    }
}