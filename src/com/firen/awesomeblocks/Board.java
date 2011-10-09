package com.firen.awesomeblocks;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import android.util.Log;

public class Board {
	private int tiles[][];
	private boolean selectedTiles[][];
	private int pallete[];

	public Board(int width, int height, int pallete[]) {
		tiles = new int[height][];
		selectedTiles = new boolean[height][];
		for (int i = 0; i < height; i++) {
			tiles[i] = new int[width];
			selectedTiles[i] = new boolean[width];
		}

		this.pallete = pallete;
	}

	public Board() {
		this(10, 10, new int[] { 0xff0000ff, -1 });
	}

	public int getTilePalleteIndex(int x, int y) {
		return this.tiles[y][x];
	}

	public int getTileColor(int x, int y) {
		return this.pallete[this.tiles[y][x]];
	}

	public int getWidth() {
		return this.tiles[0].length;
	}

	public int getHeight() {
		return this.tiles.length;
	}

	public int getSelectedBlocksCount() {
		int count = 0;
		for (int i = 0; i < getHeight(); i++) {
			for (int j = 0; j < getWidth(); j++) {
				if (isSelected(j, i))
					count++;
			}
		}
		return count;
	}

	public void moveDownBlocks() {
		for (int i = getHeight() - 2; i >= 0; i--) {
			for (int j = 0; j < getWidth(); j++) {
				int tmp = i;
				while (tmp < getHeight() - 1) {
					if (this.getTilePalleteIndex(j, tmp + 1) == -1) {
						tmp++;
					} else {
						break;
					}
				}
				if (tmp > i) {
					this.setTilePalleteIndex(j, tmp,
							this.getTilePalleteIndex(j, i));
					this.setTilePalleteIndex(j, i, -1);
				}
			}
		}
	}

	public void setSelected(int x, int y, boolean selected) {
		this.selectedTiles[y][x] = selected;
	}

	public boolean isSelected(int x, int y) {
		return this.selectedTiles[y][x];
	}

	public void clearSelection() {
		for (int i = 0; i < this.getHeight(); i++) {
			Arrays.fill(selectedTiles[i], false);
		}
	}

	static void generateBoardTiles(Board board) {
		Random r = new Random();
		for (int i = 0; i < board.tiles.length; i++) {
			for (int j = 0; j < board.tiles[i].length; j++) {
				board.tiles[i][j] = r.nextInt(board.pallete.length);
			}
		}
	}

	public void setTilePalleteIndex(int x, int y, int index) {
		this.tiles[y][x] = index;
	}
}
