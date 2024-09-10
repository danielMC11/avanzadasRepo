package com.mycompany.mavenproject3.persistencia.empleado;

import com.mycompany.mavenproject3.base.Empleado;
import com.mycompany.mavenproject3.persistencia.CrudBase;

public interface PersistenciaEmpleado extends CrudBase<Empleado> {

	Integer obtenerIdPersona(Integer id);
}
