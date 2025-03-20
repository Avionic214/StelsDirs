package com.avionic.fileTest;

import android.app.*;
import android.os.*;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.widget.AdapterView;
import android.content.Intent;
import android.widget.ListView; 
import android.widget.SimpleAdapter;

import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.Map; 

import java.io.File;
import java.nio.file.*;
import android.widget.*;
import android.graphics.drawable.*;

public class MainActivity extends Activity 
{
	Button but_up;
	TextView dir_tv;
	ListView lvSimple;
	String o_dir;
	File dir;
	String[] files;
	String NSDir;
	private FileShooser fs;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		fs= new FileShooser(this);
		but_up = findViewById(R.id.up);
		dir_tv = findViewById(R.id.nfold);
		o_dir= Environment.getExternalStorageDirectory().getPath();
		but_up.setVisibility(View.INVISIBLE);
		dir= new File(o_dir);
		
		dir_tv.setText(o_dir);
		files = dir.list();
		
        if (files != null)
		{lvSimple = findViewById(R.id.lvSimple); 
		lvSimple.setAdapter(fs.sAdapter(o_dir, files));}
		
		lvSimple.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View v, int position, long id)
				{
					// по позиции получаем выбранный элемент
					NSDir = files[position];
					dir= new File(o_dir+ "/"+ NSDir);
					newDir();
				}
			});
		}
				
public void newDir(){
		files=dir.list();
		o_dir=dir.getAbsolutePath();
	if (files != null)
	{lvSimple.setAdapter(fs.sAdapter(o_dir, files));}
	but_up.setVisibility(View.VISIBLE);
	dir_tv.setText(o_dir);}
	
	public void up(View view){
		files= o_dir.split("/");
		o_dir=files[0];
		for(int i=1; i<files.length-1;i++){
			o_dir=o_dir+"/"+ files[i];}
		String root= Environment.getExternalStorageDirectory().getPath();
		if (o_dir== root){
			but_up.setVisibility(View.INVISIBLE);}
			dir= new File(o_dir);
			files= dir.list();
		if (files != null)
		{lvSimple.setAdapter(fs.sAdapter(o_dir, files));}
		dir_tv.setText(o_dir);
		}
	
}
	
