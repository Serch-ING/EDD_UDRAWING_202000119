package lists;

import object.client;
import lists.Pila_Images;
import lists.Cola_Reception.Nodo_Cola_Reception;

import java.io.FileWriter;
import java.io.PrintWriter;

import functionalities.general;
//list of windows
public class Simple_Windows {

	Nodo_Simple_Windows primero;
	//contructor
	public Simple_Windows() {
		this.primero = null;
	}
	
	//insert new data
	public void insert(int info) {
		Nodo_Simple_Windows new_node = new Nodo_Simple_Windows(info);
		if (isNone()) {
			this.primero = new_node;
		} else {
			new_node.next = this.primero;
			this.primero = new_node;
		}
	}
	
	//show the list
	public void showList() {
		if (isNone() == false) {
			Nodo_Simple_Windows actual = this.primero;
			while (actual != null) {
				System.out.println(actual.noVentanilla);
				actual = actual.next;
			}
		}
	}

	//recolect the imges of clients
	public void recolect_img(Cola_Print bw, Cola_Print color, Circular_Doble_espera Waiting_clients) {
		if (isNone() == false) {
			Nodo_Simple_Windows actual = this.primero;
			while (actual != null) {
				if (actual.cliente != null) {
					takeimg_client(actual, bw, color,Waiting_clients);
				}
				actual = actual.next;
			}
		}
	}
	
	//giving correspond image to the client
	public void takeimg_client(Nodo_Simple_Windows windown, Cola_Print bw, Cola_Print color,Circular_Doble_espera Waiting_clients) {
		if (windown.cliente.img_bw > 0) {
			windown.cliente.img_bw -= 1;
			windown.PilaImg.Push(windown.cliente.id, false);
			System.out.println("Ventanilla: " + windown.noVentanilla
					+ " recibio: una imagen blanco y negro de cliente: " + windown.cliente.id);
		} else {
			if (windown.cliente.img_color > 0) {
				windown.cliente.img_color -= 1;
				windown.PilaImg.Push(windown.cliente.id, true);
				System.out.println("Ventanilla: " + windown.noVentanilla + " recibio: una imagen a color de cliente: "
						+ windown.cliente.id);
			} else {
				Imgs_to_printer(windown, bw, color);
				Clien__waiting(windown.cliente, Waiting_clients);
				windown.cliente = null;
			}
		}
	}
	
	//send img to the printer
	public void Imgs_to_printer(Nodo_Simple_Windows windown, Cola_Print bw, Cola_Print color) {
		windown.PilaImg.Pop_to_printer(windown,bw, color);
	}

	//send the cliend wait
	public void Clien__waiting(client cliente,Circular_Doble_espera Waiting_clients) {
		System.out.println("Cliente: " + cliente.id + " Esperando sus imagenes");
		Waiting_clients.insert(cliente);
		//Waiting_clients.showList();
	}
	
	//insert a new client
	public void insert_client(client cliente,int pasos) {

		Nodo_Simple_Windows actual = this.primero;
		while (actual != null && actual.cliente != null) {
			actual = actual.next;

		}
		if (actual != null && actual.cliente == null) {
			// System.out.println("Ventana libre: " + actual.noVentanilla);
			actual.cliente = cliente;
			cliente.PasoIngresado = pasos;
			cliente.VentanillaIngresada = actual.noVentanilla;
			System.out.println("Cliente: " + cliente.id + " paso a ventanilla: " + actual.noVentanilla);
		}

	}

	//search if is posible add a client
	public Boolean Search_disposition() {
		if (isNone() == false) {
			Nodo_Simple_Windows actual = this.primero;
			while (actual != null && actual.cliente != null) {
				actual = actual.next;
				if (actual == null) {
					// System.out.println(" Ventanas ocupadas");
					return false;
				}
			}
			if (actual != null && actual.cliente == null) {
				// System.out.println("Ventana libre: " + actual.noVentanilla);
				return true;

			}
		}
		return false;
	}

	/*
	 * public void Delete(Object data) { if (isNone() == false) {
	 * Nodo_Simple_Windows actual = this.primero; Nodo_Simple_Windows anterior =
	 * null;
	 * 
	 * while (actual != null && actual.info != data) { anterior = actual; actual =
	 * actual.next; }
	 * 
	 * if (anterior == null) { this.primero = actual.next;
	 * System.out.println("Se elimino el dato: " + data); } else if (actual != null)
	 * { anterior.next = actual.next; actual.next = null;
	 * System.out.println("Se elimino el dato: " + data); } else {
	 * System.out.println("No se encontro el dato a eliminar: " + data); } } }
	 */
	
	//verify if the list isnull
	public Boolean isNone() {
		return this.primero == null;
	}
		
	//create the text of graphivz
	public String Text_Graphivz() {
		StringBuilder dot = new StringBuilder();
		dot.append("digraph L {\n");
		dot.append("node[shape=note fillcolor=\"#A181FF\" style =filled]\n");
		dot.append("subgraph cluster_p{\n");
		dot.append("    label= \" Ventanillas \"\n");
		dot.append("    bgcolor = \"#FF7878\"\n");

		String nombresNodos = "";
		String conexiones = "";
		Nodo_Simple_Windows aux = this.primero;
		
		
		while (aux != null) {
			String hashClient ="";
			String infoClient ="";
			String pilaImg ="";
			String pilarank ="";
			//String info ="ID: " + aux.cliente.id + "\nNombre" + aux.cliente.name + "\nImg_C: " + aux.cliente.img_bwTotal + "\nImg_BN: " + aux.cliente.img_bwTotal;
			String info ="Ventanillas: " + aux.noVentanilla ;
			nombresNodos += "Nodo" + aux.hashCode() + "[label=\"" + info + "\",fillcolor=\"#81FFDA\",group=" + aux.noVentanilla + "]\n";
			if (aux.next != null) {
				conexiones += String.format("\nNodo%d -> Nodo%d\n", aux.hashCode(), aux.next.hashCode());
				
				}
			if(aux!=null) {
				if(aux.cliente != null) {
					infoClient ="ID: " + aux.cliente.id + "\nNombre" + aux.cliente.name + "\nImg_C: " + aux.cliente.img_colorTotal + "\nImg_BN: " + aux.cliente.img_bwTotal;
					//System.out.println(infoClient);
					conexiones += "\nCliente" +  aux.cliente.hashCode() + "[label=\"" + infoClient + "\",fillcolor=\"#B36AF9\",group=" + aux.noVentanilla + "]\n";
					hashClient =  ",Cliente" + aux.cliente.hashCode();
					conexiones+= "Cliente" + aux.cliente.hashCode() + "-> Nodo" + aux.hashCode();
				}
				if(aux.PilaImg.primero != null) {
					pilaImg = aux.PilaImg.Text_Graphivz();
					pilarank = aux.PilaImg.Text_Graphivz_rank();
					//System.out.println(pilaImg);
					//System.out.println(pilarank);
					conexiones += "\n" + pilaImg;
					conexiones += String.format("\nNodo%d -> Nodo%d\n", aux.hashCode(), aux.PilaImg.primero.hashCode());
				}
				conexiones += "\n{rank=same;Nodo" +  aux.hashCode() + hashClient + pilarank +"}";
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
			Create_File("Simple_Windows.dot", Text_Graphivz());
			//System.out.println(Text_Graphivz());
			ProcessBuilder pb;
			pb = new ProcessBuilder("dot", "-Tpng", "-o", "Simple_Windows.png", "Simple_Windows.dot");
			pb.redirectErrorStream(true);
			pb.start();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	//open the graph
	public void openimg() {
		try {
			String url = "Simple_Windows.png";
			ProcessBuilder p = new ProcessBuilder();
			p.command("cmd.exe", "/c", url);
			p.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

//node of the list
class Nodo_Simple_Windows {

	public Nodo_Simple_Windows next;
	public int noVentanilla;
	public client cliente;
	public Pila_Images PilaImg = new Pila_Images();
	//constructor
	public Nodo_Simple_Windows(int noVentanilla) {
		this.next = null;
		this.noVentanilla = noVentanilla;
		this.cliente = null;
	}
}
