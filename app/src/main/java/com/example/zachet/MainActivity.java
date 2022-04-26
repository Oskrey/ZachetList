package com.example.zachet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list = new ArrayList<>();                 //Создание списка
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, //Создание адаптера для списка
                android.R.layout.simple_list_item_1, list );
        ((ListView)findViewById(R.id.list)).setAdapter(adapter); //Установка списка в визуальный компонент

         int[] positionID = new int[1];  //Создание переменной для хранения выбранного элемента
        positionID[0] = -1;             //Установка значения на случай, если элемент не выбран

        findViewById(R.id.buttonAdd).setOnClickListener(view -> { //Создание действия на кнопку добавления
            AlertDialog.Builder bul = new AlertDialog.Builder(MainActivity.this);
            EditText input = new EditText(getApplicationContext());
            bul.setView(input).setMessage("Введите данные").setPositiveButton("Сохранить", (dialog, which) -> {
                if(!input.getText().toString().isEmpty()) {
                    list.add(input.getText().toString());
                    adapter.notifyDataSetChanged();
                }
            }).setNegativeButton("Отмена",(dialog, which) -> {});
            bul.show();
        });
        ((ListView)findViewById(R.id.list)).setOnItemClickListener((parent, view, position, id) ->{
                positionID[0] =  (int) id;});//Получение ID элемента списка при клике на него
        findViewById(R.id.buttonDelete).setOnClickListener(view -> {//Создание действия на кнопку удаления
            if(positionID[0] != -1) //Если не невыбрано
            {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);

                alertBuilder.setMessage("Вы действительно хотите удалить элемент "+list.get(positionID[0])+"?" )
                        .setPositiveButton("OK", (dialogInterface, i) -> {adapter.remove(list.get(positionID[0])); positionID[0] = -1; })
                        .setNegativeButton("Отмена",(dialogInterface, i) -> dialogInterface.cancel() )
                        .setTitle("Внимание").setIcon(R.drawable.error).show();
            }
        });

        findViewById(R.id.buttonEdit).setOnClickListener(view -> {//Создание действия на кнопку редактирования
            if(positionID[0] != -1) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                EditText input = new EditText(getApplicationContext());
                input.setText(list.get(positionID[0]));//ввод имеющегося текста в поле ввода
                builder.setView(input).setMessage("Изменение данных").setPositiveButton("Сохранить", (dialog, which) -> {
                    if (!input.getText().toString().isEmpty()) {
                        list.set(positionID[0], input.getText().toString());
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("Отмена",(dialog, which) -> {});
                builder.show();
            }
        });

    }




}