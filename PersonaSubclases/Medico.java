public class Medico extends Empleado {

    private Especialidad especialidad;
    private boolean disponible;



    public Medico(String id, String nombre, String password, Especialidad especialidad) {
        super(id, nombre, password);
        this.especialidad = especialidad; 
        this.disponible = true;

        empleo = Empleo.MEDICO;

    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }
    public boolean isDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
