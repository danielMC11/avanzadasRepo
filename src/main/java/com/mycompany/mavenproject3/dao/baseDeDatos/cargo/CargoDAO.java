package com.mycompany.mavenproject3.dao.baseDeDatos.cargo;

import com.mycompany.mavenproject3.base.Cargo;
import com.mycompany.mavenproject3.base.Persona;

import java.util.List;

public interface CargoDAO {
	Cargo guardar(Cargo obj);
	void actualizar(Integer id, String campo, Object valor);
	Cargo buscarPorId(Integer id);
	List<Cargo> buscarTodos();
	void eliminarPorId(Integer id);
}
