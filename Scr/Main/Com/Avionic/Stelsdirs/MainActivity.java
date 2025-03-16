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
	String o_dir;
	File dir;
	String[] files;
	String[] files2;
	String NSDir;
	File ndir;
	// имена атрибутов для Map 
	final String ATTRIBUTE_NAME_TEXT = "text"; 
	final String ATTRIBUTE_NAME_IMAGE = "image"; 
	ListView lvSimple;
    @Override
    protected void onCreate(Bundle savedInstanceState){
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Bundle arguments = getIntent().getExtras();
		if(arguments!=null){
			o_dir= arguments.getString("dir");
			NSDir= arguments.getString("file");}
			else{o_dir= Environment.getExternalStorageDirectory().getPath().toString();
			NSDir= null;}
		dir= new File(o_dir+ NSDir)
		files= dir.list();
		TextView dir_tv = findViewById(R.id.nfold);
		Button but_up = findViewById(R.id.up);
		dir_tv.setText(o_dir);
		int file_img=R.drawable.file;
		int fold_img= R.drawable.folder;
		int img;
		
		but_up.setBackgroundResource(R.drawable.updir);
		
        if (files != null)
		{ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>( 
			files.length); 
			Map<String, Object> m; 
			for (int i = 0; i < files.length; i++) { 
			m = new HashMap<String, Object>(); 
			m.put(ATTRIBUTE_NAME_TEXT, files[i]);
			ndir= new File(o_dir + "/" + files[i]);
			if (ndir.isDirectory()){
				img= fold_img;}
				else{img=file_img;}
			m.put(ATTRIBUTE_NAME_IMAGE, img); 
			data.add(m);}
		
		// массив имен атрибутов, из которых будут читаться данные 
		String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE }; 
		// массив ID View-компонентов, в которые будут вставлять данные 
		int[] to = { R.id.namef, R.id.ico }; 
		// создаем адаптер 
		SimpleAdapter sAdapter = new SimpleAdapter(this, data,
									R.layout.itemdir, from, to); 
		// определяем список и присваиваем ему адаптер 
		lvSimple = findViewById(R.id.lvSimple); 
		lvSimple.setAdapter(sAdapter);}
		lvSimple.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View v, int position, long id){
					// по позиции получаем выбранный элемент
					NSDir = files[position];
					newDir();}
		});
	}
				
public void newDir(){
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("dir", dir.toString());
		intent.putExtra("file", NSDir);
		startActivity(intent); }
	
}
	
