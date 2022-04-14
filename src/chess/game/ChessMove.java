package chess.game;

import java.awt.Point;
import java.io.FileWriter;
import java.util.Scanner;

public class ChessMove {
	
	// Position de depart et position d'arrivee.
	private Point initPos;
	private Point endPos;
	private BoardMemento boardMem;


	// Constructeur.
	public ChessMove(Point depart, Point arrivee) {
		initPos = depart;
		endPos = arrivee;
	}
	
	public ChessMove(String string, BoardMemento m) {
		boardMem = m;
		initPos = ChessUtils.convertAlgebraicPosition(string.split("-")[0]);
		endPos = ChessUtils.convertAlgebraicPosition(string.split("-")[1]);
	}
	
	// Nouveau constructeur pour la methode movePiece() de ChessGame.
	public ChessMove(String move) {
		String startMove = move.substring(0,2);
		String endMove = move.substring(3,5);
		
		// TODO Auto-generated constructor stub
		initPos = ChessUtils.convertAlgebraicPosition(startMove);
		endPos = ChessUtils.convertAlgebraicPosition(endMove);	
	}

	// Accesseur des positions pour les methodes move() de ChessBoard.
	public Point getInitPos() {
		return initPos;
	}
	
	public Point getEndPos() {
		return endPos;
	}
	
	// Ajout des methodes saveToStream et loadFromStream.
	public void saveToStream(FileWriter writer) {
		try {
			writer.append(ChessUtils.makeAlgebraicPosition(initPos.x, initPos.y)).append("-").append(ChessUtils.makeAlgebraicPosition(endPos.x, endPos.y)).append("\n");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ChessMove loadFromStream(Scanner s, BoardMemento m) {
		return new ChessMove(s.next(), m);
	}


}
