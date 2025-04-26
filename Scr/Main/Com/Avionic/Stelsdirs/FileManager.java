package com.avionic.fileTest;


import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManager {

    private Context context;

    // Конструктор класса
    public FileManager(Context context) {
        this.context = context;
    }

    /**
     * Метод для записи текста в файл
     *
     * @param fileName Имя файла
     * @param data     Данные для записи
     * @throws IOException Если возникла ошибка ввода/вывода
     */
    public void writeToFile(String fileName, String data) throws IOException {
        // Открываем поток для записи данных в файл
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
        } finally {
            if (fos != null) {
                fos.close(); // Закрываем поток после завершения операции
            }
        }
    }

    /**
     * Метод для чтения текста из файла
     *
     * @param fileName Имя файла
     * @return Содержимое файла в виде строки
     * @throws IOException Если возникла ошибка ввода/вывода
     */
    public String readFromFile(String fileName) throws IOException {
        // Открываем поток для чтения данных из файла
        FileInputStream fis = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        try {
            fis = context.openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fis != null) {
                fis.close();
            }
        }

        return content.toString();
    }

    public String pathFile(String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        return file.getAbsolutePath();}
	/* Метод для удаления файла
	*
	* @param fileName Имя файла
	* @return true, если файл успешно удален, иначе false
	*/
    public boolean deleteFile(String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        return file.delete();
    }
}

