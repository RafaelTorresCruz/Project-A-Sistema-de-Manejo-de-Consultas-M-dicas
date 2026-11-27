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
        boolean aceptado = true;
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
            int opcion = leerEntero("Selecciona una seccion: ");
            try {
                if (opcion == 1) {
                    menuPacientes();
                } else if (opcion == 2) {
                    menuCitas();
                } else if (opcion == 3) {
                    menuMedicos();
                } else if (opcion == 0) {
                    continuar = false;
                } else {
                    System.out.println("Opcion no valida. Selecciona una de las opciones del menu.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("No se pudo completar: " + e.getMessage());
            }
        }
        System.out.println("Hasta luego.");
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("        CONSULTORIO OFTALMOLOGICO - MAYAGUEZ");
        System.out.println("             Sistema de manejo medico");
        System.out.println("==================================================");
        System.out.println("MENU PRINCIPAL");
        System.out.println("1. Pacientes y atencion medica");
        System.out.println("2. Citas medicas");
        System.out.println("3. Medicos y especialidades");
        System.out.println("0. Salir del sistema");
    }

    private static void menuPacientes() {
        boolean regresar = false;
        while (!regresar) {
            System.out.println();
            System.out.println("--------------- PACIENTES Y ATENCION ---------------");
            System.out.println("1. Registrar paciente nuevo");
            System.out.println("2. Agregar paciente a la fila de espera");
            System.out.println("3. Ver pacientes en espera");
            System.out.println("4. Atender al siguiente paciente");
            System.out.println("5. Ver historial clinico y citas de un paciente");
            System.out.println("0. Volver al menu principal");
            int opcion = leerEntero("Selecciona una opcion: ");
            try {
                if (opcion == 1) {
                    registrarPaciente();
                } else if (opcion == 2) {
                    registrarLlegada();
                } else if (opcion == 3) {
                    mostrarPacientesEnEspera();
                } else if (opcion == 4) {
                    atenderSiguiente();
                } else if (opcion == 5) {
                    mostrarHistorial();
                } else if (opcion == 0) {
                    regresar = true;
                } else {
                    System.out.println("Opcion no valida. Intenta de nuevo.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("No se pudo completar: " + e.getMessage());
            }
        }
    }

    private static void menuCitas() {
        boolean regresar = false;
        while (!regresar) {
            System.out.println();
            System.out.println("------------------- CITAS MEDICAS -------------------");
            System.out.println("1. Agendar una cita");
            System.out.println("2. Ver citas de hoy");
            System.out.println("3. Ver citas de una fecha");
            System.out.println("4. Ver citas pendientes");
            System.out.println("5. Cancelar una cita pendiente");
            System.out.println("6. Ver visitas completadas recientemente");
            System.out.println("0. Volver al menu principal");
            int opcion = leerEntero("Selecciona una opcion: ");
            try {
                if (opcion == 1) {
                    crearCita();
                } else if (opcion == 2) {
                    mostrarCitasDeHoy();
                } else if (opcion == 3) {
                    mostrarCitasDeFecha();
                } else if (opcion == 4) {
                    mostrarTodasLasCitas();
                } else if (opcion == 5) {
                    cancelarCita();
                } else if (opcion == 6) {
                    mostrarVisitasRecientes();
                } else if (opcion == 0) {
                    regresar = true;
                } else {
                    System.out.println("Opcion no valida. Intenta de nuevo.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("No se pudo completar: " + e.getMessage());
            }
        }
    }

    private static void menuMedicos() {
        boolean regresar = false;
        while (!regresar) {
            System.out.println();
            System.out.println("-------------- MEDICOS Y ESPECIALIDADES -------------");
            System.out.println("1. Buscar medicos por especialidad");
            System.out.println("2. Ver medicos disponibles ahora");
            System.out.println("3. Ver pacientes atendidos o citados con un medico");
            System.out.println("0. Volver al menu principal");
            int opcion = leerEntero("Selecciona una opcion: ");
            try {
                if (opcion == 1) {
                    buscarMedicoPorEspecialidad();
                } else if (opcion == 2) {
                    mostrarMedicosDisponibles();
                } else if (opcion == 3) {
                    mostrarPacientesDeMedico();
                } else if (opcion == 0) {
                    regresar = true;
                } else {
                    System.out.println("Opcion no valida. Intenta de nuevo.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("No se pudo completar: " + e.getMessage());
            }
        }
    }
    private static void registrarLlegada() {
        String id = leerTexto("ID del paciente: ");
        consulta.registrarLlegada(id);
        System.out.println("Paciente añadido a la fila de espera.");
}

    private static void buscarMedicoPorEspecialidad(){
        Especialidad[] especialidades = Especialidad.values();
        System.out.println("Especialidades:");
        for (int i = 0; i < especialidades.length; i++) {
            System.out.println((i + 1) + ". " + especialidades[i]);
    }

    int opcion = leerEntero("Elige una especialidad: ");
        if (opcion < 1 || opcion > especialidades.length) {
            System.out.println("Opción no válida.");
            return;
    }
    Especialidad elegida = especialidades[opcion - 1];
    List<Medico> medicos = consulta.getPersonal().buscarPorEspecialidad(elegida);

     if (medicos.isEmpty()) {
        System.out.println("No hay médicos de " + elegida + ".");
    } else {
        for (Medico m : medicos) {
            imprimirMedico(m);
        }
    }


    }

    private static void mostrarMedicosDisponibles() {
        List<Medico> medicos = consulta.getPersonal().medicosDisponibles();
        if (medicos.isEmpty()) {
            System.out.println("No hay medicos disponibles.");
        } else {
            for (int i = 0; i < medicos.size(); i++) {
                imprimirMedico(medicos.get(i));
            }
        }
    }

    private static void mostrarPacientesDeMedico() {
        String idMedico = leerTexto("ID del medico: ");
        Empleado empleado = consulta.getPersonal().buscarPorID(idMedico);
        if (empleado == null || !(empleado instanceof Medico)) {
            System.out.println("No se encontro un medico con ese ID.");
            return;
        }

        Medico medico = (Medico) empleado;
        List<Paciente> pacientes = consulta.getCitas().pacientesDeMedico(medico.getId());
        if (pacientes.isEmpty()) {
            System.out.println("Este medico no tiene pacientes registrados.");
        } else {
            System.out.println("Pacientes de " + medico.getNombre() + ":");
            for (int i = 0; i < pacientes.size(); i++) {
                Paciente paciente = pacientes.get(i);
                System.out.println(paciente.getId() + " - " + paciente.getNombre());
            }
        }
    }
    private static void imprimirMedico(Medico m) {
        String estado;
        if (m.isDisponible()) {
            estado = "Disponible";
        } else {
            estado = "Ocupado";
        }
        
        System.out.println(m.getId() + " - " + m.getNombre() + " | " + m.getEspecialidad() + " | " + estado);
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
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        Paciente paciente = new Paciente("N/A", nombre);
        consulta.getPacientes().registrarPaciente(paciente);
        System.out.println("Paciente registrado con ID: " + paciente.getId());


        String respuesta = leerTexto("¿Poner paciente nuevo en espera? (Y/N): ");
        while (!respuesta.equalsIgnoreCase("Y") && !respuesta.equalsIgnoreCase("N")) {
            respuesta = leerTexto("Escribe Y o N: ");
        }

        if (respuesta.equalsIgnoreCase("Y")) {
            consulta.registrarLlegada(paciente.getId());
            System.out.println("Paciente añadido a la fila de espera.");}

        if (respuesta.equalsIgnoreCase("N")) {
            System.out.println("Paciente añadido solo al registro.");}

        
    }
    

    

    private static void atenderSiguiente() {
        Paciente paciente = consulta.asignarPaciente();
        if (paciente == null) {
            System.out.println("No hay pacientes esperando.");
            return;
        }

        LocalDate hoy = LocalDate.now();
        Cita citaAgendada = consulta.getCitas().buscarCitaDePacienteEnFecha(paciente.getId(), hoy);
        List<Medico> medicosDisponibles = consulta.getPersonal().medicosDisponibles();
        Medico medicoElegido = null;

        if (citaAgendada != null) {
            for (int i = 0; i < medicosDisponibles.size(); i++) {
                if (medicosDisponibles.get(i).getId().equals(citaAgendada.getMedico().getId())) {
                    medicoElegido = medicosDisponibles.get(i);
                }
            }
        } else {
            List<Cita> citasDeHoy = consulta.getCitas().citasDeHoy();
            for (int i = 0; i < medicosDisponibles.size(); i++) {
                Medico medico = medicosDisponibles.get(i);
                boolean tieneCitaHoy = false;
                for (int j = 0; j < citasDeHoy.size(); j++) {
                    if (citasDeHoy.get(j).getMedico().getId().equals(medico.getId())) {
                        tieneCitaHoy = true;
                    }
                }
                if (!tieneCitaHoy) {
                    medicoElegido = medico;
                    break;
                }
            }
        }

        if (medicoElegido == null) {
            consulta.getPacientes().anadirAFila(paciente);
            System.out.println("No hay medico disponible para este paciente. Sigue en la fila.");
            return;
        }

        medicoElegido.setDisponible(false);
        System.out.println("Atendiendo a " + paciente.getNombre() + " con " + medicoElegido.getNombre());
        String notas = leerTexto("Nota breve de la visita: ");
        Cita citaDeVisita = citaAgendada;
        if (citaDeVisita == null) {
            citaDeVisita = new Cita("V" + System.nanoTime(), paciente, medicoElegido, hoy);
        }
        Visita visita = new Visita(citaDeVisita, notas);
        consulta.getCitas().registrarVisita(visita);
        medicoElegido.setDisponible(true);
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

        System.out.println("Citas del paciente:");
        List<Cita> citasPaciente = consulta.getCitas().citasDePaciente(id);
        if (citasPaciente.isEmpty()) {
            System.out.println("No tiene citas registradas.");
        } else {
            for (int i = 0; i < citasPaciente.size(); i++) {
                System.out.println(citasPaciente.get(i));
            }
        }
    }

    private static void crearCita() {
        String idPaciente = leerTexto("ID del paciente: ");
        Paciente paciente = consulta.getPacientes().buscarPorId(idPaciente);
        if (paciente == null) {
            System.out.println("No se encontro un paciente con ese ID.");
            return;
        }

        LocalDate fecha;
        try {
            fecha = LocalDate.parse(leerTexto("Fecha de la cita (AAAA-MM-DD): "));
        } catch (java.time.DateTimeException e) {
            System.out.println("Fecha incorrecta. Usa el formato AAAA-MM-DD.");
            return;
        }
        if (fecha.isBefore(LocalDate.now())) {
            System.out.println("La fecha no puede ser anterior a hoy.");
            return;
        }

        Especialidad[] especialidades = Especialidad.values();
        System.out.println("Especialidades:");
        for (int i = 0; i < especialidades.length; i++) {
            System.out.println((i + 1) + ". " + especialidades[i]);
        }
        int opcionEspecialidad = leerEntero("Elige una especialidad: ");
        if (opcionEspecialidad < 1 || opcionEspecialidad > especialidades.length) {
            System.out.println("Opcion no valida.");
            return;
        }
        Especialidad especialidad = especialidades[opcionEspecialidad - 1];
        List<Medico> medicos = consulta.getPersonal().buscarPorEspecialidad(especialidad);
        if (medicos.isEmpty()) {
            System.out.println("No hay medicos en esa especialidad.");
            return;
        }

        List<Medico> medicosDisponibles = new ArrayList<Medico>();
        List<Cita> citasExistentes = consulta.getCitas().citasDeFecha(fecha);
        for (int i = 0; i < citasExistentes.size(); i++) {
            if (citasExistentes.get(i).getPaciente().getId().equals(paciente.getId())) {
                System.out.println("El paciente ya tiene una cita en esa fecha.");
                return;
            }
        }
        for (int i = 0; i < medicos.size(); i++) {
            Medico medico = medicos.get(i);
            boolean ocupado = false;
            for (int j = 0; j < citasExistentes.size(); j++) {
                if (citasExistentes.get(j).getMedico().getId().equals(medico.getId())) {
                    ocupado = true;
                }
            }
            if (!ocupado) {
                medicosDisponibles.add(medico);
            }
        }
        if (medicosDisponibles.isEmpty()) {
            System.out.println("No hay medicos disponibles en esa fecha.");
            return;
        }

        System.out.println("Medicos disponibles en esa fecha:");
        for (int i = 0; i < medicosDisponibles.size(); i++) {
            Medico medico = medicosDisponibles.get(i);
            System.out.println((i + 1) + ". " + medico.getId() + " - " + medico.getNombre());
        }
        int opcionMedico = leerEntero("Elige un medico: ");
        if (opcionMedico < 1 || opcionMedico > medicosDisponibles.size()) {
            System.out.println("Opcion no valida.");
            return;
        }
        Medico medicoElegido = medicosDisponibles.get(opcionMedico - 1);
        Cita cita = new Cita("C" + System.nanoTime(), paciente, medicoElegido, fecha);
        consulta.getCitas().crearCita(cita);
        System.out.println("Cita creada: " + cita);
    }
    private static void cancelarCita() {
        List<Cita> citas = consulta.getCitas().citasActivas();
        if (citas.isEmpty()) {
            System.out.println("No hay citas activas para cancelar.");
            return;
        }
        System.out.println("Citas activas:");
        for (int i = 0; i < citas.size(); i++) {
            System.out.println(citas.get(i));
        }
        String id = leerTexto("ID de la cita: ");
        consulta.getCitas().cancelarCita(id);
        System.out.println("Cita cancelada.");
    }

    private static void mostrarVisitasRecientes() {
        List<Visita> recientes = consulta.getCitas().visitasRecientes(5);
        if (recientes.isEmpty()) {
            System.out.println("No hay visitas completadas.");
            return;
        }
        for (Visita visita : recientes) {
            System.out.println(visita);
        }
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

    private static void mostrarCitasDeFecha() {
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(leerTexto("Fecha (AAAA-MM-DD): "));
        } catch (java.time.DateTimeException e) {
            System.out.println("Fecha incorrecta. Usa el formato AAAA-MM-DD.");
            return;
        }
        List<Cita> citas = consulta.getCitas().citasDeFecha(fecha);
        if (citas.isEmpty()) {
            System.out.println("No hay citas para esa fecha.");
        } else {
            for (int i = 0; i < citas.size(); i++) {
                System.out.println(citas.get(i));
            }
        }
    }

    private static void mostrarTodasLasCitas() {
        List<Cita> citas = consulta.getCitas().citasActivas();
        if (citas.isEmpty()) {
            System.out.println("No hay citas activas.");
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
