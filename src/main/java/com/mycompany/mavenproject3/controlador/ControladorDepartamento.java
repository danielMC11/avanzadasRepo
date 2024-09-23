package com.mycompany.mavenproject3.controlador;

import com.mycompany.mavenproject3.base.Departamento;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.persistencia.departamento.PersistenciaDepartamento;
import com.mycompany.mavenproject3.persistencia.departamento.PersistenciaDepartamentoArchivoPlano;
import com.mycompany.mavenproject3.persistencia.departamento.PersistenciaDepartamentoBD;

import java.util.ArrayList;
import java.util.List;

public class ControladorDepartamento {
	private final List<PersistenciaDepartamento> metodosPersistencia = new ArrayList<>();

	public ControladorDepartamento(AdaptadorBaseDatos db){
		metodosPersistencia.add(new PersistenciaDepartamentoBD(db));
		metodosPersistencia.add(new PersistenciaDepartamentoArchivoPlano());
	}

	public void crearDepartamento(Departamento departamento){
		for(PersistenciaDepartamento persistenciaDepartamento : metodosPersistencia)
			persistenciaDepartamento.guardar(departamento);
	}

	public void editarDepartamento(Integer id, String campo, Object valor){
		for(PersistenciaDepartamento persistenciaDepartamento : metodosPersistencia)
			persistenciaDepartamento.actualizar( id, campo, valor);
	}

	public Departamento obtenerDepartamento(Integer id){
		return metodosPersistencia.get(1).buscarPorId(id);
	}

	public List<Departamento> listarDepartamentos(){

		return metodosPersistencia.get(1).buscarTodos();
	}

	public void eliminarDepartamento(Integer id){
		for(PersistenciaDepartamento persistenciaDepartamento : metodosPersistencia)
			persistenciaDepartamento.eliminarPorId(id);
	}
}
