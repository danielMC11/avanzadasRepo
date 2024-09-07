/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3.base;

import com.mycompany.mavenproject3.base.Departamento;

import java.io.Serializable;

/**
 *
 * @author Estudiante_MCA
 */
public class Municipio implements Serializable {

	private  static  final  long serialVersionUID = 1L;
    private Integer id;
    private String nombre;
    private Departamento departamento;

		public Municipio(){
			id = null;
			nombre = null;
			departamento = null;
		}
    public Municipio(Integer id, String nombres, Departamento departamento){
        this.id = id;
        this.nombre = nombres;
        this.departamento = departamento;
    }

		public Municipio(String nombres, Departamento departamento){
			this.nombre = nombres;
			this.departamento = departamento;
		}


    @Override
    public String toString(){
        return "Municipio(id=" + this.id + ",nombre=" + this.nombre + "," + this.departamento.toString() + ")";
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
