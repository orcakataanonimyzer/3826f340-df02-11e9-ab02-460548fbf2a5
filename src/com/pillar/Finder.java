package com.pillar;

import java.util.ArrayList;
import java.util.List;

public class Finder {

	private Keyword keyword;
	private String[][] grid;

	public Finder(Keyword keyword, String[][] grid) {
		super();
		this.keyword = keyword;
		this.grid = grid;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public String[][] getGrid() {
		return grid;
	}

	public List<Coordinates> findPotentialStartingCoordinates() {
		List<Coordinates> potentials = new ArrayList<>();
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid.length; col++) {
				if (keyword.getWord().startsWith(grid[row][col]) 
						&& (keyword.getLength() <= (grid[0].length - col))) {
					potentials.add(new Coordinates(row, col));
				}
			}
		}
		return potentials;
	}

	public Boolean checkNextCoordinate() {
		return true;
	}
}
