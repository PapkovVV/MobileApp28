package com.example.simplemathquiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    TextView timer, score, question, alert;
    Button button1, button2, button3, button4;

    CountDownTimer countDownTimer;

    Random random =new Random();
    int a;
    int b;
    int c;
    String op;
    int indexOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int points = 0;
    int totalQuestions = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        timer = findViewById(R.id.timer);
        score = findViewById(R.id.livescore);
        question = findViewById(R.id.question);
        alert = findViewById(R.id.alert);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        start();
     }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    public void NextQuestion(){
        //a = random.nextInt(10);
        //b = random.nextInt(10);
        c = random.nextInt(4);
        switch (c)
        {
            case 0 : a = random.nextInt(100);
                     b = random.nextInt(100);
                     op = "+";
                     break;
            case 1 : a = random.nextInt(100);
                     b = random.nextInt(100);
                     op = "-";
                     break;
            case 2 : a = random.nextInt(10);
                     b = random.nextInt(10);
                     op = "*";
                     break;
            case 3 : a = random.ints(2,10).findFirst().getAsInt();
                     b = random.ints(2,10).findFirst().getAsInt();
                      while(a % b != 0) {
                          a = random.ints(2,10).findFirst().getAsInt();
                          b = random.ints(2,10).findFirst().getAsInt();
                      }
                      op = "/";
                      break;
        }
        question.setText(a+op+b);

        indexOfCorrectAnswer = random.nextInt(4);
        answers.clear();

        if(op == "+") {
            for (int i = 0; i < 4; i++) {

                if (indexOfCorrectAnswer == i) {
                    answers.add(a + b);
                } else {
                    int wrongAnswer = random.nextInt(200);
                    while (wrongAnswer == a + b) {

                        wrongAnswer = random.nextInt(200);
                    }
                    answers.add(wrongAnswer);
                }
            }
        }
        else if(op == "-") {
            for (int i = 0; i < 4; i++) {

                if (indexOfCorrectAnswer == i) {
                    answers.add(a - b);
                } else {
                    //int wrongAnswer = random.nextInt(100);
                    int wrongAnswer = random.ints(-100,100).findFirst().getAsInt();
                    while (wrongAnswer == a - b) {

                        //wrongAnswer = random.nextInt(100);
                        wrongAnswer = random.ints(-100,100).findFirst().getAsInt();
                    }
                    answers.add(wrongAnswer);
                }
            }
        }
        else if(op == "*") {
            for (int i = 0; i < 4; i++) {

                if (indexOfCorrectAnswer == i) {
                    answers.add(a * b);
                } else {
                    int wrongAnswer = random.nextInt(100);
                    while (wrongAnswer == a * b) {

                        wrongAnswer = random.nextInt(100);
                    }
                    answers.add(wrongAnswer);
                }
            }
        }
        else if(op == "/") {
            for (int i = 0; i < 4; i++) {

                if (indexOfCorrectAnswer == i) {
                    answers.add(a / b);
                } else {
                    int wrongAnswer = random.nextInt(10);
                    while (wrongAnswer == a / b) {

                        wrongAnswer = random.nextInt(10);
                    }
                    answers.add(wrongAnswer);
                }
            }
        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void optionSelect(View view){

        totalQuestions++;
        if(Integer.toString(indexOfCorrectAnswer).equals(view.getTag().toString())){
            points++;
            alert.setText("Correct");

        }else {
            alert.setText("Wrong");
        }

        score.setText(Integer.toString(points)+"/"+Integer.toString(totalQuestions));

        NextQuestion();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void start() {
        NextQuestion();
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timer.setText("Time Up!");
                score.setText(Integer.toString(points)+"/"+Integer.toString(totalQuestions));

                Intent it = new Intent(MainActivity2.this, MainActivity3.class);
                it.putExtra("questions",totalQuestions + "");
                it.putExtra("points", points + "");
                startActivity(it);
            }
        }.start();
    }
}