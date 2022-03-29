package by.myproject.main.dao;

public class DAOException extends Exception {
	private static final long serialVersionUID = 1189344538341364061L;
	
	public DAOException(Throwable e) {
		super(e);
	}
	
	public DAOException(String message, Throwable e) {
		super(message, e);
	}
}
