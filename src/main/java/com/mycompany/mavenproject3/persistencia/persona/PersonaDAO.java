package com.mycompany.mavenproject3.persistencia.persona;

import com.mycompany.mavenproject3.base.Persona;

import java.util.List;

public interface PersonaDAO {
	Persona guardar(Persona obj);
	void actualizar(Integer id, String campo, Object valor);
	Persona buscarPorId(Integer id);
	List<Persona> buscarTodos();
	void eliminarPorId(Integer id);
}
