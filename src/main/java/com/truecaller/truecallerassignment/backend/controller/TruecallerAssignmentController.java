package com.truecaller.truecallerassignment.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.truecaller.truecallerassignment.backend.entities.CBRouteResponse;
import com.truecaller.truecallerassignment.backend.entities.Tile;
import com.truecaller.truecallerassignment.backend.services.ChessBoardService;
import com.truecaller.truecallerassignment.backend.utilities.ChessBoardUtility;

@RestController
public class TruecallerAssignmentController {
	
	@Autowired
	private ChessBoardService service;
	
	@Autowired
	private ChessBoardUtility utility;

	@CrossOrigin()
	@GetMapping("/findpath")
	public List<CBRouteResponse> findPath(@RequestParam int row, @RequestParam int column) {
		Set<Tile> allTiles = utility.populateAllTiles();
		allTiles.stream().forEach(tile -> utility.generateAllowedMoves(tile, allTiles));
		Tile tile = utility.getTile(row, column, allTiles);
		List<Tile> traversedTiles = new ArrayList<Tile>();
		service.findPath(tile, traversedTiles);
		List<CBRouteResponse> response = utility.transformResponse(traversedTiles);
		return response;
    }
	
	
}
