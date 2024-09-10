package com.mycompany.mavenproject3.persistencia.persona;

import com.mycompany.mavenproject3.base.Direccion;
import com.mycompany.mavenproject3.base.Persona;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.persistencia.municipio.MunicipioDAO;
import com.mycompany.mavenproject3.persistencia.municipio.MunicipioDAOImpl;

import java.sql.*;
import java.util.List;

public class PersonaDAOImpl implements PersonaDAO{
	private final AdaptadorBaseDatos db;
	public MunicipioDAO municipioDAO;

	public PersonaDAOImpl(AdaptadorBaseDatos db) {

		this.db = db;
		municipioDAO = new MunicipioDAOImpl(db);
	}

	@Override
	public Persona guardar(Persona p) {
		Persona personaCreada = new Persona();

		try (Connection conn = db.obtenerConexion();
				 PreparedStatement pstmt = conn.prepareStatement(
					 "INSERT INTO PERSONA (nombres, apellidos, calle, carrera, coordenada, descripcion, municipioId) " +
						 "VALUES (?, ?, ?, ?, ?, ?, ?)",
					 Statement.RETURN_GENERATED_KEYS);
		) {
			pstmt.setString(1, p.getNombres());
			pstmt.setString(2, p.getApellidos());
			pstmt.setString(3, p.getDireccion().getCalle());
			pstmt.setString(4, p.getDireccion().getCarrera());
			pstmt.setString(5, p.getDireccion().getCoordenada());
			pstmt.setString(6, p.getDireccion().getDescripcion());
			pstmt.setInt(7, p.getDireccion().getMunicipio().getId());

			pstmt.executeUpdate();

			ResultSet generatedKeys = pstmt.getGeneratedKeys();

			if (generatedKeys.next()) {
					int personaId = generatedKeys.getInt(1);
					personaCreada.setId(personaId);
					personaCreada.setNombres(p.getNombres());
					personaCreada.setApellidos(p.getApellidos());
					personaCreada.setDireccion(p.getDireccion());
			}

		} catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage());
		}

		return personaCreada;
	}

	@Override
	public void actualizar(Integer id, String campo, Object valor) {
			try(Connection conn = db.obtenerConexion();
					PreparedStatement pstmt = conn.prepareStatement("UPDATE PERSONA SET " + campo +" = ? WHERE personaId = ?");
			){
				pstmt.setObject(1, valor);
				pstmt.setInt(2, id);
				int n = pstmt.executeUpdate();
			}
			catch(Exception e){
				System.err.println("ERROR: " + e.getMessage());
			}
	}

	@Override
	public Persona buscarPorId(Integer id){
		Persona persona = new Persona();
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PERSONA WHERE personaId = ?");
		) {
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				persona.setId(rs.getInt("personaId"));
				persona.setNombres(rs.getString("nombres"));
				persona.setApellidos(rs.getString("apellidos"));
				Direccion direccion = new Direccion(
					rs.getString("calle"),
					rs.getString("carrera"),
					rs.getString("coordenada"),
					rs.getString("descripcion"),
					municipioDAO.buscarPorId(rs.getInt("municipioId"))
				);
				persona.setDireccion(direccion);
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return persona;

	}

	@Override
	public List<Persona> buscarTodos(){return null;}

	@Override
	public void eliminarPorId(Integer id) {

		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM PERSONA WHERE personaId = ?");
		){

			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
	}



}
