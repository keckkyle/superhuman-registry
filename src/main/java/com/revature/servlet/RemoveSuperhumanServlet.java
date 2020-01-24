package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.SuperhumanService;

public class RemoveSuperhumanServlet extends HttpServlet {

	private SuperhumanService superService = new SuperhumanService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("/delete.html");
		view.forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(superService.deleteSuperhuman(req)) {
			RequestDispatcher rd = req.getRequestDispatcher("/success.html");
			rd.forward(req,resp);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("/fail.html");
			rd.forward(req,resp);
		};
		
	}

}
