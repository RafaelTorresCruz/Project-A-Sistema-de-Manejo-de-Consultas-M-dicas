import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AdministrarPersonal {
    private Map<String, Empleado> empleados;

    public AdministrarPersonal() {
        empleados = new LinkedHashMap<String, Empleado>();
    }

    public void registrarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede estar vacío.");
        }
        if (empleados.containsKey(empleado.getId())) {
            throw new IllegalArgumentException("Ya existe un empleado con ese ID.");
        }
        if (empleado.getId().equals("N/A")) {
            empleado.setId(generateNextID(empleado.getEmpleo()));
        }
        empleados.put(empleado.getId(), empleado);
    }

    public void registrarEmpleados(ArrayList<Empleado> empleados) {
        for (Empleado empleado : empleados) {
            registrarEmpleado(empleado);
        }
    }

    public String generateNextID(Empleo empleo) {
        String nuevoID = "";
        switch (empleo) {
            case Empleo.MEDICO:
                nuevoID += "M";
                break;
            case Empleo.ENFERMERO:
                nuevoID += "E";
                break;
            case Empleo.SECRETARIO:
                nuevoID += "S";
                break;
            default:
                throw new IllegalArgumentException("No existe este tipo de empleo");
        }
        nuevoID += String.format("%05d", empleados.size());
        return nuevoID;
    }

    public void eliminarMedico(String id) {
        Empleado empleado = empleados.get(id);
        if (empleado == null || !(empleado instanceof Medico)) {
            throw new IllegalArgumentException("No se encontró un médico con ese ID.");
        }
        empleados.remove(id);
    }

    public List<Medico> buscarPorEspecialidad(Especialidad especialidad) {
        List<Medico> resultado = new ArrayList<Medico>();
        for (Empleado empleado : empleados.values()) {
            if (empleado.getEmpleo() == Empleo.MEDICO) {
                Medico medico = (Medico) empleado;
                if (medico.getEspecialidad() == especialidad) {
                    resultado.add(medico);
                }
            }
        }
        return resultado;
    }

    public Empleado buscarPorID(String ID) {
        for (Empleado empleado : empleados.values()) {
            if (empleado.getId().equals(ID)) {
                return empleado;
            }
        }
        return null;
    }

    public List<Medico> medicosDisponibles() {
        // Recorremos el personal y devolvemos únicamente los médicos disponibles.
        List<Medico> resultado = new ArrayList<Medico>();
        for (Empleado empleado : empleados.values()) {
            if (empleado.getEmpleo() == Empleo.MEDICO) {
                Medico medico = (Medico) empleado;
                if (medico.isDisponible()) {
                    resultado.add(medico);
                }
            }
        }
        return resultado;
    }
}
