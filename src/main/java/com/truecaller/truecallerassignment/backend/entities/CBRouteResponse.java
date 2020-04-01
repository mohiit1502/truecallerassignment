package com.truecaller.truecallerassignment.backend.entities;

public class CBRouteResponse {
	private int row;
	private int column;
	private int order;
	private String allowedMoves;

	public CBRouteResponse(int row, int column, int order) {
		super();
		this.row = row;
		this.column = column;
		this.order = order;
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
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getAllowedMoves() {
		return allowedMoves;
	}
	public void setAllowedMoves(String allowedMoves) {
		this.allowedMoves = allowedMoves;
	}
	
}
