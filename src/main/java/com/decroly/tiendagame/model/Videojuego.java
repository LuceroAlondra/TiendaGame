package com.decroly.tiendagame.model;

public class Videojuego extends Product{
	TipoConsola consola;
	TipoGenero genero;
	private boolean isDigital;
	private boolean isNuevo;
	
	public Videojuego(int id, String nombre, String sn, String descripcion, double precio, int cantidad, String edicion,
			long fLanzamiento, TipoConsola consola, TipoGenero genero, boolean isDigital, boolean isNuevo) {
		super(id, nombre, sn, descripcion, precio, cantidad, edicion, fLanzamiento);
		this.consola = consola;
		this.genero = genero;
		this.isDigital = isDigital;
		this.isNuevo = isNuevo;
	}
	
	public Videojuego() {
		super();
	}
	

	public TipoConsola getConsola() {
		return consola;
	}
	public void setConsola(TipoConsola consola) {
		this.consola = consola;
	}
	public TipoGenero getGenero() {
		return genero;
	}
	public void setGenero(TipoGenero genero) {
		this.genero = genero;
	}
	public boolean isDigital() {
		return isDigital;
	}
	public void setDigital(boolean isDigital) {
		this.isDigital = isDigital;
	}
	public boolean isNuevo() {
		return isNuevo;
	}
	public void setNuevo(boolean isNuevo) {
		this.isNuevo = isNuevo;
	}
}

