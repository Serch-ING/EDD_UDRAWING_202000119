package objects;

import storage.Arbol_AVL;
import storage.Arbol_Binario;

public class Nodes_Colors {
	public String Name,Password;
	public String DPI;
	public Arbol_Binario ABBCapas  = new Arbol_Binario();
	public Arbol_AVL AVLImages  = new Arbol_AVL();
	
	
	public Nodes_Colors(String Name, String Password, String DPI) {
		this.Name = Name;
		this.Password = Password;
		this.DPI = DPI;
		
	}
	
}
