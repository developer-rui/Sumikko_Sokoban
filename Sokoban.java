import java.lang.Math; 


import javafx.scene.paint.*;


public final class Sokoban extends Game{	

	private int[][] levelMap;		//grille initiale
	private int[][] grid;			//grille des tuiles au cours du jeu
	
	private int characterX;			//coordonnées X du personnage principal dans la grille
	private int characterY;			//coordonnées Y du personnage principal dans la grille
	
	private int nbLignes;			//nb de lignes de briques	
	private int nbColonnes;			//nb de colonnes de briques	
	
	private double tileSize;		// larguer/hauteur d'une tuile (carrée)
	
	private double leftMargin;
	private double topMargin;	
	
	private String nextGameName;

	private String command;
			
	
	// 1 = espace    2 = point    3 = mur    4 = personnage    5 à ... = box
	
	// --------------------------- Constructeur, initialisateur --------------------------
	
	public Sokoban(CanvasWindow window, int[][] levelMap){
		
		//utiliser constructeur parent pour set window et largeur fenêtre
		super(window);
		
		//Carte initiale du niveau		
		this.levelMap = levelMap;		
		
		//nb lignes et colonnes
		this.nbLignes = this.levelMap.length;
		this.nbColonnes = this.levelMap[0].length;
		
		//calcul de la taille des tuiles
		double w = this.windowWidth / (this.nbColonnes + 1);
		double h = this.windowHeight / (this.nbLignes + 1);
		this.tileSize = (w < h) ? w : h;
		
		//calcul de la disposition de la zone de jeu à l'intérieur de l'écran (centrée et marge autour...)
		this.leftMargin = (this.windowWidth - this.tileSize * this.nbColonnes) / 2;
		this.topMargin = (this.windowHeight - this.tileSize * this.nbLignes) / 2;		
	
		// prochain jeu dans la série 
		this.nextGameName = "";

		//N'oublie pas de reset
		this.reset();
		
	}
	
	@Override
	public void reset(){
		
		//initialiser les paramètres communs
		super.reset();
		
		//initialiser grille		
		this.resetGrid();
		
		//récupérer coordonnées du personnage principal (pour accès rapide ;) )
		for(int i=0; i<this.nbLignes; i++){
			for(int j=0; j<this.nbColonnes; j++){
				if(this.grid[i][j] == 4){
					this.characterX = j;
					this.characterY = i;
				}
			}
		}
		
		this.command = "none";		
		
	}
	
	private void resetGrid(){ //pour copier un tableau 2D proprement...
		this.grid = new int[this.nbLignes][this.nbColonnes];
		
		for(int i=0; i<this.nbLignes; i++){			
			for(int j=1; j<this.nbColonnes; j++){
				this.grid[i][j] = this.levelMap[i][j];
			}			
		}
	}
	
	
	public void bindImage(String nom, String path){
		this.setImage(nom, path, this.tileSize, this.tileSize, false);
	}
	
	
	public void setBackground(String path){
		this.setImage("background", path, this.windowWidth, this.windowHeight, false);
	}

	public void setNextGameName(String gameName){
		this.nextGameName = gameName;
	}

	// --------------------------- Méthodes auxiliaires de la logique du jeu ---------------------------
	
	private boolean checkWin(){
	
		for(int i=0; i<this.nbLignes; i++){
			for(int j=0; j<this.nbColonnes; j++){
			
				//retourner false s'il reste encore un point non match par une boîte
				if(this.levelMap[i][j] == 2 && this.grid[i][j] <= 4){
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	
	// --------------------------- Logique de jeu et affichage ---------------------------	
	
	@Override
	protected void nextFrame(){		
		
		if(!this.needUpdate){ return; }
		
		// 1 = espace    2 = point    3 = mur    4 = personnage    5 à ... = box	
		if(this.command.equals("left")){			
		
			int neighbor = this.grid[this.characterY][this.characterX - 1];				
				
			if(neighbor == 1 || neighbor == 2){	//esapce vide sur la case adjacente					
				
				//déplacer le personnage (mettre à jour grille et coordonnées raccourcie)
				this.grid[this.characterY][this.characterX - 1] = 4;
				this.grid[this.characterY][this.characterX] = 1;					
				
				this.characterX -= 1;
				
			}else if(neighbor > 4){	//boîte sur la case adjacente
			
				int farNeighbor = this.grid[this.characterY][this.characterX - 2];
				
				if(farNeighbor == 1 || farNeighbor == 2){	//espace vide sur la case d'après
				
					//déplacer le personnage en poussant la boîte (mettre à jour grille et coordonnées raccourcie)
					this.grid[this.characterY][this.characterX - 2] = neighbor;
					this.grid[this.characterY][this.characterX - 1] = 4;
					this.grid[this.characterY][this.characterX] = 1;						
					
					this.characterX -= 1;
				}					
			}			
			
		}else if(this.command.equals("up")){
			
			int neighbor = this.grid[this.characterY - 1][this.characterX];			
				
			if(neighbor == 1 || neighbor == 2){	//esapce vide sur la case adjacente
			
				//déplacer le personnage (mettre à jour grille et coordonnées raccourcie)
				this.grid[this.characterY - 1][this.characterX] = 4;
				this.grid[this.characterY][this.characterX] = 1;					
				
				this.characterY -= 1;
				
			}else if(neighbor > 4){	//boîte sur la case adjacente
			
				int farNeighbor = this.grid[this.characterY - 2][this.characterX];
				
				if(farNeighbor == 1 || farNeighbor == 2){	//espace vide sur la case d'après
				
					//déplacer le personnage en poussant la boîte (mettre à jour grille et coordonnées raccourcie)
					this.grid[this.characterY - 2][this.characterX] = neighbor;
					this.grid[this.characterY - 1][this.characterX] = 4;
					this.grid[this.characterY][this.characterX] = 1;						
					
					this.characterY -= 1;
				}					
			}			
			
		}else if(this.command.equals("right")){
		
			int neighbor = this.grid[this.characterY][this.characterX + 1];			
				
			if(neighbor == 1 || neighbor == 2){	//esapce vide sur la case adjacente
			
				//déplacer le personnage (mettre à jour grille et coordonnées raccourcie)
				this.grid[this.characterY][this.characterX + 1] = 4;
				this.grid[this.characterY][this.characterX] = 1;					
				
				this.characterX += 1;
				
			}else if(neighbor > 4){	//boîte sur la case adjacente
			
				int farNeighbor = this.grid[this.characterY][this.characterX + 2];
				
				if(farNeighbor == 1 || farNeighbor == 2){	//espace vide sur la case d'après
				
					//déplacer le personnage en poussant la boîte (mettre à jour grille et coordonnées raccourcie)
					this.grid[this.characterY][this.characterX + 2] = neighbor;
					this.grid[this.characterY][this.characterX + 1] = 4;
					this.grid[this.characterY][this.characterX] = 1;						
					
					this.characterX += 1;
				}					
			}			
		
		}else if(this.command.equals("down")){
			
			int neighbor = this.grid[this.characterY + 1][this.characterX];			
				
			if(neighbor == 1 || neighbor == 2){	//esapce vide sur la case adjacente
			
				//déplacer le personnage (mettre à jour grille et coordonnées raccourcie)
				this.grid[this.characterY + 1][this.characterX] = 4;
				this.grid[this.characterY][this.characterX] = 1;					
				
				this.characterY += 1;
				
			}else if(neighbor > 4){	//boîte sur la case adjacente
			
				int farNeighbor = this.grid[this.characterY + 2][this.characterX];
				
				if(farNeighbor == 1 || farNeighbor == 2){	//espace vide sur la case d'après
				
					//déplacer le personnage en poussant la boîte (mettre à jour grille et coordonnées raccourcie)
					this.grid[this.characterY + 2][this.characterX] = neighbor;
					this.grid[this.characterY + 1][this.characterX] = 4;
					this.grid[this.characterY][this.characterX] = 1;						
					
					this.characterY += 1;
				}					
			}			
			
		}else{}		
		
		this.command = "none";	
				
		if(this.checkWin() || this.checkSecretWord("jazeker")){
			if(this.nextGameName.length() > 0){
				GameFlow.setCurrentGame(this.nextGameName);
			}        	    
			this.reset();			
		}       
		
	}
	
	@Override
	protected void display(){		
		
		// if(!this.needUpdate){ return; }
		
		this.window.clear();
        
        //background
		this.displayImage("background", 0, 0);
		//this.window.fillRect(0, 0, this.windowWidth, this.windowHeight, 0, 0, 0);
		
		// 1 = espace    2 = point    3 = mur    4 = personnage    5 à ... = box
		
		for(int i=0; i<this.nbLignes; i++){
			for(int j=0; j<this.nbColonnes; j++){
				
				//Dessiner les espaces, points, mur (en ignorant les boîtes + personnage)
				if(this.levelMap[i][j] != 0){
					if(this.levelMap[i][j] <= 3){
						this.displayImage("" + this.levelMap[i][j], 
												 this.leftMargin + j * this.tileSize, this.topMargin + i * this.tileSize);
					}else{
						this.displayImage("1", this.leftMargin + j * this.tileSize, this.topMargin + i * this.tileSize);
					}
				}
				
				
				//Ajouter les boîtes et personnage
				if(this.grid[i][j] == 4){	//personnage				
					this.displayImage("4", this.leftMargin + j * this.tileSize, this.topMargin + i * this.tileSize);					
				}else if(this.grid[i][j] > 4){ 	//boîte	
					if(this.levelMap[i][j] != 2){
						this.displayImage("5", this.leftMargin + j * this.tileSize, this.topMargin + i * this.tileSize);
					}else{					
						this.displayImage("6", this.leftMargin + j * this.tileSize, this.topMargin + i * this.tileSize);
					}
				}
				
				
			}
		}
		
		this.needUpdate = false;
		
		
	}
	
		

	// --------------------------- Évènements ---------------------------	
	
	@Override
	public void keyPress(String key){
		if(key.equals("leftArrow")){
			this.command = "left";
			this.needUpdate = true;
		}else if(key.equals("rightArrow")){
			this.command = "right";
			this.needUpdate = true;
		}else if(key.equals("upArrow")){
			this.command = "up";
			this.needUpdate = true;
		}else if(key.equals("downArrow")){
			this.command = "down";
			this.needUpdate = true;
		}else if(key.equals("r")){			
			this.reset();
		}else if(key.equals("esc")){									
			GameFlow.setCurrentGame("level_select");
			this.reset();
		}
	}	
	
}

