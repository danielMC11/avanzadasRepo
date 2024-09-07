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
public class Direccion implements Serializable {

	private  static  final  long serialVersionUID = 1L;
	private String calle;
    private String carrera;
    private String coordenada;
    private String descripcion;
    private Municipio municipio;

    public Direccion(String calle, String carrera, String coordenada, String descripcion, Municipio municipio){
        this.calle = calle;
        this.carrera = carrera;
        this.coordenada = coordenada;
        this.descripcion = descripcion;
        this.municipio = municipio;
    }



    @Override
    public String toString(){
        return "Direccion(calle=" + this.calle + ",carrera=" + this.carrera +
                ",coordenada="+ this.coordenada + ",descripcion=" + this.descripcion +
                "," + this.municipio.toString() + ")";
    }

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(String coordenada) {
		this.coordenada = coordenada;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
}
