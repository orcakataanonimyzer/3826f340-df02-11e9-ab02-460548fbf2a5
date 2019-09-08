package com.grandcircus.wordsearch.finder;

import java.util.ArrayList;
import java.util.List;

import com.grandcircus.wordsearch.keyword.Coordinates;
import com.grandcircus.wordsearch.keyword.Keyword;

public class Horizontal extends Compass {

	public Horizontal (Keyword keyword, Coordinates coordinates) {
		super();
		this.keyword = keyword;
		this.coordinates = coordinates;
		thisRow = coordinates.getRow();
		oneAhead = coordinates.getCol() + 1;

	}
	
	@Override
	public String getGridSubstring() {
		String gridSubstring = "";
		for (int i = 0; i < getRemainingLetters().length(); i++) {
			gridSubstring += grid[thisRow][oneAhead + i];
		}
		return gridSubstring;
	}
	
	@Override
	public List<Coordinates> getRemainingCoordinates() {
		List<Coordinates> foundCoordinates = new ArrayList<>();
		foundCoordinates.add(coordinates);
		for (int i = 0; i < getRemainingLetters().length(); i++) {
			foundCoordinates.add(new Coordinates(thisRow, oneAhead + i));
		}
		return foundCoordinates;
	}

	@Override
	public String toString() {
		return "Horizontal";
	}

}
