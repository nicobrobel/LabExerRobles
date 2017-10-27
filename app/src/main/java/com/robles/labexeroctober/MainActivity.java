package com.robles.labexeroctober;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText user;
    EditText pass;
    Button sshare;
    Button sint;
    Button next;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText) findViewById(R.id.etusername);
        pass = (EditText) findViewById(R.id.etpassword);
        sshare = (Button) findViewById(R.id.saveShared);
        sint = (Button) findViewById(R.id.saveInternal);
        next = (Button) findViewById(R.id.nextBtn);
    }
    public void savepref (View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", user.getText().toString());
        editor.putString("password", pass.getText().toString());
        editor.commit();
        Toast.makeText(this, "Your Data has been SAVED! >> tap next to display", Toast.LENGTH_SHORT).show();

    }
    public void savestore (View view) {
        String username = user.getText().toString();
        String space = ("\r\n");
        String password = pass.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(username.getBytes());
            fos.write(space.getBytes());
            fos.write(password.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Your Data has been SAVED! >> tap next to display", Toast.LENGTH_SHORT).show();
    }

    public void nextact (View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
