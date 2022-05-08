package main;

import java.awt.EventQueue;

import storage.Storage;
import windows.Login;
import windows.Required_Files;

public class main {
	static Storage storage = new Storage();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Thread hilo1 = new Thread(storage);
					hilo1.start();
					

					
					storage.initilize();
					
					
					
					Required_Files files_new = new Required_Files(storage);
					files_new.setVisible(true);
					
					
					
									
					//Login login = new Login(storage);
					//login.setVisible(true);
					
					 
					System.out.println("Start");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
