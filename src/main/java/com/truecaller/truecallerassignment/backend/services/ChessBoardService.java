package com.truecaller.truecallerassignment.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.truecaller.truecallerassignment.backend.entities.Tile;


/**
 * Service to find out traversal path of a chess piece to traverse each tile without repeating  any
 *
 * Rules: 
 * 	-	A piece can move 3 tiles to north, south, east or west
 * 	-	A piece can move 2 tiles diagonally to North-East, North-West, South-East and South-West
 * 	-	A piece should not visit an already visited tile.
 * 
 * @author Mohit Nagar
 *
 */
@Service
public class ChessBoardService {

	public static void findPath(Tile currentTile, List<Tile> traversedTiles) {
		if (currentTile.isVisited()) {
			return;
		}
		currentTile.setVisited(true);
		currentTile.setAllowedMoves(currentTile.getAllowedMoves().stream().filter(tile -> !tile.isVisited()).collect(Collectors.toList()));
		traversedTiles.add(currentTile);
		if (traversedTiles.size() == 100) {
			return;
		}
		currentTile.getAllowedMoves().stream().forEach(tile -> findPath(tile, traversedTiles));
	}
}
