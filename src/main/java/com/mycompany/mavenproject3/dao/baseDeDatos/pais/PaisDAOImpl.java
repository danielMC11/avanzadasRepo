package com.mycompany.mavenproject3.dao.baseDeDatos.pais;

import com.mycompany.mavenproject3.base.Pais;

import com.mycompany.mavenproject3.config.H2Server;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaisDAOImpl implements PaisDAO {
	H2Server db;
	public PaisDAOImpl(H2Server db){
		this.db = db;
	}
	@Override
	public void guardar(Pais p){
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PAIS (nombre) VALUES (?)");
		){
			pstmt.setString(1, p.getNombre());
			pstmt.executeUpdate();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();


		} catch(Exception e) {
			System.err.println("ERROR: " + e.getMessage());
		}
	}

	@Override
	public void actualizar(Integer id, String campo, Object valor){

		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE PAIS SET " +campo + " = ? WHERE paisId = ?");
		){
			pstmt.setObject(1, valor);
			pstmt.setInt(2, id);

			pstmt.executeUpdate();

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
	}
	@Override
	public Pais buscarPorId(Integer id){
		Pais pais = new Pais();
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PAIS WHERE paisId = ?");
		) {
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				pais.setId(rs.getInt("paisId"));
				pais.setNombre(rs.getString("nombre"));
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return pais;
	}
	@Override
	public List<Pais> buscarTodos(){
		List<Pais> paises = new ArrayList<>();
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PAIS");
		) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Pais pais = new Pais();
				pais.setId(rs.getInt("paisId"));
				pais.setNombre(rs.getString("nombre"));
				paises.add(pais);
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return paises;
	}
	@Override
	public void eliminarPorId(Integer id){
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM PAIS WHERE paisId = ?");
		){
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
	}


}
