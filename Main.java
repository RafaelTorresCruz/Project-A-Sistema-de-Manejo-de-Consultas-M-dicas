import java.util.Scanner;
public class Main {
    static Scanner myScanner;
    public static void main(String[] args) throws Exception {
        myScanner = new Scanner(System.in);
        System.out.println("===============================Sistema de Manejo de Consultas Medicas===============================");
        System.out.print("ID de empleado: ");
        Integer ID = myScanner.nextInt();

        Empleado empleado = buscarEmpleado(ID);
        if (empleado != null) {
            System.out.print("Password: ");
            String password = myScanner.next().strip();
            if (password.equals(empleado.password)) {
                saludos(empleado);
            }
        }
        switch (empleado.ocupacion) {
            case "Secretary":
                opcionesSecretary();
                break;
            case "Doctor":
                opcionesDoctor();
                break;
            case "Nurse":
                opcionesNurse();
                break;
            default:
                closeProgram();
                throw new Exception("Ocupacion de %d no existe".formatted(empleado.id));
        }

        closeProgram();
    }
    public static void closeProgram() {
        myScanner.close();
    }
    public static void opcionesSecretary() {
        System.out.println("Elige un numero para seguir:");
        System.out.println("1. Registar Paciente");
        System.out.println("2. Apuntar Nueva Cita");
    }
    public static void opcionesDoctor() {
        System.out.println("Elige un numero para seguir:");
        System.out.println("1. Verificar Disponibilidad");
        System.out.println("2. Verificar Citas");
        System.out.println("3. Ver Informacion de Paciente");
    }
    public static void opcionesNurse() {
        System.out.println("Elige un numero para seguir:");
        System.out.println("1. Ver Informacion de Paciente");
        System.out.println("2. Ver Historial Medico");
    }
    public static void saludos(Empleado empleado) {
        System.out.println("work?");
        System.out.println("Bienvenidos %s!".formatted(empleado.nombre));
    }
    public static Empleado buscarEmpleado(Integer ID) {
        return new Empleado(ID, "Robert Pattinson", "Secretary", "1234");
    }
}
