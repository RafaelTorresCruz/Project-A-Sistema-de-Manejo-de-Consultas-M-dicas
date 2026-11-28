public class Enfermero extends Empleado {
    private Visita visitaActual;
    public Enfermero(String id, String nombre, String password) {
        super(id, nombre, password);
        empleo = Empleo.ENFERMERO;
        visitaActual = null;
    }

    public void asignarVisita(Visita visitaNueva) {
        this.visitaActual = visitaNueva;
    }

    public Visita getVisita() {
        return this.visitaActual;
    }
}

