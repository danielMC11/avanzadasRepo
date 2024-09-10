package com.mycompany.mavenproject3.controlador;

import com.mycompany.mavenproject3.base.Pais;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.persistencia.pais.PersistenciaPais;
import com.mycompany.mavenproject3.persistencia.pais.PersistenciaPaisArchivoPlano;
import com.mycompany.mavenproject3.persistencia.pais.PersistenciaPaisBD;

import java.util.ArrayList;
import java.util.List;



public class ControladorPais {
	private final List<PersistenciaPais> metodosPersistencia = new ArrayList<>();

	public ControladorPais(H2Server db){
		metodosPersistencia.add(new PersistenciaPaisBD(db));
		metodosPersistencia.add(new PersistenciaPaisArchivoPlano());
	}

	public void crearPais(Pais pais){
		for(PersistenciaPais persistenciaPais : metodosPersistencia)
			persistenciaPais.guardar(pais);
	}

	public void editarPais(Integer id, String campo, Object valor){
		for(PersistenciaPais persistenciaPais : metodosPersistencia)
			persistenciaPais.actualizar( id, campo, valor);
	}

	public Pais obtenerPais(Integer id){
		return metodosPersistencia.get(1).buscarPorId(id);
	}

	public List<Pais> listarPaises(){
		return metodosPersistencia.get(1).buscarTodos();
	}

	public void eliminarPais(Integer id){
		for(PersistenciaPais persistenciaPais : metodosPersistencia)
			persistenciaPais.eliminarPorId(id);
	}



}
