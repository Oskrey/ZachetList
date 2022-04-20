package com.example.zachet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        ArrayList<String> list = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list );

        ((ListView)findViewById(R.id.list)).setAdapter(adapter);

        int[] positionID = new int[1];
        positionID[0] = -1;

        ((Button)findViewById(R.id.buttonAdd)).setOnClickListener(view -> {
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

        ((ListView)findViewById(R.id.list)).setOnItemClickListener((parent, view, position, id) -> positionID[0] = (int) id);

        ((Button)findViewById(R.id.buttonDelete)).setOnClickListener(view -> {
            if(positionID[0] != -1)
            adapter.remove(list.get(positionID[0]));
            positionID[0] = -1;
        });

        ((Button)findViewById(R.id.buttonEdit)).setOnClickListener(view -> {
            if(positionID[0] != -1) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                EditText input = new EditText(getApplicationContext());
                input.setText(list.get(positionID[0]));
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