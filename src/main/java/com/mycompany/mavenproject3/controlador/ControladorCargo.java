package com.mycompany.mavenproject3.controlador;

import com.mycompany.mavenproject3.base.Cargo;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.persistencia.cargo.PersistenciaCargo;
import com.mycompany.mavenproject3.persistencia.cargo.PersistenciaCargoBD;

import java.util.ArrayList;
import java.util.List;

public class ControladorCargo {
	private final List<PersistenciaCargo> metodosPersistencia = new ArrayList<>();

	public ControladorCargo(AdaptadorBaseDatos db){
		metodosPersistencia.add(new PersistenciaCargoBD(db));
	}

	public void crearCargo(Cargo cargo){
		for(PersistenciaCargo persistenciaCargo : metodosPersistencia)
			persistenciaCargo.guardar(cargo);
	}

	public void editarCargo(Integer id, String campo, Object valor){
		for(PersistenciaCargo persistenciaCargo : metodosPersistencia)
			persistenciaCargo.actualizar( id, campo, valor);
	}

	public Cargo obtenerCargo(Integer id){
		return metodosPersistencia.getFirst().buscarPorId(id);
	}

	public List<Cargo> listarCargos(){

		return metodosPersistencia.getFirst().buscarTodos();
	}

	public void eliminarCargo(Integer id){
		for(PersistenciaCargo persistenciaCargo : metodosPersistencia)
			persistenciaCargo.eliminarPorId(id);
	}
}
