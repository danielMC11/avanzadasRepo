package com.mycompany.mavenproject3.controlador;

import com.mycompany.mavenproject3.base.Empleado;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.persistencia.empleado.PersistenciaEmpleado;
import com.mycompany.mavenproject3.persistencia.empleado.PersistenciaEmpleadoBD;

import java.util.ArrayList;
import java.util.List;

public class ControladorEmpleado {
	private final List<PersistenciaEmpleado> metodosPersistencia = new ArrayList<>();

	public ControladorEmpleado(AdaptadorBaseDatos db){
		metodosPersistencia.add(new PersistenciaEmpleadoBD(db));
	}

	public void crearEmpleado(Empleado empleado){
		for(PersistenciaEmpleado persistenciaEmpleado : metodosPersistencia)
			persistenciaEmpleado.guardar(empleado);
	}

	public void editarEmpleado(Integer id, String campo, Object valor){
		for(PersistenciaEmpleado persistenciaEmpleado : metodosPersistencia)
			persistenciaEmpleado.actualizar( id, campo, valor);
	}

	public Empleado obtenerEmpleado(Integer id){
		return metodosPersistencia.getFirst().buscarPorId(id);
	}

	public List<Empleado> listarEmpleados(){

		return metodosPersistencia.getFirst().buscarTodos();
	}

	public void eliminarEmpleado(Integer id){
		for(PersistenciaEmpleado persistenciaEmpleado : metodosPersistencia)
			persistenciaEmpleado.eliminarPorId(id);
	}

	public Integer obtenerIdPersona(Integer id){
		return metodosPersistencia.getFirst().obtenerIdPersona(id);
	}
}
