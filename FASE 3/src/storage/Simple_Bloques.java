package storage;

import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;


import objects.Bloque;
import storage.ListaDG.Nodo_Simple;

public class Simple_Bloques {
	int control_indice=0;

	Nodo_Simple_bloques primero;

	public Simple_Bloques() {
		this.primero = null;
	}

	public void insert(Bloque info) {
		Nodo_Simple_bloques new_node = new Nodo_Simple_bloques(info,control_indice);
		if (isNone()) {
			this.primero = new_node;
			control_indice++;
		} else {
			Nodo_Simple_bloques actual = this.primero;
			while (actual.next != null) {
				actual = actual.next;
			}
			actual.next = new_node;
			control_indice++;
		}
	}
	
	public int Control_indices() {
		return control_indice;
	}

	public void showList() {
		if (isNone() == false) {
			Nodo_Simple_bloques actual = this.primero;
			while (actual != null) {
				System.out.println(actual.indice);
				actual = actual.next;
			}
		}
	}
	
	

	public void recorrido_bloques() {
		
		System.out.println("Comenzando recorrido");
		if (isNone() == false) {
			Nodo_Simple_bloques actual = this.primero;
			while (actual != null) {
				System.out.print("====================================================================" );
				actual.bloque.GenerarJson();
				actual = actual.next;
			}
			
			System.out.println("-------------Se termino recorrido---------------------");
		}
	}
	
	public void RecorreorJson() {
		
		System.out.println("Comenzando recorrido");
		if (isNone() == false) {
			Nodo_Simple_bloques actual = this.primero;
			while (actual != null) {
				System.out.print(actual.bloque.hashCode() + " -> " );
				actual = actual.next;
			}
			
			System.out.println("Se termino recorrido");
		}
	}
	
	public String Ultimo_Hash_bloque() {
		if (isNone() == false) {
			Nodo_Simple_bloques actual = this.primero;
			while (actual != null) {
				
				if (actual.next == null) {
					return actual.bloque.Hash;
				}
				actual = actual.next;
			}
		}
		
		return "";
	}


	public void Search(int data) {
		if (isNone() == false) {
			Nodo_Simple_bloques actual = this.primero;
			while (actual != null && actual.indice != data) {
				actual = actual.next;
				if (actual == null) {
					System.out.println("No se encontro el dato: " + data);
					break;
				}
			}
			if (actual != null && actual.indice == data) {
				System.out.println("Dato encontrado: " + data);
			}
		}
	}

	public void Delete(int data) {
		if (isNone() == false) {
			Nodo_Simple_bloques actual = this.primero;
			Nodo_Simple_bloques anterior = null;

			while (actual != null && actual.indice != data) {
				anterior = actual;
				actual = actual.next;
			}

			if (anterior == null) {
				this.primero = actual.next;
				System.out.println("Se elimino el dato: " + data);
			} else if (actual != null) {
				anterior.next = actual.next;
				actual.next = null;
				System.out.println("Se elimino el dato: " + data);
			} else {
				System.out.println("No se encontro el dato a eliminar: " + data);
			}
		}
	}

	public Boolean isNone() {
		return this.primero == null;
	}
	
	
	//cresate the text of graphiz
	public String Text_Graphivz() {
		StringBuilder dot = new StringBuilder();
		dot.append("digraph L {\n");
		dot.append("node[shape=note fillcolor=\"#A181FF\" style =filled]\n");
		dot.append("subgraph cluster_p{\n");
		
		dot.append("    label= \" BloackChain \"\n");
		dot.append(" raiz[label = \"BloackChain\" fillcolor=\"#FFD581\" ]");
	
		dot.append("    bgcolor = \"#FF7878\"\n");
		
		
		dot.append("");
		String nombresNodos = "";
		String conexiones = "";
		Nodo_Simple_bloques aux = this.primero;
		
		if(this.primero!= null) {
			conexiones+="\nraiz->Nodo" +  this.primero.hashCode();
		}
		
		while (aux != null) {
			
			
			String info = "\n" + aux.bloque.RegresarInfo().replace("\"", "'");
			nombresNodos += "Nodo" + aux.hashCode() + "[label=\"" + info + "\",fillcolor=\"#81FFDA\"]\n";
			if (aux.next != null) {
				conexiones += String.format("\nNodo%d -> Nodo%d\n", aux.hashCode(), aux.next.hashCode());
			}

			aux = aux.next;
		}
		dot.append(nombresNodos);
		dot.append(conexiones);

		dot.append("}}");

		return dot.toString();
	}
	
	
	public void generate_grapgh(String name) {
		try {
			Create_File("Grafico\\" + name + ".dot", Text_Graphivz());
			ProcessBuilder pb;
			pb = new ProcessBuilder("dot", "-Tpng", "-o", "Grafico\\" + name + ".png", "Grafico\\" + name + ".dot");
			pb.redirectErrorStream(true);
			pb.start();
			JOptionPane.showMessageDialog(null, "Blockchain actulizado");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void Create_File(String route, String contents) {

		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(route);
			pw = new PrintWriter(fw);
			pw.write(contents);
			pw.close();
			fw.close();
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (pw != null)
				pw.close();
		}

	}
}

class Nodo_Simple_bloques {
	
	Nodo_Simple_bloques next;
	
	Bloque bloque;
	int indice;
	
	String ubicacion;
	public Nodo_Simple_bloques(Bloque bloque,int indice) {
		this.bloque =bloque;
		
		this.next = null;
		this.indice = indice;
	}
	
}
