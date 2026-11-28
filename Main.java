import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner entrada = new Scanner(System.in);
    private static ConsultaMedica consulta = new ConsultaMedica();
    private static Empleado usuario;

    public static void main(String[] args) {
        //esto es solo un medico pa ejemplo para poder probar la atencion
        cargarDatosIniciales();
        limpiarConsola();
        boolean aceptado = true;
        while (!aceptado) {
            String ID = leerTexto("ID de Empleado: ");
            usuario = buscarEmpleadoPorID(ID);

            if (usuario != null) {
                String password = leerTexto("Contraseña: ");
                if (password.equals(usuario.password)) {
                    aceptado = true;
                    System.out.println("Bienvendos %s!".formatted(usuario.getNombre()));
                }
                else {
                    System.out.println("Contraseña Incorrecta. Saliendo...");
                }
            }
            else {
                System.out.println("ID no existe. Trata otra vez");
            }
        }

        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero("Selecciona una seccion: ");
            try {
                if (opcion == 1) {
                    limpiarConsola();
                    menuPacientes();
                } else if (opcion == 2) {
                    limpiarConsola();
                    menuCitas();
                } else if (opcion == 3 && tienePermiso("S")) {
                    limpiarConsola();
                    menuPersonal();
                } else if (opcion == 0) {
                    limpiarConsola();
                    continuar = false;
                } else {
                    System.out.println("Opcion no valida. Selecciona una de las opciones del menu.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("No se pudo completar: " + e.getMessage());
            }
        }
        System.out.println("==================================================");
        System.out.println("        CONSULTORIO OFTALMOLOGICO - MAYAGUEZ");
        System.out.println("                Hasta luego.");
        System.out.println("==================================================");
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
        System.out.println("3. Personal, medicos y especialidades");
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
            System.out.println("6. Tomar datos vitales de paciente");
            System.out.println("7. Registrar urgencia");
            System.out.println("0. Volver al menu principal");
            int opcion = leerEntero("Selecciona una opcion: ");
            try {
                if (opcion == 1 && tienePermiso("S")) {
                    mostrarTituloResultado("Registrar paciente nuevo");
                    registrarPaciente();
                    pausarResultado();
                } else if (opcion == 2 && tienePermiso("S")) {
                    mostrarTituloResultado("Agregar paciente a la fila de espera");
                    registrarLlegada();
                    pausarResultado();
                } else if (opcion == 3 && tienePermiso("S")) {
                    mostrarTituloResultado("Pacientes en espera");
                    mostrarPacientesEnEspera();
                    pausarResultado();
                } else if (opcion == 4 && tienePermiso("M")) {
                    mostrarTituloResultado("Atender al siguiente paciente");
                    atenderSiguiente();
                    pausarResultado();
                } else if (opcion == 5) {
                    mostrarTituloResultado("Historial clinico del paciente");
                    mostrarHistorial();
                    pausarResultado();
                } else if (opcion == 6 && tienePermiso("E")) {
                    mostrarTituloResultado("Tomando datos vitales del paciente");
                    tomarDatosVitales();
                    pausarResultado();
                }
                else if (opcion == 7 && tienePermiso("S")) {
                    mostrarTituloResultado("Registrar urgencia");
                    registrarUrgencia();
                    pausarResultado();
                }
                else if (opcion == 0) {
                    limpiarConsola();
                    regresar = true;
                } else {
                    System.out.println("Opcion no valida. Intenta de nuevo.");
                }
            } catch (IllegalArgumentException e) {
                mostrarError(e.getMessage());
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
                if (opcion == 1 && tienePermiso("S")) {
                    mostrarTituloResultado("Agendar una cita");
                    crearCita();
                    pausarResultado();
                } else if (opcion == 2) {
                    mostrarTituloResultado("Citas de hoy");
                    mostrarCitasDeHoy();
                    pausarResultado();
                } else if (opcion == 3) {
                    mostrarTituloResultado("Citas de una fecha");
                    mostrarCitasDeFecha();
                    pausarResultado();
                } else if (opcion == 4) {
                    mostrarTituloResultado("Citas pendientes");
                    mostrarTodasLasCitas();
                    pausarResultado();
                } else if (opcion == 5 && tienePermiso("S")) {
                    mostrarTituloResultado("Cancelar una cita");
                    cancelarCita();
                    pausarResultado();
                } else if (opcion == 0) {
                    limpiarConsola();
                    regresar = true;
                } else {
                    System.out.println("Opcion no valida. Intenta de nuevo.");
                }
            } catch (IllegalArgumentException e) {
                mostrarError(e.getMessage());
            }
        }
    }

    private static void menuPersonal() {
        boolean regresar = false;
        while (!regresar) {
            System.out.println();
            System.out.println("------------- PERSONAL Y MEDICOS -------------------");
            System.out.println("1. Buscar medicos por especialidad");
            System.out.println("2. Ver medicos disponibles ahora");
            System.out.println("3. Ver pacientes atendidos o citados con un medico");
            System.out.println("4. Registrar empleado");
            System.out.println("5. Buscar empleado por ID");
            System.out.println("6. Ver todo el personal");
            System.out.println("7. Eliminar medico");
            System.out.println("0. Volver al menu principal");
            int opcion = leerEntero("Selecciona una opcion: ");
            try {
                if (opcion == 1) {
                    mostrarTituloResultado("Buscar medicos por especialidad");
                    buscarMedicoPorEspecialidad();
                    pausarResultado();
                } else if (opcion == 2) {
                    mostrarTituloResultado("Medicos disponibles ahora");
                    mostrarMedicosDisponibles();
                    pausarResultado();
                } else if (opcion == 3) {
                    mostrarTituloResultado("Pacientes atendidos o citados con un medico");
                    mostrarPacientesDeMedico();
                    pausarResultado();
                } else if (opcion == 4 && tienePermiso("S")) {
                    mostrarTituloResultado("Registrar empleado");
                    registrarEmpleado();
                    pausarResultado();
                } else if (opcion == 5 && tienePermiso("S")) {
                    mostrarTituloResultado("Buscar empleado por ID");
                    mostrarEmpleadoPorID();
                    pausarResultado();
                } else if (opcion == 6) {
                    mostrarTituloResultado("Personal registrado");
                    mostrarPersonal();
                    pausarResultado();
                } else if (opcion == 7) {
                    mostrarTituloResultado("Eliminar medico");
                    eliminarMedico();
                    pausarResultado();
                } else if (opcion == 0) {
                    limpiarConsola();
                    regresar = true;
                } else {
                    System.out.println("Opcion no valida. Intenta de nuevo.");
                }
            } catch (IllegalArgumentException e) {
                mostrarError(e.getMessage());
            }
        }
    }
    private static boolean tienePermiso(String allowed) {
        if (usuario == null) {
            throw new NullPointerException("Usuario no encontrado");
        }
        Empleo empleo = usuario.getEmpleo();
        boolean permiso = false;
        switch (empleo) {
            case MEDICO:
                permiso = allowed.indexOf('M') != -1;
                break;
            case ENFERMERO:
                permiso = allowed.indexOf('E') != -1;
                break;
            case SECRETARIO:
                permiso = allowed.indexOf('S') != -1;
                break;
            default:
                throw new NullPointerException("Usuario no tiene un empleo asignado");
        }
        if (!permiso) {
            System.out.println("No tienes permiso para ver este contenido. Trata otra opcion.");
        }
        return permiso;
    }
    private static void tomarDatosVitales() {

    }
    private static void registrarEmpleado() {
        System.out.println("Tipo de empleado:");
        System.out.println("1. Medico");
        System.out.println("2. Enfermero");
        System.out.println("3. Secretaria");
        int tipo = leerEntero("Selecciona el tipo: ");
        String nombre = leerTexto("Nombre completo: ");
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
            return;
        }
        String password = leerTexto("Contrasena: ");
        if (password.isEmpty()) {
            System.out.println("La contrasena no puede estar vacia.");
            return;
        }

        Empleado empleado;
        if (tipo == 1) {
            Especialidad[] especialidades = Especialidad.values();
            System.out.println("Especialidades:");
            for (int i = 0; i < especialidades.length; i++) {
                System.out.println((i + 1) + ". " + especialidades[i]);
            }
            int opcion = leerEntero("Selecciona la especialidad: ");
            if (opcion < 1 || opcion > especialidades.length) {
                System.out.println("Opcion no valida.");
                return;
            }
            empleado = new Medico("N/A", nombre, password, especialidades[opcion - 1]);
        } else if (tipo == 2) {
            empleado = new Enfermero("N/A", nombre, password);
        } else if (tipo == 3) {
            empleado = new Secretaria("N/A", nombre, password);
        } else {
            System.out.println("Tipo de empleado no valido.");
            return;
        }

        consulta.getPersonal().registrarEmpleado(empleado);
        System.out.println("Empleado registrado con ID: " + empleado.getId());
    }

    private static void mostrarEmpleadoPorID() {
        String id = leerTexto("ID del empleado: ");
        Empleado empleado = consulta.getPersonal().buscarPorID(id);
        if (empleado == null) {
            System.out.println("No se encontro un empleado con ese ID.");
            return;
        }
        System.out.println("ID: " + empleado.getId());
        System.out.println("Nombre: " + empleado.getNombre());
        System.out.println("Puesto: " + empleado.getEmpleo());
        if (empleado instanceof Medico) {
            Medico medico = (Medico) empleado;
            System.out.println("Especialidad: " + medico.getEspecialidad());
            if (medico.isDisponible()) {
                System.out.println("Estado: Disponible");
            } else {
                System.out.println("Estado: Ocupado");
            }
        }
    }

    private static void mostrarPersonal() {
        List<Empleado> empleados = consulta.getPersonal().todosLosEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        System.out.println("PERSONAL REGISTRADO");
        for (int i = 0; i < empleados.size(); i++) {
            Empleado empleado = empleados.get(i);
            System.out.print(empleado.getId() + " - " + empleado.getNombre() + " - " + empleado.getEmpleo());
            if (empleado instanceof Medico) {
                Medico medico = (Medico) empleado;
                System.out.print(" - " + medico.getEspecialidad());
            }
            System.out.println();
        }
    }

    private static void eliminarMedico() {
        String id = leerTexto("ID del medico que desea eliminar: ");
        Empleado empleado = consulta.getPersonal().buscarPorID(id);
        if (empleado == null || !(empleado instanceof Medico)) {
            System.out.println("No se encontro un medico con ese ID.");
            return;
        }
        if (consulta.getCitas().tieneCitasPendientesMedico(id)) {
            System.out.println("No se puede eliminar: el medico tiene citas pendientes.");
            return;
        }
        String confirmar = leerTexto("Confirma la eliminacion (S/N): ");
        if (confirmar.equalsIgnoreCase("S")) {
            consulta.getPersonal().eliminarMedico(id);
            System.out.println("Medico eliminado.");
        } else {
            System.out.println("No se elimino el medico.");
        }
    }
    private static void registrarLlegada() {
        String id = leerTexto("ID del paciente: ");
        consulta.registrarLlegada(id);
        System.out.println("Paciente añadido a la fila de espera.");
}

    private static void registrarUrgencia() {
        String id = leerTexto("ID del paciente: ");
        int prioridad = leerEntero("Prioridad (1 = baja, 2 = media, 3 = critica): ");
        consulta.registrarUrgencia(id, prioridad);
        System.out.println("Paciente añadido a urgencias.");
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
        empleadosParaRegistrar.add(new Medico("N/A", "Dra. Morales", "admin", Especialidad.OFTALMOLOGIA_PEDIATRICA));
        empleadosParaRegistrar.add(new Medico("N/A", "Dr. Santos", "admin", Especialidad.NEUROOFTALMOLOGIA));
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
        Paciente paciente = consulta.getPacientes().verSiguienteParaAtender();
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
            System.out.println("No hay medico disponible para este paciente. Sigue en la fila.");
            return;
        }

        consulta.asignarPaciente();   
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

                if(paciente.isEmergencia() ){
                    System.out.println(paciente.getId() + " - " + paciente.getNombre() + " [URGENCIA prioridad " + paciente.getPrioridad() + "]");

                }

                else {
                System.out.println(paciente.getId() + " - " + paciente.getNombre());}
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

    private static void limpiarConsola() {
        System.out.print("\u001B[H\u001B[2J");
        System.out.flush();
    }

    private static void mostrarTituloResultado(String titulo) {
        limpiarConsola();
        System.out.println("==================================================");
        System.out.println("        CONSULTORIO OFTALMOLOGICO - MAYAGUEZ");
        System.out.println("                  RESULTADO");
        System.out.println("--------------------------------------------------");
        System.out.println(titulo);
        System.out.println("==================================================");
    }

    private static void pausarResultado() {
        System.out.println();
        System.out.print("Presiona ENTER para volver al menu...");
        entrada.nextLine();
        limpiarConsola();
    }

    private static void mostrarError(String mensaje) {
        limpiarConsola();
        System.out.println("==================================================");
        System.out.println("        CONSULTORIO OFTALMOLOGICO - MAYAGUEZ");
        System.out.println("                   RESULTADO");
        System.out.println("--------------------------------------------------");
        System.out.println("No se pudo completar la operacion:");
        System.out.println(mensaje);
        System.out.println("==================================================");
        pausarResultado();
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
