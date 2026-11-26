public class ConsultaMedica {


    private AdministrarPersonal personal;
    private AdministrarPacientes pacientes;
    private AdministrarCitas citas;

    public ConsultaMedica() {
        personal = new AdministrarPersonal();
        pacientes = new AdministrarPacientes();
        citas = new AdministrarCitas();
    }

    public Paciente asignarPaciente() {
        // Toma al siguiente paciente según el orden de la fila.
        return pacientes.siguienteEnFila();
    }

    public void marcarPacienteAtendido(Visita visita) {
        if (visita != null) {
            visita.getCita().getMedico().setDisponible(true);
        }
    }


    public AdministrarPersonal getPersonal() {
        return personal;
    }

    public AdministrarPacientes getPacientes() {
        return pacientes;
    }

    public AdministrarCitas getCitas() {
        return citas;
    }


}
