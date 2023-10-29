package com.decroly.tiendagame.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.decroly.tiendagame.model.Product;
import com.decroly.tiendagame.model.TipoConsola;
import com.decroly.tiendagame.model.TipoGenero;
import com.decroly.tiendagame.repository.ProductRepository;

@Service
public class TiendaService 
{
	//Instancia al repositorio
	private final ProductRepository productRepository;
	
	
	//Inicializamos el repositorio(es un singleton)
	public TiendaService(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}
	
	//Productos
	public Product getProductById(int id)
	{
		return this.productRepository.getProductById(id);
	}

	public Product getProductByName(String nombre) {
		// TODO Auto-generated method stub
		return this.productRepository.getProductByName(nombre);
	}
	
	public Product getProductBySn(String sn) {
		return this.productRepository.getProductBySn(sn);
	}
	
	//Videojuegos
	public List<Product> getAllVideojuegos() {
		return this.productRepository.getAllVideojuegos();
	}

	public List<Product> getAllVideojuegoAscendente() {
		return this.productRepository.getAllVideojuegoAscendente();
	}
	
	//Consolas
	public List<Product> getAllConsolas() {
		return this.productRepository.getAllConsolas();
	}

	public List<Product> getVideojuegoByGenero(List<TipoGenero> genero) {
		// TODO Auto-generated method stub
		return this.productRepository.getVideojuegoByGenero(genero);
	}

	public List<Product> getVideojuegosNuevos() {
		// TODO Auto-generated method stub
		return this.productRepository.getVideojuegosNuevos();
	}

	public Product getVideojuegoByFecha(long fecha) {
		// TODO Auto-generated method stub
		return this.productRepository.getVideojuegoByFecha(fecha);
	}

	public List<Product> getVideojuegoByConsola(List<TipoConsola> consola) {
		// TODO Auto-generated method stub
		return this.productRepository.getVideojuegoByConsola(consola);
	}

	public List<Product> getVideojuegoPorUnaConsola(List<TipoConsola> consola) {
		// TODO Auto-generated method stub
		return this.productRepository.getVideojuegoPorUnaConsola(consola);
	}

	public List<Product> getAllConsolasAscendente() {
		// TODO Auto-generated method stub
		return this.productRepository.getAllConsolasAscendente();
	}

	public Product getconsolaByName(String name) {
		return this.productRepository.getconsolaByName(name);
	}

	public List<Product> getConsolaByTipo(List<TipoConsola> consola) {
		// TODO Auto-generated method stub
		return this.productRepository.getConsolaByTipo(consola);
	}

	public List<Product> getConsolasNuevas() {
		// TODO Auto-generated method stub
		return this.productRepository.getConsolasNuevas();
	}

	public Product getPerifericoByName(String nombre) {
		return this.productRepository.getPerifericoByName(nombre);
	}

	public List<Product> getPerifericoByTipo(List<TipoConsola> consola) {
		// TODO Auto-generated method stub
		return this.productRepository.getPerifericoByTipo(consola);
	}

	public void guardar(Product producto) {
		this.productRepository.guardar(producto);
	}

}
