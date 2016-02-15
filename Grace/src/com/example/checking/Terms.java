package com.example.checking;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class Terms extends Activity
{
	Button btn;
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_terms);
		btn=(Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				Intent in=new Intent(getBaseContext(),Result.class);
				startActivity(in);
			}
		});
    }
}