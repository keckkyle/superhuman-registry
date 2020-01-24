package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.SuperhumanService;

public class CreateSuperhumanServlet extends HttpServlet {
	
	private SuperhumanService superService = new SuperhumanService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/create.html");
		rd.forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(superService.addSuperhuman(req)) {
			RequestDispatcher rd = req.getRequestDispatcher("/success.html");
			rd.forward(req,resp);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("/fail.html");
			rd.forward(req,resp);
		};
	}

}
