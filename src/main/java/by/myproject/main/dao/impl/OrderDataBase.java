package by.myproject.main.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.myproject.main.dao.AbstractDataBase;
import by.myproject.main.dao.DAOException;
import by.myproject.main.dao.config.ConnectionPool;
import by.myproject.main.dao.config.ConnectionPoolException;
import by.myproject.main.entity.Order;
import by.myproject.main.util.DateValidator;

public class OrderDataBase implements AbstractDataBase<Order> {
	private static final Logger log = Logger.getLogger(OrderDataBase.class);
	
	private static final String select = "SELECT * FROM orders";
	private static final String selectOne = "SELECT * FROM orders WHERE NumberOrder = ?";
	private static final String insert = "INSERT INTO orders(NumberOrder, Route, Orderscol, DateLoad, DateCustoms, DateUnload, Client, Price, Cargo, Trucks_CarNumber, Users_Login, Users_admin, Status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String update = "UPDATE orders SET  Route = ?, Orderscol = ?, DateLoad = ?, DateCustoms = ?, DateUnload = ?, Client = ?, Price = ?, Cargo = ?, Trucks_CarNumber = ?, Users_Login = ?, Users_admin = ?, Status = ? WHERE NumberOrder = ? ";
	private static final String delete = "DELETE FROM orders WHERE NumberOrder = ?";
	private static final String numOrder = "NumberOrder";
	private static final String routeCommand = "Route";
	private static final String ordColCommand = "Orderscol";
	private static final String dateL = "DateLoad";
	private static final String dateC = "DateCustoms";
	private static final String dateU = "DateUnload";
	private static final String clietnCommand = "Client";
	private static final String priceCommand = "Price";
	private static final String cargoCommand = "Cargo";
	private static final String carNumCommand = "Trucks_CarNumber";
	private static final String userLoginCommand = "Users_Login";
	private static final String adminCommand = "Users_admin";
	private static final String statusCommand = "Status";

	@Override
	public List<Order> select() throws DAOException{
		ArrayList<Order> orderArray = new ArrayList<Order>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(select);

			while (rs.next()) {
				int numOrd = rs.getInt(numOrder);
				String route = rs.getString(routeCommand);
				String ordCol= rs.getString(ordColCommand);
				String dateLoad= rs.getString(dateL);
				String dateCust= rs.getString(dateC);
				String dateUnload= rs.getString(dateU);
				String client= rs.getString(clietnCommand);
				String prise= rs.getString(priceCommand);
				String cargo= rs.getString(cargoCommand);
				String carNum= rs.getString(carNumCommand);
				String user= rs.getString(userLoginCommand);
				String admin= rs.getString(adminCommand);
				String status= rs.getString(statusCommand);
				Order order = new Order(numOrd, route, ordCol, dateLoad, dateCust, dateUnload, client, prise, cargo, carNum, user, admin, status);
				orderArray.add(order);
			}
			return orderArray;
		} catch (SQLException | ConnectionPoolException e) {			
			log.error("Database write error");
			throw new DAOException(e);
		} finally {
			try {
				ConnectionPool.returnConnection(con, st, rs);
			} catch (ConnectionPoolException | SQLException e) {				
				log.error("ConnectionPool closing error");
				throw new DAOException(e);
			}
		}		
	}
	@Override
	public Order selectOne(int numOrdOne) throws DAOException{
		Order order = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(selectOne);
			ps.setInt(1, numOrdOne);
			rs = ps.executeQuery();
			if (rs.next()) {
				int numOrd = rs.getInt(numOrder);
				String route = rs.getString(routeCommand);
				String ordCol= rs.getString(ordColCommand);
				String dateLoad= rs.getString(dateL);
				String dateCust= rs.getString(dateC);
				String dateUnload= rs.getString(dateU);
				String client= rs.getString(clietnCommand);
				String prise= rs.getString(priceCommand);
				String cargo= rs.getString(cargoCommand);
				String carNum= rs.getString(carNumCommand);
				String user= rs.getString(userLoginCommand);
				String admin= rs.getString(adminCommand);
				String status= rs.getString(statusCommand);
				order = new Order(numOrd, route, ordCol, dateLoad, dateCust, dateUnload, client, prise, cargo, carNum, user, admin, status);
			}
			return order;
		} catch (ConnectionPoolException | SQLException e){
			log.error("Database write error");
			throw new DAOException(e);
		}finally {
			try {
				ConnectionPool.returnConnection(con, ps, rs);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error");
				throw new DAOException(e);
			}
		}
		
	}	
	@Override
	public int insert(Order order) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(insert);
			ps.setInt(1, order.getNumOrd());
			ps.setString(2, order.getRoute());
			ps.setString(3, order.getOrdCol());			
			DateValidator date = new DateValidator();
			String parsDataLoad = date.dateParser(order.getDateLoad());
			String parsDateCust = date.dateParser(order.getDateCust());
			String parsDateUnload = date.dateParser(order.getDateUnload());
			ps.setString(4, parsDataLoad);
			ps.setString(5, parsDateCust);
			ps.setString(6, parsDateUnload);
			ps.setString(7, order.getClient());
			ps.setString(8, order.getPrise());
			ps.setString(9, order.getCargo());
			ps.setString(10, order.getCarNum());
			ps.setString(11, order.getUser());
			ps.setString(12, order.getAdmin());
			ps.setString(13, order.getStatus());
			return ps.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
			log.error("Database write error");		
			throw new DAOException(e);
		} finally {
			try {
				ConnectionPool.returnConnection(con, ps);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error");
				throw new DAOException(e);
			}
		}
	}
	@Override
	public int update(Order order) throws DAOException{

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(update);
			DateValidator date = new DateValidator();
			
			ps.setString(1, order.getRoute());
			ps.setString(2, order.getOrdCol());
			String parsDataLoad = null;
			String parsDateCust = null;
			String parsDateUnload = null;
			
			if(!date.isSQLValidationDate(order.getDateLoad()) || !date.isSQLValidationDate(order.getDateCust()) || !date.isSQLValidationDate(order.getDateUnload())) {				
				 parsDataLoad = date.dateParser(order.getDateLoad());
				 parsDateCust = date.dateParser(order.getDateCust());
				 parsDateUnload = date.dateParser(order.getDateUnload());
			}else{
				 parsDataLoad = order.getDateLoad();
				 parsDateCust = order.getDateCust();
				 parsDateUnload = order.getDateUnload();
			}
			
			ps.setString(3, parsDataLoad);
			ps.setString(4, parsDateCust);
			ps.setString(5, parsDateUnload);			
			ps.setString(6, order.getClient());
			ps.setString(7, order.getPrise());
			ps.setString(8, order.getCargo());
			ps.setString(9, order.getCarNum());
			ps.setString(10, order.getUser());
			ps.setString(11, order.getAdmin());
			ps.setString(12, order.getStatus());
			ps.setInt(13, order.getNumOrd());
			return ps.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			log.error("Database write error");
			throw new DAOException(e);
		} finally {
			try {
				ConnectionPool.returnConnection(con, ps);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error");
				throw new DAOException(e);
			}
		}
	}
	@Override
	public int delete(int numOrd) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(delete);
			ps.setInt(1, numOrd);
			return ps.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
			log.error("Database write error",e);
			throw new DAOException(e);
		} finally {
			try {
				ConnectionPool.returnConnection(con, ps);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error");
				throw new DAOException(e);
			}
		}		
	}
}
