package com.example.checking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Menu;
public class Iconidenty extends AsyncTask<String, Void, String> 
{
    Context context;
    Menu menu;
    public Iconidenty(Context context,Menu menu) 
    {
    	this.context=context;
    	this.menu=menu;
	}
    @Override
    protected String doInBackground(String... urls) 
    {
    	try
    	{	  String[] value=urls[0].split("-");
	    	  URL icon = new URL("http://192.168.1.4/grace/app/icon.php?number="+value[0]+"&date="+value[1]+"&forwhat="+value[2]);
	          BufferedReader in = new BufferedReader(
	          new InputStreamReader(icon.openStream()));
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
    		 menu.getItem(0).setIcon(context.getResources().getDrawable(R.drawable.greenel));
    	 }
    	 else
    	 {
    		 menu.getItem(0).setIcon(context.getResources().getDrawable(R.drawable.redel));
    	 }
    }
}