package com.mycompany.mavenproject3.persistencia.estudiante;

import com.mycompany.mavenproject3.base.Estudiante;

import java.util.List;

public interface EstudianteDAO {
	void guardar(Estudiante obj);

	void actualizar(Integer id, String campo, Object valor);

	Estudiante buscarPorId(Integer id);

	List<Estudiante> buscarTodos();
	void eliminarPorId(Integer id);

	Integer obtenerIdPersona(Integer id);
}
