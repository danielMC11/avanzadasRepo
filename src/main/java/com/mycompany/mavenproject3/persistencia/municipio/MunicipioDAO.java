package com.mycompany.mavenproject3.persistencia.municipio;

import com.mycompany.mavenproject3.base.Municipio;

import java.util.List;

public interface MunicipioDAO {
	void guardar(Municipio obj);
	void actualizar(Integer id, String campo, Object valor);
	Municipio buscarPorId(Integer id);
	List<Municipio> buscarTodos();
	void eliminarPorId(Integer id);
}
