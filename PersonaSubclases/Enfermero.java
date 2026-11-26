public class Enfermero extends Empleado {

    public Enfermero(String id, String nombre, String password) {
        super(id, nombre, password);
        empleo = Empleo.ENFERMERO;
    }}
