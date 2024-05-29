package Dos;

import java.util.*;

public class Dependiente extends Trabajador {
	private static final String ANSI_BLUE = "\u001B[34m";
	private static final String ANSI_RESET = "\u001B[0m";
	private double facturacion;
	private double beneficio;
	private int cantidadVentas;
	private Map<Billete, ArrayList<Libro>> ventasPorBillete;
	static ArrayList<Dependiente> listadoDependientes = new ArrayList<>();

	// Constructor
	public Dependiente(String nombre, String apellido, String telefono, String dni, Turno turno, boolean contratado,
			String contrasenia) {
		super(nombre, apellido, telefono, dni, turno, contratado, contrasenia);
		this.facturacion = 0;
		this.beneficio = 0;
		this.cantidadVentas = 0;
		this.ventasPorBillete = new HashMap<>();

		listadoDependientes.add(this);
	}

	
	
	
	
	@Override
	public String toString() {
		return "ID: " + id_trabajador + "DEPENDIENTE. Nombre: " + nombre + ", Apellidos: " + apellido + ", Teléfono: " + telefono + ", DNI: "
				+ dni + ", Contraseña: " + contrasenia + ", Turno: " + turno
				+ ", Contratado: " + contratado + ".";
	}



	// Método para mostrar ventas por billete ordenadas
	public void mostrarVentasPorBillete() {
		ArrayList<Billete> billetesPremium = new ArrayList<>();
		ArrayList<Billete> billetesNormales = new ArrayList<>();

		for (Map.Entry<Billete, ArrayList<Libro>> entry : ventasPorBillete.entrySet()) {
			Billete billete = entry.getKey();
			if (billete instanceof BilletePremium) {
				billetesPremium.add(billete);
			} else {
				billetesNormales.add(billete);
			}
		}

		System.out.println("Ventas por billete premium:");
		for (Billete billete : billetesPremium) {
			System.out.println(
					ANSI_BLUE + "Billete ID: " + billete.getId() + " ha comprado los siguientes libros:" + ANSI_RESET);
			for (Libro libro : ventasPorBillete.get(billete)) {
				System.out.println(ANSI_BLUE + " - " + libro.getTitulo() + ANSI_RESET);
			}
		}

		System.out.println("Ventas por billete normal:");
		for (Billete billete : billetesNormales) {
			System.out.println("Billete ID: " + billete.getId() + " ha comprado los siguientes libros:");
			for (Libro libro : ventasPorBillete.get(billete)) {
				System.out.println(" - " + libro.getTitulo());
			}
		}
	}

	// Método para vender un libro
	public void venderLibro(Billete billete, Libro libro, int cantidad) {
		if (libro.getStock() >= cantidad) {
			libro.reducirStock(cantidad);
			double totalVenta = libro.getPrecio() * cantidad;
			this.facturacion += totalVenta;
			double beneficioVenta = libro.isEdicionPropia() ? totalVenta * 0.20 : totalVenta * 0.10;
			this.beneficio += beneficioVenta;

			incrementarVentas(cantidad);

			if (!ventasPorBillete.containsKey(billete)) {
				ventasPorBillete.put(billete, new ArrayList<>());
			}
			ventasPorBillete.get(billete).add(libro);
		} else {
			System.out.println("Stock insuficiente para el libro: " + libro.getTitulo());
		}
	}

	public void venderMerchandising(Billete billete, Merchandising merchandising, int cantidad) {
		if (merchandising.tieneStock(cantidad)) {
			merchandising.vender(cantidad);
			double totalVenta = merchandising.getPrecio() * cantidad;
			this.facturacion += totalVenta; // Sumar a la facturación del dependiente
			
			double beneficioVenta =  totalVenta * 0.20;
			this.beneficio += beneficioVenta;
			
			incrementarVentas(cantidad); // Incrementar la cantidad de ventas
			System.out.println("Venta realizada: " + cantidad + " unidades de \"" + merchandising.getDescripcion()
					+ "\" por " + totalVenta + " euros.");
		} else {
			System.out.println("Stock insuficiente para el producto: " + merchandising.getDescripcion());
		}
	}

	public void mostrarResumenVentas() {
		System.out.println("Dependiente: " + nombre + " " + apellido);
		System.out.println("Cantidad de ventas: " + cantidadVentas);
		System.out.println("Facturación total: " + facturacion);
		System.out.println("Beneficio total: " + beneficio);
	}



	// Getters & setters
	public double getFacturacion() {
		return facturacion;
	}

	public void setFacturacion(double facturacion) {
		this.beneficio = beneficio;
	}

	public double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(double beneficio) {
		this.facturacion = beneficio;
	}

	public int getCantidadVentas() {
		return cantidadVentas;
	}

	public void incrementarVentas(int cantidad) {
		this.cantidadVentas += cantidad;
	}

	public Map<Billete, ArrayList<Libro>> getVentasPorBillete() {
		return ventasPorBillete;
	}

}