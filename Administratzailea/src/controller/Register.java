package controller;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import binding.Bind;
import binding.Erabiltzaileak;
import binding.Erabiltzaileak.Erabiltzailea;


/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		;


		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean registerOK = false;
		
		String action = request.getParameter("action");
		String izena = request.getParameter("izena");
		String abizena = request.getParameter("abizena");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("passwordRepeat");
		HttpSession session = request.getSession(true);


		if(action!=null && action.equals("register")) {

			System.out.println("--->"+action);
			System.out.println(izena);
			System.out.println(abizena);
			System.out.println(email);
			System.out.println(username);
			System.out.println(password);
			System.out.println(passwordRepeat);

			URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/register/register");
			HttpURLConnection con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencode");
			String params = username+"_"+password;
			con.setRequestProperty("Content-Length", Integer.toString(params.getBytes().length));
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);

			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
			dos.writeBytes(params);
			dos.flush();
			dos.close();

			InputStream is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer loginResponse = new StringBuffer();
			while((line = br.readLine())!=null) {
				loginResponse.append(line);
				loginResponse.append('\r');
			}

			br.close();
			registerOK = Boolean.parseBoolean(loginResponse.toString());
			System.out.println(registerOK);


		}

		if(registerOK) {
			response.sendRedirect(request.getContextPath() + "/erabiltzaileak");
		}
		else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.html");
			dispatcher.forward(request, response);
		}
		
	}

}