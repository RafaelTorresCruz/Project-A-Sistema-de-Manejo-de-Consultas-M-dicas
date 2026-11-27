import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AdministrarPersonal {
    private Map<String, Empleado> empleados;
    private int siguienteNumeroID;

    public AdministrarPersonal() {
        empleados = new LinkedHashMap<String, Empleado>();
        siguienteNumeroID = 0;
    }

    public void registrarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede estar vacío.");
        }
        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del empleado no puede estar vacio.");
        }
        if (empleado.getId() == null || empleado.getId().equals("N/A")) {
            empleado.setId(generateNextID(empleado.getEmpleo()));
        }
        if (empleados.containsKey(empleado.getId())) {
            throw new IllegalArgumentException("Ya existe un empleado con ese ID.");
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
            case MEDICO:
                nuevoID += "M";
                break;
            case ENFERMERO:
                nuevoID += "E";
                break;
            case SECRETARIO:
                nuevoID += "S";
                break;
            default:
                throw new IllegalArgumentException("No existe este tipo de empleo");
        }
        nuevoID += String.format("%05d", siguienteNumeroID);
        while (empleados.containsKey(nuevoID)) {
            siguienteNumeroID++;
            nuevoID = nuevoID.substring(0, 1) + String.format("%05d", siguienteNumeroID);
        }
        siguienteNumeroID++;
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
       return empleados.get(ID);
    }

    public List<Empleado> todosLosEmpleados() {
        return new ArrayList<Empleado>(empleados.values());
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
