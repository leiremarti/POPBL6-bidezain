/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PublicKey;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("izena","Peio");
        jsonObject.put("abizena","Joxepe");
        jsonObject.put("erabiltzailea","peiojoxepe");
        jsonObject.put("passwordHash","0x31000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        jsonObject.put("passwordSalt","");
        jsonObject.put("eposta","pjoxepe@posta.com");
        jsonObject.put("telefonoa","112");
        System.out.print("++++++sad+++++"+jsonObject.toString());
        
        URL u = new URL("http://localhost:8080/ZerbitzariaBidezain/webresources/register/erabiltzailea");
	HttpURLConnection con = (HttpURLConnection) u.openConnection();
	con.setRequestMethod("POST");
	con.setRequestProperty("Content-Type", "application/json");
	con.setRequestProperty("Content-Length", Integer.toString(jsonObject.toString().getBytes().length));
	con.setUseCaches(false);
	con.setDoInput(true);
	con.setDoOutput(true);

	DataOutputStream dos = new DataOutputStream(con.getOutputStream());
	dos.writeBytes(jsonObject.toString());
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

    }
    
}
