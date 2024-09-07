/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3.agrupamiento;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Estudiante_MCA
 */
public class ListadoTodos implements Serializable {
    private  static  final  long serialVersionUID = 1L;
    private ArrayList<Todos> listado = new ArrayList<>();

    public int cantidad(){
        return listado.size();
    }

    public void adicionar(Todos t){
        listado.add(t);
    }

    public String consultarPersona(Integer id){
        for(Todos t : listado){
            String info = t.informacion();
            if(info.contains("Persona(id=" + id.toString())){
                return  "La persona existe";
            }
        }

        return "La persona no existe";

    }

    public String mostrarPersona(Integer id){
        for(Todos t : listado){
            String info = t.informacion();
            if(info.contains("Persona(id=" + id.toString())){
                return  info;
            }
        }
         return "La persona no existe";
    }
    public String consultar(Integer id){
        try{
             Todos t = listado.get(id);
             return "Si existe";
        }catch(IndexOutOfBoundsException e){
            return "Fuera de rango";
        }
    }
    public String mostrar(Integer id){
         try{
            return listado.get(id).informacion();
        }catch(IndexOutOfBoundsException e){
            return "Fuera de rango";
        }
    }
}
