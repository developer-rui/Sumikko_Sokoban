//import java.lang.Math; 

import java.util.*;



public abstract class ClickGame extends Game{	

	protected HashMap<String, Zone> zones;	
	
	
	// --------------------------- Constructeur, initialisateur --------------------------
	
	public ClickGame(CanvasWindow window){
		
		super(window);	
		
		this.zones = new HashMap<String, Zone>();		
		
	}
	
	// --------------------------- Ajout/gestion des zones --------------------------
	
	public void addZone(String name, Zone z){
		this.zones.put(name, z);
	}
	
	public Zone getZone(String name){
		return this.zones.get(name);
	}
	
	public ClickZone getClickZone(String name){
		return (ClickZone) this.zones.get(name);
	}
	
	// --------------------------- Logique de jeu et affichage ---------------------------	
	
		
	@Override
	protected void endNextFrame(){
	
		//Désactiver les actions (triggered by user) des éléments interactives à la fin de chaque frame
		for(Zone z : this.zones.values()){
			if(z instanceof ClickZone){
				ClickZone t = (ClickZone) z;
				t.setAction(false);
			}
		}
	}
	
	@Override
	protected void display(){		
		
		this.window.clear();	
			
		//cette ligne pour éviter le "changement de couleur" après la fenêtre d'alert
		this.window.fillRect(0, 0, this.window.getWidth(), this.window.getHeight(), 255, 255, 255);
		
		int zIndex = 0;
		while(true){
		
			boolean zIndexOccupied = false;
			
			for(Zone z : this.zones.values()){	
				if(z.getZIndex() == zIndex){
					zIndexOccupied = true;
					z.display();	
				}
			}
			
			zIndex++;
			
			if(!zIndexOccupied && zIndex > 2){ //
				return;
			}
		}
		
	}
	
		

	// --------------------------- Évènements ---------------------------	
	
	@Override
	public void mouseClick(double x, double y){
		for(Zone z : this.zones.values()){
			if(z instanceof ClickZone){
				ClickZone t = (ClickZone) z;
				t.click(x, y);
			}
		}
	}
	
	
	
	
	
}



