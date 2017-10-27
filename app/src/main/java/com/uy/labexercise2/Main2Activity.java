package com.uy.labexercise2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
    
        Button loadShared, loadInternal, clear, back;
        TextView tvDisplay;
        SharedPreferences preferences;
        FileInputStream fis;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);

            loadShared = (Button) findViewById(R.id.btn_sharedPreferenes);
            loadInternal = (Button) findViewById(R.id.btn_internalStorage);
            clear = (Button) findViewById(R.id.btn_clear);
            back = (Button) findViewById(R.id.btn_back);
            tvDisplay = (TextView) findViewById(R.id.tv_display);

        }

        public void btnclick_sharedPreferencesLoad (View view) {
            String user = preferences.getString("username", "");
            String pwd = preferences.getString("password", "");
            tvDisplay.setText("The password of " + user + " is " + pwd);
        }



        public void btnclick_internalStorageLoad (View view) {
            StringBuffer buffer = new StringBuffer();
            int read = 0;
            try {
                fis = openFileInput("record.txt");
                while ((read = fis.read()) != -1) {
                    buffer.append((char)read);
                } fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tvDisplay.setText(buffer.toString());
        }

        public void btn_back (View view) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        public void btn_clear (View view) {
            tvDisplay.setText("");
        }
    }