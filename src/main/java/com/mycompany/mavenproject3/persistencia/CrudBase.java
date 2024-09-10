package com.mycompany.mavenproject3.persistencia;

import java.util.List;

public interface CrudBase<T>{
	T guardar(T obj);
	void actualizar(Integer id, String campo, Object valor);
	T buscarPorId(Integer id);
	List<T> buscarTodos();
	void eliminarPorId(Integer id);
}
