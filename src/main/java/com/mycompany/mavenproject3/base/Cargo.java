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
public class Cargo implements Todos, Serializable {

	private  static  final  long serialVersionUID = 1L;
    private Integer id;
    private String nombre;

	public Cargo(){
			id=null;
			nombre=null;
		}

    public Cargo(Integer id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }
		public Cargo(String nombre){
			this.nombre = nombre;
		}

    @Override
    public String toString() {
        return "Cargo{" + "id=" + id + ", nombre=" + nombre + '}';
    }

    @Override
    public String informacion(){
        return this.toString();
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
}
