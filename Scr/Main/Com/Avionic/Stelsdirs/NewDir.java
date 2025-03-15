package com.avionic.fileTest;

import android.app.*;
import android.os.*;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.content.Intent;

import java.io.File;
import java.nio.file.*;

public class NewDir extends Activity{
	File dir;
	String pdir, ndir, file,NSDir;
	String[] files;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		ListView fileList= findViewById(R.id.lvSimple);
		Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            pdir = arguments.getString("dir");
            file = arguments.getString("file");}
			ndir= pdir+ "/"+ file;
			dir=new File(ndir);
			files= dir.list();
		if (files != null)
        {ArrayAdapter<String> adapter = new ArrayAdapter(this,
														 android.R.layout.simple_list_item_1, files);

			fileList.setAdapter(adapter);}
		fileList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View v, int position, long id)
				{
					// по позиции получаем выбранный элемент
					NSDir = files[position];
					newDir();
				}
			});
		}
	public void newDir(){
		Intent intent = new Intent(this, NewDir.class);
		intent.putExtra("dir", dir.toString());
		intent.putExtra("file", NSDir);
		startActivity(intent); }
}
