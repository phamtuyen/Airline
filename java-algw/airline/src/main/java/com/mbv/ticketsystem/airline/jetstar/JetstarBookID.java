package com.mbv.ticketsystem.airline.jetstar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JetstarBookID {
	public String createBookID(String urlPayment,String ipAddress) throws Exception{
		String bookID = "";			
		// http://54.254.11.91:9091/JetstarBook
		URL url = new URL("http://"+ipAddress+":9092/JetstarBookID");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		String input = "{\""+urlPayment+"\"}";		
		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
		String output;		
		while ((output = br.readLine()) != null) {
			bookID = output;
		}
		conn.disconnect();		
		return bookID;
		
	}
}
