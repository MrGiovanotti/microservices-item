package com.aprendizaje.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aprendizaje.item.models.Item;
import com.aprendizaje.item.models.Producto;

@Service("item-service-restTemplate")
public class ItemServiceImpl implements ItemService {

	// Spring inyecta el único bean RestTemplate que tenemos
	@Autowired
	private RestTemplate clienteRest;

	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays
				.asList(clienteRest.getForObject("http://localhost:8090/api/productos/listar", Producto[].class));
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, Long> pathVariables = new HashMap<>();
		pathVariables.put("id", id);
		var producto = clienteRest.getForObject("http://localhost:8090/api/productos/ver/{id}", Producto.class,
				pathVariables);
		return new Item(producto, cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<>(producto);
		var response = clienteRest.exchange("http://localhost:8090/api/productos/crear", HttpMethod.POST,
				body, Producto.class);
		return response.getBody();
	}

	@Override
	public Producto update(Producto producto, Long id) {
		HttpEntity<Producto> body = new HttpEntity<>(producto);
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		ResponseEntity<Producto> response = clienteRest.exchange(
				"http://localhost:8090/api/productos/editar/{id}", HttpMethod.PUT, body, Producto.class,
				pathVariables);
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		clienteRest.delete("http://localhost:8090/api/productos/eliminar/{id}", pathVariables);
	}

}