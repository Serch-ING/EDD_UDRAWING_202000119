package objects;

import storage.Arbol_AVL;

public class Clients {
	public String Name,Password;
	public String DPI;
	public Arbol_AVL AVL  = new Arbol_AVL();
	
	public Clients(String Name, String Password, String DPI) {
		this.Name = Name;
		this.Password = Password;
		this.DPI = DPI;
		
	}
	
}
