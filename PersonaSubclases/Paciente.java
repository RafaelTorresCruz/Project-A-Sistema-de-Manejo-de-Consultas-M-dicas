import java.util.ArrayList;
import java.util.List;

public class Paciente extends Persona {
    private List<Visita> historial;
    private boolean emergencia;
    private int prioridad;
    
    public Paciente(String id, String nombre) {
        super(id, nombre);
        historial = new ArrayList<Visita>();
    }
    public List<Visita> getHistorial() {
        return new ArrayList<Visita>(historial);
    }
    public void agregarVisita(Visita visita) {
        if (visita != null) {
            historial.add(visita);
        }
    }

    public boolean isEmergencia() { return emergencia; }
    public int getPrioridad() { return prioridad; }


    public void setEmergencia(boolean emergencia, int prioridad) {
        this.emergencia = emergencia;
        this.prioridad = prioridad;
    }
}
