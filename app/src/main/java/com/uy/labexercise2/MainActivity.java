package com.uy.labexercise2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText et_user, et_pass;
    Button btn_sharedPref, btn_internalStorage, btn_next;
    SharedPreferences preferences;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_user = (EditText) findViewById(R.id.et_user);
        et_pass = (EditText) findViewById(R.id.et_pass);
        btn_internalStorage = (Button) findViewById(R.id.btn_internalStorage);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_sharedPref = (Button) findViewById(R.id.btn_sharedPreferenes);
        preferences = getPreferences(Context.MODE_PRIVATE);
    }

    public void btnclick_sharedPreferences(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", et_user.getText().toString());
        editor.putString("password", et_pass.getText().toString());
        editor.commit();
        Toast.makeText(this, "Data is saved", Toast.LENGTH_SHORT).show();
    }

    public void btnclick_internalStorage(View view) {
        String username = et_user.getText().toString();
        String password = et_pass.getText().toString();
        String blank = ("\r\n");

        try {
            fos = openFileOutput("record.txt", Context.MODE_PRIVATE);
            fos.write(username.getBytes());
            fos.write(blank.getBytes());
            fos.write(password.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Saved in Internal", Toast.LENGTH_SHORT).show();
    }

    public void next(View view) {
        Intent myintent = new Intent(this, Main2Activity.class);
        startActivity(myintent);
    }
}
