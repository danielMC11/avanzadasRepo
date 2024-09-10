package com.mycompany.mavenproject3.persistencia.estudiante;

import com.mycompany.mavenproject3.base.Estudiante;
import com.mycompany.mavenproject3.persistencia.CrudBase;

public interface PersistenciaEstudiante extends CrudBase<Estudiante> {
	Integer obtenerIdPersona(Integer id);
}
