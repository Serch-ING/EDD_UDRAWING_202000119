package lists;

import java.io.FileWriter;
import java.io.PrintWriter;
import object.client;

public class Simple_Clients_Served {

	Nodo_Simple_Client_Served primero;

	public Simple_Clients_Served() {
		this.primero = null;
	}

	public void insert(client info) {//log_2(n)
		Nodo_Simple_Client_Served new_node = new Nodo_Simple_Client_Served(info);
		if (isNone()) {
			this.primero = new_node;
		} else {
			Nodo_Simple_Client_Served actual = this.primero;
			while (actual.next != null) {
				actual = actual.next;
			}
			actual.next = new_node;
		}
	}

	public void showList() {
		if (isNone() == false) {
			Nodo_Simple_Client_Served actual = this.primero;
			while (actual != null) {
				System.out.println("\nNombre: " + actual.cliente.name +"\nVentanilla: " + actual.cliente.VentanillaIngresada + ""
						+ "\nNumero de imagenes: " + (actual.cliente.img_bwTotal + actual.cliente.img_colorTotal) + ""
								+ "\nPasos totales: " + (actual.cliente.PasoSalida - actual.cliente.PasoIngresado + "\n\n" ) );
				actual = actual.next;
			}
		}
	}

	public void Search(Object data) {
		if (isNone() == false) {
			Nodo_Simple_Client_Served actual = this.primero;
			while (actual != null && actual.cliente != data) {
				actual = actual.next;
				if (actual == null) {
					System.out.println("No se encontro el dato: " + data);
					break;
				}
			}
			if (actual != null && actual.cliente == data) {
				System.out.println("Dato encontrado: " + data);
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
		dot.append("    label= \" Clientes atendidos \"\n");
		dot.append("    bgcolor = \"#FF7878\"\n");

		String nombresNodos = "";
		String conexiones = "";
		Nodo_Simple_Client_Served aux = this.primero;
		while (aux != null) {
			String info ="\nNombre: " + aux.cliente.name +"\nAtendido por ventanilla: " + aux.cliente.VentanillaIngresada + ""
					+ "\nNumero de imagenes impresas: " + (aux.cliente.img_bwTotal + aux.cliente.img_colorTotal) + ""
					+ "\nPasos totales en sistema: " + (aux.cliente.PasoSalida - aux.cliente.PasoIngresado); 
			nombresNodos += "Nodo" + aux.hashCode() + "[label=\"" + info + "\",fillcolor=\"#81FFDA\"]\n";
			if (aux.next != null)
				conexiones += String.format("Nodo%d -> Nodo%d\n", aux.hashCode(), aux.next.hashCode());
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
			if(isNone()) {
				String graph = "digraph L {\r\n"
						+ "node[shape=note fillcolor=\"#A181FF\" style =filled]\r\n"
						+ "subgraph cluster_p{\r\n"
						+ "    label= \" Clientes atendidos \"\r\n"
						+ "    bgcolor = \"#FF7878\"\r\n"
						+ "Nodo1008925772[label=\"Vacio\",fillcolor=\"#81FFDA\"]\r\n"
						+ "\r\n"
						+ "}}";
				Create_File("Simple_Clients_Served.dot", graph);
			}else {
				Create_File("Simple_Clients_Served.dot", Text_Graphivz());
			}

			//System.out.println(Text_Graphivz());
			ProcessBuilder pb;
			pb = new ProcessBuilder("dot", "-Tpng", "-o", "Simple_Clients_Served.png", "Simple_Clients_Served.dot");
			pb.redirectErrorStream(true);
			pb.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void openimg() {
		try {
			String url = "Simple_Clients_Served.png";
			ProcessBuilder p = new ProcessBuilder();
			p.command("cmd.exe", "/c", url);
			p.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	class Nodo_Simple_Client_Served {

		Nodo_Simple_Client_Served next;
		client cliente;

		public Nodo_Simple_Client_Served(client cliente) {
			this.next = null;
			this.cliente = cliente;
		}
}
}
