package com.mycompany.mavenproject3.persistencia.departamento;

import com.mycompany.mavenproject3.base.Departamento;

import java.util.List;

public interface DepartamentoDAO {
	void guardar(Departamento obj);
	void actualizar(Integer id, String campo, Object valor);
	Departamento buscarPorId(Integer id);
	List<Departamento> buscarTodos();
	void eliminarPorId(Integer id);

}
