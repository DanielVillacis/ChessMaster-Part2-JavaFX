package chess.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.game.ChessBoard;
import chess.game.ChessGame;

public class MovesTest {

	private ChessGame game;

	@Before
	public void setup() {
		game = new ChessGame();
	}

	@Test
	public void testBasicCollision() throws Exception {

		game.loadBoard("boards/normalStart");

		ChessBoard result = ChessBoard.readFromFile("boards/normalStart");
		//Move tower over a pawn of the same color
		game.movePiece("a1-a2");

		assertTrue(game.compareBoard(result));

	}
	
	@Test
	public void testPawnBasic() throws Exception {

		game.loadBoard("boards/moves/pawnBasic");

		ChessBoard resultgood = ChessBoard.readFromFile("boards/moves/after/pawnBasic");
		ChessBoard resultbad = ChessBoard.readFromFile("boards/moves/pawnBasic");
		
		//Illegal moves		
		game.movePiece("e5-e7");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e5-f5");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e5-f6");
		assertTrue(game.compareBoard(resultbad));
		
		
		//Legal move
		game.movePiece("e5-e6");
		assertTrue(game.compareBoard(resultgood));

	}
	
	@Test
	public void testBishopBasic() throws Exception {

		game.loadBoard("boards/moves/bishopBasic");

		
		ChessBoard resultbad = ChessBoard.readFromFile("boards/moves/bishopBasic");
		
		//Illegal moves		
		game.movePiece("e4-e5");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-e6");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-e3");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-d4");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-d1");
		assertTrue(game.compareBoard(resultbad));
		
		
		//Legal moves
		game.movePiece("e4-d5");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/bishopbasic1")));
		game.movePiece("d5-e4");
		
		game.movePiece("e4-h1");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/bishopbasic2")));
		game.movePiece("h1-e4");
		
		game.movePiece("e4-b1");	
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/bishopbasic3")));
		game.movePiece("b1-e4");
		
		game.movePiece("e4-g6");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/bishopbasic4")));
		game.movePiece("g6-e4");

	}
	
	@Test
	public void testKingBasic() throws Exception {

		game.loadBoard("boards/moves/kingBasic");

		
		ChessBoard resultbad = ChessBoard.readFromFile("boards/moves/kingBasic");
		
		//Illegal moves		
		game.movePiece("e4-e6");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-f7");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-b1");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-e2");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-g2");
		assertTrue(game.compareBoard(resultbad));
		
		
		//Legal moves
		game.movePiece("e4-d5");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/kingBasic1")));
		game.movePiece("d5-e4");
		
		game.movePiece("e4-e5");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/kingBasic2")));
		game.movePiece("e5-e4");
		
		game.movePiece("e4-f5");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/kingBasic3")));
		game.movePiece("f5-e4");
		
		game.movePiece("e4-f4");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/kingBasic4")));
		game.movePiece("f4-e4");
		
		
		game.movePiece("e4-f3");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/kingBasic5")));
		game.movePiece("f3-e4");
		
		game.movePiece("e4-e3");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/kingBasic6")));
		game.movePiece("e3-e4");
		
		game.movePiece("e4-d3");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/kingBasic7")));
		game.movePiece("d3-e4");
		
		game.movePiece("e4-d4");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/kingBasic8")));
		game.movePiece("d4-e4");

	}
	
	
	@Test
	public void testRookBasic() throws Exception {

		game.loadBoard("boards/moves/rookBasic");

		
		ChessBoard resultbad = ChessBoard.readFromFile("boards/moves/rookBasic");
		
		//Illegal moves		
		game.movePiece("e4-f5");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-d3");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-h1");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-b1");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-g6");
		assertTrue(game.compareBoard(resultbad));
		
		
		//Legal moves
		game.movePiece("e4-d4");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/rookbasic1")));
		game.movePiece("d4-e4");
		
		game.movePiece("e4-e8");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/rookbasic2")));
		game.movePiece("e8-e4");
		
		game.movePiece("e4-h4");	
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/rookbasic3")));
		game.movePiece("h4-e4");
		
		game.movePiece("e4-e2");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/rookbasic4")));
		game.movePiece("e2-e4");

	}
	
	
	@Test
	public void testKnightBasic() throws Exception {

		game.loadBoard("boards/moves/knightBasic");

		
		ChessBoard resultbad = ChessBoard.readFromFile("boards/moves/knightBasic");
		
		//Illegal moves		
		game.movePiece("e4-e8");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-e3");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-b1");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-e6");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-d3");
		assertTrue(game.compareBoard(resultbad));
		
		
		//Legal moves
		game.movePiece("e4-d6");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/knightbasic1")));
		game.movePiece("d6-e4");
		
		game.movePiece("e4-c5");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/knightbasic2")));
		game.movePiece("c5-e4");
		
		game.movePiece("e4-c3");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/knightbasic3")));
		game.movePiece("c3-e4");
		
		game.movePiece("e4-d2");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/knightbasic4")));
		game.movePiece("d2-e4");
		
		
		game.movePiece("e4-f2");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/knightbasic5")));
		game.movePiece("f2-e4");
		
		game.movePiece("e4-g3");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/knightbasic6")));
		game.movePiece("g3-e4");
		
		game.movePiece("e4-g5");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/knightbasic7")));
		game.movePiece("g5-e4");
		
		game.movePiece("e4-f6");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/knightbasic8")));
		game.movePiece("f6-e4");

	}
	
	@Test
	public void testQueenBasic() throws Exception {

		game.loadBoard("boards/moves/queenBasic");

		
		ChessBoard resultbad = ChessBoard.readFromFile("boards/moves/queenBasic");
		
		//Illegal moves		
		game.movePiece("e4-f2");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-g5");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-d1");
		assertTrue(game.compareBoard(resultbad));
		game.movePiece("e4-g7");
		assertTrue(game.compareBoard(resultbad));
		
		
		
		//Legal moves
		game.movePiece("e4-a8");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/queenbasic1")));
		game.movePiece("a8-e4");
		
		game.movePiece("e4-a4");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/queenbasic2")));
		game.movePiece("a4-e4");
		
		game.movePiece("e4-e3");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/queenbasic3")));
		game.movePiece("e3-e4");
		
		game.movePiece("e4-f3");		
		assertTrue(game.compareBoard(ChessBoard.readFromFile("boards/moves/after/queenbasic4")));
		game.movePiece("f3-e4");
		

	}
	

}
