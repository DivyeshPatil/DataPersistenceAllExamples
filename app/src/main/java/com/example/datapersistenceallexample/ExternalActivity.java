package com.example.datapersistenceallexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;

public class ExternalActivity extends AppCompatActivity {

    Button buttonSave, buttonLoad;
    EditText text_input;
    TextView tvLoad;
    String filename = "";
    String filepath = "";
    String fileContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);

        configureBackButton(); //back button
        buttonSave = findViewById(R.id.buttonSave);
        buttonLoad = findViewById(R.id.buttonLoad);
        text_input = findViewById(R.id.text_input);
        tvLoad = findViewById(R.id.tvLoad);
        filename = "myFile.txt";
        filepath = "MyFileDir";
        if (!isExternalStorageAvailableForRW()){
            buttonSave.setEnabled(false);
        }
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvLoad.setText("");
                fileContent = text_input.getText().toString().trim();
                if (!fileContent.equals("")){
                    File myExternalFile = new File(getExternalFilesDir(filepath), filename);
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(myExternalFile);
                        fos.write(fileContent.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    text_input.setText("");
                    Toast.makeText(ExternalActivity.this, "Information saved in external storage", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ExternalActivity.this, "Text field can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileReader fr = null;
                File myExternalFile = new File(getExternalFilesDir(filepath), filename);
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    fr = new FileReader(myExternalFile);
                    BufferedReader br = new BufferedReader(fr);
                    String line = br.readLine();
                    while (line != null){
                        stringBuilder.append(line).append('\n');
                        line = br.readLine();
                    }
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    String fileContents = "File Content\n" + stringBuilder.toString();
                    tvLoad.setText(fileContents);
                }
            }
        });
    }

    private boolean isExternalStorageAvailableForRW() {
        String extStorageState = Environment.getExternalStorageState();
        if (extStorageState.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
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
