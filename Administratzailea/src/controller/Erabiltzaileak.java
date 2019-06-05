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

import binding.Bind;


@WebServlet("/erabiltzaileak")
public class Erabiltzaileak extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Erabiltzaileak() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String s = "";
		/*try {
			URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/erabiltzaileak/safe");
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
			}*/
		System.out.println(s);
		
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erabiltzaileak.jsp");
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String[] checkboxClicked = request.getParameterValues("button_clicked");
		String buttonClicked = request.getParameter("bajan_eman");
		
		if(null == buttonClicked) {
			System.out.println("NULL");	
		}
		else if("baja".equals(buttonClicked)) {
			System.out.println("Bajan eman");
			System.out.println(checkboxClicked);
			
			if(checkboxClicked!=null) {
				String send=checkboxClicked[0];
				for(int i=1; i<checkboxClicked.length; i++) {
					System.out.println(checkboxClicked[i]+" naiz");
					send+=";"+checkboxClicked[i];
				}
				

				URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/database.utils.erabiltzailea/baja") ;
				HttpURLConnection con = (HttpURLConnection) u.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/x-www-form-urlencode");
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
				System.out.println(loginResponse.toString());
				request.setAttribute("success", checkboxClicked.length+" erabiltzaile ondo eman dira bajan.");
				request.setAttribute("jsonMessage", loginResponse.toString());
			}
			
			
		}
		
			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erabiltzaileak.jsp");
		dispatcher.forward(request, response);	
	}
}
