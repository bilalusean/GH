package com.example.checking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Getmenu extends AsyncTask<String, Void, String[]> 
{
	
	private Context context;
	private ArrayAdapter adapter;
	private ListView listView;
    public Getmenu(Context context,ArrayAdapter adapter,ListView listView) 
    {
	      this.context = context;
	      this.adapter=adapter;
	      this.listView=listView;
	}
    @Override
    protected String[] doInBackground(String... urls) 
    {
		try{
		    	  URL mitem = new URL("http://192.168.1.4/grace/app/readphp.php?day="+urls[0]+"&forwhat="+urls[1]);
		          BufferedReader in = new BufferedReader(
		          new InputStreamReader(mitem.openStream()));
		          StringBuilder sb = new StringBuilder();
		          String inputLine;	          
		          while ((inputLine = in.readLine()) != null)
		          {
		              sb.append("\n"+inputLine);
		          }
		          String[] lv=sb.toString().trim().split(",");
		          return lv;
	        }
	 	catch(Exception e)
	 	{
	 		String[] lve=new String("Exception: " + e.getMessage()).split(",");
	 		return lve;
	    } 
		
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String[] result) 
    {
    	ArrayAdapter adapter = new ArrayAdapter<String>(context, R.layout.listview,R.id.Itemname,result);
        listView.setAdapter(adapter);
    }
    
}

