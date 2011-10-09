package com.firen.awesomeblocks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class GameplaySurface extends View {

	private Board board;

	public static final int BLOCK_WIDTH = 50;
	public static final int BLOCK_HEIGHT = 50;

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
					p.setStyle(Paint.Style.FILL);
					canvas.drawRect(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, j
							* BLOCK_WIDTH + BLOCK_WIDTH, i * BLOCK_HEIGHT
							+ BLOCK_HEIGHT, p);
					if (this.board.isSelected(j, i)) {
						p.setColor(Color.RED);
						p.setStyle(Paint.Style.STROKE);
						canvas.drawRect(j * BLOCK_WIDTH + 1, i * BLOCK_HEIGHT
								+ 1, j * BLOCK_WIDTH + BLOCK_WIDTH - 2, i
								* BLOCK_HEIGHT + BLOCK_HEIGHT - 2, p);
					}
				}
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawBoard(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		if (event.getAction() == MotionEvent.ACTION_UP) {
			Point p = getTileLocation((int) event.getX(), (int) event.getY());
			if (p.x < this.board.getWidth() && p.y < this.board.getHeight()) {
				this.board.setSelected(p.x, p.y, true);
				selectNeighbourBlocks(p.x, p.y);
				invalidate();
			}
		}
		return true;
	}

	private boolean isPointInTheBoard(int x, int y) {
		return !(x < 0 || x >= this.board.getWidth() || y < 0 || y >= this.board
				.getHeight());
	}

	private boolean isPointThatSameColor(int x1, int y1, int x2, int y2) {
		if (isPointInTheBoard(x1, y1) && isPointInTheBoard(x2, y2)) {
			return this.board.getTileColor(x1, y1) == this.board.getTileColor(
					x2, y2);
		} else {
			return false;
		}
	}

	private boolean isPointSelected(int x, int y) {
		return isPointInTheBoard(x, y) && this.board.isSelected(x, y);
	}

	private void selectNeighbourBlocks(int x, int y) {
		if (isPointInTheBoard(x, y)) {
			if (isPointThatSameColor(x, y, x - 1, y)
					&& !isPointSelected(x - 1, y)) {
				this.board.setSelected(x - 1, y, true);
				selectNeighbourBlocks(x - 1, y);
			}
			if (isPointThatSameColor(x, y, x, y - 1)
					&& !isPointSelected(x, y - 1)) {
				this.board.setSelected(x, y - 1, true);
				selectNeighbourBlocks(x, y - 1);
			}
			if (isPointThatSameColor(x, y, x + 1, y)
					&& !isPointSelected(x + 1, y)) {
				this.board.setSelected(x + 1, y, true);
				selectNeighbourBlocks(x + 1, y);
			}
			if (isPointThatSameColor(x, y, x, y + 1)
					&& !isPointSelected(x, y + 1)) {
				this.board.setSelected(x, y + 1, true);
				selectNeighbourBlocks(x, y + 1);
			}
		}
	}

	private Point getTileLocation(int x, int y) {
		return new Point(x / BLOCK_WIDTH, y / BLOCK_HEIGHT);
	}
}
