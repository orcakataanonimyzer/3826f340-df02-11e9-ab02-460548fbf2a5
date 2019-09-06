package com.pillar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Finder {

	private Keyword keyword;
	private List<Keyword> directionTypes;
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

	public List<Keyword> getDirectionTypes() {
		return directionTypes;
	}

	public void setDirectionTypes(List<Keyword> directionTypes) {
		this.directionTypes = directionTypes;
	}

	public void findPotentialStartingCoordinates() {
		List<PotentialStartCoordinates> potentials = new ArrayList<>();
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid.length; col++) {
				if (keyword.getWord().startsWith(grid[row][col])) {
					potentials.add(new PotentialStartCoordinates(new Coordinates(row, col)));
				}
			}
		}
		keyword.setPotentialStartCoordinates(potentials);
	}

	public void setPotentialStartCoordinatesWithDirections() {
		findPotentialStartingCoordinates();
		keyword.setPotentialStartCoordinates(getDirectionsForPotentialStartCoordinates());
	}

	public List<PotentialStartCoordinates> getDirectionsForPotentialStartCoordinates() {
		List<PotentialStartCoordinates> allKeywordPotentials = new ArrayList<>();
		for (int i = 0; i < keyword.getPotentialStartCoordinates().size(); i++) {
			Coordinates coordinates = keyword.getPotentialStartCoordinates().get(i).getStartCoordinates();
			PotentialStartCoordinates potentials = new PotentialStartCoordinates(coordinates,
					getDirections(coordinates));
			allKeywordPotentials.add(potentials);
		}
		return allKeywordPotentials;
	}

	private List<Direction> getDirections(Coordinates coordinates) {
		List<Direction> directions = new ArrayList<>();
		if (checkHorizontal(coordinates)) {
			directions.add(Direction.HORIZONTAL);
		}
		if (checkVertical(coordinates)) {
			directions.add(Direction.VERTICAL);
		}
		if (checkDiagonalDown(coordinates)) {
			directions.add(Direction.DIAGONAL_DOWN);
		}
		return directions;
	}

	private Boolean checkHorizontal(Coordinates coordinates) {
		if (keyword.getLength() <= grid.length - coordinates.getCol()) {
			return (keyword.getWord().substring(1, 2).equals(grid[coordinates.getRow()][coordinates.getCol() + 1]));
		} else {
			return false;
		}
	}

	private Boolean checkVertical(Coordinates coordinates) {
		if (keyword.getLength() <= grid.length - coordinates.getRow()) {
			return (keyword.getWord().substring(1, 2).equals(grid[coordinates.getRow() + 1][coordinates.getCol()]));
		} else {
			return false;
		}
	}

	private Boolean checkDiagonalDown(Coordinates coordinates) {
		if ((keyword.getLength() <= grid.length - coordinates.getCol())
				&& (keyword.getLength() <= grid.length - coordinates.getRow())) {
			return (keyword.getWord().substring(1, 2).equals(grid[coordinates.getRow() + 1][coordinates.getCol() + 1]));
		} else {
			return false;
		}
	}

	public void setKeywordToDirectionType() {
		Keyword directionType;
		for (int i = 0; i < keyword.getPotentialStartCoordinates().size(); i++) {
			for (Direction each : keyword.getPotentialStartCoordinates().get(i).getDirections()) {
				switch (each) {
				case HORIZONTAL:
					directionType = new Horizontal(getKeyword().getWord(), getKeyword().getPotentialStartCoordinates());
					directionType.findRemainingCoordinates(grid,
							keyword.getPotentialStartCoordinates().get(i).getStartCoordinates());
					break;
				case VERTICAL:
					directionType = new Vertical(getKeyword().getWord(), getKeyword().getPotentialStartCoordinates());;
					directionType.findRemainingCoordinates(grid,
							keyword.getPotentialStartCoordinates().get(i).getStartCoordinates());
					break;
				default:
					directionType = null;
					break;
				}
				keyword.setIsFound(directionType.getIsFound());
				keyword.setCoordinates(directionType.getCoordinates());
			}

		}
		setDirectionTypes(directionTypes);
	}

	public void testDirection(Coordinates coordinates) {
		setKeywordToDirectionType();
		for (Keyword each : directionTypes) {
			each.findRemainingCoordinates(grid, coordinates);
		}
	}
	
	
	private Boolean checkIfWordIsLongerThanTwoLetters() {
		return (keyword.getLength() > 2);
	}

}
