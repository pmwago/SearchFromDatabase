package com.example.searchfromdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {
private ListView listView;
private Button insert,retrieve;
private EditText regNo,fullName;
private SearchView search;
StudentModel student;
DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView=findViewById(R.id.list);
        insert=findViewById(R.id.insert);
        retrieve=findViewById(R.id.retrieve);
        regNo=findViewById(R.id.regNo);
        fullName=findViewById(R.id.name);
        search=findViewById(R.id.search);


        insert.setOnClickListener(view -> {
            try {
                 dbHelper = new DatabaseHelper(MainActivity.this);

                boolean results=dbHelper.insertStudent(student=new StudentModel(regNo.getText().toString(),fullName.getText().toString()));

                if (results==true) {
                    Toast.makeText(MainActivity.this,"Success "+student.toString(), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "registration failed!!", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(MainActivity.this, "error creating student!!!", Toast.LENGTH_SHORT).show();
                student=new StudentModel("","");
            }
        });


        retrieve.setOnClickListener(view -> {
            dbHelper = new DatabaseHelper(MainActivity.this);
            String searchQuery=search.getQuery().toString();
            Cursor cursor=dbHelper.retrieveRecord(searchQuery);
            if(cursor.getCount()==0){
                Toast.makeText(MainActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                return;
            }
            else {

                StringBuffer buffer = new StringBuffer();
              while (cursor.moveToNext()) {
                    buffer.append("Registration number : " + cursor.getString(0) + "\n");
                    buffer.append("FullName            : " + cursor.getString(1) + "\n");
                }
                ArrayAdapter<String>adapter=new ArrayAdapter<String>(MainActivity.this,R.layout.records,R.id.txtView, Collections.singletonList(buffer.toString()));
                listView.setAdapter(adapter);
            }
        });

    }
}