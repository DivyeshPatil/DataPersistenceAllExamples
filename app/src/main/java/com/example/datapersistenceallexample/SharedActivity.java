package com.example.datapersistenceallexample;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharedActivity extends AppCompatActivity {

    EditText editText_name, editText_email;
    Button button_save;
    SharedPreferences sharedPreferences;

    //Create shared preferences name and key name
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        configureBackButton(); //back button
        editText_name = findViewById(R.id.editext_name);
        editText_email = findViewById(R.id.editext_email);
        button_save = findViewById(R.id.button_save);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        //When open activity first check shared preferences data available or not
        String name = sharedPreferences.getString(KEY_NAME, null);

        if (name != null){
            //if data is available so directly call on HomeActivity
            Intent intent = new Intent(SharedActivity.this,HomeActivity.class);
        }

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //when click a button put data on Shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME,editText_name.getText().toString());
                editor.putString(KEY_EMAIL,editText_email.getText().toString());
                editor.apply();

                Intent intent = new Intent(SharedActivity.this,HomeActivity.class);
                startActivity(intent);

                Toast.makeText(SharedActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // back button
    private void configureBackButton() {
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
