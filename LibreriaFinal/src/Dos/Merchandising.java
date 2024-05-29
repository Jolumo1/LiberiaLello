package Dos;

import java.util.ArrayList;

public class Merchandising {

	private int codigoProducto;
	private Tipo tipo;
	private static int codigoAux = 1;
	private String descripcion;
	private double precio;
	private int stock;

	// arraylist para guardar todos los objetos merchan creados de forma automatica
	static ArrayList<Merchandising> listadoMerchandising = new ArrayList<>();

	// Constructor
	public Merchandising(int codigoProducto, Tipo tipo, String descripcion, double precio, int stock) {
		this.codigoProducto = codigoAux;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		codigoAux++;

		// Añade automáticamente el libro creado a ListadoLibros
		listadoMerchandising.add(this);
	}

	public boolean tieneStock(int cantidad) {
		return stock >= cantidad;
	}

	public void vender(int cantidad) {
		if (tieneStock(cantidad)) {
			stock -= cantidad;
		}
	}

	@Override
	public String toString() {
		return "Código Producto: " + codigoProducto + ", Tipo: " + tipo + ", Descripción: " + descripcion + ", Precio: "
				+ precio + ", Stock: " + stock;
	}

	// Getters y setters
	public int getCodigoProducto() {
		return codigoProducto;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}