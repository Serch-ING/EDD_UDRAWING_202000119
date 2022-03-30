package storage;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import objects.Clients;



public class Storage {
	public  ArbolB ClientesB = new ArbolB();
	//public  LinkedList<Clients> List_clients  = new LinkedList<Clients>();
	public Clients  clientJoin;
	
	public void initilize() {
		Clients new_client = new Clients("sergie","s","3");
		Long id = Long.valueOf("3");
		ClientesB.insertar(id, new_client);
		//List_clients.add(new_client);
	}
	
	public void InsertClients(Clients client_new, Long dPI_Long) {
		
		ClientesB.insertar(dPI_Long,client_new);
		//List_clients.add(client_new);
	}
	
	public void showClients() {
		ClientesB.raiz.print_start(ClientesB.raiz.primero);
		System.out.println("------------------------------");
		ClientesB.raiz.print_start_Cleintes(ClientesB.raiz.primero);
		System.out.println("------------------------------");
		/*for (Clients clients : List_clients) {
			System.out.println("Cleinte DPI: " + clients.DPI + " Nombre: " + clients.Name + " contrase;a: " + clients.Password);
		}*/
	}
	
	public void modifyClient(Long id,String name,String password) {
		ClientesB.buscar(ClientesB.raiz.primero, id, name, password);
		
	}
	
	
	
	public Boolean SerchClient(String DPI, String password) {
		try {
			Long id = Long.valueOf(DPI);
			
			NodoB nodotemp = ClientesB.buscar_start(ClientesB.raiz.primero,id);
			
			if (nodotemp.cliente.DPI.equals(DPI) && nodotemp.cliente.Password.equals(password)) {
				clientJoin = nodotemp.cliente;
				JOptionPane.showMessageDialog(null, "Bienvenido: " + clientJoin.Name);
				return true;
			}
			
			clientJoin= null;
			return false;
			
			
			/*for (Clients clients : List_clients) {
				if (name.equals(clients.Name) && password.equals(clients.Password)) {
					clientJoin = clients;
					return true;
				}
			}*/
		
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Debe ingresar un digito en el DPI");
			clientJoin= null;
			return false;
			
		}	
	}
	
	public Clients ClientJoin() {
		return clientJoin;
	}
	
}
