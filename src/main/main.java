package main;

import functionalities.general;
import lists.*;

public class main {

	public static void main(String[] args) {
		Circular_Doble_espera cirdoble = new Circular_Doble_espera();
		cirdoble.insert(1);
		cirdoble.insert(2);
		cirdoble.insert(3);
		cirdoble.insert(4);
		cirdoble.insert(5);
		cirdoble.insert(6);
		cirdoble.showList();
		general global = new general();
		global.inicializar();
	}
}
