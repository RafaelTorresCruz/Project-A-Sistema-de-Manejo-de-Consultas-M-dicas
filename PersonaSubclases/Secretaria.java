public class Secretaria extends Empleado {

    public Secretaria(String id, String nombre, String password) {
        super(id, nombre, password);
        empleo = Empleo.SECRETARIO;
    }}
