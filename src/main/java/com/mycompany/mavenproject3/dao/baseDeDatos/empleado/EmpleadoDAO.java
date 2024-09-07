package com.mycompany.mavenproject3.dao.baseDeDatos.empleado;

import com.mycompany.mavenproject3.base.Cargo;
import com.mycompany.mavenproject3.base.Empleado;

import java.util.List;

public interface EmpleadoDAO {
	void guardar(Empleado obj);
	void actualizar(Integer id, String campo, Object valor);
	Empleado buscarPorId(Integer id);
	List<Empleado> buscarTodos();
	void eliminarPorId(Integer id);
	Integer obtenerIdPersona(Integer id);
}
