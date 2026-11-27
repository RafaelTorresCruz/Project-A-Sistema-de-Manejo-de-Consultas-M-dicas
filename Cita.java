import java.time.LocalDate;

public class Cita {
    private String id;
    private Paciente paciente;
    private Medico medico;
    private LocalDate fecha;
    private boolean cancelada;
    private boolean completada;

    public Cita(String id, Paciente paciente, Medico medico, LocalDate fecha) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.cancelada = false;
        this.completada = false;
    }

    public String getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void cancelar() {
        cancelada = true;
    }

    public void completar() {
        completada = true;
    }

    public String toString() {
        String resultado = id + " | " + fecha + " | " + paciente.getNombre() + " | " + medico.getNombre();
        if (cancelada) {
            resultado = resultado + " | CANCELADA";
        } else if (completada) {
            resultado = resultado + " | COMPLETADA";
        } else {
            resultado = resultado + " | PENDIENTE";
        }
        return resultado;
    }
}
