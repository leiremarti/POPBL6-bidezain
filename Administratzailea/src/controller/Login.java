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
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
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
			
			URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/login/login");
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
			
			loginOK = Boolean.parseBoolean(loginResponse.toString().replaceAll("\\r|\\n", ""));
			
		}
		
		if(loginOK) {
			response.sendRedirect(request.getContextPath() + "/home");
		}else {
			doGet(request, response);
		}
	}

}