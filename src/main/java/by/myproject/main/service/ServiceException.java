package by.myproject.main.service;

public class ServiceException extends Throwable{

	private static final long serialVersionUID = 1L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ServiceException(Throwable cause) {
		super(cause);
		
	}

	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}
