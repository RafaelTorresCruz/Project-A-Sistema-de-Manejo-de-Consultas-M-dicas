import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AdministrarPacientes {
    private Map<String, Paciente> pacientes;
    private Queue<Paciente> fila;
    private Queue<Paciente> urgenciaMax;
    private Queue<Paciente> urgenciaMid;     
    private Queue<Paciente> urgenciaLow;


    public AdministrarPacientes() {
        pacientes = new LinkedHashMap<String, Paciente>();
        fila = new ArrayDeque<Paciente>();
        urgenciaMax = new ArrayDeque<Paciente>();
        urgenciaMid = new ArrayDeque<Paciente>();
        urgenciaLow = new ArrayDeque<Paciente>();

        
    }

    public void registrarPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede estar vacío.");
        }
        if (paciente.getNombre() == null || paciente.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del paciente no puede estar vacío.");
        }
        String nombreNuevo = paciente.getNombre().trim();
        for (Paciente pacienteExistente : pacientes.values()) {
            if (pacienteExistente.getNombre().trim().equalsIgnoreCase(nombreNuevo)) {
                throw new IllegalArgumentException("Ya existe un paciente con ese nombre.");
            }
        }
        if (paciente.getId() == null || paciente.getId().equals("N/A")) {
            paciente.setId(generateNextID());
        }
        // El ID identifica al paciente y evita guardar el mismo registro dos veces.
        if (pacientes.containsKey(paciente.getId())) {
            throw new IllegalArgumentException("Ya existe un paciente con ese ID.");
        }
        pacientes.put(paciente.getId(), paciente);
    }

    public String generateNextID() {
        String nuevoID = String.format("%06d",pacientes.size());
        return nuevoID;
    }

    public void registrarPacientes(ArrayList<Paciente> pacientes) {
        for (Paciente paciente : pacientes) {
            registrarPaciente(paciente);
        }
    }

    public void anadirAFila(Paciente paciente) {
        if (paciente == null || !pacientes.containsKey(paciente.getId())) {
            throw new IllegalArgumentException("Registra primero al paciente.");
        }
        if (!fila.contains(paciente)) {
            fila.add(paciente);
        }
    }

    public void anadirUrgencia(Paciente paciente, int prioridad){
        if (paciente == null || !pacientes.containsKey(paciente.getId())) {
            throw new IllegalArgumentException("Registre primero al paciente.");
        }

        if (prioridad < 1 || prioridad > 3) {
            throw new IllegalArgumentException("La prioridad debe ser 1, 2 o 3.");
        }

        if (urgenciaMax.contains(paciente) || urgenciaMid.contains(paciente) || urgenciaLow.contains(paciente)) {
        throw new IllegalArgumentException("Ese paciente ya está en urgencias.");
        }

        fila.remove(paciente);
        paciente.setEmergencia(true, prioridad);

        if (prioridad == 3) {
        urgenciaMax.add(paciente);
    } else if (prioridad == 2) {
        urgenciaMid.add(paciente);
    } else {
        urgenciaLow.add(paciente);
    }
    }


    public Paciente siguienteParaAtender() {
    Paciente siguiente = null;

    if (!urgenciaMax.isEmpty()) {
        siguiente = urgenciaMax.remove();
    } else if (!urgenciaMid.isEmpty()) {
        siguiente = urgenciaMid.remove();
    } else if (!urgenciaLow.isEmpty()) {
        siguiente = urgenciaLow.remove();
    } else {
        return siguienteEnFila();
    }


    siguiente.setEmergencia(false, 0);   
    return siguiente;
}




    public Paciente siguienteEnFila() {
        if (fila.isEmpty()) {
            return null;
        }
        // Queue conserva el orden de llegada: primero en entrar, primero en salir.
        return fila.remove();
    }

    public List<Paciente> pacientesEnEspera() {
        List<Paciente> resultado = new ArrayList<Paciente>(urgenciaMax);
        resultado.addAll(urgenciaMid);
        resultado.addAll(urgenciaLow);
        resultado.addAll(fila);
        return resultado;
    }

    public Paciente verSiguienteParaAtender() {
    if (!urgenciaMax.isEmpty()) {
        return urgenciaMax.peek();
    }
    if (!urgenciaMid.isEmpty()) {
        return urgenciaMid.peek();
    }
    if (!urgenciaLow.isEmpty()) {
        return urgenciaLow.peek();
    }
    return fila.peek();   
}

    public Paciente buscarPorId(String id) {
        return pacientes.get(id);
    }
}
