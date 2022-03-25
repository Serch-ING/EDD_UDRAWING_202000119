package storage;

import java.util.LinkedList;

import objects.Clients;



public class Storage {
	public  LinkedList<Clients> List_clients  = new LinkedList<Clients>();
	public Clients  clientJoin;
	
	public void initilize() {
		Clients new_client = new Clients("s","s","3000");
		List_clients.add(new_client);
	}
	
	public void InsertClients(Clients client_new) {
		List_clients.add(client_new);
	}
	
	public void showClients() {
		for (Clients clients : List_clients) {
			System.out.println("Cleinte DPI: " + clients.DPI + " Nombre: " + clients.Name + " contrase;a: " + clients.Password);
		}
	}
	
	public Boolean SerchClient(String name, String password) {
		
		for (Clients clients : List_clients) {
			if (name.equals(clients.Name) && password.equals(clients.Password)) {
				clientJoin = clients;
				return true;
			}
		}
		clientJoin= null;
		return false;
	}
	
	public Clients ClientJoin() {
		return clientJoin;
	}
	
}
