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
        citas.add(cita);
    }

    public void cancelarCita(String id) {
        for (int i = 0; i < citas.size(); i++) {
            Cita cita = citas.get(i);
            if (cita.getId().equals(id)) {
                cita.cancelar();
                return;
            }
        }
        throw new IllegalArgumentException("No se encontró una cita con ese ID.");
    }

    public void registrarVisita(Visita visita) {
        if (visita == null) {
            throw new IllegalArgumentException("La visita no puede estar vacía.");
        }
        visitas.add(visita);
        // La visita también se añade al historial de su paciente.
        visita.getCita().getPaciente().agregarVisita(visita);
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
}
