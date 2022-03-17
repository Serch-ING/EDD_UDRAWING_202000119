package main;

import java.awt.EventQueue;

import storage.Storage;
import windows.Login;

public class main {
	static Storage storage = new Storage();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login(storage);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
