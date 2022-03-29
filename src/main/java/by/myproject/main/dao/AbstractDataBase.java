package by.myproject.main.dao;

import java.util.List;

public interface AbstractDataBase<E> {
	
	List<E> select() throws DAOException;
	E selectOne(int id) throws DAOException;
	int insert(E e)throws DAOException;
	int update(E e)throws DAOException;
	int delete (int id)throws DAOException;
}
