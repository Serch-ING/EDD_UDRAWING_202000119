package storage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.json.simple.JSONArray;




public class Simple_Album {
	public LinkedList<String> albums = new LinkedList<String>();
	Nodo_Simple primero;
	Object[][] data;

	public Simple_Album() {
		this.primero = null;
	}
	
	public Object[][] data_toshow() {
		data = new Object[albums.size()][2];
		int contador = 0;
		
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
			
			while (actual != null) {
				data[contador][0] = actual.info;
				System.out.println("Album: " + actual.info);
				
				String temp="";
				
				for (String i : actual.images) {
					temp += i +" , ";
					System.out.println(i);
				}
				if(temp.equals("")) {
					data[contador][1] = "Vacio";
				}else {
					data[contador][1] = temp;
				}
				
				contador++;
				actual = actual.next;
			}
		}
		
		return data;
	}

	public void insert(String info) {
		Nodo_Simple new_node = new Nodo_Simple(info);
		if (isNone()) {
			this.primero = new_node;
		} else {
			new_node.next = this.primero;
			this.primero = new_node;
		}
	}
	
	public void recorrardata() {
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
			while (actual != null) {
				System.out.println("Album: " + actual.info);
				for (String i : actual.images) {
					System.out.println(i);
				}
				actual = actual.next;
			}
		}
	}
	
	public void cantidad_albums() {
		albums = new LinkedList<String>();
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
			while (actual != null) {
				albums.add(actual.info);
				System.out.println(actual.info);
				actual.images = new LinkedList<String>();
				if(actual.Sublist.isNone() == false) {
					System.out.println("->");
					actual.images = actual.Sublist.catindad_imagenes();
					System.out.println("----");
				}
				actual = actual.next;
			}
		}
	}

	
	public void graph(String name) {
		int contador = 0;
		StringBuilder dot = new StringBuilder();
		
		dot.append("digraph G {\n");
		dot.append("node[shape=note fillcolor=\"#A181FF\" style =filled]\n");
		dot.append("subgraph cluster_p{\n");
		dot.append("    label= \" Albumes \"\n");
		dot.append("    bgcolor = \"#FF7878\"\n");
		dot.append("");
		String nombresNodos = "";
		String conexiones = "";
		String tempNombreNodos = "";
		String temp = "";
		String enlaces= "";
		
		Nodo_Simple aux = this.primero;
		
		while (aux != null) {
			
			
			String info =  aux.info;
			tempNombreNodos = "Nodo" + aux.hashCode() ;
			nombresNodos +="	" +tempNombreNodos+ "[label=\"" + info + "\",fillcolor=\"#81FFDA\" group=" + contador +" ]\n";
			temp += tempNombreNodos+";";
			
			if (aux.next != null) {
				conexiones += String.format("	\nNodo%d -> Nodo%d\n", aux.hashCode(), aux.next.hashCode());
			}
			
			if(aux.Sublist.isNone() == false) {
				
				enlaces += aux.Sublist.enlaces(contador);
				enlaces += "	\nNodo" + aux.hashCode() + "->" + aux.Sublist.primero.info+";\n";
			}
			contador++;
			aux = aux.next;
		}

		String rank = "{rank=same;" + temp + "}\n";
		
		dot.append(nombresNodos);
		dot.append(conexiones);
		dot.append(enlaces);
		dot.append(rank);

		dot.append("}}");

		//System.out.println(dot);
		Draw_Graphiz(name,dot.toString());
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
	
	public void Draw_Graphiz(String name, String dot) {

		try {
			Create_File("Grafico\\" + name + ".dot", dot);
			//System.out.println(Text_Graphivz());
			ProcessBuilder pb;
			pb = new ProcessBuilder("dot", "-Tpng", "-o", "Grafico\\" + name + ".png", "Grafico\\" + name + ".dot");
			pb.redirectErrorStream(true);
			pb.start();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showList() {
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
			while (actual != null) {
				System.out.println(actual.info);
				actual = actual.next;
			}
		}
	}

	public void Search(String data) {
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
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

	public boolean SearchValidacion(String data) {
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
			while (actual != null && !(actual.info.equals(data))) {
				actual = actual.next;
				if (actual == null) {
					return false;

				}
			}
			if (actual != null && actual.info.equals(data)) {
				return true;

			}
		}
		return false;
	}

	public Nodo_Simple SearchValidacionNode(String data) {
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
			while (actual != null && !(actual.info.equals(data))) {
				actual = actual.next;
				if (actual == null) {
					return null;

				}
			}
			if (actual != null && actual.info.equals(data)) {
				return actual;

			}
		}
		return null;
	}

	public void insernews(Nodo_Simple actual, JSONArray data) {
		if (actual != null) {
			for (Object object2 : data) {
				Integer img_no = ((Long) object2).intValue();
				actual.Sublist.insert(img_no);
				// System.out.println(img_no);
			}
		}

	}

	public void Delete(String data) {
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
			Nodo_Simple anterior = null;

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

class Nodo_Simple {
	LinkedList<String> images = new LinkedList<String>();
	Nodo_Simple next;
	String info;
	Simple_Sublist_Album Sublist = new Simple_Sublist_Album();

	public Nodo_Simple(String info) {
		this.next = null;
		this.info = info;
	}
}
