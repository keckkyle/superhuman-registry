package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojos.Superhuman;
import com.revature.services.SuperhumanService;

public class SuperhumanServlet extends HttpServlet {
	
	private static SuperhumanService supServ = new SuperhumanService();

	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = resp.getWriter();
		
		String supers = supServ.getSuperhumans();
		
		pw.write(supers);
	}
}
