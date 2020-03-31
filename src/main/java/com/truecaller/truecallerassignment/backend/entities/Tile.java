package com.truecaller.truecallerassignment.backend.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tile {
	private int row;
	private int column;
	private boolean visited;
	private List<Tile> allowedMoves;
	
	public Tile(int row, int column, boolean visited) {
		super();
		this.row = row;
		this.column = column;
		this.visited = visited;
	}
	
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String getAllowedMoves() {
		String moves = null;
		if (allowedMoves != null) {
			moves = allowedMoves.stream().map(tile -> tile.row + "-" + tile.column).collect(Collectors.joining(",  "));
		}
		
		return moves;
	}

	public void setAllowedMoves(List<Tile> allowedMoves) {
		this.allowedMoves = allowedMoves;
	}

	/**
	 * Function to generate all possible moves from a tile based on it's current position.
	 * 
	 * @return List<Tile> list of all positions possible from given tile
	 * 
	 */
	public void generateAllowedMoves() {
		List<Tile> allowedMoves = new ArrayList<Tile>();
		if (row > 3)
			allowedMoves.add(getTile(row - 3, column));
		if (row < 8)
			allowedMoves.add(getTile(row + 3, column));
		if (column > 3)
			allowedMoves.add(getTile(row, column - 3));
		if (column < 8)
			allowedMoves.add(getTile(row, column + 3));
		if (row > 2 && column > 2)
			allowedMoves.add(getTile(row - 2, column - 2));
		if (row > 2 && column < 9)
			allowedMoves.add(getTile(row - 2, column + 2));
		if (row < 9 && column > 2)
			allowedMoves.add(getTile(row + 2, column - 2));
		if (row < 9 && column < 9)
			allowedMoves.add(getTile(row + 2, column + 2));
		
		this.allowedMoves = allowedMoves.stream().filter(tile -> !tile.visited).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		String moves = null;
		if (allowedMoves != null) {
			moves = allowedMoves.stream().map(tile -> tile.row + "-" + tile.column).collect(Collectors.joining(",  "));
		} 	
		return "Tile [row=" + row + ", column=" + column + ", visited=" + visited + ", allowedMoves=" + moves
				+ "]";
	}

	
}
