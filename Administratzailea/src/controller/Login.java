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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import binding.Bind;
import binding.Erabiltzaileak;
import binding.Erabiltzaileak.Erabiltzailea;
import passwordDecoder.HashGenerator;


/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		String s = "";
		try {

			URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/register/getKey");
			URLConnection con = u.openConnection();
			Reader reader = new InputStreamReader(con.getInputStream());
			while (true) {
				int ch = reader.read();
				if (ch==-1) {
					break;
				}
				s = s + (char)ch;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("***********"+s);


		String s = "";
		try {
			//URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/login/login");

			URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/database.utils.erabiltzailea");
			URLConnection con = u.openConnection();
			               Reader reader = new InputStreamReader(con.getInputStream());
			               while (true) {
			                 int ch = reader.read();
			                 if (ch==-1) {
			                   break;
			                 }
			                 s = s + (char)ch;
			               }
			} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		System.out.println(s);

		if(s != null) {
			Bind b = new Bind();
			Erabiltzaileak erabiltzaileak = b.bindErabiltzaileak(s);

			for(Erabiltzailea e : erabiltzaileak.getErabiltzailea()) {
				if(e.getErabiltzailea().equals(username)) {
					System.out.println(e.getIzena()+" erabiltzailea logeatzen saiatzen");
				}
			}
		}*/


		/*
		 */
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean loginOK = false;

		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		HttpSession session = request.getSession(true);


		if(action!=null && action.equals("login")) {			

			System.out.println("--->"+action);
			System.out.println(username);
			System.out.println(password);

			String message = null;

			try {
				JSONObject json = new JSONObject();
				json.put("erabiltzailea", username);
				

				//String passwordHash1 = BCrypt.hashpw(password);
				String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
				
				json.put("passwordHash", passwordHash);
				System.out.println(passwordHash);

				message = json.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			System.out.println(message);
			if(message!=null) {

				URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/login/langilea");
				HttpURLConnection con = (HttpURLConnection) u.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Content-Length", Integer.toString(message.getBytes().length));
				con.setUseCaches(false);
				con.setDoInput(true);
				con.setDoOutput(true);

				DataOutputStream dos = new DataOutputStream(con.getOutputStream());
				dos.writeBytes(message);
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

				loginOK = Boolean.parseBoolean(loginResponse.toString().replaceAll("\\r|\\n", ""));

			}
loginOK = true;
			if(loginOK) {
				session.setAttribute("isLoged", true);
				session.setAttribute("user", username);
				request.setAttribute("success", "Wellcome "+username+"!");
				response.sendRedirect(request.getContextPath() + "/home");
			}

		}

		if(!loginOK) {
			session.setAttribute("isLoged", false);
			request.setAttribute("error", "Incorrect userame or password.");
			doGet(request, response);
		}

	}


}