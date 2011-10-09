package com.firen.awesomeblocks;

import java.util.Random;

public class Board {
	private int tiles[][];
	private int pallete[];

	public Board(int width, int height, int pallete[]) {
		tiles = new int[height][];
		for (int i = 0; i < height; i++) {
			tiles[i] = new int[width];
		}
		this.pallete = pallete;
	}

	public Board() {
		this(10, 10, new int[] { 0, -1 });
	}

	public int getTilePalleteIndex(int x, int y) {
		return this.tiles[y][x];
	}
	
	public int getTileColor( int x, int y ) {
		return this.pallete[this.tiles[y][x]];
	}
	
	public int getWidth() {
		return this.tiles[0].length;
	}
	
	public int getHeight() {
		return this.tiles.length;
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