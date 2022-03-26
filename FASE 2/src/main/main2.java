package main;

import java.awt.EventQueue;

import storage.B;

public class main2 {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				B arbolB = new B(5);
				int[] edad = { 6, 11, 5, 4, 8, 9, 12, 21, 14, 10, 19, 28, 3, 17, 32, 15, 16, 26, 27 };
				//int[] edad = { 6 };
				for (int i : edad) {
					arbolB.insertar(i);
				}
				

				arbolB.raiz.imprimircompleto();
				System.out.println("algo");

			}
		});

	}

}
