/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3.agrupamiento;
import com.mycompany.mavenproject3.base.Persona;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Estudiante_MCA
 */
public class PersonalInscrito implements Serializable {

    private  static  final  long serialVersionUID = 1L;
    private ArrayList<Persona> listado;

    public PersonalInscrito(){
        listado = new ArrayList<>();
    }

    public void imprimirListado(){
        for(Persona p : listado){
               System.out.println(p.informacion());
        }
    }

    public void adicionar(Persona p){
        this.listado.add(p);
    }

}
