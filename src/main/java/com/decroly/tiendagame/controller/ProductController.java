package com.decroly.tiendagame.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.decroly.tiendagame.model.Consola;
import com.decroly.tiendagame.model.Product;
import com.decroly.tiendagame.model.TipoConsola;
import com.decroly.tiendagame.model.TipoGenero;
import com.decroly.tiendagame.repository.ProductRepository;
import com.decroly.tiendagame.service.TiendaService;

@RestController
@RequestMapping("/products")
public class ProductController 
{
	//Instancia del servicio
	private final TiendaService tiendaService;
	
	
	public ProductController(TiendaService tiendaService) {
		this.tiendaService = tiendaService;
	}
	
	//Ejemplos de busqueda de productos en general
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable("id") int id)
	{
		return this.tiendaService.getProductById(id);
	}
	@GetMapping("/sn/{sn}")
	public Product getProductBySn(@PathVariable("sn") String sn) {
	    return this.tiendaService.getProductBySn(sn);
	}
	
	//Ejemplos de busqueda por la clase Consola
	@PostMapping("consola/add") 
	public void setProduct(@RequestBody Consola producto)
	{
		this.tiendaService.guardar(producto);
	}

	//Debemos buscar por lista cuando se trata de devolver más de un producto
	//Obtenemos todos los videojuego
	@GetMapping("/videojuego/")
	public List<Product> getAllVideojuegoByName() {
	    return this.tiendaService.getAllVideojuegos();
	}
	//Los videojuegos con precios ascendentes
	@GetMapping("/videojuego/asc")
	public List<Product> getAllVideojuegoAscendente() {
	    return this.tiendaService.getAllVideojuegoAscendente();
	}
	//Videojuegos por nombre
	@GetMapping("/videojuego/nombre/{name}")
	public Product getProductByName(@PathVariable("name") String name) {
	    return this.tiendaService.getProductByName(name);
	}
	
	@GetMapping("/videojuego/genero")
	public List<Product> getVideojuegoByGenero(@RequestParam("genero") List<String> genero) {
		List<TipoGenero> tipoGeneros = genero.stream()
	            .map(g -> TipoGenero.valueOf(g))
	            .collect(Collectors.toList());
	    return this.tiendaService.getVideojuegoByGenero(tipoGeneros);
	}
	
	@GetMapping("/videojuego/consola")
	public List<Product> getVideojuegoByConsola(@RequestParam("consola") List<String> consola) {
		List<TipoConsola> tipoConsola = consola.stream()
	            .map(c -> TipoConsola.valueOf(c))
	            .collect(Collectors.toList());
	    return this.tiendaService.getVideojuegoByConsola(tipoConsola);
	}
	
	@GetMapping("/videojuego/consola/{consola}")
	public List<Product> getVideojuegoPorUnaConsola(@PathVariable("consola") List<String> consola) {
		List<TipoConsola> tipoConsola = consola.stream()
	            .map(c -> TipoConsola.valueOf(c))
	            .collect(Collectors.toList());
	    return this.tiendaService.getVideojuegoPorUnaConsola(tipoConsola);
	}
	
	@GetMapping("/videojuego/nuevo")
	public List<Product> getVideojuegosNuevos() { //Aqui no se pide nada ya que por la barra vamos a dar el resultado de todos los nuevos, y es lista cuando los resultados son mas que uno
	   return this.tiendaService.getVideojuegosNuevos();
	}
	
	@GetMapping("/videojuego/fecha/{fecha}")
	public Product getVideojuegoByFecha(@PathVariable("fecha") String fecha) {
		long fecha1 = Long.valueOf(fecha);
	    return this.tiendaService.getVideojuegoByFecha(fecha1);
	}
	
	//Filtros de Consola
	
	@GetMapping("/consola/")
	public List<Product> getAllConsolas() {
	    return this.tiendaService.getAllConsolas();
	}
	
	@GetMapping("/consola/asc")
	public List<Product> getAllConsolasAscendente() {
	    return this.tiendaService.getAllConsolasAscendente();
	}
	
	@GetMapping("/consola/nombre/{name}")
	public Product getconsolaByName(@PathVariable("name") String nombre) {
	    return this.tiendaService.getconsolaByName(nombre);
	}
	
	@GetMapping("/consola/tipo")
	public List<Product> getConsolaByTipo(@RequestParam("tipo") List<String> consola) {
		List<TipoConsola> tipoConsola = consola.stream()
	            .map(c -> TipoConsola.valueOf(c))
	            .collect(Collectors.toList());
	    return this.tiendaService.getConsolaByTipo(tipoConsola);
	}
	
	@GetMapping("/consola/nuevo")
	public List<Product> getConsolasNuevas() { //Aqui no se pide nada ya que por la barra vamos a dar el resultado de todos los nuevos, y es lista cuando los resultados son mas que uno
	   return this.tiendaService.getConsolasNuevas();
	}
	
	//Filtros para perifericos
	
	@GetMapping("/periferico/nombre/{name}")
	public Product getPerifericoByName(@PathVariable("name") String name) {
	    return this.tiendaService.getPerifericoByName(name);
	}
	
	@GetMapping("/periferico/tipoConsola/{tipo}")
	public List<Product> getPerifericoByTipo(@PathVariable("tipo") List<String> periferico) {
		List<TipoConsola> tipoConsola = periferico.stream()
	            .map(p -> TipoConsola.valueOf(p))
	            .collect(Collectors.toList());
	    return this.tiendaService.getPerifericoByTipo(tipoConsola);
	}
	/*@GetMapping("/products")
    public List<Product> getAllProducts() {
        List<Product> products = this.tiendaService.;
        return products;
    }*/

}
