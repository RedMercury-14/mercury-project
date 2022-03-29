package by.myproject.main.dao;

import java.util.List;

import by.myproject.main.dao.config.ConnectionPoolException;
import by.myproject.main.entity.Worker;

public interface WorkerDataBase<E> {
	
	public List<Worker> select() throws DAOException ;

	
	public Worker selectOne(int idUser) throws DAOException;


	public int insert(E e) throws DAOException;


	public int update(E e) throws DAOException;


	public int delete(int id) throws DAOException;

	
	public String getRole(String login) throws ConnectionPoolException, DAOException ;
	
	
	public boolean isUser(String login, String role) throws ConnectionPoolException, DAOException;	
	

	public boolean isContain(String login) throws DAOException;

}
