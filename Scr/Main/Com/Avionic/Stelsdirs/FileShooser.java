package com.avionic.fileTest;

import android.app.*;
import android.os.*;
import android.content.Context;
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
public class FileShooser {

    private Context context;

	public FileShooser(Context context){
		this.context= context;
	}
    public SimpleAdapter sAdapter(String o_dir, String[] files){
        int file_img=R.drawable.file;
		int fold_img= R.drawable.folder;
		int img;
		File ndir;
		// имена атрибутов для Map 
		final String ATTRIBUTE_NAME_TEXT = "text"; 
		final String ATTRIBUTE_NAME_IMAGE = "image";
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>( 
			files.length); 
		Map<String, Object> m; 
		for (int i = 0; i < files.length; i++) { 
			m = new HashMap<String, Object>(); 
			m.put(ATTRIBUTE_NAME_TEXT, files[i]);
			ndir= new File(o_dir+ "/"+files[i]);
			if (ndir.isDirectory()){
				img= fold_img;}
			else{img=file_img;}
			m.put(ATTRIBUTE_NAME_IMAGE, img); 
			data.add(m);
		}

		// массив имен атрибутов, из которых будут читаться данные 
		String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE }; 
		// массив ID View-компонентов, в которые будут вставлять данные 
		int[] to = { R.id.namef, R.id.ico }; 
// создаем адаптер 
		SimpleAdapter sAdapter = new SimpleAdapter(context, data,
												   R.layout.itemdir, from, to);
        return sAdapter;

    }



}
