package com.shreyansh.braintrain;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfquestions = 0;
    Button op1;
    Button op2;
    Button op3;;
    Button op4, playAgainButton;
    TextView timerText;
    TextView questionText;
    TextView resultText, scoreText;
    ArrayList<Integer> answers = new ArrayList<>(4);


    public void playAgainGame(View view) {
        op1.setEnabled(true);
        op2.setEnabled(true);
        op3.setEnabled(true);
        op4.setEnabled(true);
        resultText.setText("Tap tp play!");
        score = 0;
        numberOfquestions = 0;
        scoreText.setText("0/0");
        timerText.setText("30sec");
        playAgainButton.setVisibility(View.INVISIBLE);
        reset();
        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long l) {
                timerText.setText(String.valueOf(l/1000)+ "sec");
            }

            @Override
            public void onFinish() {
                resultText.setText("Score : " + Integer.toString(score));
                playAgainButton.setVisibility(View.VISIBLE);
                op1.setEnabled(false);
                op2.setEnabled(false);
                op3.setEnabled(false);
                op4.setEnabled(false);
            }
        }.start();

    }
    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.VISIBLE);
        op1.setVisibility(View.VISIBLE);
        op2.setVisibility(View.VISIBLE);
        op3.setVisibility(View.VISIBLE);
        op4.setVisibility(View.VISIBLE);
        scoreText.setVisibility(View.VISIBLE);
        questionText.setVisibility(View.VISIBLE);
        resultText.setVisibility(View.VISIBLE);
        timerText.setVisibility(View.VISIBLE);
        playAgainGame(findViewById(R.id.timerText));

    }

    @SuppressLint("SetTextI18n")
    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultText.setText("Hurray! , It's Correct");
            score++;
        }
        else {
            resultText.setText("Oops, wrong answer.");
        }
        numberOfquestions++;
        scoreText.setText(Integer.toString(score) +"/"+Integer.toString(numberOfquestions));
        reset();
    }

    @SuppressLint("SetTextI18n")
    public void reset() {
        Random rand = new Random();
        int a = rand.nextInt(26);
        int b = rand.nextInt(26);

        String ques =  Integer.toString(a) + " + " + Integer.toString(b) + " = ?";
        questionText.setText(ques);
        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i=0; i<4; i++) {
            if( i == locationOfCorrectAnswer)
                answers.add(a+b);
            else {
                int wronganswer = rand.nextInt(10) + Math.max(a,b);
                while ((wronganswer  == (a+b))||(answers.contains(wronganswer)))
                    wronganswer = rand.nextInt(10) + Math.min(a,b);
                answers.add(wronganswer);
            }
        }

        op1.setText(Integer.toString(answers.get(0)));
        op2.setText(Integer.toString(answers.get(1)));
        op3.setText(Integer.toString(answers.get(2)));
        op4.setText(Integer.toString(answers.get(3)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
        playAgainButton = findViewById(R.id.playagainbutton);
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        op3 = findViewById(R.id.op3);
        op4 = findViewById(R.id.op4);
        scoreText = findViewById(R.id.scoreText);
        questionText = findViewById(R.id.questionText);
        resultText = findViewById(R.id.resultText);
        timerText = findViewById(R.id.timerText);
        startButton.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        op1.setVisibility(View.INVISIBLE);
        op2.setVisibility(View.INVISIBLE);
        op3.setVisibility(View.INVISIBLE);
        op4.setVisibility(View.INVISIBLE);
        scoreText.setVisibility(View.INVISIBLE);
        questionText.setVisibility(View.INVISIBLE);
        resultText.setVisibility(View.INVISIBLE);
        timerText.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.VISIBLE);

    }
}