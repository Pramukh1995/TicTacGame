package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // 0->O , 1->X, 2-> empty
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningStates = {{0,3,6}, {1,4,7}, {2,5,8}, {0,1,2}, {3,4,5}, {6,7,8}, {0,4,8}, {2,4,6}};
    boolean IsGameActive = true;

    public void popUp(View view){
        ImageView section = (ImageView) view;
        int tappedSection = Integer.parseInt(view.getTag().toString());

        if(gameState[tappedSection]==2 && IsGameActive) {
            gameState[tappedSection] = activePlayer;
            //For animation
            section.setScaleX(0);
            section.setScaleY(0);

            if (activePlayer == 0) {
                section.setImageResource(R.drawable.o_image);
                activePlayer = 1;
            } else {
                section.setImageResource(R.drawable.x_image);
                activePlayer = 0;
            }
            //Animation
            section.animate().scaleX(1).scaleY(1).setDuration(300);

            //In each array of winning positions check if gameState matches
            for (int[] winningState : winningStates) {
                if (gameState[winningState[0]] == gameState[winningState[1]] && gameState[winningState[1]] == gameState[winningState[2]] && gameState[winningState[0]] != 2) {

                    String winner = "";
                    IsGameActive = false;
                    if (activePlayer == 0) {
                        winner = "X";
                    } else {
                        winner = "O";
                    }

                    Button playAgainButton = findViewById(R.id.playAgainButton);
                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has Won !!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }

            //draw
            int count = 0;
            for(int i=1; i<gameState.length; i++) {
                if (gameState[i] == 2)
                    count++;
            }
            if(count == 0 && IsGameActive){
                IsGameActive = false;
                Button playAgainButton = findViewById(R.id.playAgainButton);
                TextView winnerTextView = findViewById(R.id.winnerTextView);
                winnerTextView.setText("DRAW");
                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);
            }


        }
    }



    public void playAgain(View view){
        Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        //Resetting the layout
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i< gridLayout.getChildCount(); i++){
            ImageView sections = (ImageView) gridLayout.getChildAt(i);
            sections.setImageDrawable(null);
        }

        //Resetting game states
        activePlayer = 0;
        IsGameActive = true;
        for(int i=0; i<gameState.length; i++){
            gameState[i] =2;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}