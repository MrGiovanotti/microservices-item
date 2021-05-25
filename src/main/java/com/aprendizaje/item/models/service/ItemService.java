package com.aprendizaje.item.models.service;

import java.util.List;

import com.aprendizaje.item.models.Item;
import com.aprendizaje.item.models.Producto;

public interface ItemService {

	List<Item> findAll();

	Item findById(Long id, Integer cantidad);

	Producto save(Producto producto);

	Producto update (Producto producto, Long id);

	void delete(Long id);

}