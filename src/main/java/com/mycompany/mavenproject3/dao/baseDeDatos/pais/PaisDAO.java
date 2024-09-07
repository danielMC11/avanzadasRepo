package com.mycompany.mavenproject3.dao.baseDeDatos.pais;

import com.mycompany.mavenproject3.base.Pais;

import java.util.List;

public interface PaisDAO {
	void guardar(Pais obj);
	void actualizar(Integer id, String campo, Object valor);
	Pais buscarPorId(Integer id);
	List<Pais> buscarTodos();
	void eliminarPorId(Integer id);
}
