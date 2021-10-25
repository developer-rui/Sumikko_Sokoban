import java.util.*;

import javafx.scene.image.Image;

public abstract class Game{
	
	//L'objet CanvasWindow où on fait tout les affichages...
	protected CanvasWindow window;	
	//Dimensions de la fenêtre
	protected double windowWidth;
	protected double windowHeight;
		
	
	// Gestion des animations		
	protected double prec;
	protected double now;
	protected double intervalle;	
	// Gestion de commencement du jeu
	protected boolean start;	
	// Gestion de pause
	protected boolean pause;
	// Gestion du rafraîchissement d'écran
	protected boolean needUpdate;
	
	
	//Les images
	protected HashMap<String, Image> images;
	
	// Gestion des mots secrets	
	protected LinkedList<String> keyInputHistory;
	
	
	// --- Constructeurs et initialisateur ---
	
	public Game(CanvasWindow window){ // *** il faut utiliser super(window) au début des constructeurs enfant
	
		this.window = window;
		
		this.windowWidth = window.getWidth();
		this.windowHeight = window.getHeight();
		
		this.images = new HashMap<String, Image>();		
		
		this.keyInputHistory = new LinkedList<String>();
		
		//Au cas où on n'appelle pas reset() dans constructeur enfant 
		//Et on ne peut pas utiliser reset() ici car possibilité de conflit d'ordre...
		this.start = true;		
		this.prec = 0;			
		this.pause = false;
		this.needUpdate = true;
		
	}
	
	protected void reset(){		// *** il faut utiliser super.reset() au début des initialisateurs enfant (si on Override)
		this.start = true;		
		this.prec = 0;			
		this.pause = false;
		this.needUpdate = true;
	}
	
	
	// --- Méthodes publiques/getters/setters héritables directement sans modifications --- 
	
	//Pause
	protected void setPause(){
		this.pause = !this.pause;
	}
	
	
	//Gestion des images	
	public void setImage(String name, String path, double width, double height, boolean ratio){
		this.images.put(name, new Image(path, width, height, ratio, true));
	}
		
	protected void displayImage(String name, double x, double y){
		this.window.drawImage(this.images.get(name), x, y);
	}
	
	
	
	//Gestion des mots secrets	
	public void stackKeyInput(String input){ //dédiée à la récupération des inputs pour l'historique (ex: mot secret)
		this.keyInputHistory.addLast(input);
	}	
	
	protected boolean checkSecretWord(String secretWord){
		
		String inputHistory = "";
		
		Iterator it = this.keyInputHistory.descendingIterator();	
		
		while(inputHistory.length() < secretWord.length()){
			if(it.hasNext()){
				inputHistory = it.next() + inputHistory;
			}else{
				break;
			}
		}
		
		if(inputHistory.equals(secretWord)){
			return true;
		}else{
			return false;
		}
		
	}
	
		
	// --- Méthode à appeler pour la logique principale du jeu! :) (héritée directement sans modifier) ---
	
	public void play(double now){
		
		//Au commencement, "régulariser" la timestamp pour éviter un saut de bug
		if(this.prec == 0){
			this.prec = now;
			this.now = now;
			return;
		}
		
		//Mettre à jour le timestamp et l'intervalle
		this.prec = this.now;
		this.now = now;
		this.intervalle = this.now - this.prec;
		
		//gestion: pause
		if(this.pause){
			return;
		}
		
		
		// ----------------------------
		
		this.nextFrame();
		
		this.endNextFrame();
		
		this.display();
		
	}
	
	
	// --- sous-méthodes pour les actions liées à la logique du jeu et à l'affichage (à Override dans les enfants au besoin) ---
	
	protected void nextFrame(){}
	
	protected void endNextFrame(){}
	
	protected void display(){}
	
	
	
	// --- Gestion des évènements (à Override dans les enfants au besoin) ---	
	
	public void keyPress(String key){}	
	
	public void keyRelease(String key){}
	
	public void mouseClick(double x, double y){}
	
	
}
