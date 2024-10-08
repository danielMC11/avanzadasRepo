package com.mycompany.mavenproject3.persistencia.estudiante;

import com.mycompany.mavenproject3.base.Estudiante;

import com.mycompany.mavenproject3.base.Persona;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;

import com.mycompany.mavenproject3.persistencia.persona.PersistenciaPersona;
import com.mycompany.mavenproject3.persistencia.persona.PersistenciaPersonaBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersistenciaEstudianteBD implements PersistenciaEstudiante {
	private final AdaptadorBaseDatos db;

	private PersistenciaPersona persistenciaPersona;


	public PersistenciaEstudianteBD(AdaptadorBaseDatos db) {
		this.db = db;
		persistenciaPersona = new PersistenciaPersonaBD(db);
	}


	public Estudiante guardar(Estudiante es) {
		try (Connection conn = db.obtenerConexion();
				 PreparedStatement pstmt = conn.prepareStatement(
					 "INSERT INTO ESTUDIANTE (codigo, programa, promedio, personaId) " +
						 "VALUES (?, ?, ?, ?)");
		) {
			Persona personaCreada = persistenciaPersona.guardar(es);
			pstmt.setString(1, es.getCodigo());
			pstmt.setString(2, es.getPrograma());
			pstmt.setDouble(3, es.getPromedio());
			pstmt.setInt(4, personaCreada.getId());

			pstmt.executeUpdate();

			ResultSet generatedKeys = pstmt.getGeneratedKeys();

		} catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage());
		}
		return null;
	}

	@Override
	public void actualizar(Integer id, String campo, Object valor) {
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE ESTUDIANTE SET " + campo +" = ? WHERE estudianteId = ?");
		){

			pstmt.setObject(1, valor);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();

			}
		 catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
	}



	@Override
	public Estudiante buscarPorId(Integer id){
		Estudiante estudiante = new Estudiante();
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ESTUDIANTE WHERE estudianteId = ?");
		) {
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Persona persona = persistenciaPersona.buscarPorId(rs.getInt("personaId"));
				estudiante.setId(id);
				estudiante.setNombres(persona.getNombres());
				estudiante.setApellidos(persona.getApellidos());
				estudiante.setDireccion(persona.getDireccion());
				estudiante.setCodigo(rs.getString("codigo"));
				estudiante.setPrograma(rs.getString("programa"));
				estudiante.setPromedio(rs.getDouble("promedio"));
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return estudiante;
	}

	@Override
	public List<Estudiante> buscarTodos(){
		List<Estudiante> estudiantes = new ArrayList<>();
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ESTUDIANTE");
		) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Persona persona = persistenciaPersona.buscarPorId(rs.getInt("personaId"));
				Estudiante estudiante = new Estudiante();
				estudiante.setId(rs.getInt("estudianteId"));
				estudiante.setNombres(persona.getNombres());
				estudiante.setApellidos(persona.getApellidos());
				estudiante.setDireccion(persona.getDireccion());
				estudiante.setCodigo(rs.getString("codigo"));
				estudiante.setPrograma(rs.getString("programa"));
				estudiante.setPromedio(rs.getDouble("promedio"));
				estudiantes.add(estudiante);
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return estudiantes;
	}

	@Override
	public void eliminarPorId(Integer id){

		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT personaId FROM ESTUDIANTE WHERE estudianteId = ?");
		){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			int personaId = 0;
			if(rs.next()){
				personaId = rs.getInt("personaId");
			}
			persistenciaPersona.eliminarPorId(personaId);

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
	}
	@Override
	public Integer obtenerIdPersona(Integer id){
		Integer personaId = 0;
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ESTUDIANTE WHERE estudianteId = ?");
		) {
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				personaId = rs.getInt("personaId");
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return personaId;
	}



}
