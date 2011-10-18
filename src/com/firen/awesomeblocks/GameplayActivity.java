package com.firen.awesomeblocks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class GameplayActivity extends Activity {
	private Board board;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getIntent().hasExtra("board")) {
			this.board = (Board) getIntent().getSerializableExtra("board");
		} else {
			this.board = new Board();
			Board.generateBoardTiles(board);
		}
		setContentView(new GameplaySurface(this, this.board));
	}

	class AlertOnClickListener implements DialogInterface.OnClickListener {

		private Activity activity;

		public AlertOnClickListener(Activity activity) {
			this.activity = activity;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				this.activity.finish();
				break;
			case DialogInterface.BUTTON_NEGATIVE:
				dialog.cancel();
				break;
			}
		}
	}

	@Override
	public void onBackPressed() {
		AlertDialog alert = new AlertDialog.Builder(this).create();
		alert.setTitle("SURE?");
		AlertOnClickListener alertOnClickListener = new AlertOnClickListener(
				this);
		alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes",
				alertOnClickListener);
		alert.setButton(DialogInterface.BUTTON_NEGATIVE, "No",
				alertOnClickListener);
		alert.show();
	}

}
