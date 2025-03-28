package com.avionic.fileTest;


import android.app.Activity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    String[] countries = { "Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // получаем элемент TextView
        TextView selection = findViewById(R.id.selection);
        // получаем элемент ListView
        ListView countriesList = findViewById(R.id.del);
        // создаем адаптер
        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
														android.R.layout.simple_list_item_multiple_choice, countries);
        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter);
        // добавляем для списка слушатель
        countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View v, int position, long id)
				{
					SparseBooleanArray selected=countriesList.getCheckedItemPositions();

					
					// установка текста элемента TextView
					selection.setText("Выбрано: " + selectedItems);
				}
			});
    }
}
