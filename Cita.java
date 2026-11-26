import java.time.LocalDate;

public class Cita {
    private String id;
    private Paciente paciente;
    private Medico medico;
    private LocalDate fecha;
    private boolean cancelada;

    public Cita(String id, Paciente paciente, Medico medico, LocalDate fecha) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.cancelada = false;
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

    public void cancelar() {
        cancelada = true;
    }

    public String toString() {
        String resultado = id + " | " + fecha + " | " + paciente.getNombre() + " | " + medico.getNombre();
        if (cancelada) {
            resultado = resultado + " | CANCELADA";
        }
        return resultado;
    }
}
