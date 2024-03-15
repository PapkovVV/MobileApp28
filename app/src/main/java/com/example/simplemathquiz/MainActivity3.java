package com.example.simplemathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity {

    TextView finalscore;
    Button playagain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        finalscore = findViewById(R.id.finalscore);
        playagain = findViewById(R.id.playagain);

        Intent it = getIntent();
        String points = it.getStringExtra("points");
        String questions = it.getStringExtra("questions");

        finalscore.setText(points + "/" + questions);

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(it);
            }
        });
        TextView randomTextView = findViewById(R.id.randomTextView);
        randomTextView.setText(getRandomPhrase());
    }
    private String getRandomPhrase() {
        ArrayList<String> phrasesList = new ArrayList<>();
        try {
            // Открываем файл и читаем строки
            InputStream inputStream = getResources().openRawResource(R.raw.random_phrases);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                phrasesList.add(line);
            }
            reader.close();
            // Выбираем случайную строку из списка
            Random random = new Random();
            int randomIndex = random.nextInt(phrasesList.size());
            return phrasesList.get(randomIndex);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to read phrases";
        }
    }

}