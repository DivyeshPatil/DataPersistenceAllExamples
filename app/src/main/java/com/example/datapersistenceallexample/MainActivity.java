package com.example.datapersistenceallexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button_intsto; //button for internal activity
    private Button button_extsto; //button for external activity
    private Button button_sharepref;//button for shared preferences
    private Button button_sqllite;//button for sqllite

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for internal storage
        button_intsto = (Button) findViewById(R.id.button_intsto);
        button_intsto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInternalActivity();
            }
        });

        //for external storage
        button_extsto = (Button) findViewById(R.id.button_extsto);
        button_extsto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExternalActivity();
            }
        });

        //for shared preferences
        button_sharepref = (Button) findViewById(R.id.button_sharepref);
        button_sharepref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSharedActivity();
            }
        });

        //for sqllite
        button_sqllite = (Button) findViewById(R.id.button_sqllite);
        button_sqllite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSqlLiteActivity();
            }
        });
    }

    //Internal activity
    public void openInternalActivity() {
        Intent intent = new Intent(this, InternalActivity.class);
        startActivity(intent);
    }
    //External activity
    public void openExternalActivity() {
        Intent intent = new Intent(this, ExternalActivity.class);
        startActivity(intent);
    }

    // shared preferences
    public void openSharedActivity() {
        Intent intent = new Intent(this, SharedActivity.class);
        startActivity(intent);
    }

    // sqllite
    public void openSqlLiteActivity() {
        Intent intent = new Intent(this, SqlActivity.class);
        startActivity(intent);
    }
}
