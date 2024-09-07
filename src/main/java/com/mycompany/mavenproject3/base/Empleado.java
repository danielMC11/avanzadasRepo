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

public class Empleado extends Persona implements Serializable {

	private  static  final  long serialVersionUID = 1L;
    private Double salario;
    private Cargo cargo;

		public Empleado(){
			salario = null;
			cargo = null;
		}
    public Empleado(Integer id, String nombres, String apellidos, Direccion direccion, Double salario, Cargo cargo){
        super(id, nombres, apellidos, direccion);
        this.salario = salario;
        this.cargo = cargo;
    }

	public Empleado(String nombres, String apellidos, Direccion direccion, Double salario, Cargo cargo){
		super(nombres, apellidos, direccion);
		this.salario = salario;
		this.cargo = cargo;
	}

    @Override
    public String toString() {
        return "Empleado("+ super.toString() + ",salario=" + salario + ", cargo=" + cargo.toString() + ")";
    }


	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}
