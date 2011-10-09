package com.firen.awesomeblocks;

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
		this(10, 10, new int[] { 0, -1 });
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

	public void setSelected(int x, int y, boolean selected) {
		this.selectedTiles[y][x] = selected;
	}

	public boolean isSelected(int x, int y) {
		return this.selectedTiles[y][x];
	}

	static void generateBoardTiles(Board board) {
		Random r = new Random();
		for (int i = 0; i < board.tiles.length; i++) {
			for (int j = 0; j < board.tiles[i].length; j++) {
				board.tiles[i][j] = r.nextInt(board.pallete.length);
			}
		}
	}
}
