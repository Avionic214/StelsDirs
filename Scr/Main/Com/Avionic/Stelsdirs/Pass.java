package com.avionic.fileTest;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import android.view.View.OnClickListener;
import java.io.IOException;
import android.widget.Toast;
import android.content.Intent;

public class Pass extends Activity
implements OnClickListener{
	private FileManager fm;
	private String Password="";
	
	private String PassTxt="";
	String Vis;
	TextView passTv;

	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass);
		passTv= findViewById(R.id.passTV);
		final Button but1= findViewById(R.id.but_1);
		final Button but2= findViewById(R.id.but_2);
		final Button but3= findViewById(R.id.but_3);
		final Button but4= findViewById(R.id.but_4);
		final Button but5= findViewById(R.id.but_5);
		final Button but6= findViewById(R.id.but_6);
		final Button but7= findViewById(R.id.but_7);
		final Button but8= findViewById(R.id.but_8);
		final Button but9= findViewById(R.id.but_9);
		final Button but0= findViewById(R.id.but_0);
		final Button but_clr= findViewById(R.id.but_clr);
		final Button but_ok= findViewById(R.id.but_ok);
		
		but1.setOnClickListener(this);
		but2.setOnClickListener(this);
		but3.setOnClickListener(this);
		but4.setOnClickListener(this);
		but5.setOnClickListener(this);
		but6.setOnClickListener(this);
		but7.setOnClickListener(this);
		but8.setOnClickListener(this);
		but9.setOnClickListener(this);
		but0.setOnClickListener(this);
		but_clr.setOnClickListener(this);
		but_ok.setOnClickListener(this);
		
		fm= new FileManager(this);
		try{
			PassTxt= fm.readFromFile("password.txt");
		}catch (IOException e){
			e.printStackTrace();}
		if (PassTxt==""){
			passTv.setText("установите пароль");}
			else{passTv.setText("введите пароль");}
	}
	@Override 
	public void onClick(View v) { 
		
		switch (v.getId()) { 
			case R.id.but_1: 
				Password+="1";
				passTv.setText(Password);
				break;
			case R.id.but_2:
				Password+="2";
				passTv.setText(Password);
				break;
			case R.id.but_3: 
				Password+="3";
				passTv.setText(Password);
				break;
			case R.id.but_4: 
				Password+="4";
				passTv.setText(Password);
				break;
			case R.id.but_5: 
				Password+="5";
				passTv.setText(Password);
				break;
			case R.id.but_6: 
				Password+="6";
				passTv.setText(Password);
				break;
			case R.id.but_7: 
				Password+="7";
				passTv.setText(Password);
				break;
			case R.id.but_8: 
				Password+="8";
				passTv.setText(Password);
				break;
			case R.id.but_9: 
				Password+="9";
				passTv.setText(Password);
				break;
			case R.id.but_0: 
				Password+="0";
				passTv.setText(Password);
				break;
			case R.id.but_clr: 
				Password=Password.substring(0,Password.length()-1);
				passTv.setText(Password);
				break;
			case R.id.but_ok:
				if (Password== ""){Toast.makeText(getApplicationContext(), 
												  "установите пароль", Toast.LENGTH_LONG).show();}

				else if(Password.equals(PassTxt))
				{Vis="Visible";
				Intent intent = new Intent();
					intent.setClass( 
						getApplicationContext(), MainActivity.class);
				intent.putExtra("vis", "Visible");
				startActivity(intent);}

				else if(Password!="" && PassTxt==""){
					fm= new FileManager(this);
					try
					{fm.writeToFile("password.txt", Password);}
					catch (IOException e) {e.printStackTrace();}
					Toast.makeText(getApplicationContext(), 
								   "пароль установлен", Toast.LENGTH_LONG).show();
					Password= "";}

				else if(PassTxt!="" && Password.equals(PassTxt)==false){
					Toast.makeText(getApplicationContext(), 
								   "неверный пароль"+ PassTxt+"|"+ Password, Toast.LENGTH_LONG).show();}
			}
		}
}

	

