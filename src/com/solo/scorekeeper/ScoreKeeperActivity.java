package com.solo.scorekeeper;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;

public class ScoreKeeperActivity extends Activity {
	ArrayList<TextView> player_scores;
	ArrayList<Button> player_buttons;
    EditText input_score;
    Button add_players;
    LinearLayout player_layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_keeper);
		
		player_scores = new ArrayList<TextView>();
		player_buttons = new ArrayList<Button>();

        input_score = (EditText) findViewById(R.id.input);
        add_players = (Button) findViewById(R.id.add_btn);
        add_players.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewPlayer("allie");
            }
        });
        player_layout = (LinearLayout) findViewById(R.id.player_layout);
	}

    private void addNewPlayer(String playerName){
        LinearLayout player_column = new LinearLayout(this);
        player_column.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        player_column.setOrientation(LinearLayout.VERTICAL);

        TextView player_label = new TextView(this);
        player_label.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        player_label.setText(playerName);
        player_label.setTextSize(20);
        player_label.setTextColor(getResources().getColor(android.R.color.black));

        TextView player_score = new TextView(this);
        player_score.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        player_score.setText("0");
        player_score.setTextSize(20);
        player_score.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        player_scores.add(player_score);

        Button player_btn = new Button(this);
        player_btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        player_btn.setText(playerName);
        player_btn.setTextSize(20);
        player_btn.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        player_buttons.add(player_btn);

        player_btn.setOnClickListener(new ScoreButtonListener(player_score));

        player_column.addView(player_label);
        player_column.addView(player_score);
        player_column.addView(player_btn);

        player_layout.addView(player_column);
    }
	private void resetScores(){
		for(TextView score : player_scores){
			score.setText("0");
		}
	}

	protected class ScoreButtonListener implements OnClickListener{
		TextView scoreView;

		public ScoreButtonListener(TextView scoreView){
			this.scoreView = scoreView;
		}
		@Override
		public void onClick(View v) {
            if(input_score.getText().toString().isEmpty()){
                return;
            }
			int currentScore = Integer.parseInt((String) scoreView.getText());
			int newScore = currentScore + Integer.parseInt(input_score.getText().toString()); //user input
			scoreView.setText(Integer.toString(newScore));
		}
	}
}
