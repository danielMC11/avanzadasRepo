package com.mycompany.mavenproject3.controlador;

import com.mycompany.mavenproject3.base.Persona;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.persistencia.persona.PersistenciaPersona;
import com.mycompany.mavenproject3.persistencia.persona.PersistenciaPersonaBD;

import java.util.ArrayList;
import java.util.List;

public class ControladorPersona {
	private final List<PersistenciaPersona> metodosPersistencia = new ArrayList<>();

	public ControladorPersona(AdaptadorBaseDatos db){
		metodosPersistencia.add(new PersistenciaPersonaBD(db));
	}

	public void crearPersona(Persona persona){
		for(PersistenciaPersona persistenciaPersona : metodosPersistencia)
			persistenciaPersona.guardar(persona);
	}

	public void editarPersona(Integer id, String campo, Object valor){
		for(PersistenciaPersona persistenciaPersona : metodosPersistencia)
			persistenciaPersona.actualizar( id, campo, valor);
	}

	public Persona obtenerPersona(Integer id){
		return metodosPersistencia.getFirst().buscarPorId(id);
	}

	public List<Persona> listarPersonas(){

		return metodosPersistencia.getFirst().buscarTodos();
	}

	public void eliminarPersona(Integer id){
		for(PersistenciaPersona persistenciaPersona : metodosPersistencia)
			persistenciaPersona.eliminarPorId(id);
	}
}
