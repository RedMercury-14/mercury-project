package by.myproject.main.dao;

import java.util.List;

import by.myproject.main.entity.Role;

public interface UserDataBase<E> {
	List<E> select() throws DAOException;
	E selectOne(String login) throws DAOException;
	int insert(E e)throws DAOException;
	int update(E e)throws DAOException;
	int delete (String login)throws DAOException;
	boolean isUser(String login, String password) throws DAOException;
	boolean isContain(String login)throws DAOException;
	public List<Role> selectRole() throws DAOException;
	public Role selectOneRole(int id) throws DAOException;

}
