package com.truecaller.truecallerassignment.backend.entities;

import java.util.List;
import java.util.stream.Collectors;

public class Tile {
	private int row;
	private int column;
	private boolean visited;
	private List<Tile> allowedMoves;
	private String allowedMovesUI;
	
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
	
	public List<Tile> getAllowedMoves() {
		return allowedMoves;
	}
	
	public void setAllowedMoves(List<Tile> allowedMoves) {
		this.allowedMoves = allowedMoves;
	}

	public String getAllowedMovesUI() {
		return allowedMovesUI;
	}

	public void setAllowedMovesUI(String allowedMovesUI) {
		this.allowedMovesUI = allowedMovesUI;
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
