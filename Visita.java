public class Visita {
    private Cita cita;
    private String notas;

    public Visita(Cita cita) {
        this.cita = cita;
        this.notas = "";
    }

    public Visita(Cita cita, String notas) {
        this.cita = cita;
        this.notas = notas;
    }

    public Cita getCita() {
        return cita;
    }

    public String getNotas() {
        return notas;
    }

    public String toString() {
        return cita.toString() + " | Nota: " + notas;
    }
}
