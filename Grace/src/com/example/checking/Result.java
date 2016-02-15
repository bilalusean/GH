package com.example.checking;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.app.NotificationCompat.Builder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("NewApi")
public class Result extends ActionBarActivity implements LocationListener{
	SharedPreferences sp2;
	RelativeLayout rl;
	ListView listView;
	ImageView iv;
	Menu menu;
	Date now;
	ArrayAdapter adapter;	
	LocationManager lm;
	SimpleDateFormat dayofweek,forwhat,date;
	String timedecision,latlon="none",result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		lm=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		//lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		sp2=Result.this.getSharedPreferences("grace", Context.MODE_PRIVATE); 
	    result=sp2.getString("mobile", "none");
	    now = new Date();
        dayofweek = new SimpleDateFormat("EEEE");
        forwhat = new SimpleDateFormat("H");
        date = new SimpleDateFormat("yyyy.MM.dd");
        /*NotificationCompat.Builder mBuilder =(Builder) new NotificationCompat.Builder(this).setSmallIcon(R.drawable.redel).setContentTitle("Grace Illam").setContentText("still you are not accept your meals");
        Intent resultIntent = new Intent(this, Result.class);
        mBuilder.setAutoCancel(true);
    	TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Result.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build()); */
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.menu = menu;
        int title=Integer.parseInt(forwhat.format(now));
        if(title>=0 && title<9)
        {
        	timedecision="Breakfast";
        }
        if(title>=9 && title<14)
        {
        	timedecision="Lunch";
        }
        if(title>=14 && title<24)
        {
        	timedecision="Dinner";
        }
        setTitle(dayofweek.format(now)+" - "+timedecision);
    	listView = (ListView) findViewById(R.id.list);
    	new Getmenu(this,adapter,listView).execute(dayofweek.format(now),timedecision.toLowerCase());
    	new Iconidenty(Result.this,menu).execute(result+"-"+date.format(now)+"-"+timedecision.toLowerCase());
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		setContentView(R.layout.activity_acknowledge);
    	TextView ack=(TextView)findViewById(R.id.ack);
		switch (item.getItemId()) 
		{
        case R.id.accept: 
        	new StatusStudents(Result.this).execute("accept-"+result+"-"+date.format(now)+"-"+timedecision.toLowerCase()+"-"+latlon);
        	menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.greenel));
        	ack.setText("Thanks for Accepting, Please come at right time to dinning hall");
        	final Handler handler1 = new Handler();
        	handler1.postDelayed(new Runnable() {
        	    @Override
        	    public void run() {
        	    	Result.this.finishAffinity();
        	    }
        	}, 5000);
        	
        	return true;
        case R.id.notaccept:
        	new StatusStudents(Result.this).execute("notaccept-"+result+"-"+date.format(now)+"-"+timedecision.toLowerCase()+"-"+latlon);
        	menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.redel));
        	ack.setText("Thank You for the status update");
        	final Handler handler2 = new Handler();
        	handler2.postDelayed(new Runnable() {
        	    @Override
        	    public void run() {
        	    	Result.this.finishAffinity();
        	    }
        	}, 5000);
            return true;
        default:
            return super.onOptionsItemSelected(item);
		}
		
	}
	@Override
	public void onBackPressed() {
		
	}
	@Override
	public void onLocationChanged(Location location) 
	{
		latlon="lat="+location.getLatitude()+",lon="+location.getLongitude();
		//Toast.makeText(this, ""+location.getLatitude()+"-"+location.getLongitude(), Toast.LENGTH_LONG).show();
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) 
	{
		Log.d("Latitude","disable");
	}
	@Override
	public void onProviderEnabled(String provider) 
	{
		Log.d("Latitude","enable");
	}
	@Override
	public void onProviderDisabled(String provider) 
	{
		Log.d("Latitude","status");
	}
	
}
