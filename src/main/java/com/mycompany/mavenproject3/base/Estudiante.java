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
public class Estudiante extends Persona implements Serializable {

	private  static  final  long serialVersionUID = 1L;
    private String codigo;
    private String programa;
    private Double promedio;

		public Estudiante(){
			codigo = null;
			programa = null;
			promedio = null;
		}
    public Estudiante(Integer id, String nombres, String apellidos, Direccion direccion, String codigo, String programa, Double promedio){
        super(id, nombres, apellidos, direccion);
        this.codigo = codigo;
        this.programa = programa;
        this.promedio = promedio;
    }

		public Estudiante(String nombres, String apellidos, Direccion direccion, String codigo, String programa, Double promedio){
			super(nombres, apellidos, direccion);
			this.codigo = codigo;
			this.programa = programa;
			this.promedio = promedio;
		}

    @Override
    public String toString() {
        return "Estudiante{" + super.toString() + "codigo=" + codigo + ", programa=" + programa + "promedio="+promedio+ '}';
    }

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public Double getPromedio() {
		return promedio;
	}

	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}
}
