package by.myproject.main.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.myproject.main.dao.DAOException;
import by.myproject.main.dao.config.ConnectionPool;
import by.myproject.main.dao.config.ConnectionPoolException;
import by.myproject.main.entity.Worker;

public class WorkerDataBase implements by.myproject.main.dao.WorkerDataBase<Worker> {
	
	public WorkerDataBase(){
	}
	private static final Logger log = Logger.getLogger(WorkerDataBase.class);	
	

	private static final String select = "SELECT * FROM workers";
	private static final String selectOne = "SELECT * FROM workers WHERE idUser = ?";
	private static final String insert = "INSERT INTO workers(idUser, Users_Login, Name, SurName, Patronymic, NumberOfPassport, Position, eMail) VALUES(?,?,?,?,?,?,?,?)";
	private static final String update = "UPDATE workers SET Users_Login = ?, Name = ?, SurName = ?, Patronymic = ?, NumberOfPassport = ?, Position = ?, eMail = ?  WHERE idUser = ?";
	private static final String delete = "DELETE FROM workers WHERE idUser = ?";	
		
	private static final String idCommandDB = "idUser";
	private static final String loginCommandDB = "Users_Login";
	private static final String nameCommandDB = "Name";
	private static final String surNameCommandDB = "SurName";
	private static final String patronymicCommandDB = "Patronymic";
	private static final String numberOfPassportCommandDB = "NumberOfPassport";
	private static final String positionCommandDB = "Position";
	private static final String eMailCommandDB ="eMail";


	public List<Worker> select() throws DAOException {
		List<Worker> workerArray = new ArrayList<Worker>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ConnectionPool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(select);

			while (rs.next()) {
				String id = "" + rs.getInt(idCommandDB);
				String login = rs.getString(loginCommandDB);
				String name = rs.getString(nameCommandDB);
				String surName = rs.getString(surNameCommandDB);
				String patronymic = rs.getString(patronymicCommandDB);
				String numberOfPassport = rs.getString(numberOfPassportCommandDB);
				String position = rs.getString(positionCommandDB);
				String eMail = rs.getString(eMailCommandDB);	
				Worker worker = new Worker(id, login, name, surName, patronymic, numberOfPassport, position, eMail);
				workerArray.add(worker);
			}
			return workerArray;
		} catch (ConnectionPoolException | SQLException e) {
			log.error("Database write error", e);
			throw new DAOException(e);
		} finally {
			try {
				ConnectionPool.returnConnection(con, st, rs);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error", e);
				throw new DAOException(e);
			}
		}
		
	}

	
	public Worker selectOne(int idUser) throws DAOException{
		Worker worker = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(selectOne);
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			if (rs.next()) {
				String id = "" + rs.getInt(idCommandDB);
				String login = rs.getString(loginCommandDB);
				String name = rs.getString(nameCommandDB);
				String surName = rs.getString(surNameCommandDB);
				String patronymic = rs.getString(patronymicCommandDB);
				String numberOfPassport = rs.getString(numberOfPassportCommandDB);
				String position = rs.getString(positionCommandDB);
				String eMail = rs.getString(eMailCommandDB);
				worker = new Worker(id, login, name, surName, patronymic, numberOfPassport, position, eMail);				
			}
			return worker;
		} catch (SQLException |ConnectionPoolException e) {
			log.error("Database write error", e);
			throw new DAOException(e);
		} finally {
			try {
				ConnectionPool.returnConnection(con, ps, rs);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error", e);
				throw new DAOException(e);
			}
		}		
	}


	public int insert(Worker worker) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(insert);
			ps.setString(1, worker.getId());
			ps.setString(2, worker.getLogin());
			ps.setString(3, worker.getName());
			ps.setString(4, worker.getSurName());
			ps.setString(5, worker.getPatronymic());
			ps.setString(6, worker.getNumberOfPassport());
			ps.setString(7, worker.getPosition());
			ps.setString(8, worker.geteMail());
			return ps.executeUpdate();
		} catch (SQLException |ConnectionPoolException e) {
			log.error("Database write error", e);
			throw new DAOException(e);
		} finally {
			try {
				ConnectionPool.returnConnection(con, ps);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error", e);
				throw new DAOException(e);
			}
		}		
	}


	public int update(Worker worker) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(update);			
			ps.setString(1, worker.getLogin());
			ps.setString(2, worker.getName());
			ps.setString(3, worker.getSurName());
			ps.setString(4, worker.getPatronymic());
			ps.setString(5, worker.getNumberOfPassport());
			ps.setString(6, worker.getPosition());
			ps.setString(7, worker.geteMail());
			ps.setString(8, worker.getId());
			return ps.executeUpdate();
		} catch (SQLException |ConnectionPoolException e) {
			log.error("Database write error", e);
			throw new DAOException(e);
		} finally {
			try {
				ConnectionPool.returnConnection(con, ps);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error", e);
				throw new DAOException(e);
			}
		}		
	}


	public int delete(int id) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(delete);
			ps.setInt(1, id);
			return ps.executeUpdate();
		} catch (SQLException |ConnectionPoolException e) {			
			log.error("Database write error", e);
			throw new DAOException(e);
		} finally {
			try {
				ConnectionPool.returnConnection(con, ps);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error", e);
				throw new DAOException(e);
			}
		}
	}

	private static final String selectOneUser = "SELECT * FROM users WHERE Login = ?";
	private static final String selectOneRole = "SELECT * FROM role WHERE id= ?";	
	private static final String roleCommand = "Role_id";
	private static final String titleCommand = "Title";
	private static final String loginUserCommand = "Login";
	
	public String getRole(String login) throws ConnectionPoolException, DAOException {
		Integer result = null;
		Connection con = ConnectionPool.takeConnection();		
		ResultSet rs = null;
		String role = null;
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(selectOneUser);			
			ps.setString(1, login);
			rs = ps.executeQuery();			
			while (rs.next()) {
				result = rs.getInt(roleCommand);
			}
			
			ps = con.prepareStatement(selectOneRole);			
			ps.setInt(1, result);
			rs = ps.executeQuery();	
			while (rs.next()) {
				role = (rs.getString(titleCommand));
			}
			return role;
		} catch (SQLException e) {
			log.error("Database write error", e);
			throw new DAOException(e);
		} finally {
			try {
				ConnectionPool.returnConnection(con, ps, rs);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error", e);
				throw new DAOException(e);
			}
		}
		
	}
	
	private static final String selectOnTitle = "SELECT * FROM role WHERE Title= ? ";

	@SuppressWarnings("resource")
	public boolean isUser(String login, String role) throws ConnectionPoolException, DAOException {
		String loginResult = null;
		String roleResult = null;

		Connection con = ConnectionPool.takeConnection();		
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {				
			ps = con.prepareStatement(selectOneUser);			
			ps.setString(1, login);
			rs = ps.executeQuery();	
			while (rs.next()) {
				loginResult = rs.getString(loginUserCommand);
			}
			ps = con.prepareStatement(selectOnTitle);			
			ps.setString(1, role);
			rs = ps.executeQuery();
			while (rs.next()) {
				roleResult = (rs.getString(titleCommand));
			}
			if (loginResult != null && roleResult != null) {			
				return true;
			}
			return false;
		} catch (SQLException e) {
			log.error("Database write error", e);
			throw new DAOException(e);
		} finally {
			try {
				ConnectionPool.returnConnection(con, ps, rs);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error", e);
				throw new DAOException(e);
			}
		}		
	}


	public boolean isContain(String login) throws DAOException {

		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = ConnectionPool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(select);
			while (rs.next()) {
				if (login.equals(rs.getString(loginCommandDB))) {
					return true;
				}
			}
			return false;
		} catch (ConnectionPoolException | SQLException e) {			
			log.error("Database write error", e);
			throw new DAOException(e);
		}finally {
			try {
				ConnectionPool.returnConnection(con, st,rs);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error", e);
				throw new DAOException(e);
			}
		}			
	}
}
