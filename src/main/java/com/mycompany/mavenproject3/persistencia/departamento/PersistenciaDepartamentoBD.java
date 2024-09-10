package com.mycompany.mavenproject3.persistencia.departamento;

import com.mycompany.mavenproject3.base.Departamento;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.persistencia.pais.PersistenciaPais;
import com.mycompany.mavenproject3.persistencia.pais.PersistenciaPaisBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaDepartamentoBD implements PersistenciaDepartamento {
	private final H2Server db;
	public PersistenciaPais paisDAO;
	public PersistenciaDepartamentoBD(H2Server db){
		this.db = db;
		paisDAO = new PersistenciaPaisBD(db);
	}


	@Override
	public Departamento guardar(Departamento d){
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DEPARTAMENTO (nombre, paisId) VALUES (?, ?)");
		){
			pstmt.setString(1, d.getNombre());
			pstmt.setInt(2, d.getPais().getId());
			pstmt.executeUpdate();

			ResultSet generatedKeys = pstmt.getGeneratedKeys();

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return null;
	}

	@Override
	public void actualizar(Integer id, String campo, Object valor){
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE DEPARTAMENTO SET "+ campo +" = ? WHERE departamentoId = ?");
		){
			pstmt.setObject(1, valor);
			pstmt.setInt(2, id);

			pstmt.executeUpdate();

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
	}
	@Override
	public Departamento buscarPorId(Integer id){
		Departamento departamento = new Departamento();
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM DEPARTAMENTO WHERE departamentoId = ?");
		) {
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				departamento.setId(rs.getInt("departamentoId"));
				departamento.setNombre(rs.getString("nombre"));
				departamento.setPais(paisDAO.buscarPorId( rs.getInt("paisId")));
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return departamento;
	}
	@Override
	public List<Departamento> buscarTodos(){
		List<Departamento> departamentos = new ArrayList<>();
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM DEPARTAMENTO");
		) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Departamento departamento = new Departamento();
				departamento.setId(rs.getInt("departamentoId"));
				departamento.setNombre(rs.getString("nombre"));
				departamento.setPais(paisDAO.buscarPorId( rs.getInt("paisId")));
				departamentos.add(departamento);
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return departamentos;
	}
	@Override
	public void eliminarPorId(Integer id){
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM DEPARTAMENTO WHERE departamentoId = ?");
		){
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
	}

}
