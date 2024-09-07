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
public class Departamento implements Serializable {

	private  static  final  long serialVersionUID = 1L;
    private Integer id;
    private String nombre;
    private Pais pais;

		public Departamento(){
			id = null;
			nombre = null;
			pais = null;
		}

    public Departamento(Integer id, String nombres, Pais pais){
        this.id = id;
        this.nombre = nombres;
        this.pais = pais;
    }

		public Departamento(String nombres, Pais pais){
			this.nombre = nombres;
			this.pais = pais;
		}

		public Departamento(String nombres){
			this.nombre = nombres;
		}


    @Override
    public String toString(){
        return "Departamento(id=" + this.id + ",nombre=" + this.nombre + "," + this.pais.toString() + ")";
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

		public Pais getPais() {
			return pais;
		}

		public void setPais(Pais pais) {
			this.pais = pais;
		}
}
