package storage;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import objects.Clients;



public class Storage {
	public  ArbolB ClientesB = new ArbolB();
	//public  LinkedList<Clients> List_clients  = new LinkedList<Clients>();
	public Clients  clientJoin;
	
	public void initilize() {
		Clients new_client = new Clients("3","Sergie","serch","sergie@gmail.com","123","+502xxxxxxxx","planes",1);
		Long id = Long.valueOf("3");
		ClientesB.insertar(id, new_client);
		
		//List_clients.add(new_client);
		/*int[] edad = {14,17,18,20,23,25,27,31,38,44,48,52,54,60,69,73,80,35,62,83,86,88,90};
		
		for (int i : edad) {
			String temp = String.valueOf(i);
			Long id7 = Long.valueOf(temp);
			
			Clients new_client7 = new Clients("NUEVO" + i,"n",temp);
			ClientesB.insertar(id7, new_client7);
		}*/
		System.out.println("INICIANDO");
		
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
	
	
	
	public Boolean SerchClient(String ususario, String password) {
		try {
			
			
			NodoB nodotemp = ClientesB.buscar_start_string(ClientesB.raiz.primero,ususario);
			
			if (nodotemp.cliente.usuario.equals(ususario) && nodotemp.cliente.Password.equals(password)) {
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
			//JOptionPane.showMessageDialog(null, "error en busqueda de usuario");
			clientJoin= null;
			return false;
			
		}	
	}
	
	
	public Boolean ClinteExite(String ususario) {
		try {
			
			NodoB nodotemp = ClientesB.buscar_start_string(ClientesB.raiz.primero,ususario);
			
			if (nodotemp == null ) {
				return true;
			}else {
				return false;
			}
			
			
		} catch (Exception e) {
			return false;	
		}	
	}
	
	public Clients ClientJoin() {
		return clientJoin;
	}
	
}
