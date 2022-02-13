package main;

import functionalities.general;
import lists.*;

public class main {

	public static void main(String[] args) {
		try {
			general global = new general();
			global.inicializar();
		} catch (Exception e) {
			System.out.println("Ocurrio un error");
		}
		
	}
}
