package by.myproject.main.dao;

import java.util.List;

public interface TruckDataBase<E> {
	List<E> select() throws DAOException;
	E selectOne(String id) throws DAOException;
	int insert(E e)throws DAOException;
	int update(E e)throws DAOException;
	int delete (String id)throws DAOException;
}
