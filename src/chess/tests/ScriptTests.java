package chess.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.game.ChessBoard;
import chess.game.ChessGame;

public class ScriptTests {
	
	private ChessGame game;

	@Before
	public void setup() {
		game = new ChessGame();
	}

	@Test
	public void testKnighTour() throws Exception {
		
		game.loadBoard("scripts/knightTourStart");		

		ChessBoard result = ChessBoard.readFromFile("scripts/afterKnightTour");
		
		game.loadScript("scripts/knightTour");

		assertTrue(game.compareBoard(result));

	}

}
