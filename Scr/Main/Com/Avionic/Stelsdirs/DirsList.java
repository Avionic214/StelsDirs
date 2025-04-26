package com.avionic.fileTest;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.*;
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
import java.util.Arrays;
import android.widget.TextView;

public class DirsList extends Activity
{
	TextView tv;
	Button Sbut, Cbut;
	ListView dir_list;
	String[] dir_it;
	String selectedItems, savItems;
	String dirs= "";
	private FileManager fm;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {	
		super.onCreate(savedInstanceState);
        setContentView(R.layout.dir);
		Sbut= findViewById(R.id.Sbut);
		Cbut= findViewById(R.id.Cbut);
		tv= findViewById(R.id.Tv);
		dir_list= findViewById(R.id.dir);
		
		fm= new FileManager(this);
		try{
			dirs= fm.readFromFile("dirs.txt");
		}catch (IOException e){
			e.printStackTrace();}
			if(dirs!= "")
			{dir_it= dirs.split("&");
			tv.setText("список дирректорий");
			cDirList();}
		else{tv.setText("список дирректорий пуст");
		Sbut.setText("ok");
			selectedItems=null;
			savItems="";}
			
      }
	  public void cDirList(){
		  ArrayAdapter<String> adapter = new ArrayAdapter(this,
														  android.R.layout.simple_list_item_multiple_choice, dir_it);
		  // устанавливаем для списка адаптер
		  dir_list.setAdapter(adapter);
		  // добавляем для списка слушатель
		  dir_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				  @Override
				  public void onItemClick(AdapterView<?> parent, View v, int position, long id)
				  {
					  SparseBooleanArray selected=dir_list.getCheckedItemPositions();

					  selectedItems=null;
					  savItems="";
					  for(int i=0;i < dir_it.length;i++)
					  {
						  if(selected.get(i))
						  {if (selectedItems!= null)
								  {selectedItems+=dir_it[i]+",";}
								  else {selectedItems=dir_it[i]+ ",";}}
							  else{savItems+= dir_it[i] + "&";}
						
					  }
					  if (savItems!= "")
					  {savItems= savItems.substring(0,savItems.length()-1);}
					  if (selectedItems!=null)
					  {Sbut.setText("del");}
					  else{Sbut.setText("ok");}
				  }
			  });
	  }
	public void delDir(View view){
		if (selectedItems != null){
			if(savItems!=""){
				dir_it= savItems.split("&");
				cDirList();
				}
				else{
					tv.setText("список дирректорий пуст");
					dir_list.setVisibility(View.INVISIBLE);
					Sbut.setText("ok");}
			Sbut.setText("ok");
			fm= new FileManager(this);
			try{
				fm.writeToFile("dirs.txt",savItems);}
			catch (IOException e) {
				e.printStackTrace();}
				selectedItems=null;
		}
		else{Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);}
	}
	public void cancel(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		}
}
