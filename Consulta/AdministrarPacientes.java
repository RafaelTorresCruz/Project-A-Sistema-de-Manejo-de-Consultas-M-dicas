import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AdministrarPacientes {
    private Map<String, Paciente> pacientes;
    private Queue<Paciente> fila;

    public AdministrarPacientes() {
        pacientes = new LinkedHashMap<String, Paciente>();
        fila = new ArrayDeque<Paciente>();
    }

    public void registrarPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede estar vacío.");
        }
        // El ID identifica al paciente y evita guardar el mismo registro dos veces.
        if (pacientes.containsKey(paciente.getId())) {
            throw new IllegalArgumentException("Ya existe un paciente con ese ID.");
        }
        pacientes.put(paciente.getId(), paciente);
    }

    public void anadirAFila(Paciente paciente) {
        if (paciente == null || !pacientes.containsKey(paciente.getId())) {
            throw new IllegalArgumentException("Registra primero al paciente.");
        }
        if (!fila.contains(paciente)) {
            fila.add(paciente);
        }
    }

    public Paciente siguienteEnFila() {
        if (fila.isEmpty()) {
            return null;
        }
        // Queue conserva el orden de llegada: primero en entrar, primero en salir.
        return fila.remove();
    }

    public List<Paciente> pacientesEnEspera() {
        return new ArrayList<Paciente>(fila);
    }

    public Paciente buscarPorId(String id) {
        return pacientes.get(id);
    }
}
