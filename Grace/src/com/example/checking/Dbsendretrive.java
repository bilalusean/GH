package com.example.checking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

public class Dbsendretrive extends AsyncTask<String, Void, String> 
{
    Context context;
    TextView tv1;
    SharedPreferences.Editor se;
    String sendnum;
    public Dbsendretrive(TextView tv1,Intent in,Context context,SharedPreferences.Editor se,String sendnum) 
    {
    	this.context=context;
    	this.tv1=tv1;
    	this.se=se;
    	this.sendnum=sendnum;
	}
    @Override
    protected String doInBackground(String... urls) 
    {
    	
    	try
    	{
	    	  URL numberval = new URL("http://192.168.1.4/grace/app/register.php?number="+urls[0]);
	          BufferedReader in = new BufferedReader(
	          new InputStreamReader(numberval.openStream()));
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
    	 
    	if(result.equals("added"))
    	{
    		se.putString("mobile", sendnum);
			se.commit();
    		Intent s = new Intent(context, Terms.class);
    		context.startActivity(s);
    	}
    	else if(result.equals("exist"))
    	{
    		se.putString("mobile", sendnum);
			se.commit();
    		//tv1.setText("2"+result);
    		Intent s = new Intent(context, Result.class);
    		context.startActivity(s);
    	}
    	else
    	{
    		tv1.setText(result+" User");
    	}
    	
    }
}