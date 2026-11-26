import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner entrada = new Scanner(System.in);
    private static ConsultaMedica consulta = new ConsultaMedica();

    public static void main(String[] args) {
        //esto es solo un medico pa ejemplo para poder probar la atencion
        cargarDatosIniciales();
        boolean aceptado = false;
        while (!aceptado) {
            String ID = leerTexto("ID de Empleado: ");
            Empleado empleado = buscarEmpleadoPorID(ID);

            if (empleado != null) {
                String password = leerTexto("Contraseña: ");
                if (password.equals(empleado.password)) {
                    aceptado = true;
                    System.out.println("Bienvendos %s!".formatted(empleado.getNombre()));
                }
                else {
                    System.out.println("Contraseña Incorrecta. Saliendo...");
                }
            }
        }

        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero("Opción: ");

            try {
                switch(opcion) {
                    case 1:
                        registrarPaciente();
                        break;
                    case 2:
                        atenderSiguiente();
                        break;
                    case 3:
                        mostrarPacientesEnEspera();
                        break;
                    case 4:
                        mostrarHistorial();
                        break;
                    case 5:
                        crearCita();
                        break;
                    case 6:
                        mostrarCitasDeHoy();
                        break;
                    case 0:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("No se pudo completar: " + e.getMessage());
            }
        }
        System.out.println("Hasta luego.");
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Sistema de Consultas Médicas ===");
        System.out.println("1. Registrar paciente y ponerlo en espera");
        System.out.println("2. Atender siguiente paciente");
        System.out.println("3. Ver pacientes en espera");
        System.out.println("4. Ver historial de paciente");
        System.out.println("5. Crear cita para hoy");
        System.out.println("6. Ver citas de hoy");
        System.out.println("0. Salir");
    }

    private static Empleado buscarEmpleadoPorID(String ID) {
        return consulta.getPersonal().buscarPorID(ID);
    }

    private static void cargarDatosIniciales() {
        ArrayList<Empleado> empleadosParaRegistrar = new ArrayList<Empleado>();
        ArrayList<Paciente> pacientesParaRegistrar = new ArrayList<Paciente>();

        empleadosParaRegistrar.add(new Medico("N/A", "Dra. Rivera", "admin", Especialidad.OFTALMOLOGIA_GENERAL));
        empleadosParaRegistrar.add(new Medico("N/A", "Dr. López", "admin", Especialidad.RETINOLOGIA));
        empleadosParaRegistrar.add(new Enfermero("N/A", "Tom Holland", "admin"));
        empleadosParaRegistrar.add(new Enfermero("N/A", "Robert Downey Jr.", "admin"));
        empleadosParaRegistrar.add(new Secretaria("N/A", "Jeff Bezos", "admin"));
        empleadosParaRegistrar.add(new Secretaria("N/A", "Yuji Itadori", "admin"));

        pacientesParaRegistrar.add(new Paciente("N/A", "Muhammad Ali"));
        pacientesParaRegistrar.add(new Paciente("N/A", "Matt Murdock"));

        consulta.getPersonal().registrarEmpleados(empleadosParaRegistrar);
        consulta.getPacientes().registrarPacientes(pacientesParaRegistrar);
    }

    private static void registrarPaciente() {
        String nombre = leerTexto("Nombre: ");
        Paciente paciente = new Paciente("N/A", nombre);
        consulta.getPacientes().registrarPaciente(paciente);
        // El paciente queda en la fila rapido q termina su registro
        consulta.getPacientes().anadirAFila(paciente);
        System.out.println("Paciente registrado y añadido a la fila.");
    }

    private static void atenderSiguiente() {
        // La fila entrega primero al paciente que lleva mas tiempo esperando
        Paciente paciente = consulta.asignarPaciente();
        if (paciente == null) {
            System.out.println("No hay pacientes esperando.");
            return;
        }

        List<Medico> medicos = consulta.getPersonal().medicosDisponibles();
        if (medicos.size() == 0) {
            consulta.getPacientes().anadirAFila(paciente);
            System.out.println("No hay médicos disponibles. El paciente sigue en la fila.");
            return;
        }

        Medico medico = medicos.get(0);
        System.out.println("Atendiendo a " + paciente.getNombre() + " con " + medico.getNombre());
        String notas = leerTexto("Nota breve de la visita: ");
        Cita cita = new Cita("V" + System.nanoTime(), paciente, medico, LocalDate.now());
        Visita visita = new Visita(cita, notas);
        consulta.getCitas().registrarVisita(visita);
        consulta.marcarPacienteAtendido(visita);
        System.out.println("Visita registrada.");
    }

    private static void mostrarPacientesEnEspera() {
        List<Paciente> pacientes = consulta.getPacientes().pacientesEnEspera();
        if (pacientes.size() == 0) {
            System.out.println("No hay pacientes esperando.");
        } else {
            for (int i = 0; i < pacientes.size(); i++) {
                Paciente paciente = pacientes.get(i);
                System.out.println(paciente.getId() + " - " + paciente.getNombre());
            }
        }
    }

    private static void mostrarHistorial() {
        String id = leerTexto("ID del paciente: ");
        Paciente paciente = consulta.getPacientes().buscarPorId(id);
        if (paciente == null) {
            System.out.println("No se encontró ese paciente.");
            return;
        }

        List<Visita> historial = paciente.getHistorial();
        if (historial.size() == 0) {
            System.out.println("El paciente no tiene visitas registradas.");
        } else {
            for (int i = 0; i < historial.size(); i++) {
                System.out.println(historial.get(i));
            }
        }
    }

    private static void crearCita() {
        String id = leerTexto("ID del paciente: ");
        Paciente paciente = consulta.getPacientes().buscarPorId(id);
        if (paciente == null) {
            System.out.println("Registra primero al paciente.");
            return;
        }

        List<Medico> medicos = consulta.getPersonal().medicosDisponibles();
        if (medicos.size() == 0) {
            System.out.println("No hay médicos disponibles.");
            return;
        }

        Medico medico = medicos.get(0);
        Cita cita = new Cita("C" + System.nanoTime(), paciente, medico, LocalDate.now());
        consulta.getCitas().crearCita(cita);
        System.out.println("Cita creada: " + cita);
    }

    private static void mostrarCitasDeHoy() {
        List<Cita> citas = consulta.getCitas().citasDeHoy();
        if (citas.size() == 0) {
            System.out.println("No hay citas para hoy.");
        } else {
            for (int i = 0; i < citas.size(); i++) {
                System.out.println(citas.get(i));
            }
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje);
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Escribe un número.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine().trim();
    }
}
