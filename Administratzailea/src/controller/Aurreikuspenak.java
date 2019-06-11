package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Aurreikuspenak
 * Web orrialdean aurreikuspenak kargatzen duen klasea
 * @author user
 */
@WebServlet("/aurreikuspenak")
public class Aurreikuspenak extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 *Aurreikuspenak HttpServlet sortzen da
	 * @see HttpServlet#HttpServlet()
	 */
	public Aurreikuspenak() {
		super();
	}

	/**
	 * Aurreikuspenak orritik GET petizioak jasotzen ditu
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param value
     *     allowed object is
     *     {@link HttpServletRequest, HttpServletResponse}
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/aurreikuspenak.jsp");
		dispatcher.forward(request, response);	
	}

	/**
	 * Aurreikuspeneko POST petizioak jasotzen ditu
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param value
     *     allowed object is
     *     {@link HttpServletRequest, HttpServletResponse}
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
