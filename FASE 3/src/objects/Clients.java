package objects;

import java.util.LinkedList;

import storage.Arbol_AVL;
import storage.Arbol_Binario;
import storage.Simple_Album;

public class Clients {
	public String Name,Password;
	public String DPI;
	
	//Nuevos valores-----------------------
	public String usuario,correo,direccion,telefono;
	public int id_municipio;
	//-------------------------------------
	
	
	public Arbol_Binario ABBCapas  = new Arbol_Binario();
	public Arbol_AVL AVLImages  = new Arbol_AVL();
	public Simple_Album Album_list = new Simple_Album();
	public int ID_IMG = 0;
	public LinkedList<String> generated_images = new LinkedList<String>();
	public LinkedList<String> generate_struc  = new LinkedList<String>();
	public int no_capas= 0;
	public int no_images= 0;
	public int no_albumes= 0;
	
	
	public Clients(String DPI,String Name,String usuario,String correo,String Password,String telefono,String direccion,int id_municipio ) {
		this.DPI = DPI;
		this.Name = Name;
		this.usuario = usuario;
		this.correo = correo;
		this.Password = Password;
		this.telefono = telefono;
		this.direccion = direccion;
		this.id_municipio = id_municipio;
	}
	
}
