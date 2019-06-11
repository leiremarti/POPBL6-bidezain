package controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import binding.Bind;
import encrypt.Encrypter;

/**
 * Servlet implementation class Aurreikuspenak
 * Web orrialdean erabiltzaileak kargatzen duen klasea
 * @author user
 */
@WebServlet("/erabiltzaileak")
public class Erabiltzaileak extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String encrypterKey = "mysecretencrypter";

	/**
	 * Erabiltzaileak HttpServlet sortzen da
	 * @see HttpServlet#HttpServlet()
	 */
	public Erabiltzaileak() {
		super();
	}

	/**
	 * Erabiltzaileak orritik GET petizioak jasotzen ditu
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param value
     *     allowed object is
     *     {@link HttpServletRequest, HttpServletResponse}
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erabiltzaileak.jsp");
		dispatcher.forward(request, response);	
	}

	/**
	 * 
	 * Erabiltzaileak POST petizioak jasotzen ditu
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param value
     *     allowed object is
     *     {@link HttpServletRequest, HttpServletResponse}
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checkboxClicked = request.getParameterValues("button_clicked");
		String buttonClicked = request.getParameter("bajan_eman");
		
		if(null == buttonClicked) {
			System.out.println("NULL");	
		}
		else if("baja".equals(buttonClicked)) {
			System.out.println("Bajan eman");
			System.out.println(checkboxClicked);
			
			if(checkboxClicked!=null) {
				JSONArray array = new JSONArray();
				for(int i=0; i<checkboxClicked.length; i++) {
					System.out.println(checkboxClicked[i]+" naiz");
					array.put(checkboxClicked[i]);
				}
				
				Encrypter en = new Encrypter(encrypterKey);
				String send = en.encrypt(array.toString());
				URL u = new URL("http://localhost:8080/BidezainZerbitzaria/webresources/database.utils.erabiltzailea/baja") ;
				HttpURLConnection con = (HttpURLConnection) u.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "text/plain");
				con.setRequestProperty("Content-Length", Integer.toString(send.getBytes().length));
				con.setUseCaches(false);
				con.setDoInput(true);
				con.setDoOutput(true);

				DataOutputStream dos = new DataOutputStream(con.getOutputStream());
				dos.writeBytes(send);
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
				String jsonMessage = en.decrypt(loginResponse.toString());
				System.out.println(jsonMessage);
				
				request.setAttribute("message", checkboxClicked.length+" erabiltzaile ondo eman dira bajan.");
				request.setAttribute("jsonMessage", jsonMessage);
			}
			
			
		}
		
			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erabiltzaileak.jsp");
		dispatcher.forward(request, response);	
	}
}
