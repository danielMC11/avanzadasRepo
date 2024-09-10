package com.mycompany.mavenproject3.controlador;

import com.mycompany.mavenproject3.base.Pais;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.persistencia.pais.PersistenciaPais;
import com.mycompany.mavenproject3.persistencia.pais.PersistenciaPaisArchivoPlano;
import com.mycompany.mavenproject3.persistencia.pais.PersistenciaPaisBD;

import java.util.ArrayList;
import java.util.List;



public class ControladorPais {
	private final List<PersistenciaPais> metodosPersistencia = new ArrayList<>();

	public ControladorPais(AdaptadorBaseDatos db){
		metodosPersistencia.add(new PersistenciaPaisBD(db));
		metodosPersistencia.add(new PersistenciaPaisArchivoPlano());
	}

	public void crearPais(Pais pais){
		for(PersistenciaPais p : metodosPersistencia)
			p.guardar(pais);
	}

	public void editarPais(Integer id, String campo, Object valor){
		for(PersistenciaPais p : metodosPersistencia)
			p.actualizar( id, campo, valor);
	}

	public Pais obtenerPais(Integer id){
		return metodosPersistencia.get(1).buscarPorId(id);
	}

	public List<Pais> listarPaises(){
		return metodosPersistencia.get(1).buscarTodos();
	}

	public void eliminarPais(Integer id){
		for(PersistenciaPais p : metodosPersistencia)
			p.eliminarPorId(id);
	}



}
