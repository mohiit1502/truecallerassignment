package com.truecaller.truecallerassignment.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.truecaller.truecallerassignment.backend.entities.Tile;

@Service
public class ChessBoardService {

	public static void findPath(Tile currentTile, List<Tile> traversedTiles) {
		if (currentTile.isVisited()) {
			return;
		}
		if (currentTile.isVisited()) {
			return;
		}
		currentTile.setVisited(true);
		traversedTiles.add(currentTile);
		if (traversedTiles.size() == 100) {
			return;
		}
		currentTile.getAllowedMoves().stream().forEach(tile -> findPath(tile, traversedTiles));
	}
}
