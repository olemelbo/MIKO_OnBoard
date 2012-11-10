package com.example.miko_onboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class DatabaseHelper {
	public void SaveUser(String username) {
		PostRequest task = new PostRequest();
	    task.execute(new String[] {username});
	}
	
	private class PostRequest extends AsyncTask<String, Void, String> {
		URL url = null;
		Integer count;
		HttpURLConnection conn;
		OutputStreamWriter request = null;
		String jsonData = null;
		@Override
		protected String doInBackground(String... params) {
			count = params.length;
			for (int i = 0; i < count; i++) {
				String param = "postData=" + params[i];
				
		    	try {
			        //Open the connection
		    		url = new URL("http://skole.ocmelbostad.com/fadder/getSpecificEvent.php");
			        conn = (HttpURLConnection) url.openConnection();
			        conn.setDoOutput(true);
			        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			        //Specify the request metod
			        conn.setRequestMethod("POST");
			        //Get the OutputStream
			        request = new OutputStreamWriter(conn.getOutputStream());
			        //Add the params
			        request.write(param);
			        request.flush();
			        request.close();
			        String line = "";
			        //Read the jsonString
			        InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			        //Create a new BufferedReader
			        BufferedReader reader = new BufferedReader(isr);
			        StringBuilder sb = new StringBuilder();
			        while ((line = reader.readLine()) != null) {
			            //Append line to string builder
			        		sb.append(line + " ");
			        }
			        //Convert JSON data to string
			        jsonData = sb.toString();
			    } catch(IOException e) {
			        	Log.d("Network","Network Error: " + e);
			    }
		    }
		    return jsonData;
		}
		
		protected void onPostExecute(String jsonData) {
			
		}

		
	}//End PostRequest
	
}// End DatabaseHelper
