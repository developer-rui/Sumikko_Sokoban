import java.util.HashMap;

public class GameFlow{
	
	public static int nbBonus = 0;
	
	private static Game currentGame;
	
	private static HashMap<String, Game> games = new HashMap<String, Game>();
	
	
	public static void addGame(String name, Game game){
		games.put(name, game);		
	}
	
	public static void setCurrentGame(String name){
		currentGame = games.get(name);
	}
	
	public static Game getGame(String name){
		return games.get(name);
	}
	
	
	// --- Transmet au currentGame ---
	public static void play(long now){
		currentGame.play(now);
	}
	
	
	// --- Transmet les évènements au currentGame ---
	
	public static void keyPress(String key){
		currentGame.keyPress(key);
		currentGame.stackKeyInput(key);
	}
	
	public static void keyRelease(String key){
		currentGame.keyRelease(key);
	}
	
	public static void mouseClick(double x, double y){
		currentGame.mouseClick(x, y);
	}

	
	
	
}