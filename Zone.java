
import javafx.scene.image.Image;

public class Zone{
	
// 1 - fenêtre parent et ses dimensions
	protected CanvasWindow window;
	protected double windowWidth;
	protected double windowHeight;
	
// 2 - positions absolues calculées de la zone à partir des positions relatives fournies
	protected double left;
	protected double top;
	protected double width;
	protected double height;	
		
// 3 - visibilité
	protected boolean visible;	// false = n'affichera pas   			true = affichera
	protected int zIndex;   // allant de 0 (défaut) à ...
	
// 4 - couleur de fond
	protected int fillRed;
	protected int fillGreen;
	protected int fillBlue;
	
// 5 - bordure
	protected int borderRed;
	protected int borderGreen;
	protected int borderBlue;	
	
	protected double borderWidth; // valeur stockée en relative; calcule live lors de l'affichage pour éviter conflit
	
// 6 - image 
	protected String imagePath;
	protected Image image;
	
	

	public Zone(CanvasWindow window){
						  
	// 1 - fenêtre parent et ses dimensions
		this.window = window;		
		this.windowWidth = window.getWidth();
		this.windowHeight = window.getHeight();	
	
	// 2 - positions absolues de la zone calculées à partir des positions relatives fournies
		this.left = 0; this.top = 0; this.width = this.windowWidth; this.height = this.windowHeight;
	
	// 3 - visibilité et zIndex
		this.visible = true;
		this.zIndex = 0;
	
	// 4 - couleur de fond	
		this.setFillColor(255,255,255);
	
	// 5 - bordure
		this.setBorderColor(0,0,0);
		this.setBorderWidth(0); // 0% de border par défaut; attention c'est "à délais"
	
	// 6 - image 
		this.imagePath = "";
		this.image = null;
		
	}

	
// 2 - positions absolues de la zone calculées à partir des positions relatives fournies
	
	public void setLeft(double pmt){		
		this.left = this.windowWidth * pmt / 1000;		
	}
	
	public void setTop(double pmt){
		this.top = this.windowHeight * pmt / 1000;
	}
	
	public void setWidth(double pmt, String reference){
		if(reference.equals("w")){
			this.width = this.windowWidth * pmt / 1000;
		}else if(reference.equals("h")){
			this.width = this.windowHeight * pmt / 1000;
		}

		if(pmt < 1){ //avertissement anti-bug d'oublie de * 1000...
			System.out.println("Avertissement: valeur de pmt < 1, possibilite d'oublie de * 1000 ? ");
		}
	}
	
	public void setHeight(double pmt, String reference){
		if(reference.equals("w")){
			this.height = this.windowWidth * pmt / 1000;
		}else if(reference.equals("h")){
			this.height = this.windowHeight * pmt / 1000;
		}	

		if(pmt < 1){ //avertissement anti-bug d'oublie de * 1000...
			System.out.println("Avertissement: valeur de pmt < 1, possibilite d'oublie de * 1000 ? ");
		}	
	}	
	
	public void setSize(double pmt, String reference){ //raccourcie pour zone carrée 
		this.setWidth(pmt, reference);
		this.setHeight(pmt, reference);
	}
	
	    
// 3 - visibilité et zIndex
	
	public void setVisible(boolean n){
		this.visible = n;
	}
	
	public boolean getVisible(){
		return this.visible;
	}
	
	public void setZIndex(int n){
		this.zIndex = n;
	}
	
	public int getZIndex(){
		return this.zIndex;
	}
	
	public void show(){
		this.setVisible(true);
	}
	
	public void hide(){
		this.setVisible(false);
	}
	
	
	
	
// 4 - couleur de fond
	
	public void setFillColor(int r, int g, int b){
		this.fillRed = r;
		this.fillGreen = g;
		this.fillBlue = b;
	}

	
// 5 - bordure
	
	public void setBorderColor(int r, int g, int b){
		this.borderRed = r;
		this.borderGreen = g;
		this.borderBlue = b;
	}
	
	public void setBorderWidth(double pmt){
		this.borderWidth = pmt;		
	}	
	
	protected double getBorderWidth(){	//Au moment de l'affichage seulement, pour éviter conflit...
		double dim = (this.width < this.height) ? this.width : this.height;	
		return this.borderWidth / 1000 * dim;
	}


// 6 - image

	public void setImage(String path){ //après dimensions et borderWidth, car besoin des dimensions...
		this.imagePath = path;	
		this.image = null;		//si jamais on recharge une image...
	}
	
	private void loadImage(){
		double borderW = this.getBorderWidth();
		this.image = new Image(this.imagePath, this.width - borderW, this.height - borderW, false, true);
	}
	
	
	////////////////// --- affichage --- ////////////////////	
	
	public void display(){
		
		//si en mode invisible, n'affichera pas 
		if(!this.getVisible()){
			return;
		}
		
		//NE PAS FILL la couleur ici comme j'ai fait avant!!! ça cause le bug de "pourquoi l'image n'est pas transparente..."
		
		
		double borderW = this.getBorderWidth(); //calculer la largeur réelle de la bordure
		
		//Image (si applicable, et "écrase" le fill)
		if(!this.imagePath.equals("")){	//Si on a définie une image
			
			if(this.image == null){ // Si l'image n'a pas encore été load
				this.loadImage();
			}
			
			//Afficher
			this.window.drawImage(this.image, this.left + borderW / 2, this.top + borderW / 2);
		}else{
			//Fill la zone avec la couleur, ssi on n'a pas d'image
			this.window.fillRect(this.left, this.top, this.width, this.height, this.fillRed, this.fillGreen, this.fillBlue);
		}
		
		//Border (si applicable)
		if(borderW > 0){
			this.window.setLineWidth(borderW);
			this.window.setStroke(this.borderRed, this.borderGreen, this.borderBlue);
			
			this.window.strokeRect(this.left, this.top, this.width, this.height);
		}		
		
	}	
	
	
}

