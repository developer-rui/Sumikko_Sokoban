/*

        compiler & exécuter:
javac --module-path "/Library/Java/JavaVirtualMachines/javafx-sdk-17.0.1/lib" --add-modules=javafx.controls *.java && java --module-path "/Library/Java/JavaVirtualMachines/javafx-sdk-17.0.1/lib"  --add-modules=javafx.controls SokobanGame




		Compiler:
javac --module-path "C:\Program Files\Java Fx\lib" --add-modules=javafx.controls  NomFichierCode.java
  
		Executer:
java --module-path "C:\Program Files\Java Fx\lib"  --add-modules=javafx.controls  NomFichierCode

        créer un jar:
jar cfve  jarFile.jar   NomClasseContenantLaMethodeMain   *

        exécuter depuis jar:
java -jar --module-path "C:\Program Files\Java Fx\lib" --add-modules=javafx.controls NomJar.jar

        extraire un jar:
jar xf jar-file.jar

*/



import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;

import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import javafx.scene.image.*;//ImageView

//Pour récupérer la taille de l'écran...
import javafx.stage.Screen;		
import javafx.geometry.Rectangle2D;

public class SokobanGame extends Application{	
	
	public static void main(String[] args){
		SokobanGame.launch(args);
	}
	
	public void addGame(CanvasWindow window, int[][] levelMap, int level, String background_path){

		Sokoban sokoban = new Sokoban(window, levelMap);

		sokoban.bindImage("1", "images/sokoban_space.png");
		sokoban.bindImage("2", "images/sokoban_destination.png");
		sokoban.bindImage("3", "images/sokoban_wall.png");
		sokoban.bindImage("4", "images/sokoban_character.png");
		sokoban.bindImage("5", "images/sokoban_box.png");
		sokoban.bindImage("6", "images/sokoban_goodBox.png");

		sokoban.setBackground(background_path);	
		
		if(level < 10){
			sokoban.setNextGameName("level_" + (level + 1));
		}else{
			sokoban.setNextGameName("level_select");
		}		

		GameFlow.addGame("level_" + level, sokoban);
	}
	
	public void start(Stage primaryStage) throws Exception{		
				
	// --- --- --- Fenêtre et canvas du jeu --- --- --- 
		
		// Porportion relative de la largeur de la fenêtre par rapport à la hauteur
		double largeurFenetre = 4;
		double hauteurFenetre = 3;
		
		// % de la hauteur de la fenêtre par rapport à la taille verticale de l'écran
		double proportionVerticale = 90;  
		
		CanvasWindow window = new CanvasWindow(primaryStage, largeurFenetre, hauteurFenetre, proportionVerticale);		
		window.setTitle("Sumikko Sokoban");		
		window.setIcon("images/sokoban_box.png");
		
		
	// --- --- --- Les niveaux --- --- ---         	
		
		//0 = vide    1 = espace    2 = point    3 = mur    4 = personnage    5 = box
		
		
		int [][] levelMap1 = 
		  { {0, 0, 0, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 3, 1, 1, 3, 3, 3, 3, 3},
			{0, 0, 0, 0, 3, 3, 1, 1, 1, 1, 1, 1, 3},
			{3, 3, 3, 3, 3, 1, 1, 1, 3, 3, 1, 1, 3},
			{3, 1, 2, 2, 3, 1, 3, 1, 3, 3, 1, 3, 3},
			{3, 1, 1, 1, 4, 1, 5, 1, 1, 3, 5, 3, 0},//
			{3, 3, 1, 1, 3, 1, 3, 1, 1, 1, 1, 3, 0},
			{0, 3, 3, 3, 3, 1, 1, 1, 3, 3, 5, 3, 3},
			{0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 1, 1, 3},
			{0, 0, 0, 0, 3, 2, 1, 1, 1, 1, 1, 1, 3},
			{0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3} };			  
		this.addGame(window, levelMap1, 1, "images/background1.png"); 


		int [][] levelMap2 = 
		  { {0, 0, 0, 3, 3, 3, 3, 3, 3, 3},
			{0, 0, 3, 3, 1, 1, 3, 1, 1, 3},
			{0, 0, 3, 1, 1, 1, 3, 1, 1, 3},
			{0, 0, 3, 5, 1, 5, 1, 5, 4, 3},
			{0, 0, 3, 1, 5, 3, 3, 1, 1, 3},
			{3, 3, 3, 1, 5, 1, 3, 1, 3, 3},
			{3, 2, 2, 2, 2, 2, 1, 1, 3, 0},
			{3, 3, 3, 3, 3, 3, 3, 3, 3, 0} };					  
		this.addGame(window, levelMap2, 2, "images/background1.png"); 


		int [][] levelMap3 = 
		{ {0, 0, 3, 3, 3, 3, 3, 3, 3, 0},
		  {0, 0, 3, 1, 1, 1, 1, 1, 3, 0},
		  {0, 0, 3, 1, 5, 1, 4, 1, 3, 0},
		  {3, 3, 3, 3, 3, 1, 3, 1, 3, 0},
		  {3, 1, 5, 1, 1, 1, 1, 1, 3, 0},
		  {3, 1, 1, 3, 5, 3, 3, 1, 3, 3},
		  {3, 2, 2, 5, 1, 1, 3, 1, 1, 3},
		  {3, 2, 2, 1, 1, 1, 1, 1, 1, 3},
		  {3, 3, 3, 3, 3, 3, 3, 3, 3, 3} };					  
	    this.addGame(window, levelMap3, 3, "images/background1.png");


		int [][] levelMap4 = 
		{ {0, 3, 3, 3, 3, 3, 0},
		  {0, 3, 1, 1, 1, 3, 3},
		  {0, 3, 2, 3, 5, 1, 3},
		  {0, 3, 1, 1, 1, 4, 3},
		  {0, 3, 2, 3, 5, 1, 3},
		  {0, 3, 1, 1, 1, 3, 3},		  
		  {0, 3, 2, 3, 5, 3, 0},
		  {3, 3, 1, 1, 1, 3, 0},
		  {3, 1, 2, 3, 5, 3, 0},
		  {3, 1, 1, 1, 1, 3, 0},
		  {3, 1, 1, 3, 3, 3, 0},
		  {3, 3, 3, 3, 0, 0, 0} };					  
	    this.addGame(window, levelMap4, 4, "images/background1.png");		

	
		int[][] levelMap5 = 
		  { {0, 0, 3, 3, 0, 0, 0, 3, 3, 0, 0},
			{0, 3, 3, 3, 3, 0, 3, 3, 3, 3, 0},
			{3, 3, 1, 1, 1, 3, 1, 1, 1, 3, 3},
			{3, 1, 2, 5, 1, 4, 1, 5, 2, 1, 3},
			{3, 1, 5, 2, 3, 3, 3, 2, 5, 1, 3},
			{3, 3, 2, 5, 1, 3, 1, 5, 2, 1, 3},
			{3, 3, 1, 1, 5, 2, 5, 1, 1, 3, 3},
			{0, 3, 3, 3, 2, 5, 2, 1, 3, 3, 0},
			{0, 0, 3, 3, 3, 1, 3, 3, 3, 0, 0},
			{0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0} };
		this.addGame(window, levelMap5, 5, "images/background1.png");


		int [][] levelMap6 = 
		  { {3, 3, 3, 3, 3, 3, 3, 3},
			{3, 1, 1, 1, 1, 1, 1, 3},
			{3, 1, 3, 3, 3, 2, 3, 3},
			{3, 1, 5, 1, 2, 5, 2, 3},
			{3, 1, 3, 4, 5, 2, 1, 3},
			{3, 1, 3, 5, 2, 5, 2, 3},
			{3, 1, 1, 5, 1, 2, 1, 3},
			{3, 1, 3, 5, 3, 3, 3, 3},
			{3, 1, 1, 1, 1, 1, 1, 3},			
			{3, 3, 3, 3, 3, 3, 3, 3} };				  
		this.addGame(window, levelMap6, 6, "images/background1.png"); 


		int [][] levelMap7 = 
		  { {0, 0, 0, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 3, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 3, 5, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 3, 3, 3, 1, 1, 5, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 3, 1, 1, 5, 1, 5, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{3, 3, 3, 1, 3, 1, 3, 3, 1, 3, 0, 0, 0, 3, 3, 3, 3, 3, 3},
			{3, 1, 1, 1, 3, 1, 3, 3, 1, 3, 3, 3, 3, 3, 1, 1, 2, 2, 3},
			{3, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3},
			{3, 3, 3, 3, 3, 1, 3, 3, 3, 1, 3, 4, 3, 3, 1, 1, 2, 2, 3},	
			{0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3},
		    {0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0} };
		this.addGame(window, levelMap7, 7, "images/background1.png"); 
	

		int [][] levelMap8 = 
		  { {0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0},
			{0, 0, 0, 3, 1, 1, 3, 1, 1, 1, 1, 1, 3, 0},
			{0, 0, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0},
			{0, 3, 3, 1, 1, 3, 3, 3, 1, 3, 1, 5, 3, 3},
			{0, 3, 1, 2, 2, 2, 1, 3, 1, 5, 1, 5, 1, 3},
			{0, 3, 1, 2, 3, 2, 1, 3, 3, 5, 1, 5, 1, 3},
			{3, 3, 1, 2, 2, 2, 1, 3, 1, 1, 1, 5, 1, 3},
			{3, 1, 1, 1, 1, 1, 1, 3, 1, 5, 4, 5, 1, 3},
			{3, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 3},			
			{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3} };				  
		this.addGame(window, levelMap8, 8, "images/background1.png");


		int [][] levelMap9 = 		
		{
			{0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0},
			{0, 0, 0, 3, 3, 3, 1, 2, 1, 3, 3, 3, 0, 0, 0},
	  		{0, 0, 0, 3, 2, 1, 1, 5, 1, 1, 2, 3, 0, 0, 0},
			{0, 0, 0, 3, 3, 1, 5, 1, 5, 1, 3, 3, 0, 0, 0},
			{0, 0, 0, 0, 3, 3, 3, 1, 3, 3, 3, 0, 0, 0, 0},
			{0, 0, 0, 0, 3, 1, 1, 4, 1, 1, 3, 0, 0, 0, 0},
			{0, 0, 0, 0, 3, 2, 5, 1, 5, 2, 3, 0, 0, 0, 0},
			{0, 0, 0, 3, 3, 5, 3, 5, 3, 5, 3, 3, 0, 0, 0},
			{0, 0, 3, 3, 1, 2, 2, 5, 2, 2, 1, 3, 3, 0, 0},
			{0, 3, 3, 1, 5, 1, 1, 5, 1, 1, 5, 1, 3, 3, 0},
			{3, 3, 3, 2, 1, 1, 2, 5, 2, 1, 1, 2, 3, 3, 3},
			{0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0}
		};		  
	  this.addGame(window, levelMap9, 9, "images/background1.png");


		int [][] levelMap10 = 
		  { {0, 0, 0, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 3, 1, 1, 1, 3, 3, 3, 3, 3, 0, 0, 0, 0},
			{0, 0, 0, 0, 3, 1, 3, 1, 3, 1, 1, 1, 3, 0, 0, 0, 0},
			{0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 3, 1, 3, 0, 0, 0, 0},
			{0, 3, 3, 3, 3, 3, 2, 3, 1, 2, 2, 2, 3, 3, 3, 3, 3},
			{0, 3, 1, 1, 2, 5, 5, 1, 3, 3, 3, 5, 3, 1, 1, 1, 3},
			{0, 3, 1, 3, 2, 3, 1, 1, 1, 1, 1, 5, 2, 1, 3, 1, 3},
			{0, 3, 1, 1, 2, 3, 1, 5, 5, 5, 1, 1, 3, 1, 1, 1, 3},
			{3, 3, 3, 1, 1, 3, 1, 5, 4, 5, 1, 3, 1, 1, 3, 3, 3},	
			{3, 1, 1, 1, 3, 1, 1, 5, 5, 5, 1, 3, 2, 1, 1, 3, 0},
		    {3, 1, 3, 1, 2, 5, 1, 1, 1, 1, 1, 3, 2, 3, 1, 3, 0},
			{3, 1, 1, 1, 3, 5, 3, 3, 3, 1, 5, 5, 2, 1, 1, 3, 0},
			{3, 3, 3, 3, 3, 2, 2, 2, 1, 3, 2, 3, 3, 3, 3, 3, 0},
			{0, 0, 0, 0, 3, 1, 3, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0},
			{0, 0, 0, 0, 3, 1, 1, 1, 3, 1, 3, 1, 3, 0, 0, 0, 0},
			{0, 0, 0, 0, 3, 3, 3, 3, 3, 1, 1, 1, 3, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0}
		 };
		this.addGame(window, levelMap10, 10, "images/background1.png"); 



    // --- --- --- Menu principal de sélection de niveaux --- --- ---    

		LevelSelect levelSelect = new LevelSelect(window);
        GameFlow.addGame("level_select", levelSelect);

        GameFlow.setCurrentGame("level_select");       
        
    
		
	// --- --- --- Acheminement des évènements d'interaction avec l'utilisateur --- --- --- 
		
		window.getScene().setOnKeyPressed(e -> {	
			GameFlow.keyPress(InputConverter.key(e));
		});
		
		window.getScene().setOnKeyReleased(e -> {	
			GameFlow.keyRelease(InputConverter.key(e));			
		});
		
		window.getScene().setOnMouseClicked(e -> {	
			GameFlow.mouseClick(InputConverter.mouseX(e), InputConverter.mouseY(e));			
		});
		
		
	// --- --- --- Boucle d'animation --- --- --- 
		
		AnimationTimer timer = new AnimationTimer(){		
			@Override			
			public void handle(long now){				
				GameFlow.play(now);					
			}
		};
		timer.start();	
		
	}
	
}
