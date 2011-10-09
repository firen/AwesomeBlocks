package com.firen.awesomeblocks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class GameplaySurface extends View {

	private Board board;

	public static final int BLOCK_WIDTH = 10;
	public static final int BLOCK_HEIGHT = 10;

	public GameplaySurface(Context context) {
		super(context);
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	private void drawBoard(Canvas canvas) {
		if (this.board != null) {
			Paint p = new Paint();
			for (int i = 0; i < this.board.getHeight(); i++) {
				for (int j = 0; j < this.board.getWidth(); j++) {
					p.setColor(this.board.getTileColor(j, i));
					canvas.drawRect(j*BLOCK_WIDTH, i*BLOCK_HEIGHT, j*BLOCK_WIDTH+BLOCK_WIDTH, i*BLOCK_HEIGHT+BLOCK_HEIGHT, p);
				}
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawBoard(canvas);
	}

}
