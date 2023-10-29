package com.decroly.tiendagame.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.decroly.tiendagame.model.Consola;
import com.decroly.tiendagame.model.Periferico;
import com.decroly.tiendagame.model.Product;
import com.decroly.tiendagame.model.TipoConsola;
import com.decroly.tiendagame.model.TipoGenero;
import com.decroly.tiendagame.model.TipoPeriferico;
import com.decroly.tiendagame.model.Videojuego;


@Repository
public class ProductRepository {

    private final String fichero = "ListaProductos";
    private List<Product> almacen;

    public ProductRepository() {
    	try {
			if (!Files.exists(Paths.get(fichero))) {
				Files.createFile(Paths.get(fichero));
			}
			this.almacen = leerFichero();
		}catch(IOException e) {
			throw new RuntimeException("Error initializing repository", e);
		}
    	  inicializarProductosManualmente();
    }

    //Ejemplos de productos en general
    public Product getProductById(int id) {
        return this.almacen.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    public Product getProductByName(String nombre) {
        return this.almacen.stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }
    
    
    public Product getProductBySn(String sn) {
    	return this.almacen.stream()
                .filter(p -> p.getSn().equals(sn))
                .findFirst()
                .orElse(null);
    }
    
    //Metodos de Videojuegos
    public List<Product> getAllVideojuegos() {
        List<Product> lista = this.almacen.stream()
        		.filter(p -> p.getSn().startsWith("VI"))
        		.collect(Collectors.toList());
		return lista;
    }
    
    public List<Product> getAllVideojuegoAscendente() {
    	return this.almacen.stream()
        		.filter(p -> p.getSn().startsWith("VI")) //Aqui aplico sin instancia
        		.sorted(Comparator.comparingDouble(Product::getPrecio))
        		.collect(Collectors.toList());
    }
    

	public List<Product> getVideojuegoByGenero(List<TipoGenero> genero) {
		return this.almacen.stream()
        		.filter(p -> p instanceof Videojuego) // Paso a cambiar Product a Videojuego, asi jugamos con sus atributos hijos
        		.map(p -> (Videojuego) p) // Downcast a Videojuego == El verdadero cambio a videojuego, es una funcion de pasar produto a videojuego
        		.filter(v -> genero.contains(v.getGenero())) // Verificar si el género está en la lista de géneros
        		.collect(Collectors.toList());
	}
	

	public List<Product> getVideojuegoPorUnaConsola(List<TipoConsola> consola) {
		return this.almacen.stream()
				.filter(p -> p instanceof Videojuego)
				.map(p ->(Videojuego)p)
				.filter(v -> consola.contains(v.getConsola()))
				.collect(Collectors.toList());
	}

	
	public List<Product> getVideojuegoByConsola(List<TipoConsola> consola) {
		return this.almacen.stream()
				.filter(p -> p instanceof Videojuego)
				.map(p ->(Videojuego)p)
				.filter(v -> consola.contains(v.getConsola()))
				.collect(Collectors.toList());
	}

	public List<Product> getVideojuegosNuevos() {
		List<Product> lista = this.almacen.stream()
        		.filter(p -> p.getSn().startsWith("VI"))
        		.map(p -> (Videojuego) p)
        		.filter(v -> v.isNuevo())
        		.collect(Collectors.toList()); //Pasar el stream a lista 
				
		return lista;
	}
	
	public Product getVideojuegoByFecha(long fecha) { //Cuando es un atributo de videojuego hago el downcast
		return this.almacen.stream()
				.filter(p -> p.getSn().startsWith("VI"))
				.filter(p -> p.getfLanzamiento()==fecha)
				.findFirst()
				.orElse(null);
				
	}

	
    //Metodos de Consolas
    public List<Product> getAllConsolas() {
        List<Product> lista = this.almacen.stream()
        		.filter(p -> p.getSn().startsWith("CO"))
        		.collect(Collectors.toList());
		return lista;
    }
    
    public List<Product> getAllConsolasAscendente() {
    	return this.almacen.stream()
        		.filter(p -> p.getSn().startsWith("CO"))
        		.sorted(Comparator.comparingDouble(Product::getPrecio))//ordena por precio ascendente
        		.collect(Collectors.toList());
	}
    
    public Product getconsolaByName(String nombre) {
    	return this.almacen.stream()
        .filter(p -> p.getNombre().equals(nombre))
        .findFirst()
        .orElse(null);
	}

    public List<Product> getConsolaByTipo(List<TipoConsola> consola) {
    	return this.almacen.stream()
				.filter(p -> p instanceof Consola)
				.map(p ->(Consola)p)
				.filter(c -> consola.contains(c.getConsola()))
				.collect(Collectors.toList());
	}
    

	public List<Product> getConsolasNuevas() {
		List<Product> lista = this.almacen.stream()
        		.filter(p -> p.getSn().startsWith("CO"))
        		.map(p -> (Consola) p)
        		.filter(c -> c.isNuevo())
        		.collect(Collectors.toList()); 
				
		return lista;
	}	

 //Filtros con perifericos
	

	public Product getPerifericoByName(String nombre) {
		return this.almacen.stream()
		        .filter(p -> p.getNombre().equals(nombre))
		        .findFirst()
		        .orElse(null);
	}
	
	public List<Product> getPerifericoByTipo(List<TipoConsola> consola) {
		return this.almacen.stream()
				.filter(p -> p instanceof Periferico)
				.map(p ->(Periferico)p)
				.filter(pe -> consola.contains(pe.getConsola()))
				.collect(Collectors.toList());
	}
	
    private void inicializarProductosManualmente() {
        // Agregar 3 Consolas manualmente
        //Consola consola1 = new Consola(11, "Consola1", "CO001", "Descripción Consola 1", 299.99, 10, "Edición Estandar", System.currentTimeMillis(), TipoConsola.nintendoSwitch, true);
        Consola consola2 = new Consola(12, "Consola2", "CO002", "Descripción Consola 2", 249.99, 8, "Edición Especial", System.currentTimeMillis(), TipoConsola.ps5, true);
        Consola consola3 = new Consola(13, "Consola3", "CO003", "Descripción Consola 3", 199.99, 12, "Edición Estandar", System.currentTimeMillis(), TipoConsola.nintendoSwitch, false);

        // Agregar 3 Videojuegos manualmente
      
        Videojuego videojuego1 = new Videojuego(21, "Videojuego1", "VI101", "Descripción Videojuego 1", 59.99, 1, "Especial", 2023, TipoConsola.nintendoSwitch, TipoGenero.aventura, true, true);
        Videojuego videojuego2 = new Videojuego(22, "Videojuego2", "VI102", "Descripción Videojuego 2", 49.99, 20, "Estandar", 2022, TipoConsola.xbox, TipoGenero.terror, false, false);
        Videojuego videojuego3 = new Videojuego(23, "Videojuego3", "VI103", "Descripción Videojuego 3", 39.99, 25, "Edición Especial", 2021, TipoConsola.ps5, TipoGenero.multijugador, true, true);

        // Agregar 3 Periféricos manualmente
        Periferico periferico1 = new Periferico(31, "Periférico1", "PE201", "Descripción Periférico 1", 29.99, 30, "Estandar", System.currentTimeMillis(), TipoConsola.ps5, TipoPeriferico.mando, false, false, "Logitech");
        Periferico periferico2 = new Periferico(32, "Periférico2", "PE202", "Descripción Periférico 2", 19.99, 40, "Especial", System.currentTimeMillis(), TipoConsola.xbox, TipoPeriferico.raton, true, true, "Xbox");
        //Periferico periferico3 = new Periferico(33, "Periférico3", "PE203", "Descripción Periférico 3", 24.99, 35, "Estandar", System.currentTimeMillis(), TipoConsola.ps5, TipoPeriferico.casco, true, false, "Razer");

        // Agregar los productos a la lista de almacenamiento
       // this.almacen.add(consola1);
        this.almacen.add(consola2);
        this.almacen.add(consola3);
        this.almacen.add(videojuego1);
        this.almacen.add(videojuego2);
        this.almacen.add(videojuego3);
        this.almacen.add(periferico1);
        this.almacen.add(periferico2);
        //this.almacen.add(periferico3);
    }
  
    public List<Product> leerFichero() {
        List<Product> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) { // Leer fichero
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // TODO: Comprobacion la cual verifique que producto sacara ¿consola (1), videojuego(2) o periferico(3)?
                //if parts line[0] = parseInt(int.tostring.substring 0,1) if videojuego else periferico
                //productos.add(new Periferico(31, "Periférico1", "SN201", "Descripción Periférico 1", 29.99, 30, "Estandar", System.currentTimeMillis(), TipoConsola.ps5, TipoPeriferico.mando, false, false, "Logitech"));
                if(parts[2].substring(0,2).equalsIgnoreCase("CO")) {
                	productos.add(new Consola(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Double.parseDouble(parts[4]), Integer.parseInt(parts[5]), parts[6], Long.parseLong(parts[7]), TipoConsola.valueOf(parts[8]), Boolean.parseBoolean(parts[9])));
                } else if (parts[2].substring(0,2)=="VI") {
                	productos.add(new Videojuego(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Double.parseDouble(parts[4]), Integer.parseInt(parts[5]), parts[6], Long.parseLong(parts[7]), TipoConsola.valueOf(parts[8]), TipoGenero.valueOf(parts[9]), Boolean.parseBoolean(parts[10]), Boolean.parseBoolean(parts[11])));
                } else if (parts[2].substring(0,2)=="PE") {
                	productos.add(new Periferico(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Double.parseDouble(parts[4]), Integer.parseInt(parts[5]), parts[6], Long.parseLong(parts[7]), TipoConsola.valueOf(parts[8]), TipoPeriferico.valueOf(parts[9]),Boolean.parseBoolean(parts[10]),Boolean.parseBoolean(parts[11]), parts[12]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }
    public void guardar(Product producto) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fichero, true))) {
        	if(producto.getSn().substring(0,2).equalsIgnoreCase("CO") && producto instanceof Consola) {
        		Consola consola = (Consola) producto;
        		writer.println((this.almacen.size()+1) + "," + producto.getNombre() + "," +producto.getSn() + "," + producto.getDescripcion()+ "," + producto.getPrecio()
        		+ "," + producto.getCantidad()  + "," +producto.getEdicion()  + "," + producto.getfLanzamiento() + "," + consola.getConsola() + "," + consola.isNuevo());
        		this.almacen.add(producto);
        	}
        	else if(producto.getSn().substring(0,2).equalsIgnoreCase("VI") && producto instanceof Videojuego) {
        		Videojuego videojuego = (Videojuego) producto;
        		writer.println((this.almacen.size()+1) + "," + producto.getNombre() + "," +producto.getSn() + "," + producto.getDescripcion()+ "," + producto.getPrecio()
        		+ "," + producto.getCantidad()  + "," +producto.getEdicion()  + "," + producto.getfLanzamiento() + "," + videojuego.getConsola() + "," + videojuego.isDigital()+ "," + videojuego.isNuevo());
        		this.almacen.add(producto);
        	}
        	else if(producto.getSn().substring(0,2).equalsIgnoreCase("PE") && producto instanceof Periferico) {
        		Periferico periferico = (Periferico) producto;
        		writer.println((this.almacen.size()+1) + "," + producto.getNombre() + "," +producto.getSn() + "," + producto.getDescripcion()+ "," + producto.getPrecio()
        		+ "," + producto.getCantidad()  + "," +producto.getEdicion()  + "," + producto.getfLanzamiento() + "," + periferico.getConsola() + "," + periferico.getPeriferico()+ "," + periferico.isNuevo() + "," + periferico.isWireless());
        		this.almacen.add(producto);
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
