/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3.agrupamiento;
import com.mycompany.mavenproject3.base.Estudiante;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Estudiante_MCA
 */
public class EstudiantesInscritos implements Serializable {
    private  static  final  long serialVersionUID = 1L;
    private ArrayList<Estudiante> listado;

    public EstudiantesInscritos() {
        this.listado = new ArrayList<>();
    }



    public void imprimirListado(){
        for(Estudiante e : listado){
            System.out.println(e.informacion());
        }
    }

    public void adicionar(Estudiante e){
        listado.add(e);
    }

}
