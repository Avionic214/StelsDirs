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
import android.widget.Toast;

import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.Map; 
import java.io.IOException;
import java.io.File;
import java.nio.file.*;
import android.widget.*;
import android.graphics.drawable.*;

public class MainActivity extends Activity 
{
	Button but_up, but_dir_l, but_hid, but_shoose;
	TextView dir_tv;
	ListView lvSimple;
	String o_dir, l_dir;
	File dir;
	String[] files, L_dirs;
	String NSDir;
	private String Vis ="INVISIBLE";
	private FileShooser fs;
	private FileManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Bundle extras = getIntent().getExtras();
		if (extras != null){
		Vis = extras.getString("vis");}
		fs= new FileShooser(this);
		but_up = findViewById(R.id.up);
		dir_tv = findViewById(R.id.nfold);
		but_dir_l= findViewById(R.id.listD);
		but_hid= findViewById(R.id.hid);
		but_shoose=findViewById(R.id.shoose);
		o_dir= Environment.getExternalStorageDirectory().getPath();
		but_up.setVisibility(View.INVISIBLE);
		if (Vis.equals("Visible")){
		but_hid.setVisibility(View.VISIBLE);
		but_shoose.setVisibility(View.VISIBLE);}
		dir= new File(o_dir);

		dir_tv.setText(Vis);
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
		if (o_dir.equals(root)){
			but_up.setVisibility(View.INVISIBLE);}
		dir= new File(o_dir);
		files= dir.list();
		if (files != null)
		{lvSimple.setAdapter(fs.sAdapter(o_dir, files));}
		dir_tv.setText(o_dir);}

	public void CheckDir(View view){
		fm= new FileManager(this);
		try{
			l_dir= fm.readFromFile("dirs.txt");
		}catch (IOException e){
			e.printStackTrace();}
		try{
			if (l_dir!=""){
				fm.writeToFile("dirs.txt",l_dir+ "&"+ o_dir);}
				else{fm.writeToFile("dirs.txt", o_dir);}
			
			Toast.makeText(getApplicationContext(), 
						   "Дирректория сохранена", Toast.LENGTH_LONG).show();}
		catch (IOException e) {
			e.printStackTrace();}
	}

	public void OpList(View view){
		Intent intent = new Intent(this, DirsList.class);
		startActivity(intent);

	}
	public void Pass(View view){
		Intent intent = new Intent(this, Pass.class);
		startActivity(intent);}

	public void RenToHid(View view){
		String DirList="";
		fm= new FileManager(this);
		try{
			DirList= fm.readFromFile("dirs.txt");
		}catch (IOException e){
			e.printStackTrace();}
		String[] dl= DirList.split("&");
		if(DirList.equals("")){Toast.makeText(getApplicationContext(), 
				"список дирректорий пуст", Toast.LENGTH_LONG).show();}
		else{
			for(int i=0; i<dl.length; i++){
				HidDir(dl[i]);}
			}
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		Toast.makeText(getApplicationContext(), 
					   "дирректории скрыты", Toast.LENGTH_LONG).show();}
		
	public void RenToSh(View view){
		String hiDirList= "";
		fm= new FileManager(this);
		try{
			hiDirList= fm.readFromFile("dirs_hid.txt");
		}catch (IOException e){
			e.printStackTrace();}
		String[] dl= hiDirList.split("&");
		if(hiDirList.equals("")){Toast.makeText(getApplicationContext(), 
											  "список дирректорий пуст", Toast.LENGTH_LONG).show();}
		else{
			for(int i=0; i<dl.length; i++){
				ShDir(dl[i]);}
		}
		fm= new FileManager(this);
		fm.deleteFile("dirs_hid.txt");
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		Toast.makeText(getApplicationContext(), 
					   "дирректории показаны", Toast.LENGTH_LONG).show();}
	
	public void HidDir(String ShDir){
		String newd="";
		String[] old= ShDir.split("/");
		for(int k=0; k< old.length; k++){
			if (k<old.length-1)
			{newd+= old[k]+ "/";}
			else{newd+= "."+ old[k];}}
		File RoldFile= new File(ShDir);
		File RnewFile = new File(newd);
		if (!RnewFile.exists()) {
			RoldFile.renameTo(RnewFile);
			fm= new FileManager(this);
			String hid_dir="";
			try{
				hid_dir= fm.readFromFile("dirs_hid.txt");
			}catch (IOException e){
				e.printStackTrace();}
			try{
				if (hid_dir.equals("")){
					fm.writeToFile("dirs_hid.txt", newd);}
				else{
					fm.writeToFile("dirs_hid.txt",hid_dir+ "&"+ newd);}}
				catch (IOException e) {
					e.printStackTrace();}
				}
			}
			
	public void ShDir(String HiDir){
		String newd="";
		String[] old= HiDir.split("/");
		for(int k=0; k< old.length; k++){
			if (k<old.length-1)
			{newd+= old[k]+ "/";}
			else{newd+= old[k].substring(1,old[k].length());}}
		File RoldFile= new File(HiDir);
		File RnewFile = new File(newd);
		if (!RnewFile.exists()) {
			RoldFile.renameTo(RnewFile);}
		
	}

}
	
