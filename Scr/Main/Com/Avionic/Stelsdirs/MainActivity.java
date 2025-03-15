import android.app.*;
import android.os.*;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;

import java.io.File;
import java.nio.file.*;

public class MainActivity extends Activity 
{
	File dir = new File(Environment.getExternalStorageDirectory().getPath());
	String[] files = dir.list();
	String[] files2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		// получаем элемент ListView
        ListView fileList = findViewById(R.id.dirs);
		
		
		//File ren_dir = new File("/storage/emulated/0/test_file");
		//File new_dir = new File("/storage/emulated/0/.test_file");
		//ren_dir.renameTo(new_dir);
		

		// если доступ к содержимому папки запрещён - она вернёт null
		// обязательно проверяем! 
        if (files != null)
        {ArrayAdapter<String> adapter = new ArrayAdapter(this,
			android.R.layout.simple_list_item_1, files);
			
			fileList.setAdapter(adapter);}
        // устанавливаем для списка адаптер
		// добавляем для списка слушатель
        fileList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View v, int position, long id)
				{
					// по позиции получаем выбранный элемент
					String selectedDir = files[position];
					File new_dir= new File(selectedDir);
					String[] files2=new_dir.list();
				}
		});
    }
public void newDir(){
	if (files2 != null)
	{ArrayAdapter<String> adapter2 = new ArrayAdapter(this,
	 android.R.layout.simple_list_item_1, files2);
	fileList()
}
	
}
