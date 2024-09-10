package com.mycompany.mavenproject3.administradorRegistros.binario;

import com.mycompany.mavenproject3.agrupamiento.EstudiantesInscritos;
import com.mycompany.mavenproject3.base.*;

import java.io.IOException;
import java.util.List;

import static com.mycompany.mavenproject3.util.ConsolaUtil.*;
import static com.mycompany.mavenproject3.util.MenuUtil.*;

public class AdministradorRegistroEstudiantesInscritos {

    private EstudiantesInscritos estudiantesInscritos = new EstudiantesInscritos();
    public void menu(){
        while (true) {
            int opcion = mostrarMenu("MENU ESTUDIANTES INSCRITOS",
                    List.of("Cargar Estudiantes inscrito","Adicionar estudiantes", "Guardar y regresar al menu anterior",
                            "Salir sin guardar"
                    )
            );

            switch (opcion) {
                case 1: cargarInformacionEstudiantesInscritos(); break;
                case 2: agregarEstudiantes(); break;
                case 3: guardarEstudiantesInscritosArchivoBinario(); return;
                case 4: return;
                default: break;
            }

        }
    }
    public void agregarEstudiantes(){
        Integer idEstudiante = (Integer) leerValorPorConsola("Id empleado: ",Integer.class);
        String nombres = (String) leerValorPorConsola("Nombres empleado: ", String.class);
        String apellidos = (String) leerValorPorConsola("Apellidos empleado: ", String.class);
        String codigo = (String) leerValorPorConsola("Codigo: ", String.class);
        String programa = (String) leerValorPorConsola("Programa: ", String.class);
        Double promedio = (Double) leerValorPorConsola("Promedio: ", Double.class);
        String calle = (String) leerValorPorConsola("Calle: ", String.class);
        String carrera = (String) leerValorPorConsola("Carrera: ", String.class);
        String coordenada = (String) leerValorPorConsola("Coordenada: ", String.class);
        String descripcion = (String) leerValorPorConsola("Descripcion: ", String.class);
        Integer municipioId = (Integer) leerValorPorConsola("Id Municipio: ", Integer.class);
        String nombreMunicipio = (String) leerValorPorConsola("Nombre municipio:",String.class);
        Integer idDepartamento = (Integer) leerValorPorConsola("Id Departamento: ", Integer.class);
        String nombreDepartamento = (String) leerValorPorConsola("Nombre departamento: ", String.class);
        Integer paisId = (Integer) leerValorPorConsola("Id pais: ", Integer.class);
        String nombrePais = (String) leerValorPorConsola("Nombre pais: ", String.class);

        Pais pais = new Pais(paisId,nombrePais);
        Departamento departamento = new Departamento(idDepartamento,nombreDepartamento,pais);
        Municipio municipio = new Municipio(municipioId,nombreMunicipio,departamento);
        Direccion direccion = new Direccion(calle,carrera,coordenada,descripcion,municipio);
        Estudiante estudiante = new Estudiante(idEstudiante,nombres,apellidos,direccion,codigo,programa,promedio);

        this.estudiantesInscritos.adicionar(estudiante);

        System.out.println("\n-----------------Estudiante agregado exitosamente-----\n");
    }

    public void guardarEstudiantesInscritosArchivoBinario() {
        try {
            AdministradorDeArchivos.escribirEnArchivoBinario(this.estudiantesInscritos, "EstudiantesInscritos.bin");
        } catch (IOException exception) {
            exception.printStackTrace(System.out);
        }
    }
    public void cargarInformacionEstudiantesInscritos(){
        EstudiantesInscritos estudiantes = null;
        try {
            estudiantes = (EstudiantesInscritos) AdministradorDeArchivos.cargarObjetoDesdeArchivoBinario("EstudiantesInscritos.bin");
        }catch (IOException | ClassNotFoundException exception){
            exception.printStackTrace(System.out);
        }
        if(estudiantes !=null){
            this.estudiantesInscritos=estudiantes;
            System.out.println("\n---------------Objeto cargado exitosamente---\n");
            estudiantesInscritos.imprimirListado();
            System.out.println("\n-------------Prueba de validacion------------\n");
        }else{
            System.out.println("No se puede cargar objeto");
        }

    }
}
