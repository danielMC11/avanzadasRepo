package com.mycompany.mavenproject3.persistencia.empleado;

import com.mycompany.mavenproject3.base.Empleado;

import com.mycompany.mavenproject3.base.Persona;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.persistencia.cargo.PersistenciaCargo;
import com.mycompany.mavenproject3.persistencia.cargo.PersistenciaCargoBD;

import com.mycompany.mavenproject3.persistencia.persona.PersistenciaPersona;
import com.mycompany.mavenproject3.persistencia.persona.PersistenciaPersonaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaEmpleadoBD implements PersistenciaEmpleado {
	private final AdaptadorBaseDatos db;

	private PersistenciaPersona persistenciaPersona;

	public PersistenciaCargo cargoDAO;


	public PersistenciaEmpleadoBD(AdaptadorBaseDatos db) {
		this.db = db;
		persistenciaPersona = new PersistenciaPersonaBD(db);
		cargoDAO = new PersistenciaCargoBD(db);
	}

	@Override
	public Empleado guardar(Empleado em) {
		try (Connection conn = db.obtenerConexion();
				 PreparedStatement pstmt = conn.prepareStatement(
					 "INSERT INTO EMPLEADO (salario, personaId, cargoId) " +
						 "VALUES (?, ?, ?)");
		) {
			Persona personaCreada = persistenciaPersona.guardar(em);

			pstmt.setDouble(1, em.getSalario());
			pstmt.setInt(2, personaCreada.getId());
			pstmt.setInt(3, em.getCargo().getId());

			pstmt.executeUpdate();


		} catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage());
		}
		return null;
	}

	@Override
	public void actualizar(Integer id, String campo, Object valor){
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE EMPLEADO SET " + campo +" = ? WHERE empleadoId = ?");
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
	public Empleado buscarPorId(Integer id) {
		Empleado empleado = new Empleado();
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM EMPLEADO WHERE empleadoId = ?");
		) {
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Persona persona = persistenciaPersona.buscarPorId(rs.getInt("personaId"));
				empleado.setId(id);
				empleado.setNombres(persona.getNombres());
				empleado.setApellidos(persona.getApellidos());
				empleado.setDireccion(persona.getDireccion());
				empleado.setSalario(rs.getDouble("salario"));
				empleado.setCargo(cargoDAO.buscarPorId(rs.getInt("cargoId")));
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return empleado;

	}

	@Override
	public List<Empleado> buscarTodos(){
		List<Empleado> empleados = new ArrayList<>();
		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM EMPLEADO");
		) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Persona persona = persistenciaPersona.buscarPorId(rs.getInt("personaId"));
				Empleado empleado	 = new Empleado();
				empleado.setId(rs.getInt("empleadoId"));
				empleado.setNombres(persona.getNombres());
				empleado.setApellidos(persona.getApellidos());
				empleado.setDireccion(persona.getDireccion());
				empleado.setSalario(rs.getDouble("salario"));
				empleado.setCargo(cargoDAO.buscarPorId( rs.getInt("cargoId")));

				empleados.add(empleado);
			}

		} catch(Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}
		return empleados;

	}

	@Override
	public void eliminarPorId(Integer id){

		try(Connection conn = db.obtenerConexion();
				PreparedStatement pstmt = conn.prepareStatement("SELECT personaId FROM EMPLEADO WHERE empleadoId = ?");
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
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM EMPLEADO WHERE empleadoId = ?");
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
