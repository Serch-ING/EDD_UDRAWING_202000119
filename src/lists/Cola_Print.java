package lists;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;
//list if impresor
public class Cola_Print {
	public static ThreadLocalRandom tlr = ThreadLocalRandom.current();

	Nodo_Cola_Print primero;
	public int pasosTotales;
	//contructor
	public Cola_Print(int pasosTotales) {
		this.primero = null;
		this.pasosTotales = pasosTotales;
	}
	
	//add new data
	public void enqueue(int id_cliente) {

		Nodo_Cola_Print new_node = new Nodo_Cola_Print(id_cliente);
		if (isNone()) {
			this.primero = new_node;
		} else {
			Nodo_Cola_Print actual = this.primero;

			while (actual.next != null) {

				actual = actual.next;
			}

			actual.next = new_node;
		}
	}
	
	//print img
	public void printing(Circular_Doble_espera Waiting_clients) {
		if (isNone() == false) {
			this.primero.pasos += 1;
			if (this.primero.pasos == this.pasosTotales) {
				Boolean lleva_color;
				String tipo = "";
				if (this.pasosTotales == 1) {
					tipo = "Blanco y negro";
					lleva_color = false;
				} else {
					tipo = "color";
					lleva_color = true;
				}
				Waiting_clients.Give_img(this.primero.id_cliente, lleva_color);
				System.out.println("Se entrego imagen: " + tipo + " a cliente " + this.primero.id_cliente);
				Dequeue_last();
			}
		}
	}

	//get out the list
	public void Dequeue_last() {
		if (isNone()) {
			// System.out.println("cola vacia");
		} else {
			Nodo_Cola_Print anterior = this.primero;
			this.primero = this.primero.next;
			anterior = null;
		}

	}
	
	//show the top data
	public void Peek() {
		System.out.println(this.primero.id_cliente);

	}

	//deledt top data
	public void Dequeue() {
		Nodo_Cola_Print anterior = this.primero;
		this.primero = this.primero.next;

	}

	/*
	 * public Boolean Dequeue_posibility() { if (isNone() == true) {
	 * System.out.println("cola vacia"); return false; } else { return true; } }
	 */
	
	//show the list
	public void showList() {
		if (isNone() == false) {
			Nodo_Cola_Print actual = this.primero;
			while (actual != null) {
				// System.out.println(actual.info+" 1");
				String color = "";
				if (this.pasosTotales == 1) {
					color = "Blanco negro";
				} else if (this.pasosTotales == 2) {
					color = "Color";
				}
				System.out.println("id: " + actual.id_cliente + " TIpo: " + color + " pasos: " + this.pasosTotales);
				actual = actual.next;
			}
		}
	}
	
	//cresate the text of graphiz
	public String Text_Graphivz() {
		StringBuilder dot = new StringBuilder();
		dot.append("digraph L {\n");
		dot.append("node[shape=note fillcolor=\"#A181FF\" style =filled]\n");
		dot.append("subgraph cluster_p{\n");
		if (this.pasosTotales == 1) {
			dot.append("    label= \" Cola impresora Blaco y Negro \"\n");
			dot.append(" raiz[label = \"Impresora Blaco y Negro\" fillcolor=\"#FFD581\" ]");
		} else {
			dot.append("    label= \" Cola impresora a Color \"\n");
			dot.append(" raiz[label = \"Impresora a Color\" fillcolor=\"#FFD581\" ]");
		}
		dot.append("    bgcolor = \"#FF7878\"\n");
		
		
		dot.append("");
		String nombresNodos = "";
		String conexiones = "";
		Nodo_Cola_Print aux = this.primero;
		
		if(this.primero!= null) {
			conexiones+="\nraiz->Nodo" +  this.primero.hashCode();
		}
		
		while (aux != null) {
			
			
			String info = "IMG C \nId Cliente: " + aux.id_cliente;
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
	
	//create the dot file
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
	
	//draw the graph
	public void Draw_Graphiz() {

		try {
			ProcessBuilder pb;
			if(this.pasosTotales ==1) {
				Create_File("Cola_Print_bw.dot", Text_Graphivz());
				pb = new ProcessBuilder("dot", "-Tpng", "-o", "Cola_Print_bw.png", "Cola_Print_bw.dot");
			}else {
				Create_File("Cola_Print_color.dot", Text_Graphivz());
				pb = new ProcessBuilder("dot", "-Tpng", "-o", "Cola_Print_color.png", "Cola_Print_color.dot");
			}
			
			pb.redirectErrorStream(true);
			pb.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//show the graph
	public void openimg() {
		try {
			String url = "";
			if(this.pasosTotales==1) {
				url = "Cola_Print_bw.png";
			}else {
				url = "Cola_Print_color.png";
			}
			
			ProcessBuilder p = new ProcessBuilder();
			p.command("cmd.exe", "/c", url);
			p.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * public void Search(Object data) { if (isNone() == false) {
	 * Nodo_Cola_Reception actual = this.primero; while (actual != null &&
	 * actual.cliente != data) { actual = actual.next; if (actual == null) {
	 * System.out.println("No se encontro el dato: " + data); break; } } if (actual
	 * != null && actual.cliente == data) { System.out.println("Dato encontrado: " +
	 * data); } } }
	 * 
	 * public void Delete(Object data) { if (isNone() == false) {
	 * Nodo_Cola_Reception actual = this.primero; Nodo_Cola_Reception anterior =
	 * null;
	 * 
	 * while (actual != null && actual.cliente != data) { anterior = actual; actual
	 * = actual.next; }
	 * 
	 * if (anterior == null) { this.primero = actual.next;
	 * System.out.println("Se elimino el dato: " + data); } else if (actual != null)
	 * { anterior.next = actual.next; actual.next = null;
	 * System.out.println("Se elimino el dato: " + data); } else {
	 * System.out.println("No se encontro el dato a eliminar: " + data); } } }
	 */
	
	//verify its none
	public Boolean isNone() {
		return this.primero == null;
	}
	
	//node of the list
	public class Nodo_Cola_Print {

		public Nodo_Cola_Print next;
		public int id_cliente;
		public int pasos;
		//contructor
		public Nodo_Cola_Print(int id_cliente) {
			this.next = null;
			this.id_cliente = id_cliente;
			this.pasos = 0;
		}
	}

}
