package objects;

import storage.Arbol_AVL;
import storage.Arbol_Binario;
import storage.Simple_Album;

public class Clients {
	public String Name,Password;
	public String DPI;
	public Arbol_Binario ABBCapas  = new Arbol_Binario();
	public Arbol_AVL AVLImages  = new Arbol_AVL();
	public Simple_Album Album_list = new Simple_Album();
	
	
	public Clients(String Name, String Password, String DPI) {
		this.Name = Name;
		this.Password = Password;
		this.DPI = DPI;
		
	}
	
}
