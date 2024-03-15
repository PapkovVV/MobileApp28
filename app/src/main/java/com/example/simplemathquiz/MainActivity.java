package com.example.simplemathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View view)
    {
        Intent it = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(it);
    }

    public void study(View view)
    {
        Intent it = new Intent(MainActivity.this, MainActivity4.class);
        startActivity(it);
    }

}