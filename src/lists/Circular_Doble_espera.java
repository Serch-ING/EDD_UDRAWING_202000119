package lists;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;

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
					System.out.println("Cliente: " + actual.client.id + " salio del sistema");
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
		dot.append("    label= \"Lista de Espera \"\n");
		dot.append("    bgcolor = \"#FF7878\"\n");

		String nombresNodos = "";
		String conexiones = "";
		Nodo_Doble_waiting_clients aux = this.primero;
		
		do {
		
			String hashBN ="";
			String hashColor ="";
			Boolean Entro = false;
			
			String info ="ID: " + aux.client.id + "\nNombre" + aux.client.name + "\nImg_C: " + aux.client.img_colorTotal + "\nImg_BN: " + aux.client.img_bwTotal;
			//String info ="Ventanillas: " + aux.noVentanilla ;
			nombresNodos += "Nodo" + aux.hashCode() + "[label=\"" + info + "\",fillcolor=\"#81FFDA\"]\n";
			if (aux.next != null) {
				conexiones += String.format("\nNodo%d -> Nodo%d\n", aux.hashCode(), aux.next.hashCode());
				
				}
			if(aux.client.img_bw >0) {
				Entro = true;
				String connect = aux.hashCode()  + "0000" + 1;
				conexiones+= "Nodo" +  aux.hashCode() + "->Nodo" + connect;
				//conexiones += String.format("\nNodo%d -> Nodo%d\n", aux.hashCode(),connect );
				for (int i = 1; i <= aux.client.img_bw; i++) {
					conexiones+="\nNodo" + aux.hashCode() + "0000" + i + "[label=\"IMG BN\",fillcolor=\"#C388FC\"]";
					hashBN+= ",Nodo" + aux.hashCode() + "0000" + i ;
					if(i+1 <= aux.client.img_bw) {
						String connected = aux.hashCode()  + "0000" + (i +1);
						conexiones+= "Nodo" +  aux.hashCode() + "0000" + i+ "->Nodo" + connected;
						
					}
				}
			}
			if(aux.client.img_color >0) {
				if(Entro) {
					String connect = aux.hashCode()  + "1111" + 1;
					conexiones+= "\nNodo" + aux.hashCode() +"0000" + aux.client.img_bw + "->Nodo" + connect;
				}else {
					String connect = aux.hashCode()  + "1111" + 1;
					conexiones+= "Nodo" +  aux.hashCode() + "->Nodo" + connect;
				}
				
				for (int i = 1; i <= aux.client.img_color; i++) {
					conexiones+="\nNodo" + aux.hashCode() + "1111" + i + "[label=\"IMG C\",fillcolor=\"#C388FC\"]";
					hashColor+= ",Nodo" + aux.hashCode() + "1111" + i ;
					if(i+1 <= aux.client.img_color) {
						String connected = aux.hashCode()  + "1111" + (i +1);
						conexiones+= "Nodo" +  aux.hashCode() + "1111" + i+ "->Nodo" + connected;
						
					}
				}
			}
			
			conexiones += "\n{rank=same;Nodo" +  aux.hashCode() + hashBN + hashColor +"}";
			
			aux = aux.next;
		} while (aux != this.primero);
		
		
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
			
			if(isNone()) {
				String graph = "digraph L {\r\n"
						+ "node[shape=note fillcolor=\"#A181FF\" style =filled]\r\n"
						+ "subgraph cluster_p{\r\n"
						+ "    label= \"Lista de Espera\"\r\n"
						+ "    bgcolor = \"#FF7878\"\r\n"
						+ "Nodo1008925772[label=\"Vacio\",fillcolor=\"#81FFDA\"]\r\n"
						+ "\r\n"
						+ "}}";
				Create_File("Circular_Doble_espera.dot", graph);
			}else {
				Create_File("Circular_Doble_espera.dot", Text_Graphivz());
			}
			
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