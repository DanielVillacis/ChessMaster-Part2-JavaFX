package chess.game;

import java.awt.Point;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardMemento {
	
	// Est une liste de PieceMemento
	private ChessPiece[][] grid;
	private ArrayList<PieceMemento> listePiece = new ArrayList<PieceMemento> ();
	
	
	// Les methodes readFromFile et saveToFile de ChessBoard sont 
	// maintenant dans BoardMement.
	
	// Constructeur.
	public BoardMemento(ChessBoard b) {
		grid = b.getGrid();
		for(int i=0; i<grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				PieceMemento piece = new PieceMemento(grid[i][j]);
				listePiece.add(piece);
			}
		}
	}
	
	// Lecture d'un ChessBoard � partir d'un fichier
	public static ChessBoard readFromFile(ChessBoard board, Scanner reader ) throws Exception {
		while (reader.hasNext()) {
			ChessPiece piece;
			try {
				piece = PieceMemento.readFromStream(reader, board);
			} catch (Exception e) {
				break;
			}
			board.putPiece(piece);
		}
		return board;
	}

	//Sauvegarde dans un fichier.
	public void saveToFile(FileWriter writer) throws Exception {

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				PieceMemento pieceMem = new PieceMemento(grid[i][j]);
				if (!pieceMem.isNone()) {
					pieceMem.saveToStream(writer);
				}
			}
		}
	}
	
	// Accesseur pour la methode restoreMemento().
	public ChessPiece getPieces(Point p) {
		return grid[p.x][p.y];
	}
	
	
	
	

}
