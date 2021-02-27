package com.example.datapersistenceallexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InternalActivity extends AppCompatActivity {

    private static final String FILE_NAME = "example.txt"; // Internal Storage File Name example.txt

    EditText mEditText;
    // EditText mEditEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        configureBackButton(); //back button
        mEditText = findViewById(R.id.edit_text);
        // mEditEmail = findViewById(R.id.edit_email);
    }


    public void Register(View view) {
        String text = mEditText.getText().toString();
        // String text1 = mEditEmail.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            // fos.write(text1.getBytes());
            mEditText.getText().clear();
            // mEditEmail.getText().clear();
            Toast.makeText(this,"Saved to " +getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Load(View view) {
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            //String text1;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            mEditText.setText(sb.toString());
            /*while ((text1 = br.readLine()) != null) {
                sb.append(text1).append("\n");
            }
            mEditEmail.setText(sb.toString());*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //back button
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
