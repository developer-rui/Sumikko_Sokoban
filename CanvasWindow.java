/*

		Compiler:
javac --module-path "C:\Program Files\Java Fx\lib" --add-modules=javafx.controls  NomFichierCode.java
  
		Executer:
java --module-path "C:\Program Files\Java Fx\lib"  --add-modules=javafx.controls  NomFichierCode

*/
import java.lang.Math; 

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.canvas.*;
import javafx.scene.effect.*;  //blendmode
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;



public class CanvasWindow{
	
	
	//Les objets graphiques
	private Stage window;
	private Scene scene;
	private Canvas canvas;
	private GraphicsContext context;
	
	
	//dimensions de l'écran de l'ordinateur
	private double screen_w;	
	private double screen_h;
	
	
	//positions + dimensions de notre fenêtre
	private double width;	
	private double height;
	private double left;
	private double top;	
	
		
			
			
	// --- Constructeurs ---
	
	public CanvasWindow(Stage fenetre){	//fullscreen
		
		this.findScreenDim();
		
		//dimension et position: écran complet
		this.width = this.screen_w;
		this.height = this.screen_h;		
		
		//Créer et initialiser la fenêtre
		this.init(fenetre);
		
	}
	
	public CanvasWindow(Stage fenetre, double largeur, double hauteur){	 // Dimensions absolues
		
		//Récupérer les dimensions de l'écran
		this.findScreenDim();
		
		//dimensions de la fenêtre set manuellement, en évitant les dépassements	
		if (largeur < this.screen_w){
			this.width = largeur;
		}else{
			this.width = this.screen_w;	
		}
		
		if (hauteur < this.screen_h){
			this.height = hauteur;
		}else{
			this.height = this.screen_h;	
		}		
		
		//Créer et initialiser la fenêtre
		this.init(fenetre);
		
	}
	
	public CanvasWindow(Stage fenetre, double largeur, double hauteur, boolean proportion){	 // Dimensions en % écran
		
		//Récupérer dimensions écran
		this.findScreenDim();
		
		//dimensions de la fenêtre en % relative par rapport à l'écran		
		this.width = largeur / 100 * this.screen_w;
		this.height = hauteur / 100 * this.screen_h;		
		
		//Créer et initialiser la fenêtre
		this.init(fenetre);
		
	}
	
	public CanvasWindow(Stage fenetre, double largeur, double hauteur, double hProportion){ // Proportion conservée, ratio hauteur %
		
		//Récupérer dimensions écran
		this.findScreenDim();
		
		//dimensions de la fenêtre : ratio largeur / hauteur conservée, avec hauteur = % par rapport à la hauteur d'écran	
		this.height = hProportion / 100 * this.screen_h;
		this.width = largeur / hauteur * this.height;		
		
		//Créer et initialiser la fenêtre
		this.init(fenetre);
		
	}
	
	
	// initialisateur: set up la fenêtre (position, afficher), créer la scène, pane et canvas et context
	private void init(Stage fenetre){	
		
		//position fenêtre:  
		this.left = (this.screen_w - this.width) / 2;	//horizontal: centrée
		this.top = (this.screen_h - this.height) / 6;	//verticale: plus proche du bord haut
		
		//Fenêtre principal
		this.window = fenetre;		
		this.window.setResizable(false);
		this.window.setX(this.left);
		this.window.setY(this.top);
		this.window.show();
		
		//conteneur à la racine
		Pane root = new Pane();							
		
		//scène
		this.scene = new Scene(root, this.width, this.height); 
		this.window.setScene(this.scene);		
		
		//canvas et contexte graphique	
		this.canvas = new Canvas(this.width, this.height);	 
		root.getChildren().add(this.canvas);		
		this.context = this.canvas.getGraphicsContext2D();	

		
		//this.context.setGlobalBlendMode(BlendMode.SCREEN); ///
		
	}
	
	private void findScreenDim(){
		
		//Récupérer et stocker les dimensions de l'écran de l'ordinateur
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();	
			
		this.screen_w = primaryScreenBounds.getWidth();
		this.screen_h = primaryScreenBounds.getHeight();
		
	}
	
	
	
	// --- getters ---
	
	public Stage getWindow(){
		return this.window;
	}
	
	public Scene getScene(){
		return this.scene;
	}
	
	public Canvas getCanvas(){
		return this.canvas;
	}
	
	public GraphicsContext getContext(){
		return this.context;
	}
	
	
	public double getScreenW(){
		return this.screen_w;
	}
	
	public double getScreenH(){
		return this.screen_h;
	}
	
		
	public double getWidth(){
		return this.width;
	}
	
	public double getHeight(){
		return this.height;
	}
	
	public double getLeft(){
		return this.left;
	}
	
	public double getTop(){
		return this.top;
	}
	
	
	
	// --- raccourcis... ---
	
	public void setTitle(String titre){
		this.window.setTitle(titre);
	}	
	
	public void setIcon(Image img){
		this.window.getIcons().add(img);
	}
	
	public void setIcon(String imgPath){
		this.window.getIcons().add(new Image(imgPath));
	}
	
	
	public void setLineWidth(double width){
		this.context.setLineWidth(width);
	}
	
	public void setFontSize(double size){ //nom diff
		this.context.setFont(new Font(size));
	}
	
	public void setFill(int r, int g, int b){
		this.context.setFill(Color.rgb(r, g, b));
	}
	
	public void setFill(LinearGradient gradient){
		this.context.setFill(gradient);
	}
    
    public void setFill(RadialGradient gradient){
		this.context.setFill(gradient);
	}
	
	public void setStroke(int r, int g, int b){
		this.context.setStroke(Color.rgb(r, g, b));
	}
	
	
	
	public void strokeLine(double x1, double y1, double x2, double y2){
		this.context.strokeLine(x1, y1, x2, y2);
	}
	
	public void strokeRect(double l, double t, double w, double h){
		this.context.strokeRect(l, t, w, h);
	}
	
	public void strokeRect(double l, double t, double w, double h, int r, int g, int b){
		this.setStroke(r, g, b);
		this.context.strokeRect(l, t, w, h);
	}
	
	public void strokeOval(double l, double t, double w, double h){
		this.context.strokeOval(l, t, w, h);
	}	
	
	
	public void fillRect(double l, double t, double w, double h){
		this.context.fillRect(l, t, w, h);
	}
	
	public void fillRect(double l, double t, double w, double h, int r, int g, int b){
		this.setFill(r, g, b);
		this.context.fillRect(l, t, w, h);
	}
	
	public void fillOval(double l, double t, double w, double h){
		this.context.fillOval(l, t, w, h);
	}
	
	public void fillOval(double l, double t, double w, double h, int r, int g, int b){
		this.setFill(r, g, b);
		this.context.fillOval(l, t, w, h);
	}
	
	public void fillRoundRect(double l, double t, double w, double h, double r1, double r2){
		this.context.fillRoundRect(l, t, w, h, r1, r2);
	}
	
	
	public void drawImage(Image img, double x, double y){
		this.context.drawImage(img, x, y);
	}
	
	public void drawImage(String imgPath, double x, double y){
		this.context.drawImage(new Image(imgPath), x, y);
	}
		
	
	
	public void clear(){
		context.clearRect(0, 0, this.width, this.height);
	}
	
	public void show(){
		this.window.show();
	}
	
	
}
