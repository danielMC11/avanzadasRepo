/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3.base;

import com.mycompany.mavenproject3.agrupamiento.Todos;

import java.io.Serializable;

/**
 *
 * @author Estudiante_MCA
 */
public class Persona implements Todos, Serializable {

	private  static  final  long serialVersionUID = 1L;
    private Integer id;
    private String nombres;
    private String apellidos;
    private Direccion direccion;


		public Persona(){
			id = null;
			nombres = null;
			apellidos = null;
			direccion = null;
		}

    public Persona(Integer id, String nombres, String apellidos, Direccion direccion){
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

		public Persona(String nombres, String apellidos, Direccion direccion){
			this.id = id;
			this.nombres = nombres;
			this.apellidos = apellidos;
			this.direccion = direccion;
		}

    @Override
    public String informacion(){
        return this.toString();
    }


    @Override
    public String toString(){
        return "Persona(id=" + this.id + ",nombres=" + this.nombres + ",apellidos=" + this.apellidos + "," + this.direccion.toString() + ")";
    }


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
}
