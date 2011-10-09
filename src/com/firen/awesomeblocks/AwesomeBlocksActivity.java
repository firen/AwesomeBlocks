package com.firen.awesomeblocks;

import android.app.Activity;
import android.os.Bundle;

public class AwesomeBlocksActivity extends Activity {
	private Board board;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameplaySurface gameplaySurface = new GameplaySurface(this);
        Board board = new Board();
        Board.generateBoardTiles(board);
        gameplaySurface.setBoard(board);
        setContentView(gameplaySurface);
    }
}