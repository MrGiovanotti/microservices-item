package com.aprendizaje.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aprendizaje.item.models.Producto;

// En name va el nombre del microservicio al que nos queremos conectar
@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

	// Indicamos la ruta para consumir el endpoint
	@GetMapping("/listar")
	List<Producto> listar();

	@GetMapping("/ver/{id}")
	Producto detalle(@PathVariable Long id);

	@PostMapping("/crear")
	Producto crear(@RequestBody Producto producto);

	@PutMapping("/editar/{id}")
	Producto editar(@RequestBody Producto producto, @PathVariable Long id);

	@DeleteMapping("/eliminar/{id}")
	void eliminar(@PathVariable Long id);

}