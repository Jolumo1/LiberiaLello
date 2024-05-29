package Dos;

public class Vigilante extends Trabajador {

    // Constructor
    public Vigilante(String nombre, String apellido, String telefono, String dni, Turno turno, boolean contratado, String contrasenia) {
        super(nombre, apellido, telefono, dni, turno, contratado, contrasenia);
    }


    
	@Override
	public String toString() {
		return "ID: " + id_trabajador + "VIGILANTE. Nombre: " + nombre + ", Apellidos: " + apellido + ", Teléfono: " + telefono + ", DNI: "
				+ dni + ", Contraseña: " + contrasenia + ", Turno: " + turno
				+ ", Contratado: " + contratado + ".";
	}



	public static void vigilar() {
    	System.out.println("El vigilante vigila, ¿Pero quién vigila al vigilante?" );
    }
    
}