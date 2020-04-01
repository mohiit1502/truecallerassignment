package com.truecaller.truecallerassignment.backend.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.truecaller.truecallerassignment.backend.entities.CBRouteResponse;
import com.truecaller.truecallerassignment.backend.entities.Tile;

@Component
public class ChessBoardUtility {
	
	public static Set<Tile> populateAllTiles() {
		return IntStream.rangeClosed(1, 10).boxed().flatMap(
					row -> IntStream.rangeClosed(1, 10)
				    		.boxed().map(column -> new Tile(row, column, false))).collect(Collectors.toSet());
	}
	
	public static Tile getTile(int x, int y, Set<Tile> allTiles) {
		if (allTiles == null) {
			allTiles = new HashSet<Tile>();
		}
		List<Tile> pList = allTiles.stream().filter(tile -> tile.getRow() == x && tile.getColumn() == y).collect(Collectors.toList());
		if (pList.size() != 0) {
			return pList.get(0);	
		} else {
			Tile p = new Tile(x, y, false);
			allTiles.add(p);
			return p;
		}
	}
	
	public static void displayPath(List<Tile> traversedTiles) {
		Function<Tile, String> tileToPosition = tile -> tile.getRow() + "," + tile.getColumn();
		if (traversedTiles.size() == 100) {
			String path = traversedTiles.stream().map(tileToPosition).collect(Collectors.joining(" => "));
			System.out.println("Found a tour from the specified position:\n" + path);
		} else {
			System.out.println("No path could be generated with given initial position and rules of movement!");
		}
	}
	
	public static void displayAdvanced(List<Tile> traversedTiles) {
		Function<Tile, String> tileToPosition = tile -> tile.getRow() + "," + tile.getColumn();
		int count = 1;
		String [][] chessBoard = new String[10][10];
		if (traversedTiles.size() == 100) {
			for (Tile tile : traversedTiles) {
				String displayValue = count + ": " + tile.getRow() + "-" + tile.getColumn();
				count++;
				chessBoard[tile.getRow() - 1][tile.getColumn()-1] = displayValue;
			}
			System.out.println("Found a tour from the specified position:\n");
//			Stream.of(chessBoard)
//				.flatMap(Stream::of)
////				.map(t -> Integer.parseInt(t.substring(0, t.indexOf(":"))))
////				.sorted((t1, t2) -> Integer.compare(Integer.parseInt(t1.substring(0, t1.indexOf(":"))), Integer.parseInt(t2.substring(0, t2.indexOf(":")))))
//				.forEach(System.out::print);
			for (int i = 0; i < chessBoard.length; i++) {
				System.out.println();
				if (i == 9) {
					for (int j = 0; j < chessBoard[0].length; j++) {
						System.out.print(chessBoard[i][j] + "\t");
					}
				} else {
					for (int j = 0; j < chessBoard[0].length; j++) {
						System.out.print(chessBoard[i][j] + "\t\t");
					}
				}
			}
		} else {
			System.out.println("No path could be generated with given initial position and rules of movement!");
		}
	}
	
	/**
	 * Function to generate all possible moves from a tile based on it's current position.
	 * 
	 * @return List<Tile> list of all positions possible from given tile
	 * 
	 */
	public void generateAllowedMoves(Tile tile, Set<Tile> allTiles) {
		List<Tile> allowedMoves = new ArrayList<Tile>();
		int row = tile.getRow();
		int column = tile.getColumn();
		if (row > 3)
			allowedMoves.add(getTile(row - 3, column, allTiles));
		if (row < 8)
			allowedMoves.add(getTile(row + 3, column, allTiles));
		if (column > 3)
			allowedMoves.add(getTile(row, column - 3, allTiles));
		if (column < 8)
			allowedMoves.add(getTile(row, column + 3, allTiles));
		if (row > 2 && column > 2)
			allowedMoves.add(getTile(row - 2, column - 2, allTiles));
		if (row > 2 && column < 9)
			allowedMoves.add(getTile(row - 2, column + 2, allTiles));
		if (row < 9 && column > 2)
			allowedMoves.add(getTile(row + 2, column - 2, allTiles));
		if (row < 9 && column < 9)
			allowedMoves.add(getTile(row + 2, column + 2, allTiles));
		
		tile.setAllowedMoves(allowedMoves.stream().filter(tile1 -> !tile1.isVisited()).collect(Collectors.toList()));
	}

	public List<CBRouteResponse> transformResponse(List<Tile> traversedTiles) {
		AtomicInteger index = new AtomicInteger();
		List<CBRouteResponse> response = traversedTiles.stream().map(tile -> {
			CBRouteResponse singleResponse = new CBRouteResponse(tile.getRow(), tile.getColumn(), index.incrementAndGet());
			List<Tile> tiles = tile.getAllowedMoves();
			String moves = null;
			if (tiles != null) {
				moves = tiles.stream().map(tile1 -> tile1.getRow() + "-" + tile1.getColumn()).collect(Collectors.joining(",  "));
			}
			singleResponse.setAllowedMoves(moves);
//			tile.setAllowedMoves(null);
//			tile.setAllowedMovesUI(moves);
			return singleResponse;
		}).collect(Collectors.toList());
		return response;
	}
}
