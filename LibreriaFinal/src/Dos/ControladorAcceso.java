package Dos;

public class ControladorAcceso extends Trabajador {

	// Constructor
	public ControladorAcceso(String nombre, String apellido, String telefono, String dni, Turno turno,
			boolean contratado, String contrasenia) {

		super(nombre, apellido, telefono, dni, turno, contratado, contrasenia);
	}

	@Override
	public String toString() {
		return "ID: " + id_trabajador + "CONTROLADOR DE ACCESO. Nombre: " + nombre + ", Apellidos: " + apellido + ", Teléfono: " + telefono + ", DNI: "
				+ dni + ", Contraseña: " + contrasenia + ", Turno: " + turno
				+ ", Contratado: " + contratado + ".";
	}

}
