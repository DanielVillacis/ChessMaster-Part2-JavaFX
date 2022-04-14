package chess.game;

import java.io.Writer;
import java.util.Scanner;

public class PieceMemento {
	
	// Conserve le type de piece, la couleur et la position. (Creer des accesseurs pour les garder privees?)
	public int gridPosX;
	public int gridPosY;

	public int type;
	public int color;
	
	// Constructeur.
	public PieceMemento(ChessPiece piece) {
		gridPosX = piece.getGridX();
		gridPosY = piece.getGridY();
		type = piece.getType();
		color = piece.getColor();
	}

	
	
	// Methodes de ChessPiece maintenant dans PieceMemento.
	public static ChessPiece readFromStream(Scanner reader, ChessBoard b) {

		String pieceDescription = reader.next();

		if (pieceDescription.length() != 5) {
			throw new IllegalArgumentException("Badly formed Chess Piece description: " + pieceDescription);
		}

		return new ChessPiece(pieceDescription.substring(3, 5), pieceDescription.substring(0, 2), b);

	}

	
	public void saveToStream(Writer writer) throws Exception {

		writer.append(ChessUtils.makeAlgebraicPosition(gridPosX, gridPosY));
		writer.append('-');
		writer.append(ChessUtils.makePieceName(color, type));
		writer.append('\n');
	}
	
	
	public boolean isNone() {
		return type == ChessUtils.TYPE_NONE;
	}
	
	
	

}
