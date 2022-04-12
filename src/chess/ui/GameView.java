package chess.ui;

import java.io.File;

import chess.game.ChessGame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/* 
 * NOTE: si Eclipse refuse de compiler avec "class blablabla is not API", 
 * alors il faut désactiver cette erreur comme ceci:
 * Windows -> Preferences -> Java -> Compiler -> Errors/Warnings -> 
 * Deprecated and restricted API -> Forbidden reference (access rules): -> change to warning
 */


public class GameView extends Application {

	private int gameSizeX = 1200;
	private int gameSizeY = 1000;
	private int boardPosX=200;
	private int boardPosY=100;

	private Scene scene;
	
	
	private FileChooser fileDialog;

	private Pane gamePane;
	
	private ChessGame game;

	@Override
	public void start(Stage stage) {

		fileDialog = new FileChooser();
		gamePane = new Pane();

		resetGame();

		Button resetButton = new Button("Reset");
		resetButton.setLayoutX(250);
		resetButton.setLayoutY(50);
		gamePane.getChildren().add(resetButton);

		resetButton.setOnAction(event -> resetGame());

		Button recordButton = new Button("Record moves");
		recordButton.setLayoutX(325);
		recordButton.setLayoutY(50);
		gamePane.getChildren().add(recordButton);
		recordButton.setOnAction(event -> {

			File file = getSaveFile("Record moves...","scripts/saves", stage);
			saveScript(file);
		});

		Button saveButton = new Button("Save board");
		saveButton.setLayoutX(425);
		saveButton.setLayoutY(50);
		gamePane.getChildren().add(saveButton);

		saveButton.setOnAction(event -> {

			File file = getSaveFile("Save Board...","boards/saves", stage);
			saveBoard(file);
		});

		CheckBox aiBox = new CheckBox("AI player");
		aiBox.setLayoutX(525);
		aiBox.setLayoutY(50);

		gamePane.getChildren().add(aiBox);

		Button loadButton = new Button("Load board");
		loadButton.setLayoutX(625);
		loadButton.setLayoutY(50);
		gamePane.getChildren().add(loadButton);

		loadButton.setOnAction(event -> {

			File file = getOpenFile("Open Board...","boards", stage);
			
			resetGame(file);
		});

		Button playButton = new Button("Play moves");
		playButton.setLayoutX(725);
		playButton.setLayoutY(50);
		gamePane.getChildren().add(playButton);

		playButton.setOnAction(event -> {
			File file = getOpenFile("Open Script...","scripts", stage);
			loadScript(file);
		});

		Button undoButton = new Button("Undo");
		undoButton.setLayoutX(825);
		undoButton.setLayoutY(50);
		gamePane.getChildren().add(undoButton);
		Button redoButton = new Button("Redo");
		redoButton.setLayoutX(900);
		redoButton.setLayoutY(50);
		gamePane.getChildren().add(redoButton);

		CheckBox editBox = new CheckBox("Edit board");
		editBox.setLayoutX(200);
		editBox.setLayoutY(950);

		gamePane.getChildren().add(editBox);

		Label blackCapture = new Label("Black captures");
		blackCapture.setLayoutX(50);
		blackCapture.setLayoutY(200);
		gamePane.getChildren().add(blackCapture);

		Label whiteCapture = new Label("White captures");
		whiteCapture.setLayoutX(1050);
		whiteCapture.setLayoutY(200);
		gamePane.getChildren().add(whiteCapture);

		scene = new Scene(gamePane, gameSizeX, gameSizeY);

		stage.setTitle("Super Mega Chess 3000");

		stage.setScene(scene);

		stage.show();
	}
	
	private void clearGame(){
		if (game != null) {
			gamePane.getChildren().remove(game.getUI());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void resetGame(){		
		resetGame(new File("boards/normalStart"));
	}

	private void resetGame(File file) {
		
		clearGame();
		game = new ChessGame(boardPosX,boardPosY);
		loadBoard(file);

		
		gamePane.getChildren().add(game.getUI());
		// Attention! Le board peut masquer les autres contr�les s'il n'est pas
		// placé complètement derrière eux.
		game.getUI().toBack();
	}

	private void loadBoard(File file){
		try {
			game.loadBoardFromFile(file);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Error reading file", ButtonType.OK);
			alert.showAndWait();
			return;
		}
	}

	private void saveBoard(File file){
		
		try{
			game.saveBoardToFile(file);
		}
		catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR, "Error writing file", ButtonType.OK);
			alert.showAndWait();
			return;
		}
	}
	
	// LaboX: Exercice 5
	public void saveScript(String fileName){
		saveScript(new File(fileName));
	}
	private void saveScript(File file){
		
	}
	
	

	// LaboX : Exercice 5
	public void loadScript(String fileName) {

		loadScript(new File(fileName));
	}

	private void loadScript(File file) {

		// Incomplet!

	}

	//Utilisé pour obtenir un dialogue d'ouverture
	private File getOpenFile(String title,String baseDir, Stage stage) {

		fileDialog.setTitle(title);
		fileDialog.setInitialDirectory(new File(System.getProperty("user.dir")+"/"+baseDir));
		return fileDialog.showOpenDialog(stage);
	}
	
	//Utilisé pour obtenir un dialogue de sauvegarde
	private File getSaveFile(String title,String baseDir, Stage stage) {

		fileDialog.setTitle(title);
		fileDialog.setInitialDirectory(new File(System.getProperty("user.dir")+"/"+baseDir));
		return fileDialog.showSaveDialog(stage);
	}

}
