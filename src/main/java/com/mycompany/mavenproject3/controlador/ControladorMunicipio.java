package com.mycompany.mavenproject3.controlador;

import com.mycompany.mavenproject3.base.Departamento;
import com.mycompany.mavenproject3.base.Municipio;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.persistencia.departamento.PersistenciaDepartamento;
import com.mycompany.mavenproject3.persistencia.departamento.PersistenciaDepartamentoArchivoPlano;
import com.mycompany.mavenproject3.persistencia.departamento.PersistenciaDepartamentoBD;
import com.mycompany.mavenproject3.persistencia.municipio.PersistenciaMunicipio;
import com.mycompany.mavenproject3.persistencia.municipio.PersistenciaMunicipioBD;

import java.util.ArrayList;
import java.util.List;

public class ControladorMunicipio {
	private final List<PersistenciaMunicipio> metodosPersistencia = new ArrayList<>();

	public ControladorMunicipio(H2Server db){
		metodosPersistencia.add(new PersistenciaMunicipioBD(db));
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
