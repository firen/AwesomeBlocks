package com.firen.awesomeblocks;

import java.io.IOException;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectLevelActivity extends Activity {

	private Intent gamePlayActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_select_layout);
		Button button = (Button) findViewById(R.id.level1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					getGamePlayActivity().putExtra("board", Board.createBoard(getAssets().open("level1.lvl")));
				} catch (IOException e) {
					Log.e(SelectLevelActivity.class.getSimpleName(), "level1.lvl cannot be loaded", e);
				}
				startActivity(getGamePlayActivity());
			}
		});
		
		button = (Button) findViewById(R.id.level2);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					getGamePlayActivity().putExtra("board", Board.createBoard(getAssets().open("level2.lvl")));
				} catch (IOException e) {
					Log.e(SelectLevelActivity.class.getSimpleName(), "level2.lvl cannot be loaded", e);
				}
				startActivity(getGamePlayActivity());
			}
		});
		
		button = (Button) findViewById(R.id.level_select_back);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private Intent getGamePlayActivity() {
		if (this.gamePlayActivity == null) {
			this.gamePlayActivity = new Intent(this, GameplayActivity.class);
		}
		return gamePlayActivity;
	}
}
