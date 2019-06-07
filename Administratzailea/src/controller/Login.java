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
import encrypt.Encrypter;
import passwordDecoder.HashGenerator;
import sun.security.krb5.EncryptedData;


/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean loginOK;
	String encrypterKey = "mysecretencrypter";

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

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String message = "";
		loginOK = false;

		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		HttpSession session = request.getSession(true);


		if(action!=null && action.equals("login")) {			

			System.out.println("--->"+action);
			System.out.println(username);
			System.out.println(password);

			Encrypter en = new Encrypter(encrypterKey);
			JSONObject json = new JSONObject();

			try {
				json.put("erabiltzailea", en.encrypt(username));
				json.put("password", en.encrypt(password));

				URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/login/langilea");
				HttpURLConnection con = (HttpURLConnection) u.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Content-Length", Integer.toString(json.toString().getBytes().length));
				con.setUseCaches(false);
				con.setDoInput(true);
				con.setDoOutput(true);

				DataOutputStream dos = new DataOutputStream(con.getOutputStream());
				dos.writeBytes(json.toString());
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

				String ans = en.decrypt(loginResponse.toString());
				loginOK = Boolean.valueOf(ans);
				System.out.println(loginOK);
			} catch (JSONException | java.io.IOException e) {
				message = "Ezin izan da logeatu. Arazo bat egon da.";
			}

			if(loginOK) {
				message = "Login OK!";
				session.setAttribute("isLoged", true);
				session.setAttribute("user", username);
				request.setAttribute("message", message);
				response.sendRedirect(request.getContextPath() + "/home");
			}
			else {
				message = "Incorrect username or password.";
			}

		}

		if(!loginOK) {
			session.setAttribute("isLoged", false);
			request.setAttribute("error", message);
			doGet(request, response);
		}

	}


}