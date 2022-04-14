package chess.game;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Stack;

import chess.ui.BoardView;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public class ChessBoard {

	// Grille de jeu 8x8 cases. Contient des r�f�rences aux pi�ces pr�sentes sur
	// la grille.
	// Lorsqu'une case est vide, elle contient une pi�ce sp�ciale
	// (type=ChessPiece.NONE, color=ChessPiece.COLORLESS).
	private ChessPiece[][] grid;	
	private BoardView view;
	private BoardMemento initialState;
	private Stack<ChessMove> oldMoves; // Pile des anciens mouvements.
	private Stack<ChessMove> recentMoves; // Pile des mouvements recents.
	
	

	public ChessBoard(int x, int y) {
		//view = new BoardView(x, y);
		initBoard();
		initialState = createMemento();
		view = new BoardView(x, y);
		oldMoves = new Stack<ChessMove>();
	}
	
	
	public void initBoard() {
		// Initialise la grille avec des pi�ces vides.
		grid = new ChessPiece[8][8];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new ChessPiece(i, j, this);
			}
		}
	}

	// Place une pi�ce vide dans la case
	public void clearSquare(int x, int y) {
		grid[x][y] = new ChessPiece(x, y, this);
	}

	// Place une pi�ce sur le planche de jeu.
	public void putPiece(ChessPiece piece) {

		Point2D pos = view.gridToPane(piece.getGridX(), piece.getGridY());
		piece.moveUI(pos);
		piece.getUI().relocate(pos.getX(), pos.getY());
		view.getPane().getChildren().add(piece.getUI());
		grid[piece.getGridX()][piece.getGridY()] = piece;
	}

	// V�rifie si la case contient une pi�ce vide
	public boolean isEmpty(Point pos) {
		return (grid[pos.x][pos.y].getType() == ChessUtils.TYPE_NONE);
	}

	// V�rifie si une coordonn�e est valide sur la planche de jeu
	public boolean isValid(Point pos) {
		return (pos.x >= 0 && pos.x <= 7 && pos.y >= 0 && pos.y <= 7);
	}

	// V�rifie si deux pi�ces sont de la m�me couleur
	public boolean isSameColor(Point pos1, Point pos2) {
		return grid[pos1.x][pos1.y].getColor() == grid[pos2.x][pos2.y].getColor();
	}

	// D�place une pi�ce. Appel� par l'interface graphique lorsqu'un d�placement est
	// d�tect�.
	public boolean move(Point2D pixelPos, Point2D newPixelPos) {

		Point gridPos = view.paneToGrid(pixelPos);
		// Position finale.
		Point endGridPos = view.paneToGrid(newPixelPos);
		
		// Creation du ChessMove.
		ChessMove cMove = new ChessMove(gridPos, endGridPos);

		ChessPiece toMove = grid[gridPos.x][gridPos.y];

		boolean result = move(cMove);

		toMove.moveUI(view.gridToPane(toMove.getGridPos()));

		return result;
	}

	// D�place une pi�ce sur la grille. V�rifie les r�gles de d�placement.
	public boolean move(ChessMove move) {

		ChessPiece toMove = getPiece(move.getInitPos());

		if (toMove.isNone()) {
			return false;
		}

		if (!isValid(move.getEndPos())) {
			return false;
		}

		if (isSameColor(move.getInitPos(), move.getEndPos())) {
			return false;
		}

		if (!toMove.verifyMove(move.getInitPos(), move.getEndPos())) {
			return false;
		}

		if (!isEmpty(move.getEndPos())) {

			// Capture!
			removePiece(move.getEndPos());
		}
		assignSquare(move.getEndPos(), toMove);
		clearSquare(move.getInitPos());

		oldMoves.add(move); // Sauvegarde de l'objet dans la pile des movements.
		return true;
	}

	// Lecture et ex�cution d'une suite de mouvements 
	public void loadMovesFromFile(File file) throws Exception {
		// Non implant�!!
		Scanner readFile = new Scanner(new FileReader(file));
		while (readFile.hasNext()) {
			// Pas clair pour loadMoves rip question 5.d ... nouveau constructeur?
			break;
		}
		
	}

	// Lecture d'un ChessBoard � partir d'un fichier. Utilis� par les tests.
	public static ChessBoard readFromFile(String fileName) throws Exception {
		return readFromFile(new File(fileName), 0, 0);
	}
	
	// Lecture d'un ChessBoard � partir d'un fichier
	public static ChessBoard readFromFile(File file, int x, int y) throws Exception {

		ChessBoard board = new ChessBoard(x, y);

		Scanner reader = new Scanner(new FileReader(file));

		while (true) {
			ChessPiece piece;
			try {
				piece = ChessPiece.readFromStream(reader, board);
			} catch (Exception e) {
				break;
			}
			board.putPiece(piece);
		}
		reader.close();
		return board;
	}

	//Sauvegarde dans un fichier.
	public void saveToFile(File file) throws Exception {

		FileWriter writer = new FileWriter(file);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				ChessPiece toWrite = grid[i][j];
				if (!toWrite.isNone()) {
					toWrite.saveToStream(writer);
					initialState.saveToFile(writer);
				}
			}
		}
		//S�parateur. N�cessaire pour la lecture de scripts.
		writer.write("</BOARD>\n");
		writer.close();
	}

	@Override
	public boolean equals(Object other) {

		if (other instanceof ChessBoard) {
			ChessBoard otherBoard = (ChessBoard) other;

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (!grid[i][j].equals(otherBoard.grid[i][j])) {
						return false;
					}
				}
			}
			return true;
		}

		return false;

	}

	public void removePiece(Point pos) {

		view.getPane().getChildren().remove(grid[pos.x][pos.y].getUI());
		clearSquare(pos);
	}

	public void clearSquare(Point pos) {
		grid[pos.x][pos.y] = new ChessPiece(pos.x, pos.y, this);
	}

	public void assignSquare(Point pos, ChessPiece piece) {
		piece.setGridPos(pos);
		grid[pos.x][pos.y] = piece;
	}

	public ChessPiece getPiece(Point pos) {
		return grid[pos.x][pos.y];
	}

	public Node getUI() {

		return view.getPane();
	}
	
	public ChessPiece[][] getGrid() {
		return grid;
	}
	
	
	// Creation des methodes memento.
	public BoardMemento createMemento() {
		return new BoardMemento(this);
	}
	
	public ChessBoard restoreMemento(BoardMemento boardMem) {
		// Vide l'ancien tableau et le restore
		for (ChessPiece[] pieces : grid) {
            for (ChessPiece piece : pieces) {
                if (piece != null)
                    removePiece(new Point(piece.getGridX(), piece.getGridY()));
            }
        }
		// Restore le tableau
		for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
            	Point p = new Point(i,j);
				grid[i][j] = boardMem.getPieces(p);     
            }
		}
		return (this);
	}


	public static void redoMove(File file) {
		// TODO Auto-generated method stub
		// Un peu comme le labo 7 (double stack)
		//
		// if(recentMoves.size() > 0) {			
		//  	ChessMoves temp = recentMoves.pop();			
		//  	temp.redoMove();		
		//  	oldMoves.push(temp);
		// }
		
	}

	public static void undoMove(File file) {
		// TODO Auto-generated method stub
		// Un peu comme le labo 7 (double stack)
		//
		// if(oldMoves.size() > 0) {
		// 		ChessMoves temp = oldMoves.pop();
		// 		temp.undoMove();
		// 		recentMoves.push(temp);
		// }
	}	
	

	
	
}
