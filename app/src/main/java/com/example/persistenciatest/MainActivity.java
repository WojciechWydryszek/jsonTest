package com.example.persistenciatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText txtName, txtPassword;
    private TextView txtView;
    private ArrayList<JSONObject> jsonList = new ArrayList<>();
    private String path = "properties.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = new File(path);

        try {
            if(!file.exists())
                file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        loadForms();

        String content = readFromFile(path);

        txtView.setText(content);
    }

    private void loadForms() {
        txtName = findViewById(R.id.txtName);
        txtPassword = findViewById(R.id.txtPassword);
        txtView = findViewById(R.id.txtView);
    }

    public void btnAccept(View view) throws JSONException {

        JSONObject json = new JSONObject();

        String context = txtName.getText().toString();
        String context2 = txtPassword.getText().toString();

        json.put("Name", context);
        json.put("Password", context2);

        jsonList.add(json);

        writeToFile(path, json.toString());
    }

    public void btnRead(View view) {



        String content = readFromFile(path);

        txtView.setText(content);
    }

    private void writeToFile(String fileName, String context) {
        File path = getApplicationContext().getFilesDir();
        try {
            FileOutputStream write = new FileOutputStream(new File(path, fileName));
            write.write(context.getBytes());
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFromFile(String fileName) {
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, fileName);
        byte[] content = new byte[(int) readFrom.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(readFrom);
            fileInputStream.read(content);
            return new String(content);
        } catch (Exception e){
            return e.toString();
        }
    }
}