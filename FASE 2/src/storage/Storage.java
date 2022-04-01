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
		
		Clients new_client1 = new Clients("maria","m","123");
		Long id1 = Long.valueOf("123");
		Clients new_client2 = new Clients("juan","j","500");
		Long id2 = Long.valueOf("500");
		Clients new_client3 = new Clients("jasmin","j","900");
		Long id3 = Long.valueOf("900");
		ClientesB.insertar(id1, new_client1);
		ClientesB.insertar(id2, new_client2);
		ClientesB.insertar(id3, new_client3);
		
		Clients new_client4 = new Clients("jasmin","j","550");
		Long id4 = Long.valueOf("550");
		ClientesB.insertar(id4, new_client4);
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
	
	public void RemovingClient(Long id) {	
		ClientesB.buscartoRomove(ClientesB.raiz, id);	
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
