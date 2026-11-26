import java.util.ArrayList;
import java.util.List;

public class Paciente extends Persona {
    private List<Visita> historial;
    
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
}
