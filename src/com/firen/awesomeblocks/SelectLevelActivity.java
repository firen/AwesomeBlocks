package com.firen.awesomeblocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher.ViewFactory;

public class SelectLevelActivity extends Activity {

	private Intent gamePlayActivity;
	private List<GridView> levelsGridViews = new ArrayList<GridView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_select_layout);

		ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.levels_flipper);

		try {
			String levels[] = getAssets().list("levels");
			if (levels != null) {
				for (int i = 0; i < levels.length; i++) {
					GridView levelsGridView = (GridView) getLayoutInflater()
							.inflate(R.layout.level_grid_view, null);
					levelsGridView.setAdapter(new LevelsAdapter(this, "levels/"
							+ levels[i]));
					viewFlipper.addView(levelsGridView);
				}
			}
		} catch (IOException e) {
			Log.e(SelectLevelActivity.class.getSimpleName(),
					"Cannot list files in levels folder", e);
		}

		Button button = (Button) findViewById(R.id.level_select_back);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		button = (Button) findViewById(R.id.previous_level);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.levels_flipper);
				viewFlipper.setInAnimation(SelectLevelActivity.this,
						R.anim.slide_in_right);
				viewFlipper.setOutAnimation(SelectLevelActivity.this,
						R.anim.slide_out_left);
				viewFlipper.showPrevious();
			}
		});
		button = (Button) findViewById(R.id.next_level);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.levels_flipper);
				viewFlipper.setInAnimation(SelectLevelActivity.this,
						R.anim.slide_in_left);
				viewFlipper.setOutAnimation(SelectLevelActivity.this,
						R.anim.slide_out_right);
				viewFlipper.showNext();
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
		private String levelsPath;

		public LevelsAdapter(Context context, String levelsPath) {
			this.context = context;
			this.levelsPath = levelsPath;
		}

		@Override
		public int getCount() {
			try {
				return getAssets().list(levelsPath).length;
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
				Log.e(SelectLevelActivity.class.getSimpleName(), "level"
						+ level + ".lvl cannot be loaded", e);
			}
			startActivity(getGamePlayActivity());
		}

	}
}
