public abstract class Empleado extends Persona {
    protected Empleo empleo;
    protected String password;
    public Empleado(String id, String nombre, String password) {
        super(id, nombre);
        this.password = password;
    }

    public Empleo getEmpleo() {
        return this.empleo;
    }
    
    public String getPassword() {
        return this.password;
    }

    public void setEmpleo(Empleo newEmpleo) {
        this.empleo = newEmpleo;
    }
    
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
