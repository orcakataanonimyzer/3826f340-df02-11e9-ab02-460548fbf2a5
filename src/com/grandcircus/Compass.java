package com.grandcircus;

import java.util.ArrayList;
import java.util.List;

public class Compass {

	protected Keyword keyword;
	protected Coordinates coordinates;
	protected String[][] grid = Finder.grid;
	protected String secondLetter;
	protected Integer thisRow;
	protected Integer thisCol;
	protected Integer oneAhead;
	protected Integer oneBelow;
	protected Integer oneAbove;
	protected Integer oneBehind;
	
	public Compass() {
	}

	public Compass(Keyword keyword, Coordinates coordinates) {
		super();
		this.keyword = keyword;
		this.coordinates = coordinates;
		secondLetter = keyword.getSecondLetter();
		thisRow = coordinates.getRow();
		thisCol = coordinates.getCol();
		oneAhead = coordinates.getCol() + 1;
		oneBelow = coordinates.getRow() + 1;
		oneAbove = coordinates.getRow() - 1;
		oneBehind = coordinates.getCol() - 1;
	}	
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
		
	protected List<Direction> getDirections() {
		List<Direction> directions = new ArrayList<>();
		if (checkHorizontal()) {
			directions.add(Direction.HORIZONTAL);
		}
		if (checkVertical()) {
			directions.add(Direction.VERTICAL);
		}
		if (checkDiagonalDown()) {
			directions.add(Direction.DIAGONAL_DOWN);
		}
		if (checkDiagonalUp()) {
			directions.add(Direction.DIAGONAL_UP);
		}
		if (checkBwHorizontal()) {
			directions.add(Direction.BW_HORIZONTAL);
		}
		if (checkBwVertical()) {
			directions.add(Direction.BW_VERTICAL);
		}
		if (checkBwDiagonalDown()) {
			directions.add(Direction.BW_DIAGONAL_DOWN);
		}
		if (checkBwDiagonalUp()) {
			directions.add(Direction.BW_DIAGONAL_UP);
		}
		return directions;
	}

	protected Boolean checkHorizontal() {
		if (thereIsRoomAhead()) {
			return (secondLetter.equals(grid[thisRow][oneAhead]));
		}
		return false;
	}

	protected Boolean checkVertical() {
		if (thereIsRoomBelow()) {
			return (secondLetter.equals(grid[oneBelow][thisCol]));
		}
		return false;
	}

	protected Boolean checkDiagonalDown() {
		if (thereIsRoomAhead() && thereIsRoomBelow()) {
			return (secondLetter.equals(grid[oneBelow][oneAhead]));
		}
		return false;
	}

	protected Boolean checkDiagonalUp() {
		if (thereIsRoomAhead() && thereIsRoomAbove()) {
			return (secondLetter.equals(grid[oneAbove][oneAhead]));
		}
		return false;
	}

	protected Boolean checkBwHorizontal() {
		if (thereIsRoomBehind()) {
			return (secondLetter.equals(grid[thisRow][oneBehind]));
		}
		return false;
	}

	protected Boolean checkBwVertical() {
		if (thereIsRoomAbove()) {
			return (secondLetter.equals(grid[oneAbove][thisCol]));
		}
		return false;
	}

	protected Boolean checkBwDiagonalDown() {
		if (thereIsRoomBehind() && thereIsRoomBelow()) {
			return (secondLetter.equals(grid[oneBelow][oneBehind]));
		}
		return false;
	}

	protected Boolean checkBwDiagonalUp() {
		if (thereIsRoomBehind() && thereIsRoomAbove()) {
			return (secondLetter.equals(grid[oneAbove][oneBehind]));
		}
		return false;
	}

	private Boolean thereIsRoomAhead() {
		return keyword.getLength() <= grid.length - thisCol;
	}

	private Boolean thereIsRoomBelow() {
		return keyword.getLength() <= grid.length - thisRow;
	}

	private Boolean thereIsRoomAbove() {
		return keyword.getLength() <= oneBelow;
	}

	private Boolean thereIsRoomBehind() {
		return keyword.getLength() <= oneAhead;
	}
	
	public String getRemainingLetters() {
		return keyword.word.substring(1, keyword.word.length());
	}
	
	public void findRemainingCoordinates() {
		setCoordinatesOfRemainingLetters(getGridSubstring(), getRemainingCoordinates());
	}
	
	public void setCoordinatesOfRemainingLetters(String gridSubstring, List<Coordinates> foundCoordinates) {
		if (keywordEqualsSubsubtring(gridSubstring)) {
			keyword.setIsFound(true);
			keyword.setCoordinates(foundCoordinates);
		}
	}
	
	public Boolean keywordEqualsSubsubtring(String gridSubstring) {
		return getRemainingLetters().equals(gridSubstring);
	}

	public String getGridSubstring() {
		return null;
	}

	public List<Coordinates> getRemainingCoordinates() {
		return null;
	}
}
