package com.firen.awesomeblocks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.BoringLayout;
import android.text.method.MovementMethod;
import android.view.MotionEvent;
import android.view.View;

public class GameplaySurface extends View {

	private Board board;

	private int marginX;

	private int marginY;

	private int score;

	public static final int BLOCK_WIDTH = 30;
	public static final int BLOCK_HEIGHT = 30;

	private Bitmap blockBitmap;

	public GameplaySurface(Context context, Board board) {
		super(context);
		this.board = board;
		this.marginY = 30;
		blockBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.block);
	}

	public void setBoard(Board board) {
		this.board = board;
		this.marginX = (int) (this.getWidth() * 0.5 - board.getWidth()
				* BLOCK_WIDTH * 0.5);
		// this.marginY = (int) (this.getHeight() * 0.5 - board.getHeight() *
		// BLOCK_HEIGHT * 0.5);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		this.marginX = (int) (this.getWidth() * 0.5 - board.getWidth()
				* BLOCK_WIDTH * 0.5);
	}

	private Rect calcBlockRect(int x, int y) {
		int left = marginX + x * BLOCK_WIDTH;
		int top = marginY + y * BLOCK_HEIGHT;
		int right = left + BLOCK_WIDTH;
		int bottom = top + BLOCK_HEIGHT;
		return new Rect(left, top, right, bottom);
	}

	private Point getTileLocation(int x, int y) {
		return new Point((x - marginX) / BLOCK_WIDTH, (y - marginY)
				/ BLOCK_HEIGHT);
	}

	private void drawScore(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		canvas.drawText(Integer.toString(this.score), .0f, 15.0f, paint);
	}

	private void drawBoard(Canvas canvas) {
		if (this.board != null) {
			Paint p = new Paint();
			for (int i = 0; i < this.board.getHeight(); i++) {
				for (int j = 0; j < this.board.getWidth(); j++) {
					if (this.board.getTilePalleteIndex(j, i) != -1) {
						p.setColor(this.board.getTileColor(j, i));
						p.setStyle(Paint.Style.FILL);
						// canvas.drawRect(calcBlockRect(j, i), p);
						final ColorFilter colorFilter = new LightingColorFilter(
								this.board.getTileColor(j, i), 0);
						// final ColorFilter colorFilter = new
						// PorterDuffColorFilter(
						// this.board.getTileColor(j, i),
						// PorterDuff.Mode.XOR);
						p.setColorFilter(colorFilter);
						canvas.drawBitmap(blockBitmap, null,
								calcBlockRect(j, i), p);
						if (this.board.isSelected(j, i)) {
							p.setColor(Color.RED);
							p.setColorFilter(null);
							p.setStyle(Paint.Style.STROKE);
							Rect rect = calcBlockRect(j, i);
							rect.left++;
							rect.right -= 2;
							rect.top++;
							rect.bottom -= 2;
							canvas.drawRect(rect, p);
						}
					}
				}
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawBoard(canvas);
		drawScore(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		if (event.getAction() == MotionEvent.ACTION_UP) {
			Point p = getTileLocation((int) event.getX(), (int) event.getY());
			if (p.x < this.board.getWidth() && p.y < this.board.getHeight()) {
				if (isPointInTheBoard(p.x, p.y)) {
					if (this.board.isSelected(p.x, p.y)
							&& this.board.getSelectedBlocksCount() > 1) {
						score += this.board.getSelectedBlocksCount() * 100;
						removeSelectedBlocks();
						this.board.clearSelection();
						this.board.moveDownBlocks();
						this.board.moveLeftBlocks();
					} else {
						this.board.clearSelection();
						this.board.setSelected(p.x, p.y, true);
						selectNeighbourBlocks(p.x, p.y);
					}
					invalidate();
				}
			}
		}
		return true;
	}

	private void removeSelectedBlocks() {
		for (int i = 0; i < this.board.getHeight(); i++) {
			for (int j = 0; j < this.board.getWidth(); j++) {
				if (this.board.isSelected(j, i)) {
					this.board.setTilePalleteIndex(j, i, -1);
				}
			}
		}
	}

	private boolean isPointInTheBoard(int x, int y) {
		if (!(x < 0 || x >= this.board.getWidth() || y < 0 || y >= this.board
				.getHeight())) {
			return this.board.getTilePalleteIndex(x, y) != -1;
		}
		return false;
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

}
