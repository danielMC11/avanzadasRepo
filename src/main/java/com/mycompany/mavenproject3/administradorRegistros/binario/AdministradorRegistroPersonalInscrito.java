package com.mycompany.mavenproject3.administradorRegistros.binario;

import com.mycompany.mavenproject3.agrupamiento.PersonalInscrito;
import com.mycompany.mavenproject3.base.*;

import java.io.IOException;
import java.util.List;

import static com.mycompany.mavenproject3.util.ConsolaUtil.*;
import static com.mycompany.mavenproject3.util.MenuUtil.*;

public class AdministradorRegistroPersonalInscrito {
    private PersonalInscrito personalInscrito = new PersonalInscrito();
    public void menu(){
        while (true) {
            int opcion = mostrarMenu("MENU PERSONAL INSCRITO",
                    List.of("Cargar Personal inscrito","Adicionar personal", "Guardar y regresar al menu anterior",
                            "Salir sin guardar"
                    )
            );

            switch (opcion) {
                case 1: cargarInformacionPersonalInscritos(); break;
                case 2: validarTipoPersonal(); break;
                case 3: guardarPersonalInscritosArchivoBinario(); return;
                case 4: return;
                default: break;
            }

        }
    }

    void agregarEstudiante(){
        Integer idEstudiante = (Integer) leerValorPorConsola("Id estudainte: ",Integer.class);
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

        this.personalInscrito.adicionar(estudiante);
        System.out.println("\n-----------------Estudiante agregado exitosamente-----\n");

    }

    public void  agregarEmpleado(){
        Integer idEmpleado = (Integer) leerValorPorConsola("Id empleado: ",Integer.class);
        String nombres = (String) leerValorPorConsola("Nombres empleado: ", String.class);
        String apellidos = (String) leerValorPorConsola("Apellidos empleado: ", String.class);
        String calle = (String) leerValorPorConsola("Calle: ", String.class);
        String carrera = (String) leerValorPorConsola("Carrera: ", String.class);
        String coordenada = (String) leerValorPorConsola("Coordenada: ", String.class);
        String descripcion = (String) leerValorPorConsola("Descripcion: ", String.class);
        Integer municipioId = (Integer) leerValorPorConsola("Id Municipio: ", Integer.class);
        String nombreMunicipio = (String) leerValorPorConsola("Nombre municipio:",String.class);
        Double salario = (Double) leerValorPorConsola("Salario: ", Double.class);
        Integer cargoId = (Integer) leerValorPorConsola("Id Cargo: ", Integer.class);
        String nombreCargo =(String) leerValorPorConsola("Nombre cargo: ",String.class);
        Integer idDepartamento = (Integer) leerValorPorConsola("Id Departamento: ", Integer.class);
        String nombreDepartamento = (String) leerValorPorConsola("Nombre departamento: ", String.class);
        Integer paisId = (Integer) leerValorPorConsola("Id pais: ", Integer.class);
        String nombrePais = (String) leerValorPorConsola("Nombre pais: ", String.class);

        Pais pais = new Pais(paisId,nombrePais);
        Departamento departamento = new Departamento(idDepartamento,nombreDepartamento,pais);
        Municipio municipio = new Municipio(municipioId,nombreMunicipio,departamento);
        Direccion direccion = new Direccion(calle,carrera,coordenada,descripcion,municipio);
        Cargo cargo = new Cargo(cargoId,nombreCargo);
        Empleado empleado = new Empleado(idEmpleado,nombres,apellidos,direccion,salario,cargo);

        this.personalInscrito.adicionar(empleado);
    }
    public void validarTipoPersonal(){
        while (true) {
            int opcion = mostrarMenu("¿QUE TIPO PERSONAL ERES?",
                    List.of("Empleado","Estudiante","Regresar"
                    )
            );

            switch (opcion) {
                case 1: agregarEmpleado(); break;
                case 2: agregarEstudiante(); break;
                case 3: return;
                default: break;
            }

        }
    }


    public void guardarPersonalInscritosArchivoBinario() {
        try {
            AdministradorDeArchivos.escribirEnArchivoBinario(personalInscrito,"PersonalInscrito.bin");
        } catch (IOException exception) {
            exception.printStackTrace(System.out);
        }
    }
    public void cargarInformacionPersonalInscritos(){
        PersonalInscrito personalInscrito1 = null;
        try {
            personalInscrito1 = (PersonalInscrito) AdministradorDeArchivos.cargarObjetoDesdeArchivoBinario("PersonalInscrito.bin");
        }catch (IOException | ClassNotFoundException exception){
            exception.printStackTrace(System.out);
        }
        if(personalInscrito1 !=null){
            this.personalInscrito=personalInscrito1;
            System.out.println("\n---------------Objeto cargado exitosamente---\n");
            personalInscrito.imprimirListado();
            System.out.println("\n-------------Prueba de validacion------------\n");
        }else{
            System.out.println("No se puede cargar objeto");
        }

    }
}
