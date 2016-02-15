package com.example.checking;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity 
{
	Button btn;
	EditText et1;
	TextView tv1;
	Intent in;	
	SharedPreferences sp,sp1;
	SharedPreferences.Editor se;
	String sendnum;
	NetworkInfo wifi,mobile;
	ConnectivityManager cm;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
			setContentView(R.layout.activity_main);
			tv1=(TextView)findViewById(R.id.textView3);	
			cm= (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
			sp1=this.getSharedPreferences("grace", Context.MODE_PRIVATE);
			String result=sp1.getString("mobile", "none");
			
			if(result=="none")
			{
				btn=(Button)findViewById(R.id.button1);
				btn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) 
				{	
					wifi=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
					mobile=cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
					if(wifi.isConnected()||mobile.isConnected())
					{
						et1=(EditText)findViewById(R.id.editText1);
						sendnum=et1.getText().toString();
						if(sendnum.matches("^\\d{10}$"))
						{
						sp=MainActivity.this.getSharedPreferences("grace", Context.MODE_PRIVATE);
						se=sp.edit();
						new Dbsendretrive(tv1,in,MainActivity.this,se,sendnum).execute(sendnum);
						}
						else
						{
							tv1.setText("Please enter your 10 digit valid number");
						}
					}
					else
					{
						tv1.setText("Please enable data connection");
					}
				}
				});
			}
			else
			{
				Intent in=new Intent(getBaseContext(),Result.class);
				startActivity(in);
			}
		
		
		
		
    }
	
}
