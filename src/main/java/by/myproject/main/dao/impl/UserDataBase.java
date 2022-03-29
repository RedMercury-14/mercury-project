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
import by.myproject.main.entity.Role;
import by.myproject.main.entity.User;

public class UserDataBase implements by.myproject.main.dao.UserDataBase<User> {
	private static final Logger log = Logger.getLogger(UserDataBase.class);
	private static final String select = "SELECT * FROM users";
	private static final String selectOne = "SELECT * FROM users WHERE Login = ?";
	private static final String insert = "INSERT INTO users(Login, Role_id, TabelNumber, Password) VALUES(?,?,?,?)";
	private static final String update = "UPDATE users SET Role_id = ?, TabelNumber = ?, Password = ? WHERE Login = ?";
	private static final String delete = "DELETE FROM users WHERE Login = ?";
	private static final String isContain = "SELECT * FROM users";
	private static final String loginCommand = "Login";
	private static final String role = "Role_id";
	private static final String tabelNum = "TabelNumber";
	private static final String passwordCommand = "Password";
	
	
	public List<User> select() throws DAOException {
		List<User> userArray = new ArrayList<User>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(select);

			while (rs.next()) {
				String login = rs.getString(loginCommand);
				int roleID = rs.getInt(role);
				String tabelNumber = rs.getString(tabelNum);
				String password = rs.getString(passwordCommand);
				User user = new User(login, roleID, tabelNumber, password);
				userArray.add(user);				
			}
			return userArray;
		} catch (SQLException | ConnectionPoolException e) {
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

	public User selectOne(String login) throws DAOException {
		User user = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(selectOne);
			ps.setString(1, login);
			rs = ps.executeQuery();
			if (rs.next()) {
				int roleID = rs.getInt(role);
				String tabelNumber = rs.getString(tabelNum);
				String password = rs.getString(passwordCommand);
				user = new User(login, roleID, tabelNumber, password);
			}
			return user;
		} catch (SQLException | ConnectionPoolException e) {
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

	public int insert(User user) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionPool.takeConnection();			
			ps = con.prepareStatement(insert);
			ps.setString(1, user.getLogin());
			ps.setInt(2, user.getRoleID());
			ps.setString(3, user.getTabelNumber());
			ps.setString(4, user.getPassword());
			return ps.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
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

	public int update(User user) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(update);
			ps.setInt(1, user.getRoleID());
			ps.setString(2, user.getTabelNumber());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getLogin());
			return ps.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
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

	public int delete(String login) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();			
			ps = con.prepareStatement(delete);
			ps.setString(1, login);
			return ps.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
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

	public boolean isContain(String login) throws DAOException {

		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = ConnectionPool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(isContain);
			while (rs.next()) {
				if (login.equals(rs.getString(loginCommand))) {
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
	
	public boolean isUser(String login, String password) throws DAOException {
		String loginResult = null;
		String passwordResult = null;			
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		try {			
			con = ConnectionPool.takeConnection();	
			ps = con.prepareStatement(selectOne);			
			ps.setString(1, login);
			rs = ps.executeQuery();	
			while (rs.next()) {
				loginResult = rs.getString(loginCommand);
				passwordResult = (rs.getString(passwordCommand));
			}		
			if(loginResult == null && passwordResult == null) {
				return false;
			}
			if (loginResult.equals(login) && passwordResult.equals(password)) {
				return true;
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				ConnectionPool.returnConnection(con, ps, rs);
			} catch (ConnectionPoolException | SQLException e) {
				throw new DAOException(e);
			}
		}		
	}
	
	private static final String selectRole = "SELECT * FROM role";
	private static final String titleSelectRole = "Title";
	private static final String idSelectRole = "id";
	
	public List<Role> selectRole() throws DAOException {
		List<Role> roleArray = new ArrayList<Role>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(selectRole);

			while (rs.next()) {
				String title = rs.getString(titleSelectRole);
				int id = rs.getInt(idSelectRole);	
				Role role = new Role(id, title);
				roleArray.add(role);				
			}
			return roleArray;
		} catch (SQLException | ConnectionPoolException e) {
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
	
	private static final String selectOneRole = "SELECT * FROM role WHERE id = ?";
	public Role selectOneRole(int id) throws DAOException {
		Role role = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(selectOneRole);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				String title = rs.getString(titleSelectRole);
				role = new Role(id, title);
			}
			return role;
		} catch (SQLException | ConnectionPoolException e) {
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

}
