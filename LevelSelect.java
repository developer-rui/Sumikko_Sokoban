
import javafx.scene.image.Image;

public class LevelSelect extends ClickGame{

	public LevelSelect(CanvasWindow window){
	
		super(window);
		
		double box_w = 0.15;
        double box_h = 0.2;
        double margin_x = 0.04166;
        double margin_y = 0.2;

        for(int x=0; x<5; x++){
            for(int y=0; y<2; y++){

                int lvl = y * 5 + x + 1;

                ClickZone z = new ClickZone(window);

                z.setLeft(((margin_x + box_w) * x + margin_x) * 1000);
                z.setTop(((margin_y + box_h) * y + margin_y) * 1000);
                z.setWidth(box_w * 1000, "w");
                z.setHeight(box_h * 1000, "h");
                z.setImage("images/" + lvl + ".png");
                z.setZIndex(2);
                this.addZone("" + lvl, z);
            }
        }
        
        Zone z = new Zone(window);
        z.setLeft(0);
        z.setTop(0);
        z.setWidth(1000, "w");
        z.setHeight(1000, "h");
        z.setImage("images/background2.png");
        z.setZIndex(1);
        this.addZone("background", z);
	}	
	
	@Override
	protected void nextFrame(){		
        for(int i=1; i<=10; i++){
            if(this.getClickZone("" + i).getAction()){
                GameFlow.setCurrentGame("level_" + i);
                // System.out.println("clicked " + i);
                return;
            }
        }		
	}


    @Override
	public void keyPress(String key){
        if(key.equals("1")){
            GameFlow.setCurrentGame("level_1");
        }else if(key.equals("2")){
            GameFlow.setCurrentGame("level_2");
        }else if(key.equals("3")){
            GameFlow.setCurrentGame("level_3");
        }else if(key.equals("4")){
            GameFlow.setCurrentGame("level_4");
        }else if(key.equals("5")){
            GameFlow.setCurrentGame("level_5");
        }else if(key.equals("6")){
            GameFlow.setCurrentGame("level_6");
        }else if(key.equals("7")){
            GameFlow.setCurrentGame("level_7");
        }else if(key.equals("8")){
            GameFlow.setCurrentGame("level_8");
        }else if(key.equals("9")){
            GameFlow.setCurrentGame("level_9");
        }else if(key.equals("0")){
            GameFlow.setCurrentGame("level_10");
        }		
	}	
	

}

