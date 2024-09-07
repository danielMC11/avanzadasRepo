package com.mycompany.mavenproject3.dao.baseDeDatos.empleado;

import com.mycompany.mavenproject3.base.Empleado;

import com.mycompany.mavenproject3.base.Persona;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.dao.baseDeDatos.cargo.CargoDAO;
import com.mycompany.mavenproject3.dao.baseDeDatos.cargo.CargoDAOImpl;

import com.mycompany.mavenproject3.dao.baseDeDatos.persona.PersonaDAO;
import com.mycompany.mavenproject3.dao.baseDeDatos.persona.PersonaDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOImpl implements EmpleadoDAO{
	private final H2Server db;

	private PersonaDAO personaDAO;

	public CargoDAO cargoDAO;


	public EmpleadoDAOImpl(H2Server db) {
		this.db = db;
		personaDAO = new PersonaDAOImpl(db);
		cargoDAO = new CargoDAOImpl(db);
	}

	@Override
	public void guardar(Empleado em) {
		try (Connection conn = db.obtenerConexion();
				 PreparedStatement pstmt = conn.prepareStatement(
					 "INSERT INTO EMPLEADO (salario, personaId, cargoId) " +
						 "VALUES (?, ?, ?)");
		) {
			Persona personaCreada = personaDAO.guardar(em);

			pstmt.setDouble(1, em.getSalario());
			pstmt.setInt(2, personaCreada.getId());
			pstmt.setInt(3, em.getCargo().getId());

			pstmt.executeUpdate();


		} catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage());
		}
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
				Persona persona = personaDAO.buscarPorId(rs.getInt("personaId"));
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
				Persona persona = personaDAO.buscarPorId(rs.getInt("personaId"));
				Empleado empleado	 = new Empleado();
				empleado.setId(rs.getInt("empleadoId"));
				empleado.setNombres(persona.getNombres());
				empleado.setApellidos(persona.getApellidos());
				empleado.setDireccion(persona.getDireccion());
				empleado.setSalario(rs.getDouble("salario"));
				empleado.setCargo(cargoDAO.buscarPorId(rs.getInt("cargoId")));

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
			personaDAO.eliminarPorId(personaId);

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
