package com.firen.awesomeblocks;

import java.io.IOException;
import java.util.List;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class SelectLevelActivity extends Activity {

	private Intent gamePlayActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_select_layout);

		GridView levelsGridView = (GridView) findViewById(R.id.levelsGridView);
		levelsGridView.setAdapter(new LevelsAdapter(this));

		Button button = (Button) findViewById(R.id.level_select_back);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private String[] getStages() {
		try {
			return getAssets().list("levels");
		} catch (IOException e) {
			Log.e(SelectLevelActivity.class.getSimpleName(),
					"can not get list of stages from assets", e);
			return null;
		}
	}

	private Intent getGamePlayActivity() {
		if (this.gamePlayActivity == null) {
			this.gamePlayActivity = new Intent(this, GameplayActivity.class);
		}
		return gamePlayActivity;
	}

	private class LevelsAdapter extends BaseAdapter {

		private Context context;

		public LevelsAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			try {
				return getAssets().list("levels").length;
			} catch (IOException e) {
				Log.e(LevelsAdapter.class.getSimpleName(),
						"Cannot get levels count");
				return 0;
			}
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Button levelButton;
			if (convertView == null) {
				levelButton = new Button(context);
				levelButton.setText("Level" + (position + 1));
				levelButton.setOnClickListener(new LevelOnClickListener(
						position + 1));
				return levelButton;
			} else {
				return convertView;
			}
		}

	}

	private class LevelOnClickListener implements View.OnClickListener {

		private int level;

		public LevelOnClickListener(int level) {
			this.level = level;
		}

		@Override
		public void onClick(View v) {
			try {
				getGamePlayActivity().putExtra(
						"board",
						Board.createBoard(getAssets().open(
								"levels/" + "level" + level + ".lvl")));
			} catch (IOException e) {
				Log.e(SelectLevelActivity.class.getSimpleName(),
						"level" + level + ".lvl cannot be loaded", e);
			}
			startActivity(getGamePlayActivity());
		}

	}
}
