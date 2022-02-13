package lists;

import java.io.FileWriter;
import java.io.PrintWriter;

import object.client;

public class Circular_Doble_espera {
	Nodo_Doble_waiting_clients primero;
	Nodo_Doble_waiting_clients ultimo;

	public Circular_Doble_espera() {
		
		this.primero = null;
		this.ultimo = null;
	}

	public void insert(client client) {
		Nodo_Doble_waiting_clients new_node = new Nodo_Doble_waiting_clients(client);
		if (isNone()) {
			this.primero = new_node;
			this.ultimo = new_node;
		} else {
			this.ultimo.next = new_node;
			new_node.next = this.primero;
			new_node.previous = this.ultimo;
			this.ultimo = new_node;
			this.primero.previous = ultimo;
		}
	}

	public void showList() {
		Nodo_Doble_waiting_clients actual = this.primero;

		if (isNone() == false) {
			do {
				System.out.println(actual.client.id);
				actual = actual.next;
			} while (actual != this.primero);
		}
	}

	public void Search(int idClient) {

		if (isNone() == false) {
			Nodo_Doble_waiting_clients actual = this.primero;
			Boolean Encontrado = true;
			do {
				if (actual.client.id == idClient) {
					// System.out.println("Dato encontrado: " + idClient);
					Encontrado = false;
					break;
				} else {
					actual = actual.next;
				}
			} while (actual != this.primero);

			if (Encontrado) {
				System.out.println("No se encontro el dato: " + idClient);
			}

		} else {
			System.out.println("Vacio");
		}
	}

	public void exit_of_system() {
		Nodo_Doble_waiting_clients actual = this.primero;
		if (isNone() == false) {
			do {
				if (actual.client.img_bwTotal == actual.client.img_bw
						&& actual.client.img_colorTotal == actual.client.img_color) {
					Delete(actual.client.id);
				}
				actual = actual.next;
				if(isNone() == true) {
					break;
				}
			} while (actual != this.primero);
		}
	}

	public void Give_img(int idClient, Boolean Lleva_color) {

		if (isNone() == false) {
			Nodo_Doble_waiting_clients actual = this.primero;
			Boolean Encontrado = true;
			do {
				if (actual.client.id == idClient) {
					// System.out.println("Dato encontrado: " + idClient);
					if (Lleva_color) {
						actual.client.img_color += 1;
					} else {
						actual.client.img_bw += 1;
					}

					Encontrado = false;
					break;
				} else {
					actual = actual.next;
				}
			} while (actual != this.primero);

			if (Encontrado) {
				System.out.println("No se encontro el dato: " + idClient);
			}

		} else {
			System.out.println("Vacio");
		}
	}

	public void Delete(int idClient) {
		if (isNone() == false) {

			Nodo_Doble_waiting_clients actual = this.primero;
			Nodo_Doble_waiting_clients anterior = this.ultimo;
			if (this.primero == this.ultimo) { 
				if (actual.client.id == idClient) {
					System.out.println("Cliente: " + actual.client.id + " salio del sistema last");
					this.primero = null;
					this.ultimo = null;
				}
			} else {
				
				do {
					if (actual.client.id == idClient) {
						if (actual == this.primero) {
							this.primero = this.primero.next;
							this.ultimo.next = this.primero;
							this.primero.previous = this.ultimo;
						}
						if (actual == ultimo) {
							this.ultimo = anterior;
							this.primero.previous = this.ultimo;
							this.ultimo.next = this.primero;
						} else {
							anterior.next = actual.next;
							actual.next.previous = anterior;
						}
						System.out.println("Cliente: " + actual.client.id + " salio del sistema");
					}
					anterior = actual;
					actual = actual.next;
				} while (actual != this.primero);

			}
			
		}

	}

	public Boolean isNone() {
		return this.primero == null;
	}
	
	
	public String Text_Graphivz() {
		StringBuilder dot = new StringBuilder();
		dot.append("digraph L {\n");
		dot.append("node[shape=note fillcolor=\"#A181FF\" style =filled]\n");
		dot.append("subgraph cluster_p{\n");
		dot.append("    label= \" Ventanillas \"\n");
		dot.append("    bgcolor = \"#FF7878\"\n");

		String nombresNodos = "";
		String conexiones = "";
		Nodo_Doble_waiting_clients aux = this.primero;
		
		
		while (aux != null) {
			String hashClient ="";
			String infoClient ="";
			String pilaImg ="";
			String pilarank ="";
			
			String info ="ID: " + aux.client.id + "\nNombre" + aux.client.name + "\nImg_C: " + aux.client.img_bwTotal + "\nImg_BN: " + aux.client.img_bwTotal;
			//String info ="Ventanillas: " + aux.noVentanilla ;
			nombresNodos += "Nodo" + aux.hashCode() + "[label=\"" + info + "\",fillcolor=\"#81FFDA\",group=" + aux.noVentanilla + "]\n";
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

	public void Draw_Graphiz() {

		try {
			Create_File("Circular_Doble_espera.dot", Text_Graphivz());
			//System.out.println(Text_Graphivz());
			ProcessBuilder pb;
			pb = new ProcessBuilder("dot", "-Tpng", "-o", "Circular_Doble_espera.png", "Circular_Doble_espera.dot");
			pb.redirectErrorStream(true);
			pb.start();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openimg() {
		try {
			String url = "Circular_Doble_espera.png";
			ProcessBuilder p = new ProcessBuilder();
			p.command("cmd.exe", "/c", url);
			p.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

class Nodo_Doble_waiting_clients {

	Nodo_Doble_waiting_clients next, previous;
	client client;

	public Nodo_Doble_waiting_clients(client client) {
		this.next = this;
		this.previous = this;
		this.client = client;
	}

}