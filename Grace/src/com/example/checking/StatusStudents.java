package com.example.checking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import android.content.Context;
import android.os.AsyncTask;
public class StatusStudents extends AsyncTask<String, Void, String> 
{
    Context context;
    public StatusStudents(Context context) 
    {
    	this.context=context;
	}
    @Override
    protected String doInBackground(String... urls) 
    {
    	try
    	{	  String[] value=urls[0].split("-");
	    	  URL status = new URL("http://192.168.1.4/grace/app/status.php?number="+value[1]+"&status="+value[0]+"&date="+value[2]+"&forwhat="+value[3]+"&location="+value[4]);
	          BufferedReader in = new BufferedReader(
	          new InputStreamReader(status.openStream()));
	          StringBuilder sb = new StringBuilder();
	          String inputLine;	          
	          while ((inputLine = in.readLine()) != null)
	          {
	              sb.append("\n"+inputLine);
	          }
	          String lv=sb.toString().trim();
	          return lv;
        }
		catch(Exception e)
		{
			String lve=new String("Exception: " + e.getMessage());
			return lve;
	    } 
    	
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) 
    {
    	 if(result.equals("accept"))
    	 {
    		 
    	 }
    	 else
    	 {
    		 
    	 }
    }
}