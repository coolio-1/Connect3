package com.example.android.knex;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // true = yellow, false = red
    int activePlayer=0;
    boolean gameActive=true;
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int winningPositions[][] = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8},
            {2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public  void dropIn(View view)

    {
        System.out.println(activePlayer);

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameActive == true) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            else
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
        }
        TextView resultWindow = (TextView) findViewById(R.id.resultWindow);
        LinearLayout layout = (LinearLayout) findViewById(R.id.displayResult);

        for(int winningPosition[] : winningPositions)
        {
            if(gameState[winningPosition[0]] != 2 &&
                    gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[0]] == gameState[winningPosition[2]])

            {   if(activePlayer == 0)

                resultWindow.setText("Red has won!");
            else
                resultWindow.setText("Yellow has won!");

                layout.setVisibility(View.VISIBLE);
                gameActive = false;
                break;
            }



        }
        if(gameActive == true)
        {
            boolean draw = true;
            for(int x = 0;x < 9;x++)
            {
                if(gameState[x] == 2) draw = false;

            }
            if(draw == true)
            {
                resultWindow.setText("It's a draw!");
                layout.setVisibility(View.VISIBLE);
                gameActive = false;
            }
        }
    }
    public void playAgain(View view)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.displayResult);
        layout.setVisibility(View.INVISIBLE);
        for(int x = 0;x < gameState.length;x++)
            gameState[x] = 2;
        activePlayer = 0;
        gameActive = true;
        GridLayout board = (GridLayout) findViewById(R.id.board);
        for(int x = 0;x < board.getChildCount();x++)
        {
            ((ImageView)board.getChildAt(x)).setImageResource(0);
        }

    }
}
