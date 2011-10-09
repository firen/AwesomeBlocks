package com.firen.awesomeblocks;

import android.app.Activity;
import android.os.Bundle;

public class AwesomeBlocksActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Board board = new Board();
		Board.generateBoardTiles(board);
		GameplaySurface gameplaySurface = new GameplaySurface(this, board);
		setContentView(gameplaySurface);
	}
}