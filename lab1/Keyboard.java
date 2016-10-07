package lab1;

import java.util.Scanner;

public class Keyboard {

	private static Keyboard miKeyboard;
	private Scanner scan;
	
	public Keyboard(){
		scan = new Scanner(System.in);
	}
	
	public static Keyboard getMyKeyboard(){
		if (miKeyboard==null){
			miKeyboard = new Keyboard();
		}
		return miKeyboard;
	}

	/*
	 * Recoge un int. En caso de que no sea valido, vuelve a solicitarlo hasta que
	 * reciba un int valido.
	 */
	public int getInt(){
		String auxS = scan.nextLine();
		while(!this.isNumeric(auxS)){
			try{
				Integer.parseInt(auxS);
			}
			catch (NumberFormatException nfe){
				System.out.println("Insert a valid number");
				auxS = scan.nextLine();
			}
		}
		int resul = Integer.parseInt(auxS);
		return resul;
	}
	
	/*
	 * Comprueba si el String que se recibe es parseable a int
	 */
	private boolean isNumeric(String cadena){
		try{
			Integer.parseInt(cadena);
			return true;
		}
		catch(NumberFormatException nfe){
			return false;
		}
	}
	
	/*
	 * Recoge por Keyboard un String
	 */
	public String getString(){
		String auxS = scan.nextLine();
		return auxS;
	}

}