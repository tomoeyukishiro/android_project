package com.solo.scorekeeper;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ScoreKeeperActivity extends Activity {
	ArrayList<TextView> player_scores;
	ArrayList<Button> player_buttons;
    EditText user_input;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_keeper);
		
		player_scores = new ArrayList<TextView>();
		player_buttons = new ArrayList<Button>();

		player_scores.add((TextView) findViewById(R.id.player1_score));
		player_scores.add((TextView) findViewById(R.id.player2_score));
		player_scores.add((TextView) findViewById(R.id.player3_score));
		
		player_buttons.add((Button) findViewById(R.id.player1_btn));
		player_buttons.add((Button) findViewById(R.id.player2_btn));
		player_buttons.add((Button) findViewById(R.id.player3_btn));

        user_input = (EditText) findViewById(R.id.input);

		resetScores();
		setupButtons();
	}
	
	private void resetScores(){
		for(TextView score : player_scores){
			score.setText("0");
		}
	}

	private void setupButtons(){
		int counter = 0;
		for(Button button: player_buttons){
			TextView currentScore = player_scores.get(counter);
			button.setOnClickListener(new ScoreButtonListener(currentScore));
			counter +=1 ;
		}
	}
	
	protected class ScoreButtonListener implements OnClickListener{
		TextView scoreView;

		public ScoreButtonListener(TextView scoreView){
			this.scoreView = scoreView;
		}
		@Override
		public void onClick(View v) {
            if(user_input.getText().toString().isEmpty()){
                return;
            }
			int currentScore = Integer.parseInt((String) scoreView.getText());
			int newScore = currentScore + Integer.parseInt(user_input.getText().toString()); //user input
			scoreView.setText(Integer.toString(newScore));
		}
	}
}
