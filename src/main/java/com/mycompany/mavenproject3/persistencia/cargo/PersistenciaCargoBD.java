package com.mycompany.mavenproject3.persistencia.cargo;

import com.mycompany.mavenproject3.base.Cargo;

import com.mycompany.mavenproject3.config.H2Server;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaCargoBD implements PersistenciaCargo {
	private final H2Server db;

	public PersistenciaCargoBD(H2Server db) {
		this.db = db;
	}

	@Override
	public Cargo guardar( Cargo c) {
		Cargo cargo = new Cargo();

		try (Connection conn = db.obtenerConexion();
				 PreparedStatement pstmt = conn.prepareStatement(
					 "INSERT INTO CARGO (nombre) VALUES (?)");
		) {

			pstmt.setString(1, c.getNombre());

			pstmt.executeUpdate();


		} catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage());
		}

		return cargo;
	}


	@Override
	public void actualizar(Integer id, String campo, Object valor) {
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE CARGO SET " + campo +" = ? WHERE cargoId = ?");
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
	public Cargo buscarPorId(Integer id){
		Cargo cargo = new Cargo();
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CARGO WHERE cargoId = ?");
		) {
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				cargo.setId(rs.getInt("cargoId"));
				cargo.setNombre(rs.getString("nombre"));
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return cargo;

	}
	@Override
	public List<Cargo> buscarTodos(){
		List<Cargo> cargos = new ArrayList<>();
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CARGO");
		) {

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Cargo cargo = new Cargo();
				cargo.setId(rs.getInt("cargoId"));
				cargo.setNombre(rs.getString("nombre"));
				cargos.add(cargo);
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return cargos;

	}

	@Override
	public void eliminarPorId( Integer id){
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM CARGO WHERE cargoId = ?");
		){
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
	}


}
