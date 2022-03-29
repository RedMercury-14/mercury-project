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
import by.myproject.main.entity.WorkerHasTruck;
import by.myproject.main.util.DateValidator;

public class WorkerHasTruckDataBase implements AbstractDataBase<WorkerHasTruck> {
	private static final Logger log = Logger.getLogger(WorkerHasTruckDataBase.class);
	private static final String select = "SELECT * FROM workers_has_trucks";
	private static final String selectOne = "SELECT * FROM workers_has_trucks WHERE id = ?";
	private static final String insert = "INSERT INTO workers_has_trucks(id, Workers_id, Trucks_CarNumber, WorkersPosition, DateOfStart, DateOfFinish) VALUES(?,?,?,?,?,?)";
	private static final String update = "UPDATE workers_has_trucks SET  Workers_id = ?, Trucks_CarNumber = ?, WorkersPosition = ?, DateOfStart = ?, DateOfFinish = ? WHERE id = ? ";
	private static final String delete = "DELETE FROM workers_has_trucks WHERE id = ?";
	private static final String searchWaybills = "SELECT * FROM workers_has_trucks WHERE Trucks_CarNumber = ?";

	private static final String idCommand = "id";
	private static final String idWorkersCommand = "Workers_id";
	private static final String trucksNumberCommand = "Trucks_CarNumber";
	private static final String positionCommand = "WorkersPosition";

	private static final String DateS = "DateOfStart";
	private static final String DateF = "DateOfFinish";

	@Override
	public List<WorkerHasTruck> select() throws DAOException {
		ArrayList<WorkerHasTruck> workerHasTruckArray = new ArrayList<WorkerHasTruck>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(select);

			while (rs.next()) {
				int id = rs.getInt(idCommand);
				int idWorkers = rs.getInt(idWorkersCommand);
				String trucksNumber = rs.getString(trucksNumberCommand);
				String WorkersPosition = rs.getString(positionCommand);
				String DateOfStart = rs.getString(DateS);
				String DateOfFinish = rs.getString(DateF);

				WorkerHasTruck workerHasTruck = new WorkerHasTruck(id, idWorkers, trucksNumber, WorkersPosition,
						DateOfStart, DateOfFinish);
				workerHasTruckArray.add(workerHasTruck);
			}
			return workerHasTruckArray;
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

	@Override
	public WorkerHasTruck selectOne(int id) throws DAOException {
		WorkerHasTruck workerHasTruck = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(selectOne);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				int idWorkers = rs.getInt(idWorkersCommand);
				String trucksNumber = rs.getString(trucksNumberCommand);
				String WorkersPosition = rs.getString(positionCommand);
				String DateOfStart = rs.getString(DateS);
				String DateOfFinish = rs.getString(DateF);
				workerHasTruck = new WorkerHasTruck(id, idWorkers, trucksNumber, WorkersPosition, DateOfStart,
						DateOfFinish);
			}
			return workerHasTruck;
		} catch (ConnectionPoolException | SQLException e) {
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

	@Override
	public int insert(WorkerHasTruck workerHasTruck) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(insert);
			ps.setInt(1, workerHasTruck.getId());
			ps.setInt(2, workerHasTruck.getIdWorkers());
			ps.setString(3, workerHasTruck.getTrucksNumber());
			ps.setString(4, workerHasTruck.getWorkersPosition());
			DateValidator date = new DateValidator();
			String parsDateOfStart = date.dateParser(workerHasTruck.getDateOfStart());
			String parsDateOfFinish = date.dateParser(workerHasTruck.getDateOfFinish());
			ps.setString(5, parsDateOfStart);
			ps.setString(6, parsDateOfFinish);
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

	@Override
	public int update(WorkerHasTruck workerHasTruck) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(update);
			DateValidator date = new DateValidator();
			ps.setInt(1, workerHasTruck.getIdWorkers());
			ps.setString(2, workerHasTruck.getTrucksNumber());
			ps.setString(3, workerHasTruck.getWorkersPosition());

			String parsDateOfStart = null;
			String parsDateOfFinish = null;

			if (!date.isSQLValidationDate(workerHasTruck.getDateOfStart())) {
				parsDateOfStart = date.dateParser(workerHasTruck.getDateOfStart());
			} else {
				parsDateOfStart = workerHasTruck.getDateOfStart();
			}
			if (!date.isSQLValidationDate(workerHasTruck.getDateOfFinish())) {
				parsDateOfFinish = date.dateParser(workerHasTruck.getDateOfFinish());
			} else {
				parsDateOfFinish = workerHasTruck.getDateOfFinish();
			}

			ps.setString(4, parsDateOfStart);
			ps.setString(5, parsDateOfFinish);
			ps.setInt(6, workerHasTruck.getId());
			return ps.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
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

	@Override
	public int delete(int id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(delete);
			ps.setInt(1, id);
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

	public List<WorkerHasTruck> searchWaybills(String trucksNumber) throws DAOException {
		ArrayList<WorkerHasTruck> workerHasTruckArray = new ArrayList<WorkerHasTruck>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.takeConnection();
			ps = con.prepareStatement(searchWaybills);
			ps.setString(1, trucksNumber);
			rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(idCommand);
				int idWorkers = rs.getInt(idWorkersCommand);
				String WorkersPosition = rs.getString(positionCommand);
				String DateOfStart = rs.getString(DateS);
				String DateOfFinish = rs.getString(DateF);
				WorkerHasTruck workerHasTruck = new WorkerHasTruck(id, idWorkers, trucksNumber, WorkersPosition,
						DateOfStart, DateOfFinish);
				workerHasTruckArray.add(workerHasTruck);
			}
			return workerHasTruckArray;
		} catch (ConnectionPoolException | SQLException e) {
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
