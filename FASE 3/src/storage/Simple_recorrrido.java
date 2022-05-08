package storage;

import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import storage.ListaDG.Nodo_Simple;
import storage.Merkle_tree.nodo_merkle;
 
public class Simple_recorrrido {

	Nodo_Simple_recorrido primero;

	public Simple_recorrrido() {
		this.primero = null;
	}

	public void insert(Object info, Nodo_Simple data) {
		Nodo_Simple_recorrido new_node = new Nodo_Simple_recorrido(info,data);
		if (isNone()) {
			this.primero = new_node;
		} else {
			new_node.next = this.primero;
			this.primero = new_node;
		}
	}

	public void showList() {
		if (isNone() == false) {
			Nodo_Simple_recorrido actual = this.primero;
			while (actual != null) {
				System.out.println(actual.info);
				actual = actual.next;
			}
		}
	}	

	public void showList_recorrido() {
		int Contador =0;
		System.out.println("Comenzando recorrido");
		if (isNone() == false) {
			Nodo_Simple_recorrido actual = this.primero;
			while (actual != null) {
				JOptionPane.showMessageDialog(null,"Mensajero se encuntra en:\n id: " + actual.data.id  + "\nUbicacion: " + actual.data.departamento + ", " + actual.data.nombre + "\nTimpo: " + Contador + " h");
				Contador +=  Math.floor(Math.random()*(1-3+1)+3);
				System.out.print(actual.data.id + " -> " );
				actual = actual.next;
			}
			
			System.out.println("Se termino recorrido");
		}
	}
	
	//cresate the text of graphiz
	public String Text_Graphivz() {
		StringBuilder dot = new StringBuilder();
		dot.append("digraph L {\n");
		dot.append("node[shape=note fillcolor=\"#A181FF\" style =filled]\n");
		dot.append("subgraph cluster_p{\n");
		
		dot.append("    label= \" Lista viaje \"\n");
		dot.append(" raiz[label = \"VIAJE\" fillcolor=\"#FFD581\" ]");
	
		dot.append("    bgcolor = \"#FF7878\"\n");
		
		
		dot.append("");
		String nombresNodos = "";
		String conexiones = "";
		Nodo_Simple_recorrido aux = this.primero;
		
		if(this.primero!= null) {
			conexiones+="\nraiz->Nodo" +  this.primero.hashCode();
		}
		
		while (aux != null) {
			
			
			String info = "\nId Lugar: " + aux.data.id + "\n"+ aux.data.nombre;
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
	

	public void Search(Object data) {
		if (isNone() == false) {
			Nodo_Simple_recorrido actual = this.primero;
			while (actual != null && actual.info != data) {
				actual = actual.next;
				if (actual == null) {
					System.out.println("No se encontro el dato: " + data);
					break;
				}
			}
			if (actual != null && actual.info == data) {
				System.out.println("Dato encontrado: " + data);
			}
		}
	}

	public void Delete(Object data) {
		if (isNone() == false) {
			Nodo_Simple_recorrido actual = this.primero;
			Nodo_Simple_recorrido anterior = null;

			while (actual != null && actual.info != data) {
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
}

class Nodo_Simple_recorrido {
	
	Nodo_Simple_recorrido next;
	Nodo_Simple data;
	Object info;
	
	//int 0 = inicio
	//int 1 = final
	//int 2 = trancursp
	String ubicacion;
	public Nodo_Simple_recorrido(Object info,Nodo_Simple data) {
		this.data =data;
		
		this.next = null;
		this.info = info;
	}
}
