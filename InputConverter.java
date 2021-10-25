

import javafx.scene.input.*; //MouseEvent, KeyEvent, ..

public class InputConverter{

	public static String key(KeyEvent e){		
		
		if(e.getCode() == KeyCode.LEFT){
			return "leftArrow";
		}else if(e.getCode() == KeyCode.RIGHT){
			return "rightArrow";
		}else if(e.getCode() == KeyCode.UP){
			return "upArrow";
		}else if(e.getCode() == KeyCode.DOWN){
			return "downArrow";
		}
		
		else if(e.getCode() == KeyCode.SPACE){
			return "space";
		}else if(e.getCode() == KeyCode.ENTER){
			return "enter";
		}else if(e.getCode() == KeyCode.BACK_SPACE){
			return "backspace";
		}else if(e.getCode() == KeyCode.ESCAPE){
			return "esc";
		}
		
		else if(e.getCode() == KeyCode.A){
			return "a";
		}else if(e.getCode() == KeyCode.B){
			return "b";
		}else if(e.getCode() == KeyCode.C){
			return "c";
		}else if(e.getCode() == KeyCode.D){
			return "d";
		}else if(e.getCode() == KeyCode.E){
			return "e";
		}else if(e.getCode() == KeyCode.F){
			return "f";
		}else if(e.getCode() == KeyCode.G){
			return "g";
		}else if(e.getCode() == KeyCode.H){
			return "h";
		}else if(e.getCode() == KeyCode.I){
			return "i";
		}else if(e.getCode() == KeyCode.J){
			return "j";
		}else if(e.getCode() == KeyCode.K){
			return "k";
		}else if(e.getCode() == KeyCode.L){
			return "l";
		}else if(e.getCode() == KeyCode.M){
			return "m";
		}else if(e.getCode() == KeyCode.N){
			return "n";
		}else if(e.getCode() == KeyCode.O){
			return "o";
		}else if(e.getCode() == KeyCode.P){
			return "p";
		}else if(e.getCode() == KeyCode.Q){
			return "q";
		}else if(e.getCode() == KeyCode.R){
			return "r";
		}else if(e.getCode() == KeyCode.S){
			return "s";
		}else if(e.getCode() == KeyCode.T){
			return "t";
		}else if(e.getCode() == KeyCode.U){
			return "u";
		}else if(e.getCode() == KeyCode.V){
			return "v";
		}else if(e.getCode() == KeyCode.W){
			return "w";
		}else if(e.getCode() == KeyCode.X){
			return "x";
		}else if(e.getCode() == KeyCode.Y){
			return "y";
		}else if(e.getCode() == KeyCode.Z){
			return "z";
		}
		
		else if(e.getCode() == KeyCode.DIGIT0){
			return "0";
		}else if(e.getCode() == KeyCode.DIGIT1){
			return "1";
		}else if(e.getCode() == KeyCode.DIGIT2){
			return "2";
		}else if(e.getCode() == KeyCode.DIGIT3){
			return "3";
		}else if(e.getCode() == KeyCode.DIGIT4){
			return "4";
		}else if(e.getCode() == KeyCode.DIGIT5){
			return "5";
		}else if(e.getCode() == KeyCode.DIGIT6){
			return "6";
		}else if(e.getCode() == KeyCode.DIGIT7){
			return "7";
		}else if(e.getCode() == KeyCode.DIGIT8){
			return "8";
		}else if(e.getCode() == KeyCode.DIGIT9){
			return "9";
		}
		
		else{
			return "other";
		}
		
	}
	
	public static double mouseX(MouseEvent e){
		return e.getX();   
	}
	
	public static double mouseY(MouseEvent e){
		return e.getY();   
	}
	

}