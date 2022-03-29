package by.myproject.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.myproject.main.service.ServiceException;

public interface Command {
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException;

}
