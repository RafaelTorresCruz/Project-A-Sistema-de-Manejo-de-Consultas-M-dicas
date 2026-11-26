public class Medico extends Empleado {

    private Especialidad especialidad;
    private boolean disponible;



    public Medico(String id, String nombre, Especialidad especialidad) {
        super(id, nombre);
        this.especialidad = especialidad; 
        this.disponible = true;


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
