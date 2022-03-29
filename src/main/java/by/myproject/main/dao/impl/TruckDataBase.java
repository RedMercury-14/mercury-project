package by.myproject.main.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.myproject.main.dao.DAOException;
import by.myproject.main.dao.config.ConnectionPool;
import by.myproject.main.dao.config.ConnectionPoolException;
import by.myproject.main.entity.Truck;
import by.myproject.main.util.DateValidator;

public class TruckDataBase implements by.myproject.main.dao.TruckDataBase<Truck> {
	private static final Logger log = Logger.getLogger(TruckDataBase.class);
	
	private static final String select = "SELECT * FROM trucks";
	private static final String selectOne = "SELECT * FROM trucks WHERE CarNumber = ?";
	private static final String insert = "INSERT INTO trucks(CarNumber, TrailerNumber, CarModel, TrailerModel, TypeTraler, YearOfMadeCar, YearOfMadeTrailer) VALUES(?,?,?,?,?,?,?)";
	private static final String update = "UPDATE trucks SET TrailerNumber = ?, CarModel = ?, TrailerModel = ?, TypeTraler = ?, YearOfMadeCar= ?, YearOfMadeTrailer = ?  WHERE CarNumber = ?";
	private static final String delete = "DELETE FROM trucks WHERE CarNumber = ?";
	private static final String numCarCommand = "CarNumber";
	private static final String numTrCommand = "TrailerNumber";
	private static final String carModelCommand = "CarModel";
	private static final String trModelCommand = "TrailerModel";
	private static final String typeTrCommand = "TypeTraler";
	private static final String dateCarCommand = "YearOfMadeCar";
	private static final String dateTrCommand = "YearOfMadeTrailer";

	public List<Truck> select() throws DAOException{
		List<Truck> truckArray = new ArrayList<Truck>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(select);

			while (rs.next()) {
				String numCar = rs.getString(numCarCommand);
				String numTr = rs.getString(numTrCommand);
				String carModel = rs.getString(carModelCommand);
				String trModel = rs.getString(trModelCommand);
				String typeTr = rs.getString(typeTrCommand);
				String dateCar = rs.getString(dateCarCommand);
				String dateTr = rs.getString(dateTrCommand);
				
				Truck truck = new Truck(numCar, numTr, carModel, trModel, typeTr, dateCar, dateTr);
				truckArray.add(truck);
			}
			return truckArray;
		} catch (SQLException |ConnectionPoolException e) {
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

	public Truck selectOne(String numCarOne) throws DAOException{
		Truck truck = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(selectOne);
			ps.setString(1, numCarOne);
			rs = ps.executeQuery();
			if (rs.next()) {
				String numCar = rs.getString(numCarCommand);
				String numTr = rs.getString(numTrCommand);
				String carModel = rs.getString(carModelCommand);
				String trModel = rs.getString(trModelCommand);
				String typeTr = rs.getString(typeTrCommand);
				String dateCar = rs.getString(dateCarCommand);
				String dateTr = rs.getString(dateTrCommand);
				truck = new Truck(numCar, numTr, carModel, trModel, typeTr, dateCar, dateTr);
			}
			return truck;
		} catch (SQLException |ConnectionPoolException e) {
			log.error("Database write error");
			throw new DAOException(e);
		} finally {
			try {
				ConnectionPool.returnConnection(con, ps, rs);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error");
				throw new DAOException(e);
			}
		}
		
	}

	public int insert(Truck truck) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(insert);
			DateValidator date = new DateValidator();
			ps.setString(1, truck.getNumCar());
			ps.setString(2, truck.getNumTr());
			ps.setString(3, truck.getCarModel());
			ps.setString(4, truck.getTrModel());
			ps.setString(5, truck.getTypeTr());				
			String parsDataCar = date.dateParser(truck.getDateCar());
			String parsDataTr = date.dateParser(truck.getDateTr());
			ps.setString(6, parsDataCar);
			ps.setString(7, parsDataTr);
			return ps.executeUpdate();
		} catch (SQLException |ConnectionPoolException e) {
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

	public int update(Truck truck) throws DAOException{

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(update);
			ps.setString(1, truck.getNumTr());
			ps.setString(2, truck.getCarModel());
			ps.setString(3, truck.getTrModel());
			ps.setString(4, truck.getTypeTr());	
			DateValidator date = new DateValidator();
			String parsDataCar = null;
			String parsDataTr = null;
			
			if(!date.isSQLValidationDate(truck.getDateCar()) || !date.isSQLValidationDate(truck.getDateTr())) {
				parsDataCar = date.dateParser(truck.getDateCar());
				parsDataTr = date.dateParser(truck.getDateTr());
			}else{
				parsDataCar = truck.getDateCar();
				parsDataTr = truck.getDateTr();
			}
			
			ps.setString(5, parsDataCar);
			ps.setString(6, parsDataTr);
			
			
			ps.setString(7, truck.getNumCar());
			return ps.executeUpdate();
		} catch (SQLException |ConnectionPoolException e) {
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

	public int delete(String numCar) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(delete);
			ps.setString(1, numCar);
			return ps.executeUpdate();
		} catch (SQLException |ConnectionPoolException e) {			
			log.error("Database write error");
			throw new DAOException(e);
		}finally {
			try {
				ConnectionPool.returnConnection(con, ps);
			} catch (ConnectionPoolException | SQLException e) {
				log.error("ConnectionPool closing error");
				throw new DAOException(e);
			}
		}
	}

}
