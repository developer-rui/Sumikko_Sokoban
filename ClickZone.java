
public class ClickZone extends Zone{		
	
	protected int nbState;			// le nombre total d'états	
	protected int state;			// les états possibles, au nombre variables, et "set on" de façon cyclique par modulo	
	protected boolean action;		// false = pas d'action,  				true = on vient de cliquer et en attente de traitement	
	protected boolean active;		// false = ne répond pas aux input      true = répond aux inputs
	
		
	public ClickZone(CanvasWindow window){	
		
		super(window);
		
		this.nbState = 2;
		this.state = 0;
		this.action = false;
		this.active = true;			
		
	}

	
	// --- Méthodes de définition/hydradation d'objet ---	
	
	public void setNbState(int n){
		this.nbState = n;
	}
	
	
	// --- Méthodes d'affectation de la logique d'interaction ---
			
	public void nextState(){
		if(this.active){
			this.state = (this.state + 1) % this.nbState;
		}
	}
	
	public void setAction(boolean n){
		/*if(n == false){			//car même quand inactive, a besoin de capter évents (comme pour jazeker...)
			this.action = false;
		}else{
			if(this.active){
				this.action = true;
			}
		}*/
		this.action = n;
	}
	
	public void setActive(boolean n){
		this.active = n;
	}
			
	@Override
	public void show(){
		this.setActive(true);
		this.setVisible(true);
	}
	
	@Override
	public void hide(){
		this.setActive(false);
		this.setVisible(false);
	}	
	
	
	
	public int getState(){
		return this.state;
	}
	
	public boolean getAction(){
		return this.action;
	}
	
	public boolean getActive(){
		return this.active;
	}
	
	public boolean aa(){ // raccourci
		if(this.getAction() && this.getActive()){
			return true;
		}else{
			return false;
		}
	}
	
		
	
	// --- Click ---
	
	public void click(double x, double y){
		if(x >= this.left && x <= this.left + this.width && y >= this.top && y <= this.top + this.height){
			
			this.setAction(true);
			if(this.active){
				this.nextState();		//mettre après setAction() pour que itemWell puisse activer action()...
			}
		}
	}	
		
	
	
}

