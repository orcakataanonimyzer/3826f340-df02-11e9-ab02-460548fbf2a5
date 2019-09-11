package com.grandcircus.wordsearch.compass;

import java.util.ArrayList;
import java.util.List;

import com.grandcircus.wordsearch.app.Finder;
import com.grandcircus.wordsearch.keyword.Coordinates;
import com.grandcircus.wordsearch.keyword.Direction;
import com.grandcircus.wordsearch.keyword.Keyword;

public class Compass {

	protected Keyword keyword;
	protected Coordinates coordinates;
	private List<Direction> directions;
	protected List<Coordinates> remainingCoordinates;
	protected String[][] grid = Finder.grid;
	protected String gridSubstring;
	protected String secondLetter;
	protected String remainingLetters;
	protected Integer numberOfRemainingLetters;
	protected Integer thisRow;
	protected Integer thisCol;
	protected Integer forward;
	protected Integer down;
	protected Integer up;
	protected Integer back;

	public Compass(Keyword keyword, Coordinates coordinates) {
		super();
		this.keyword = keyword;
		this.coordinates = coordinates;
		secondLetter = keyword.getSecondLetter();
		remainingLetters = keyword.getRemainingLetters();
		numberOfRemainingLetters = keyword.getRemainingLetters().length();
		thisRow = coordinates.getRow();
		thisCol = coordinates.getCol();
		forward = coordinates.getCol() + 1;
		down = coordinates.getRow() + 1;
		up = coordinates.getRow() - 1;
		back = coordinates.getCol() - 1;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public List<Direction> getDirections() {
		return directions;
	}

	public void setDirections(List<Direction> directions) {
		this.directions = directions;
	}

	public List<Coordinates> getRemainingCoordinates() {
		return remainingCoordinates;
	}

	public void setRemainingCoordinates(List<Coordinates> remainingCoordinates) {
		this.remainingCoordinates = remainingCoordinates;
	}

	public void setGridSubstring(String gridSubstring) {
		this.gridSubstring = gridSubstring;
	}

	public void findDirections() {
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
		setDirections(directions);
	}

	private Boolean checkHorizontal() {
		if (thereIsRoomAhead()) {
			return (secondLetter.equals(grid[thisRow][forward]));
		}
		return false;
	}

	private Boolean checkVertical() {
		if (thereIsRoomBelow()) {
			return (secondLetter.equals(grid[down][thisCol]));
		}
		return false;
	}

	private Boolean checkDiagonalDown() {
		if (thereIsRoomAhead() && thereIsRoomBelow()) {
			return (secondLetter.equals(grid[down][forward]));
		}
		return false;
	}

	private Boolean checkDiagonalUp() {
		if (thereIsRoomAhead() && thereIsRoomAbove()) {
			return (secondLetter.equals(grid[up][forward]));
		}
		return false;
	}

	private Boolean checkBwHorizontal() {
		if (thereIsRoomBehind()) {
			return (secondLetter.equals(grid[thisRow][back]));
		}
		return false;
	}

	private Boolean checkBwVertical() {
		if (thereIsRoomAbove()) {
			return (secondLetter.equals(grid[up][thisCol]));
		}
		return false;
	}

	private Boolean checkBwDiagonalDown() {
		if (thereIsRoomBehind() && thereIsRoomBelow()) {
			return (secondLetter.equals(grid[down][back]));
		}
		return false;
	}

	private Boolean checkBwDiagonalUp() {
		if (thereIsRoomBehind() && thereIsRoomAbove()) {
			return (secondLetter.equals(grid[up][back]));
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
		return keyword.getLength() <= down;
	}

	private Boolean thereIsRoomBehind() {
		return keyword.getLength() <= forward;
	}

	public void findFullMatch() {
		makeGridSubstring();
		if (keywordEqualsSubsubtring()) {
			keyword.setIsFound(true);
			keyword.setAllCoordinates(findRemainingCoordinates());
		}
	}

	public void makeGridSubstring() {
		String gridSubstring = "";
		int aStep = 0;
		for (; !outOfLetters(aStep); aStep++) {
			gridSubstring += buildSubString(aStep);
		}
		setGridSubstring(gridSubstring);
	}

	public String buildSubString(Integer aStep) {
		return null;
	}

	public Boolean keywordEqualsSubsubtring() {
		return remainingLetters.equals(gridSubstring);
	}

	public List<Coordinates> findRemainingCoordinates() {
		List<Coordinates> remainingCoordinates = new ArrayList<>();
		remainingCoordinates.add(coordinates);
		int aStep = 0;
		for (; !outOfLetters(aStep); aStep++) {
			remainingCoordinates.add(buildRemainingCoordinates(aStep));
		}
		return remainingCoordinates;
	}

	public Coordinates buildRemainingCoordinates(Integer aStep) {
		return null;
	}

	public Boolean outOfLetters(Integer aStep) {
		return numberOfRemainingLetters - aStep <= 0;
	}
}
