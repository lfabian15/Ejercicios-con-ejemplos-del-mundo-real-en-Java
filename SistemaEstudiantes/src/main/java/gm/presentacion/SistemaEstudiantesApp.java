package gm.presentacion;


import gm.datos.EstudianteDAO;
import gm.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstudiantesApp {
    public static void main(String[] args) {
        var salir = false;
        var entrada = new Scanner(System.in);
        //Se crea una instancia de la clase de servicio
        var estuadianteDao = new EstudianteDAO();
        while(!salir){
            try {
                mostrarMenu();
               salir = ejecutarOpciones(entrada, estuadianteDao);
            }catch (Exception e){
                System.out.println("Oucrrio un error al ejecutar operacion: " + e.getMessage());
            }
            System.out.println();
        }//Fin while
    }

    private static void mostrarMenu(){
        System.out.print("""
                *** Sistema De Estudiantes ***
                1. Listar Estudiantes
                2. Buscar Estudiante
                3. Agregar Estudiate
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                Elige una opcion:
                """);
    }

    private static boolean ejecutarOpciones(Scanner entrada, EstudianteDAO estudianteDAO){
        var opcion = Integer.parseInt(entrada.nextLine());
        var salir = false;
        switch (opcion){
            case 1 ->{//Listar estudiantes
                System.out.println("Listado de estudiantes...");
                var estudiantes = estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }
            case 2 ->{//Buscar estudiante por id
                System.out.println("Buscar Estudiante: ");
                System.out.println("Id Estudiante");
                var idEstudiante = Integer.parseInt(entrada.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if(encontrado)
                    System.out.println("El estudiante fue encontrado: " + estudiante);
                else
                    System.out.println("Estudiante no encontrado: " + estudiante);
            }
            case 3 ->{//Agregar estudiante
                System.out.println("Agregar estudiante: ");
                System.out.print("Nombre: ");
                var nombre = entrada.nextLine();
                System.out.print("Apellido: ");
                var apellido = entrada.nextLine();
                System.out.print("Telefono: ");
                var telefono = entrada.nextLine();
                System.out.print("Email: ");
                var email = entrada.nextLine();
                //Crear el objeto estudiante (sin el id)
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDAO.agregarEstudiante(estudiante);
                if (agregado)
                    System.out.println("Se agrego el estudiante: " + estudiante);
                else
                    System.out.println("Ocurrio un error al agregar el estudiante: " + estudiante);
            }
            case 4 ->{//Modificar estudiante
                System.out.println("Modificar Estudiante: ");
                System.out.println("Id Estudiante: ");
                var idEstuiante = Integer.parseInt(entrada.nextLine());
                System.out.print("Nombre: ");
                var nombre = entrada.nextLine();
                System.out.print("Apellido: ");
                var apellido = entrada.nextLine();
                System.out.print("Telefono: ");
                var telefono = entrada.nextLine();
                System.out.print("Email: ");
                var email = entrada.nextLine();
                //Crear el objeto estudiante a modificar
                var estudiante = new Estudiante(idEstuiante, nombre, apellido, telefono, email);
                var modificado = estudianteDAO.modificarEstudiante(estudiante);
                if (modificado)
                    System.out.println("Estudiante modificado: " + estudiante);
                else
                    System.out.println("Error al modificar al estudiante: " + estudiante);
            }
            case 5 ->{//Eliminar estudiante
                System.out.println("Eliminar Estudiante: ");
                System.out.println("Is Estudiante: ");
                var idEstudiante = Integer.parseInt(entrada.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDAO.eliminarEstudiante(estudiante);
                if (eliminado)
                    System.out.println("Se elimino al estudiante: " + estudiante);
                else
                    System.out.println("Ocurrio un error al eliminar el estudiante: " + estudiante);
            }
            case 6 ->{//Salir
                System.out.println("Hata pronto!!");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida");
        }
        return salir;
    }
}