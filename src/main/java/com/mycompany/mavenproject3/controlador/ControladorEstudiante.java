package com.mycompany.mavenproject3.controlador;

import com.mycompany.mavenproject3.base.Estudiante;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.persistencia.estudiante.PersistenciaEstudiante;
import com.mycompany.mavenproject3.persistencia.estudiante.PersistenciaEstudianteBD;

import java.util.ArrayList;
import java.util.List;

public class ControladorEstudiante {
	private final List<PersistenciaEstudiante> metodosPersistencia = new ArrayList<>();

	public ControladorEstudiante(AdaptadorBaseDatos db){
		metodosPersistencia.add(new PersistenciaEstudianteBD(db));
	}

	public void crearEstudiante(Estudiante estudiante){
		for(PersistenciaEstudiante persistenciaEstudiante : metodosPersistencia)
			persistenciaEstudiante.guardar(estudiante);
	}

	public void editarEstudiante(Integer id, String campo, Object valor){
		for(PersistenciaEstudiante persistenciaEstudiante : metodosPersistencia)
			persistenciaEstudiante.actualizar( id, campo, valor);
	}

	public Estudiante obtenerEstudiante(Integer id){
		return metodosPersistencia.getFirst().buscarPorId(id);
	}

	public List<Estudiante> listarEstudiantes(){

		return metodosPersistencia.getFirst().buscarTodos();
	}

	public void eliminarEstudiante(Integer id){
		for(PersistenciaEstudiante persistenciaEstudiante : metodosPersistencia)
			persistenciaEstudiante.eliminarPorId(id);
	}

	public Integer obtenerIdPersona(Integer id){
		return metodosPersistencia.getFirst().obtenerIdPersona(id);
	}


}
