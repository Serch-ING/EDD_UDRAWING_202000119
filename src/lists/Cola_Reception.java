package lists;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;
import object.client;
//list of reception
public class Cola_Reception {
	public static ThreadLocalRandom tlr = ThreadLocalRandom.current();
	
	Nodo_Cola_Reception primero;
	//cjontructor
	public Cola_Reception() {
		this.primero = null;
	}
	
	//add new data to the lisdt
	public void enqueue(int id, String name, int img_color, int img_bw,int pasoIngreso) {
		client cliente = new client(id, name, img_color, img_bw);

		Nodo_Cola_Reception new_node = new Nodo_Cola_Reception(cliente);
		if (isNone()) {

			if (id == -1) {
				new_node.cliente.id = 1;
			}
			new_node.cliente.PasoIngresado = pasoIngreso;
			this.primero = new_node;
			
		} else {

			int idAnterior = 2;
			Nodo_Cola_Reception actual = this.primero;

			while (actual.next != null) {

				actual = actual.next;
				if (id == -1) {
					idAnterior = actual.cliente.id;
				}
			}

			if (id == -1) {
				new_node.cliente.id = idAnterior + 1;
			}
			new_node.cliente.PasoIngresado = pasoIngreso;
			actual.next = new_node;
		}
	}
	
	
	//generate a rsmadom client
	public void Generate_Random(int pasoIngreso) {
		int cantidad =  tlr.nextInt(0, 3 + 1);
		for (int waiting = 0; waiting <cantidad; waiting++) {
			
			int imgs = tlr.nextInt(1, 4 + 1);
			int img_color = 0;
			int img_bw = 0;
			int aux;
			for (int i = 0; i < imgs; i++) {
				aux = tlr.nextInt(3, 4 + 1);
				if (aux % 2 == 0) {
					img_color += 1;
				} else {
					img_bw += 1;
				}
			}

			String[] names = { "Reid", "Brady", "Lorene", "Randi", "Eal", "Dene", "Karry", "Astrix", "Davina", "Ellsworth",
					"Dorey", "Sanderson", "Marylynne", "Zeke", "Stu", "Fidelity", "Ludvig", "Glenn", "Phylis", "Adlai",
					"Corbet", "Theodora", "Travus", "Dannel", "Delora", "Paulette", "Haskel", "Clovis", "Peder", "Edwina",
					"Helli", "Zachary", "Godiva", "Sabrina", "Sheffield", "Karry" };

			String[] lastnames = { "Sowersby", "Stebles", "Vittore", "Rivalant", "Plummer", "Leon", "Ickovits", "Tayspell",
					"Scading", "McElrath", "Bricknall", "Plesing", "Bagshawe", "Pinnion", "Killby", "Gange", "Fust",
					"Sindell", "Lawrenceson", "Dimbylow", "Misson", "Rudolf", "Aldin", "McCafferky", "Stirton", "Meagher",
					"Liepmann", "Ramelot", "Selvester", "Montague", "Meneghelli", "Korneichik", "Peever", "Flanagan",
					"Martinolli" };

			int noName = tlr.nextInt(0, names.length);
			int noLastnames = tlr.nextInt(0, lastnames.length);

			String Name = names[noName] + " " + lastnames[noLastnames];
			//System.out.println("Ramdon color: " + img_color + "BN: " + img_bw);
			enqueue(-1, Name, img_color, img_bw,pasoIngreso);
		}
		/*
		int imgs = tlr.nextInt(1, 4 + 1);
		int img_color = 0;
		int img_bw = 0;
		int aux;
		for (int i = 0; i < imgs; i++) {
			aux = tlr.nextInt(3, 4 + 1);
			if (aux % 2 == 0) {
				img_color += 1;
			} else {
				img_bw += 1;
			}
		}

		String[] names = { "Reid", "Brady", "Lorene", "Randi", "Eal", "Dene", "Karry", "Astrix", "Davina", "Ellsworth",
				"Dorey", "Sanderson", "Marylynne", "Zeke", "Stu", "Fidelity", "Ludvig", "Glenn", "Phylis", "Adlai",
				"Corbet", "Theodora", "Travus", "Dannel", "Delora", "Paulette", "Haskel", "Clovis", "Peder", "Edwina",
				"Helli", "Zachary", "Godiva", "Sabrina", "Sheffield", "Karry" };

		String[] lastnames = { "Sowersby", "Stebles", "Vittore", "Rivalant", "Plummer", "Leon", "Ickovits", "Tayspell",
				"Scading", "McElrath", "Bricknall", "Plesing", "Bagshawe", "Pinnion", "Killby", "Gange", "Fust",
				"Sindell", "Lawrenceson", "Dimbylow", "Misson", "Rudolf", "Aldin", "McCafferky", "Stirton", "Meagher",
				"Liepmann", "Ramelot", "Selvester", "Montague", "Meneghelli", "Korneichik", "Peever", "Flanagan",
				"Martinolli" };

		int noName = tlr.nextInt(0, names.length);
		int noLastnames = tlr.nextInt(0, lastnames.length);

		String Name = names[noName] + " " + lastnames[noLastnames];

		insert(-1, Name, img_color, img_bw);*/
	}
	
	//get of og the list nomal functionality
	public void Dequeue_last() {
		if (isNone()) {	
			//System.out.println("cola vacia");
		}else {
			Nodo_Cola_Reception anterior = this.primero;
			this.primero = this.primero.next;
			anterior = null;
		}
		
	}
	
	//show the first data
	public void Peek() {
		System.out.println(this.primero.cliente.name);

	}

	//get out of the list
	public client Dequeue() {

		Nodo_Cola_Reception anterior = this.primero;
		this.primero = this.primero.next;
		return anterior.cliente;

	}
	
	//is posible delete the first data
	public Boolean Dequeue_posibility() {
		if (isNone() == true) {	
			//ystem.out.println("cola vacia");
			return false;
		}else {
			return true;
		}		
	}
	
	//show the listS
	public void showList() {
		if (isNone() == false) {
			Nodo_Cola_Reception actual = this.primero;
			while (actual != null) {
				//System.out.println(actual.info+" 1");
				System.out.println("id: " + actual.cliente.id + " Name: " + actual.cliente.name + " color: " + actual.cliente.img_color+ " ByN: " + actual.cliente.img_bw);
				actual = actual.next;
			}
		}
	}
	
	//creste the text data of graphivz
	public String Text_Graphivz() {
		StringBuilder dot = new StringBuilder();
		dot.append("digraph L {\n");
		dot.append("node[shape=note fillcolor=\"#A181FF\" style =filled]\n");
		dot.append("subgraph cluster_p{\n");
		dot.append("    label= \" Cola Recepcion \"\n");
		dot.append("    bgcolor = \"#FF7878\"\n");

		String nombresNodos = "";
		String conexiones = "";
		Nodo_Cola_Reception aux = this.primero;
		while (aux != null) {
			String info ="ID: " + aux.cliente.id + "\nNombre: " + aux.cliente.name + "\nImg_C: " + aux.cliente.img_colorTotal + "\nImg_BN: " + aux.cliente.img_bwTotal;
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
	
	//create the dot dile
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
			if(isNone()) {
				String graph = "digraph L {\r\n"
						+ "node[shape=note fillcolor=\"#A181FF\" style =filled]\r\n"
						+ "subgraph cluster_p{\r\n"
						+ "    label= \" Cola Recepcion \"\r\n"
						+ "    bgcolor = \"#FF7878\"\r\n"
						+ "Nodo1008925772[label=\"Vacio\",fillcolor=\"#81FFDA\"]\r\n"
						+ "\r\n"
						+ "}}";
				Create_File("Cola_Reception.dot", graph);
			}else {
				Create_File("Cola_Reception.dot", Text_Graphivz());
			}
			
			//System.out.println(Text_Graphivz());
			ProcessBuilder pb;
			pb = new ProcessBuilder("dot", "-Tpng", "-o", "Cola_Reception.png", "Cola_Reception.dot");
			pb.redirectErrorStream(true);
			pb.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//open img
	public void openimg() {
		try {
			String url = "Cola_Reception.png";
			ProcessBuilder p = new ProcessBuilder();
			p.command("cmd.exe", "/c", url);
			p.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/*
	public void Search(Object data) {
		if (isNone() == false) {
			Nodo_Cola_Reception actual = this.primero;
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

	public void Delete(Object data) {
		if (isNone() == false) {
			Nodo_Cola_Reception actual = this.primero;
			Nodo_Cola_Reception anterior = null;

			while (actual != null && actual.cliente != data) {
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
	}*/
	
	//verify id the lsit is none
	public Boolean isNone() {
		return this.primero == null;
	}
	
	//node of list
	public class Nodo_Cola_Reception {

		public Nodo_Cola_Reception next;
		public client cliente;
		//contructor
		public Nodo_Cola_Reception(client info) {
			this.next = null;
			this.cliente = info;
		}
	}
	
}
