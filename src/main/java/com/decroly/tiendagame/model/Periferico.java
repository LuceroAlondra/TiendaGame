package com.decroly.tiendagame.model;

public class Periferico extends Product{
	TipoConsola consola;
	TipoPeriferico periferico;
	private boolean isNuevo;
	private boolean isWireless;
	private String marca;
	
	public Periferico(int id, String nombre, String sn, String descripcion, double precio, int cantidad, String edicion,
			long fLanzamiento, TipoConsola consola, TipoPeriferico periferico, boolean isNuevo, boolean isWireless,
			String marca) {
		super(id, nombre, sn, descripcion, precio, cantidad, edicion, fLanzamiento);
		this.consola = consola;
		this.periferico = periferico;
		this.isNuevo = isNuevo;
		this.isWireless = isWireless;
		this.marca = marca;
	}
	
	public Periferico() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TipoConsola getConsola() {
		return consola;
	}
	public void setConsola(TipoConsola consola) {
		this.consola = consola;
	}
	public TipoPeriferico getPeriferico() {
		return periferico;
	}
	public void setPeriferico(TipoPeriferico periferico) {
		this.periferico = periferico;
	}
	public boolean isNuevo() {
		return isNuevo;
	}
	public void setNuevo(boolean isNuevo) {
		this.isNuevo = isNuevo;
	}
	public boolean isWireless() {
		return isWireless;
	}
	public void setWireless(boolean isWireless) {
		this.isWireless = isWireless;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
}
