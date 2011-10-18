package com.firen.awesomeblocks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class AwesomeBlocksActivity extends Activity {
	/** Called when the activity is first created. */

	private Intent gamePlayActivity;
	private Intent selectLevelActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		View button = findViewById(R.id.play_game);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(getGamePlayActivity());
			}
		});

		button = findViewById(R.id.select_level);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(getSelectLevelActivity());
			}
		});

		button = findViewById(R.id.exit_game);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	private Intent getSelectLevelActivity() {
		if (this.selectLevelActivity == null) {
			this.selectLevelActivity = new Intent(this,
					SelectLevelActivity.class);
		}
		return this.selectLevelActivity;
	}

	private Intent getGamePlayActivity() {
		if (gamePlayActivity == null) {
			gamePlayActivity = new Intent(this, GameplayActivity.class);
		}
		return this.gamePlayActivity;
	}
}