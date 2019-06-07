package controller;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
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
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import binding.Bind;
import binding.Erabiltzaileak;
import binding.Erabiltzaileak.Erabiltzailea;
import binding.Langilea;
import encrypt.Encrypter;


/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MIN_STRENGTH = 7;
	String message;
	boolean registerOK;
	String encrypterKey = "mysecretencrypter";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		message = null;
		registerOK = false;
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

		String action = request.getParameter("action");
		String izena = request.getParameter("izena");
		String abizena = request.getParameter("abizena");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//String passwordRepeat = request.getParameter("passwordRepeat");
		HttpSession session = request.getSession(true);


		if(action!=null && action.equals("register")) {
			
			if(calculatePasswordStrength(password)>=MIN_STRENGTH) {
				registerAction(izena, abizena, email, username, password);
			}else {
				message = "Pasahitza ez da nahikoa indartsua. Gutxienez 8 karaktere izan behar ditu.";
			}

			
		}

		if(registerOK) {
			session.setAttribute("isLoged", true);
			session.setAttribute("user", username);
			request.setAttribute("message", message);
			response.sendRedirect(request.getContextPath() + "/home");
		}
		else {
			System.out.println(message);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register");
			session.setAttribute("isLoged", false);
			request.setAttribute("error", message);
			response.sendRedirect(request.getContextPath() + "/register");
		//	dispatcher.forward(request, response);
		}

	}

	private boolean registerAction(String izena, String abizena, String email, String username, String password) {
		Encrypter e = new Encrypter(encrypterKey);

		JSONObject object = new JSONObject();
		String passwordHash = null;
		try {
			object.put("izena", e.encrypt(izena));
			object.put("abizena", e.encrypt(abizena));
			object.put("eposta", e.encrypt(email));
			object.put("erabiltzailea", e.encrypt(username));
			object.put("passwordHash", e.encrypt(password)); //Erregistratzerakoan pasahitza normal pasatzen da, soilik enkriptatuta
			object.put("telefonoa", "");
			object.put("ID_mota", 1);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}


		try {

			/*	Langilea l = new Langilea();
			l.setIzena(izena);
			l.setAbizena(abizena);
			l.setEposta(email);
			l.setErabiltzailea(username);
			l.setPasswordHash(password.getBytes());
			l.setTelefonoa("");
		//	l.setIDmota(1);
			JAXBContext jaxbContext = JAXBContext.newInstance(Langilea.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(l, sw);
			System.out.println(sw.toString());*/  

			URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/register/langilea");

			HttpURLConnection con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			//con.setRequestProperty("Content-Length", Integer.toString(sw.toString().getBytes().length));
			con.setRequestProperty("Content-Length", Integer.toString(object.toString().getBytes().length));
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);

			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
			//dos.writeBytes(sw.toString());
			dos.writeBytes(object.toString());
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

			JSONObject o = new JSONObject(loginResponse.toString());
			o.put("regOK", e.decrypt(String.valueOf(o.get("regOK"))));
			message = (String)o.get("message");
			o.put("message", e.decrypt(message));
			registerOK = Boolean.valueOf((String) o.get("regOK"));
			System.out.println(o.toString());
			
		} catch (JSONException | IOException i) {
			message = "Register failed";
			registerOK = false;
		}
		
		return registerOK;
	}

	public int calculatePasswordStrength(String password){

		int iPasswordScore = 0;

		if( password.length() < 8 )
			return 0;
		else if( password.length() >= 10 )
			iPasswordScore += 2;
		else
			iPasswordScore += 1;

		/*
		 * if password contains 2 digits, add 2 to score.
		 * if contains 1 digit add 1 to score
		 */
		if( password.matches("(?=.*[0-9].*[0-9]).*") )
			iPasswordScore += 2;
		else if ( password.matches("(?=.*[0-9]).*") )
			iPasswordScore += 1;

		//if password contains 1 lower case letter, add 2 to score
		if( password.matches("(?=.*[a-z]).*") )
			iPasswordScore += 2;

		/*
		 * if password contains 2 upper case letters, add 2 to score.
		 * if contains only 1 then add 1 to score.
		 */
		if( password.matches("(?=.*[A-Z].*[A-Z]).*") )
			iPasswordScore += 2;
		else if( password.matches("(?=.*[A-Z]).*") )
			iPasswordScore += 1;

		/*
		 * if password contains 2 special characters, add 2 to score.
		 * if contains only 1 special character then add 1 to score.
		 */
		if( password.matches("(?=.*[~!@#$%^&*()_-].*[~!@#$%^&*()_-]).*") )
			iPasswordScore += 2;
		else if( password.matches("(?=.*[~!@#$%^&*()_-]).*") )
			iPasswordScore += 1;
		return iPasswordScore;
	}

}