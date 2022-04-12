package chess.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import chess.game.ChessBoard;
import chess.game.ChessGame;

public class ComplexMovesTest {

	private ChessGame game;

	public static void dumpBoard(ChessGame game) {
		try {
			game.saveBoardToFile(new File("boards/testDump"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Before
	public void setup() {
		game = new ChessGame();
	}

	// Test pour capture des pions.
	@Test
	public void testPawnCapture() throws Exception {

		game.loadBoard("boards/moves/pawnCapture");

		ChessBoard resultWhite = ChessBoard.readFromFile("boards/moves/after/pawnCaptureWhite");
		ChessBoard resultBlack = ChessBoard.readFromFile("boards/moves/after/pawnCaptureBlack");

		ChessBoard resultbad = ChessBoard.readFromFile("boards/moves/pawnCapture");

		// Illegal moves: un pion ne peut pas avancer s'il y a une pièce qui lui bloque
		// le chemin.
		// Un pion ne peut pas non plus aller en diagonale si la case est vide.
		game.movePiece("e6-e7");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("f7-f6");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e6-d7");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("f7-g6");
		assertTrue(game.compareBoard(resultbad));

		// Legal moves: diagonales, si une pièce est présente
		game.movePiece("e6-f7");
		assertTrue(game.compareBoard(resultWhite));
		game.movePiece("e7-f6");
		assertTrue(game.compareBoard(resultBlack));

	}

	// Test le pas de 2 du pion. Vérifie également la ligne de mire du pion.
	@Test
	public void testPawnBigStep() throws Exception {

		game.loadScript("scripts/pawnBigSteps");

		ChessBoard result = ChessBoard.readFromFile("boards/moves/after/pawnBigSteps");

		assertTrue(game.compareBoard(result));

	}

	// Test pour la ligne de mire diagonale
	@Test
	public void testDiagonalLineOfSight() throws Exception {

		game.loadBoard("boards/moves/bishopLOS");

		ChessBoard resultNear = ChessBoard.readFromFile("boards/moves/after/bishopLOSnear");
		ChessBoard resultMedium = ChessBoard.readFromFile("boards/moves/after/bishopLOSmedium");
		ChessBoard resultFar = ChessBoard.readFromFile("boards/moves/after/bishopLOSFar");
		ChessBoard resultbad = ChessBoard.readFromFile("boards/moves/bishopLOS");
		ChessBoard resultCapture = ChessBoard.readFromFile("boards/moves/bishopBasic");

		game.movePiece("e4-h6");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-b1");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-a8");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-g6");
		assertTrue(game.compareBoard(resultbad));

		game.movePiece("e4-d3");
		assertTrue(game.compareBoard(resultNear));
		game.movePiece("d3-e4");
		game.movePiece("e4-c6");
		assertTrue(game.compareBoard(resultMedium));
		game.movePiece("c6-e4");
		game.movePiece("e4-h1");
		assertTrue(game.compareBoard(resultFar));

		game.loadScript("scripts/bishopCaptures");
		assertTrue(game.compareBoard(resultCapture));

	}

	// Test pour la ligne de mire perpendiculaire
	@Test
	public void testPerpendicularLineOfSight() throws Exception {

		game.loadBoard("boards/moves/rookLOS");

		ChessBoard resultNear = ChessBoard.readFromFile("boards/moves/after/rookLOSnear");
		ChessBoard resultMedium = ChessBoard.readFromFile("boards/moves/after/rookLOSmedium");
		ChessBoard resultFar = ChessBoard.readFromFile("boards/moves/after/rookLOSFar");
		ChessBoard resultbad = ChessBoard.readFromFile("boards/moves/rookLOS");
		ChessBoard resultCapture = ChessBoard.readFromFile("boards/moves/rookBasic");

		game.movePiece("e4-e6");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-h4");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-a4");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-e1");
		assertTrue(game.compareBoard(resultbad));

		game.movePiece("e4-f4");
		dumpBoard(game);
		assertTrue(game.compareBoard(resultNear));
		game.movePiece("f4-c4");
		assertTrue(game.compareBoard(resultMedium));
		game.movePiece("c4-c8");
		assertTrue(game.compareBoard(resultFar));

		game.loadScript("scripts/rookCaptures");
		assertTrue(game.compareBoard(resultCapture));

	}

	// Test pour la ligne de mire combinée
	@Test
	public void testQueenLineOfSight() throws Exception {

		game.loadScript("scripts/queenCaptures");
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/queenBasic")));
	}

}
