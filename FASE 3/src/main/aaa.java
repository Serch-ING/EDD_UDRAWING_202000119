package main;

import objects.Mensajero;
import storage.TablaHash;

public class aaa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TablaHash tabla = new TablaHash();
		tabla.mostar();
		
		Long dpi = Long.valueOf("4");
		
		Mensajero mensajero = new Mensajero(dpi, null, null, null, null, null, null);
		
		//tabla.insertar(mensajero);
	
		
		
	
		
		for (int i = 0; i < 22; i++) {
			tabla.insertar(mensajero);
		}
		tabla.mostar();

		
		
	}

}
