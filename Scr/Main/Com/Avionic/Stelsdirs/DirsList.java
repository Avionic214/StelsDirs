package com.avionic.fileTest;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.AdapterView;
import android.content.Intent;
import android.widget.ListView; 
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.util.SparseBooleanArray;
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.Map; 
import java.io.IOException;
import java.io.File;
import java.nio.file.*;

import android.graphics.drawable.*;

public class DirsList extends Activity
{
	Button del_but;
	ListView dir_list;
	String [] dirs;
	private FileManager fm;
	private String l_dir;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {	
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		del_but= findViewById(R.id.del);
		dir_list= findViewById(R.id.list_d);
		fm= new FileManager(this);

		try{
			l_dir= fm.readFromFile("dirs.txt");
		}catch (IOException e){
			e.printStackTrace();}
		dirs=l_dir.split("&");
	
		ArrayAdapter<String> adapter = new ArrayAdapter(this,
														android.R.layout.simple_list_item_multiple_choice, dirs);
        // устанавливаем для списка адаптер
        dir_list.setAdapter(adapter);
        // добавляем для списка слушатель
        dir_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View v, int position, long id)
				{
					SparseBooleanArray selected=dir_list.getCheckedItemPositions();

					}
					
	});
	}
	
	public void OpList(){
		fm= new FileManager(this);

		try{
			l_dir= fm.readFromFile("dirs.txt");
		}catch (IOException e){
			e.printStackTrace();}
		dirs=l_dir.split("&");
		ArrayAdapter<String> adapter = new ArrayAdapter(this,
														android.R.layout.simple_list_item_1, dirs);

		dir_list.setAdapter(adapter);

	}
}
