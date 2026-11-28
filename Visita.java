public class Visita {
    private Cita cita;
    private String notasDelEnfermero;
    private String notasDelMedico;

    public Visita(Cita cita) {
        this.cita = cita;
        this.notasDelEnfermero = "";
        this.notasDelMedico = "";
    }

    public Visita(Cita cita, String notasDelMedico, String notasDelEnfermero) {
        this.cita = cita;
        this.notasDelMedico = notasDelMedico;
        this.notasDelEnfermero = notasDelEnfermero;
    }

    public Cita getCita() {
        return cita;
    }

    public String getNotasDelMedico() {
        return notasDelMedico;
    }

    public void setNotasDelMedico(String notasDelMedico) {
        this.notasDelMedico = notasDelMedico;
    }

    public String getNotasDelEnfermero() {
        return notasDelEnfermero;
    }

    public void setNotasDelEnfermero(String notasDelEnfermero) {
        this.notasDelEnfermero = notasDelEnfermero;
    }

    public String toString() {
        String val = cita.toString();
        if (notasDelMedico.length() > 0) {
            val += "\nNotas del Medico: " + notasDelMedico;
        }
        if (notasDelEnfermero.length() > 0) {
            val += "\nNotas del Enfermero: " + notasDelEnfermero;
        }
        return val;
    }
}
