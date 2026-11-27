import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdministrarCitas {
    private List<Cita> citas;
    private List<Visita> visitas;

    public AdministrarCitas() {
        citas = new ArrayList<Cita>();
        visitas = new ArrayList<Visita>();
    }

    public void crearCita(Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita no puede estar vacía.");
        }
        if (cita.getId() == null || cita.getId().trim().isEmpty() || cita.getPaciente() == null
                || cita.getMedico() == null || cita.getFecha() == null) {
            throw new IllegalArgumentException("La cita debe tener ID, paciente, médico y fecha.");
        }
        if (cita.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se pueden crear citas en fechas pasadas.");
        }
        for (Cita existente : citas) {
            if (existente.getId().equals(cita.getId())) {
                throw new IllegalArgumentException("Ya existe una cita con ese ID.");
            }
            if (!existente.isCancelada() && existente.getFecha().equals(cita.getFecha())) {
                if (existente.getMedico().equals(cita.getMedico())) {
                    throw new IllegalArgumentException("El médico ya tiene una cita en esa fecha.");
                }
                if (existente.getPaciente().equals(cita.getPaciente())) {
                    throw new IllegalArgumentException("El paciente ya tiene una cita en esa fecha.");
                }
            }
        }
        citas.add(cita);
    }

    public void cancelarCita(String id) {
        for (int i = 0; i < citas.size(); i++) {
            Cita cita = citas.get(i);
            if (cita.getId().equals(id) && !cita.isCancelada() && !cita.isCompletada()) {
                cita.cancelar();
                return;
            }
        }
        throw new IllegalArgumentException("No se encontró una cita con ese ID.");
    }

    public void registrarVisita(Visita visita) {
        if (visita == null || visita.getCita() == null) {
            throw new IllegalArgumentException("La visita no puede estar vacía.");
        }
        Cita cita = visita.getCita();
        if (cita.isCancelada()) {
            throw new IllegalArgumentException("No se puede registrar una visita para una cita cancelada.");
        }
        if (cita.isCompletada()) {
            throw new IllegalArgumentException("Esta cita ya fue completada.");
        }
        visitas.add(visita);
        cita.completar();
        // La visita también se añade al historial de su paciente.
        cita.getPaciente().agregarVisita(visita);
    }

    public Cita buscarCitaDePacienteEnFecha(String idPaciente, LocalDate fecha) {
        for (int i = 0; i < citas.size(); i++) {
            Cita cita = citas.get(i);
            if (cita.getPaciente().getId().equals(idPaciente)
                    && cita.getFecha().equals(fecha)
                    && !cita.isCancelada()
                    && !cita.isCompletada()) {
                return cita;
            }
        }
        return null;
    }

    public List<Cita> citasDeHoy() {
        // Se muestran solo las citas de hoy que no fueron canceladas.
        List<Cita> citasHoy = new ArrayList<Cita>();
        LocalDate hoy = LocalDate.now();
        for (int i = 0; i < citas.size(); i++) {
            Cita cita = citas.get(i);
            if (cita.getFecha().equals(hoy) && !cita.isCancelada()) {
                citasHoy.add(cita);
            }
        }
        return citasHoy;
    }

    public List<Cita> citasDeFecha(LocalDate fecha) {
        List<Cita> citasFecha = new ArrayList<Cita>();
        for (int i = 0; i < citas.size(); i++) {
            Cita cita = citas.get(i);
            if (cita.getFecha().equals(fecha) && !cita.isCancelada()) {
                citasFecha.add(cita);
            }
        }
        return citasFecha;
    }

    public List<Cita> citasActivas() {
        List<Cita> citasActivas = new ArrayList<Cita>();
        for (int i = 0; i < citas.size(); i++) {
            Cita cita = citas.get(i);
            if (!cita.isCancelada() && !cita.isCompletada()) {
                citasActivas.add(cita);
            }
        }
        return citasActivas;
    }

    public List<Cita> citasDePaciente(String idPaciente) {
        List<Cita> citasPaciente = new ArrayList<Cita>();
        for (int i = 0; i < citas.size(); i++) {
            Cita cita = citas.get(i);
            if (cita.getPaciente().getId().equals(idPaciente)) {
                citasPaciente.add(cita);
            }
        }
        return citasPaciente;
    }

    public List<Paciente> pacientesDeMedico(String idMedico) {
        List<Paciente> pacientesMedico = new ArrayList<Paciente>();

        for (int i = 0; i < citas.size(); i++) {
            Cita cita = citas.get(i);
            if (!cita.isCancelada() && cita.getMedico().getId().equals(idMedico)) {
                if (!pacientesMedico.contains(cita.getPaciente())) {
                    pacientesMedico.add(cita.getPaciente());
                }
            }
        }

        for (int i = 0; i < visitas.size(); i++) {
            Cita cita = visitas.get(i).getCita();
            if (cita.getMedico().getId().equals(idMedico)) {
                if (!pacientesMedico.contains(cita.getPaciente())) {
                    pacientesMedico.add(cita.getPaciente());
                }
            }
        }

        return pacientesMedico;
    }

    public List<Cita> citas() {
        return new ArrayList<Cita>(citas);
    }

    public List<Visita> visitasRecientes(int limite) {
        List<Visita> recientes = new ArrayList<Visita>();
        for (int i = visitas.size() - 1; i >= 0 && recientes.size() < limite; i--) {
            recientes.add(visitas.get(i));
        }
        return recientes;
    }
}
