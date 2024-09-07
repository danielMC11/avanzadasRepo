/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3.base;

import java.io.Serializable;

/**
 *
 * @author Estudiante_MCA
 */
public class Pais implements Serializable {

	private  static  final  long serialVersionUID = 1L;
    private Integer id;
    private String nombre;

		public Pais() {
			id = null;
			nombre = null;
		}

    public Pais(Integer id, String nombres){
        this.id = id;
        this.nombre = nombres;
    }

	public Pais(String nombres){
		this.nombre = nombres;
	}

    @Override
    public String toString(){
        return "Pais(id=" + this.id + ",nombre=" + this.nombre + ")";
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
