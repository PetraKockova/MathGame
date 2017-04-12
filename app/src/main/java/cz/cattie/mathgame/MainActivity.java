package cz.cattie.mathgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int result, player1Score, player2Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateEquation();
    }

    void generateEquation() {
        Random random = new Random();
        int numberA = random.nextInt(100);
        int numberB = random.nextInt(100);
        int operatorIndex = random.nextInt(4);

        char operator;

        switch (operatorIndex) {
            default:
                operator = '+';
                result = numberA + numberB;
                break;
            case 1:
                operator = '-';
                result = numberA - numberB;
                break;
            case 2:
                operator = '*';
                result = numberA * numberB;
                break;
            case 3:
                operator = '/';
                while (numberB == 0) {
                    numberB = random.nextInt(100);
                }
                result = numberA / numberB;
                break;
        }

        String equationText = numberA + " " + operator + " " + numberB + " = ?";

        TextView equationTop = (TextView) findViewById(R.id.equationTop);
        equationTop.setText(equationText);

        TextView equationBottom = (TextView) findViewById(R.id.equationBottom);
        equationBottom.setText(equationText);

        int operatorWrongResult1 = random.nextInt(2);
        int wrongResult1 = random.nextInt(100);
        if (operatorWrongResult1 == 0) {
            wrongResult1 = result + wrongResult1;
        } else {
            wrongResult1 = result - wrongResult1;
        }

        int wrongResult2 = random.nextInt(100);
        int operatorWrongResult2 = random.nextInt(2);
        if (operatorWrongResult2 == 0) {
            wrongResult2 = result + wrongResult2;
        } else {
            wrongResult2 = result - wrongResult2;

        }

        Button topButton1 = (Button) findViewById(R.id.topButton1);
        Button topButton2 = (Button) findViewById(R.id.topButton2);
        Button topButton3 = (Button) findViewById(R.id.topButton3);
        Button bottomButton1 = (Button) findViewById(R.id.bottomButton1);
        Button bottomButton2 = (Button) findViewById(R.id.bottomButton2);
        Button bottomButton3 = (Button) findViewById(R.id.bottomButton3);

        int resultPosition = random.nextInt(3);
        switch (resultPosition) {
            default:
                topButton1.setText(result + "");
                bottomButton1.setText(result + "");
                topButton2.setText(wrongResult1 + "");
                bottomButton2.setText(wrongResult1 + "");
                topButton3.setText(wrongResult2 + "");
                bottomButton3.setText(wrongResult2 + "");
                break;
            case 1:
                topButton1.setText(wrongResult1 + "");
                bottomButton1.setText(wrongResult1 + "");
                topButton2.setText(result + "");
                bottomButton2.setText(result + "");
                topButton3.setText(wrongResult2 + "");
                bottomButton3.setText(wrongResult2 + "");
                break;
            case 2:
                topButton1.setText(wrongResult1 + "");
                bottomButton1.setText(wrongResult1 + "");
                topButton2.setText(wrongResult2 + "");
                bottomButton2.setText(wrongResult2 + "");
                topButton3.setText(result + "");
                bottomButton3.setText(result + "");
                break;
        }
    }

    void updatePlayer1Score() {

        TextView score = (TextView) findViewById(R.id.score1);
        if(player1Score == 10) {
            score.setText(R.string.won);
            TextView score2 = (TextView) findViewById(R.id.score2);
            score2.setText(R.string.lose);
            showHideButtons(true);
        } else {
            score.setText(player1Score + "");
        }
    }

    void updatePlayer2Score() {
        TextView score = (TextView) findViewById(R.id.score2);
        if(player2Score == 10){
            score.setText(R.string.won);
            TextView score1 = (TextView) findViewById(R.id.score1);
            score1.setText(R.string.lose);
            showHideButtons(true);
        } else {
            score.setText(player2Score + "");
        }
    }

    void showHideButtons(boolean hide){
        Button topButton1 = (Button) findViewById(R.id.topButton1);
        Button topButton2 = (Button) findViewById(R.id.topButton2);
        Button topButton3 = (Button) findViewById(R.id.topButton3);
        Button bottomButton1 = (Button) findViewById(R.id.bottomButton1);
        Button bottomButton2 = (Button) findViewById(R.id.bottomButton2);
        Button bottomButton3 = (Button) findViewById(R.id.bottomButton3);

        topButton1.setVisibility(hide ? View.INVISIBLE : View.VISIBLE);
        topButton2.setVisibility(hide ? View.INVISIBLE : View.VISIBLE);
        topButton3.setVisibility(hide ? View.INVISIBLE : View.VISIBLE);
        bottomButton1.setVisibility(hide ? View.INVISIBLE : View.VISIBLE);
        bottomButton2.setVisibility(hide ? View.INVISIBLE : View.VISIBLE);
        bottomButton3.setVisibility(hide ? View.INVISIBLE : View.VISIBLE);

    }

    public void answerPlayer1Click(View view) {
        Button button = (Button) view;
        int buttonNumber = Integer.parseInt(button.getText().toString());
        if (buttonNumber == result)
            player1Score++;
        else if(player1Score > 0)
            player1Score--;

        updatePlayer1Score();
        generateEquation();
    }

    public void answerPlayer2Click(View view) {
        Button button = (Button) view;
        int buttonNumber = Integer.parseInt(button.getText().toString());
        if (buttonNumber == result)
            player2Score++;
        else if(player2Score > 0)
            player2Score--;

        updatePlayer2Score();
        generateEquation();
    }

    public void resetClick (View view) {
        player1Score=0;
        player2Score=0;

        showHideButtons(false);
        updatePlayer1Score();
        updatePlayer2Score();
        generateEquation();
    }
}
