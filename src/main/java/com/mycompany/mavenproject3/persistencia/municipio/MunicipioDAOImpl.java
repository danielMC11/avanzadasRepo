package com.mycompany.mavenproject3.persistencia.municipio;

import com.mycompany.mavenproject3.base.Municipio;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.persistencia.departamento.DepartamentoDAO;
import com.mycompany.mavenproject3.persistencia.departamento.DepartamentoDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class MunicipioDAOImpl implements MunicipioDAO{
	private final AdaptadorBaseDatos db;
	public DepartamentoDAO departamentoDAO;

	public MunicipioDAOImpl(AdaptadorBaseDatos db) {
		this.db = db;
		departamentoDAO = new DepartamentoDAOImpl(db);
	}


	@Override
	public void guardar(Municipio m){
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO MUNICIPIO (nombre, departamentoId) VALUES (?, ?)");
		){
			pstmt.setString(1, m.getNombre());
			pstmt.setInt(2, m.getDepartamento().getId());
			pstmt.executeUpdate();

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}


	}

	@Override
	public void actualizar(Integer id, String campo, Object valor){
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE MUNICIPIO SET " +campo+"= ? WHERE municipioId = ?");
		){
			pstmt.setObject(1, valor);
			pstmt.setInt(2, id);

			pstmt.executeUpdate();
		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
	}

	@Override
	public Municipio buscarPorId(Integer id){
		Municipio municipio = new Municipio();
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MUNICIPIO WHERE municipioId = ?");
		) {
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				municipio.setId(rs.getInt("municipioId"));
				municipio.setNombre(rs.getString("nombre"));
				municipio.setDepartamento(departamentoDAO.buscarPorId(rs.getInt("departamentoId")));
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return municipio;
	}

	@Override
	public List<Municipio> buscarTodos(){
		List<Municipio> municipios = new ArrayList<>();
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MUNICIPIO");
		) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Municipio municipio = new Municipio();
				municipio.setId(rs.getInt("municipioId"));
				municipio.setNombre(rs.getString("nombre"));
				municipio.setDepartamento(departamentoDAO.buscarPorId(rs.getInt("departamentoId")));
				municipios.add(municipio);
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return municipios;
	}

	@Override
	public void eliminarPorId(Integer id){
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM MUNICIPIO WHERE municipioId = ?");
		){
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
	}

}
