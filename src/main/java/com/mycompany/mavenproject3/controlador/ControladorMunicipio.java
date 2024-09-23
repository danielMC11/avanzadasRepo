package com.mycompany.mavenproject3.controlador;

import com.mycompany.mavenproject3.base.Municipio;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.persistencia.municipio.PersistenciaMunicipio;
import com.mycompany.mavenproject3.persistencia.municipio.PersistenciaMunicipioArchivoPlano;
import com.mycompany.mavenproject3.persistencia.municipio.PersistenciaMunicipioBD;

import java.util.ArrayList;
import java.util.List;

public class ControladorMunicipio {
	private final List<PersistenciaMunicipio> metodosPersistencia = new ArrayList<>();

	public ControladorMunicipio(AdaptadorBaseDatos db){

		metodosPersistencia.add(new PersistenciaMunicipioBD(db));
		metodosPersistencia.add(new PersistenciaMunicipioArchivoPlano());
	}

	public void crearMunicipio(Municipio municipio){
		for(PersistenciaMunicipio persistenciaMunicipio : metodosPersistencia)
			persistenciaMunicipio.guardar(municipio);
	}

	public void editarMunicipio(Integer id, String campo, Object valor){
		for(PersistenciaMunicipio persistenciaMunicipio : metodosPersistencia)
			persistenciaMunicipio.actualizar( id, campo, valor);
	}

	public Municipio obtenerMunicipio(Integer id){
		return metodosPersistencia.getFirst().buscarPorId(id);
	}

	public List<Municipio> listarMunicipios(){

		return metodosPersistencia.getFirst().buscarTodos();
	}

	public void eliminarMunicipio(Integer id){
		for(PersistenciaMunicipio persistenciaMunicipio : metodosPersistencia)
			persistenciaMunicipio.eliminarPorId(id);
	}
}
