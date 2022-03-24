package storage;

import java.io.FileWriter;
import java.io.PrintWriter;

public class MatrizDispersa {
	public nodoDispersa raiz;
	public String DOT = "";

	public MatrizDispersa() {
		raiz = new nodoDispersa(-1, -1, "Raiz");
	}

	public nodoDispersa insertarEnFila(nodoDispersa nuevo, nodoDispersa cabeceraFila) {
		nodoDispersa actual = cabeceraFila;
		Boolean mayorEncontrado = false;
		while (true) {
			if (actual.i > nuevo.i) {
				mayorEncontrado = true;
				break;
			} else if (actual.siguiente != null) {
				actual = actual.siguiente;
			} else {
				break;
			}
		}

		if (mayorEncontrado) {

			if (actual.anterior.i == nuevo.i) {
				actual.anterior.info = nuevo.info;
			} else {
				nuevo.siguiente = actual;
				nuevo.anterior = actual.anterior;
				actual.anterior.siguiente = nuevo;
				actual.anterior = nuevo;
			}

		} else {
			if (actual.i == nuevo.i) {
				actual.info = nuevo.info;
			} else {
				actual.siguiente = nuevo;
				nuevo.anterior = actual;
			}

		}
		return nuevo;
	}

	public nodoDispersa insertarEnColumna(nodoDispersa nuevo, nodoDispersa cabeceraColumna) {
		nodoDispersa actual = cabeceraColumna;
		Boolean mayorEncontrado = false;
		while (true) {
			if (actual.j > nuevo.j) {
				mayorEncontrado = true;
				break;
			} else if (actual.abajo != null) {
				actual = actual.abajo;
			} else {
				break;
			}
		}

		if (mayorEncontrado) {
			nuevo.abajo = actual;
			nuevo.arriba = actual.arriba;
			actual.arriba.abajo = nuevo;
			actual.arriba = nuevo;
		} else {
			actual.abajo = nuevo;
			nuevo.arriba = actual;
		}

		return nuevo;
	}

	public nodoDispersa buscarColumna(Integer i) {
		nodoDispersa actual = raiz;
		while (actual != null) {
			if (actual.i == i) {
				return actual;
			}
			actual = actual.siguiente;
		}
		return null;
	}

	public nodoDispersa buscarFila(Integer j) {
		nodoDispersa actual = raiz;
		while (actual != null) {
			if (actual.j == j) {
				return actual;
			}
			actual = actual.abajo;
		}
		return null;
	}

	public nodoDispersa crearColumna(Integer i) {
		nodoDispersa nodo_new = new nodoDispersa(i, -1, "Col");
		return insertarEnFila(nodo_new, raiz);
	}

	public nodoDispersa crearFila(Integer j) {
		nodoDispersa nodo_new = new nodoDispersa(-1, j, "Row");
		return insertarEnColumna(nodo_new, raiz);
	}

	public void insertarNodo(Integer i, Integer j, Object info) {
		nodoDispersa nodo_new = new nodoDispersa(i, j, info);

		nodoDispersa columna = buscarColumna(i);
		nodoDispersa fila = buscarFila(j);

		if (columna == null && fila == null) {
			columna = crearColumna(i);
			fila = crearFila(j);

			nodo_new = insertarEnFila(nodo_new, fila);
			nodo_new = insertarEnColumna(nodo_new, columna);
		} else if (columna != null && fila == null) {
			fila = crearFila(j);
			nodo_new = insertarEnFila(nodo_new, fila);
			nodo_new = insertarEnColumna(nodo_new, columna);
		} else if (columna == null && fila != null) {
			columna = crearColumna(i);
			nodo_new = insertarEnFila(nodo_new, fila);
			nodo_new = insertarEnColumna(nodo_new, columna);
		} else {
			nodo_new = insertarEnFila(nodo_new, fila);
			nodo_new = insertarEnColumna(nodo_new, columna);
		}

	}

	public void RecorrerCompletoSimple() {
		nodoDispersa fila;
		nodoDispersa columna = raiz;

		while (columna != null) {
			fila = columna;

			while (fila != null) {

				System.out.print("[" + fila.i + " , " + fila.j + " INFO: " + fila.info + "] ");
				fila = fila.siguiente;
			}
			System.out.println("\n");
			columna = columna.abajo;
		}

	}
/*
	public void Grapgh(String name) {
		DOT = "";
		DOT += "digraph L{\n";
		DOT += "nodesep=0.4\n";
		DOT += "ranksep=0.4;\n";
		DOT += "node[shape=box fillcolor=\"#A181FF\" style =filled]\n";
		DOT += "subgraph cluster_p{\n    edge[style = \"bold\", dir= \"both\"]\n";
		// DOT += "label= \"Sergie Daniel Arizandieta Yol - 202000119\"";
		DOT += "bgcolor = \"#FFFFFF\"\n";
		DOT += "raiz[label = \"F/C\" fillcolor=\"#FFFFFF\" group=0]\n";

		int files = MaxFile(this.raiz);
		int columns = MaxColum(this.raiz);

		if (files != -1) {
			String temp = "";
			for (int i = 0; i <= files; i++) {
				DOT += "Columna" + i + "[ label=\"C" + i + "\" fillcolor=\"#FFFFFF\" group=" + (i + 1) + " ];\n";
				temp += "Columna" + i + ";";
				if (i == 0) {
					DOT += "raiz->Columna0;\n";
				}
				if (i + 1 <= files) {
					DOT += "Columna" + (i) + "->Columna" + (i + 1) + "\n";
				}
			}
			String rank = "{rank=same;raiz;" + temp + "}\n";
			DOT += rank;
		}

		if (columns != -1) {
			for (int i = 0; i <= columns; i++) {
				DOT += "Fila" + i + "[ label=\"F" + +i + "\" fillcolor=\"#FFFFFF\" group=0 ];\n";
				if (i == 0) {
					DOT += "raiz->Fila0;\n";
				}
				if (i + 1 <= columns) {
					DOT += "Fila" + (i) + "->Fila" + (i + 1) + "\n";
				}
			}
		}
		DOT += "\n\n";
		Files(this.raiz);
		DOT += "\n}}";
		// System.out.println(DOT);
		// System.out.println(files + " x " + columns);
		Draw_Graphiz(name, DOT);
		openimg("Matriz Dispersa");
	}

	public void Files(nodoDispersa cabeceraFila) {

		String temp = "";
		nodoDispersa actual = cabeceraFila;
		nodoDispersa Sig = null;
		if (actual != null) {
			Sig = actual.abajo;
		}
		if (!(actual.j == -1)) {

			while (actual != null) {
				if (!(actual.i == -1)) {

					if (actual.anterior != null) {
						DOT += ("Nodo" + actual.i + "_" + actual.j + "[label=\"\t\" fillcolor=\"" + actual.info
								+ "\" group=" + (actual.i + 1) + "];\n");
						// DOT+= ("Nodo" + actual.i+ "_" + actual.j + "[label=\"" + actual.info + "\"
						// fillcolor=\""+ actual.info +"\" group=" + (actual.i + 1) + "];\n");
						// System.out.print("Nodo" + actual.i+ "_" + actual.j + "[label=\"" +
						// actual.info + "\" group=" + (actual.i + 1) + "];\n");

						if (actual.anterior.i == -1) {
							// System.out.print("Fila" +actual.anterior.j + "->Nodo"+ actual.i + "_" +
							// actual.j +";\n");
							DOT += ("Fila" + actual.anterior.j + "->Nodo" + actual.i + "_" + actual.j + ";\n");
							temp += "Fila" + +actual.anterior.j + ";";
						} else {
							// System.out.print("Nodo" + actual.anterior.i + "_" + actual.anterior.j +
							// "->Nodo"+ actual.i + "_" + actual.j +";\n");
							DOT += ("Nodo" + actual.anterior.i + "_" + actual.anterior.j + "->Nodo" + actual.i + "_"
									+ actual.j + ";\n");
							temp += "Nodo" + actual.anterior.i + "_" + actual.anterior.j + ";";
						}

						if (actual.arriba.j == -1) {
							// System.out.print("Columna" +actual.arriba.i + "->Nodo"+ actual.i + "_" +
							// actual.j +";\n");
							DOT += ("Columna" + actual.arriba.i + "->Nodo" + actual.i + "_" + actual.j + ";\n");
						} else {
							// System.out.print("Nodo" + actual.arriba.i + "_" + actual.arriba.j + "->Nodo"+
							// actual.i + "_" + actual.j +";\n");
							DOT += ("Nodo" + actual.arriba.i + "_" + actual.arriba.j + "->Nodo" + actual.i + "_"
									+ actual.j + ";\n");
							// temp+="Nodo" + actual.arriba.i + "_" + actual.arriba.j + ";";
						}

						temp += "Nodo" + actual.i + "_" + actual.j + ";";
						// System.out.print("[" + actual.i + " , " + actual.j + " INFO: " + actual.info
						// + "] ");
					}

					// System.out.print("[" + actual.i + " , " + actual.j + " INFO: " + actual.info
					// + "] ");
				}

				actual = actual.siguiente;
			}

			System.out.println("\n");
		}

		if (!temp.equals("")) {
			String rank = "{rank=same;" + temp + "}\n";
			// System.out.println(rank);

			DOT += (rank);

		}

		if (Sig != null) {
			Files(Sig);
		}

	}
*/
	// --------------------------------------------------------------------------------------------------------------------------------------------------------
	public void GrapghInvisible(String name) {
		DOT = "";
		DOT += "digraph L{\n";
		DOT += "nodesep=0.4\n";
		DOT += "ranksep=0.4;\n";
		DOT += "node[shape=box fillcolor=\"#A181FF\" style =filled]\n";
		DOT += "subgraph cluster_p{\n    edge[style = \"bold\", dir= \"both\" style= invisible]\n";
		// DOT += "label= \"Sergie Daniel Arizandieta Yol - 202000119\"";
		DOT += "bgcolor = \"#FFFFFF\"\n";
		DOT += "raiz[label = \"F/C\" fillcolor=\"#FFFFFF\" group=0 style= invisible]\n";

		int files = MaxFile(this.raiz);
		int columns = MaxColum(this.raiz);

		if (files != -1) {
			String temp = "";
			for (int i = 0; i <= files; i++) {
				DOT += "Columna" + i + "[ label=\"C" + i + "\" fillcolor=\"#FFFFFF\" group=" + (i + 1) + " style= invisible ];\n";
				temp += "Columna" + i + ";";
				if (i == 0) {
					DOT += "raiz->Columna0[arrowsize=0]\n";
				}
				if (i + 1 <= files) {
					DOT += "Columna" + (i) + "->Columna" + (i + 1) + "[arrowsize=0]\n";
				}
			}
			String rank = "{rank=same;raiz;" + temp + "}\n";
			DOT += rank;
		}

		if (columns != -1) {
			for (int i = 0; i <= columns; i++) {
				DOT += "Fila" + i + "[ label=\"F" + +i + "\" fillcolor=\"#FFFFFF\" group=0 style= invisible];\n";
				if (i == 0) {
					DOT += "raiz->Fila0[arrowsize=0]\n";
				}
				if (i + 1 <= columns) {
					DOT += "Fila" + (i) + "->Fila" + (i + 1) + "[arrowsize=0]\n";
				}
			}
		}
		DOT += "\n\n";
		FilesInvisible(this.raiz);
		DOT += "\n}}";
		 System.out.println(DOT);
		// System.out.println(files + " x " + columns);
		Draw_Graphiz(name, DOT);
		
	}

	public void FilesInvisible(nodoDispersa cabeceraFila) {
	
		String temp = "";
		nodoDispersa actual = cabeceraFila;
		nodoDispersa Sig = null;
		if (actual != null) {
			Sig = actual.abajo;
		}
		if (!(actual.j == -1)) {

			while (actual != null) {
				if (!(actual.i == -1)) {

					if (actual.anterior != null) {
						DOT += ("Nodo" + actual.i + "_" + actual.j + "[label=\"\t\" fillcolor=\"" + actual.info+ "\" group=" + (actual.i + 1) + "];\n");


						if (actual.anterior.i == -1) {

							DOT += ("Fila" + actual.anterior.j + "->Nodo" + actual.i + "_" + actual.j+ "[arrowsize=0]\n");
							temp += "Fila" + +actual.anterior.j + ";";
						} else {

							DOT += ("Nodo" + actual.anterior.i + "_" + actual.anterior.j + "->Nodo" + actual.i + "_"+ actual.j + "[arrowsize=0]\n");
							temp += "Nodo" + actual.anterior.i + "_" + actual.anterior.j + ";";
						}

						if (actual.arriba.j == -1) {
						
							DOT += ("Columna" + actual.arriba.i + "->Nodo" + actual.i + "_" + actual.j+ "[arrowsize=0]\n");
						} else {
							
							DOT += ("Nodo" + actual.arriba.i + "_" + actual.arriba.j + "->Nodo" + actual.i + "_"+ actual.j + "[arrowsize=0]\n");
							
						}

						temp += "Nodo" + actual.i + "_" + actual.j + ";";
						
					}

					
				}

				actual = actual.siguiente;
			}

			System.out.println("\n");
		}

		if (!temp.equals("")) {
			String rank = "{rank=same;" + temp + "}\n";
			// System.out.println(rank);

			DOT += (rank);

		}

		if (Sig != null) {
			FilesInvisible(Sig);
		}

	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------------------------------------
	public void Draw_GraphizNeato(String name, String dot) {

		try {

			Create_File("Grafico\\" + name + ".dot", dot);
			ProcessBuilder pb;
			

			pb = new ProcessBuilder("neato", "-Tpng", "-o", "Grafico\\" + name + ".png", "Grafico\\" + name + ".dot");
			pb.redirectErrorStream(true);
			pb.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void GrapghInvisibleNewLogico(String name) {
		DOT = "";
		DOT += "digraph G{\n";
		DOT += "    node[shape=box,width=0.6 height=0.6 fontname=\"Arial\" fillcolor=\"white\" style=filled /*invisible*/]\n";
		DOT += "	edge[style = \"bold\" dir= \"both\"/* style= invisible arrowsize=0 */ ]\n";
		
		DOT += "    raiz[label = \"f/c\" fillcolor=\"darkolivegreen1\" pos = \"-1,1!\"  ]; \n";

		int files = MaxFile(this.raiz);
		int columns = MaxColum(this.raiz);

		if (files != -1) {
			
			for (int i = 0; i <= files; i++) {
				DOT += "	Columna" + i + "[ label=\"C" + i + "\" fillcolor=\"#FFFFFF\" pos=\"" + (i) +",1!\" ];\n";
				
				if (i == 0) {
					DOT += "	raiz->Columna0\n";
				}
				if (i + 1 <= files) {
					DOT += "	Columna" + (i) + "->Columna" + (i + 1) + "\n";
				}
			}
			
		
		}

		if (columns != -1) {
			for (int i = 0; i <= columns; i++) {
				DOT += "	Fila" + i + "[ label=\"F" + i + "\" fillcolor=\"#FFFFFF\" pos=\"-1,-" + i +"!\"];\n";
				if (i == 0) {
					DOT += "	raiz->Fila0\n";
				}
				if (i + 1 <= columns) {
					DOT += "	Fila" + (i) + "->Fila" + (i + 1) + "\n";
				}
			}
		}
		DOT += "\n\n";
		FilesInvisibleNewLogico(this.raiz);
		DOT += "\n}";
		System.out.println("Hi");
		 System.out.println(DOT);
		// System.out.println(files + " x " + columns);
		 Draw_GraphizNeato(name, DOT);
		
	}

	public void FilesInvisibleNewLogico(nodoDispersa cabeceraFila) {
	

		nodoDispersa actual = cabeceraFila;
		nodoDispersa Sig = null;
		if (actual != null) {
			Sig = actual.abajo;
		}
		if (!(actual.j == -1)) {

			while (actual != null) {
				if (!(actual.i == -1)) {

					if (actual.anterior != null) {
						DOT += ("	Nodo" + actual.i + "_" + actual.j + "[style= filled  label=\"\t\" fillcolor=\"" + actual.info+ "\" pos=\"" + (actual.i) + ",-" +(actual.j) +"!\"];\n");


						if (actual.anterior.i == -1) {

							DOT += ("	Fila" + actual.anterior.j + "->Nodo" + actual.i + "_" + actual.j+ "\n");
							
						} else {

							DOT += ("	Nodo" + actual.anterior.i + "_" + actual.anterior.j + "->Nodo" + actual.i + "_"+ actual.j + "\n");
							
						}

						if (actual.arriba.j == -1) {
						
							DOT += ("	Columna" + actual.arriba.i + "->Nodo" + actual.i + "_" + actual.j+ "\n");
						} else {
							
							DOT += ("	Nodo" + actual.arriba.i + "_" + actual.arriba.j + "->Nodo" + actual.i + "_"+ actual.j + "\n");
							
						}

						
						
					}

					
				}

				actual = actual.siguiente;
			}

			System.out.println("\n");
		}

	

		if (Sig != null) {
			FilesInvisibleNewLogico(Sig);
		}

	}
	
	
	
	
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------------------------------------// --------------------------------------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public void GrapghInvisibleNewAplicacion(String name) {
		DOT = "";
		DOT += "digraph G{\n";
		DOT += "    node[shape=box,width=1 height=1 fontname=\"Arial\" fillcolor=\"white\" style=invisible]\n";
		DOT += "	edge[style = \"bold\" dir= \"both\" style= invisible arrowsize=0  ]\n";
		
		DOT += "    raiz[label = \"f/c\" fillcolor=\"darkolivegreen1\" pos = \"-1,1!\"  ]; \n";

		int files = MaxFile(this.raiz);
		int columns = MaxColum(this.raiz);

		if (files != -1) {
			
			for (int i = 0; i <= files; i++) {
				DOT += "	Columna" + i + "[ label=\"C" + i + "\" fillcolor=\"#FFFFFF\" pos=\"" + (i) +",1!\" ];\n";
				
				if (i == 0) {
					DOT += "	raiz->Columna0\n";
				}
				if (i + 1 <= files) {
					DOT += "	Columna" + (i) + "->Columna" + (i + 1) + "\n";
				}
			}
			
		
		}

		if (columns != -1) {
			for (int i = 0; i <= columns; i++) {
				DOT += "	Fila" + i + "[ label=\"F" + i + "\" fillcolor=\"#FFFFFF\" pos=\"-1,-" + i +"!\"];\n";
				if (i == 0) {
					DOT += "	raiz->Fila0\n";
				}
				if (i + 1 <= columns) {
					DOT += "	Fila" + (i) + "->Fila" + (i + 1) + "\n";
				}
			}
		}
		DOT += "\n\n";
		FilesInvisibleNewAplicacion(this.raiz);
		DOT += "\n}";
		System.out.println("Hi");
		 System.out.println(DOT);
		// System.out.println(files + " x " + columns);
		 Draw_GraphizNeato(name, DOT);
		
	}

	public void FilesInvisibleNewAplicacion(nodoDispersa cabeceraFila) {
	

		nodoDispersa actual = cabeceraFila;
		nodoDispersa Sig = null;
		if (actual != null) {
			Sig = actual.abajo;
		}
		if (!(actual.j == -1)) {

			while (actual != null) {
				if (!(actual.i == -1)) {

					if (actual.anterior != null) {
						DOT += ("	Nodo" + actual.i + "_" + actual.j + "[style= filled  label=\"\t\" fillcolor=\"" + actual.info+ "\" pos=\"" + (actual.i) + ",-" +(actual.j) +"!\"];\n");


						if (actual.anterior.i == -1) {

							DOT += ("	Fila" + actual.anterior.j + "->Nodo" + actual.i + "_" + actual.j+ "\n");
							
						} else {

							DOT += ("	Nodo" + actual.anterior.i + "_" + actual.anterior.j + "->Nodo" + actual.i + "_"+ actual.j + "\n");
							
						}

						if (actual.arriba.j == -1) {
						
							DOT += ("	Columna" + actual.arriba.i + "->Nodo" + actual.i + "_" + actual.j+ "\n");
						} else {
							
							DOT += ("	Nodo" + actual.arriba.i + "_" + actual.arriba.j + "->Nodo" + actual.i + "_"+ actual.j + "\n");
							
						}	
					}
				}
				actual = actual.siguiente;
			}

			System.out.println("\n");
		}

		if (Sig != null) {
			FilesInvisibleNewAplicacion(Sig);
		}

	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------------------------------------
	public Integer MaxFile(nodoDispersa cabeceraFila) {
		int Max = -1;
		nodoDispersa actual = cabeceraFila;
		while (actual != null) {
			if (actual.i > Max) {
				Max = actual.i;
			}
			actual = actual.siguiente;
		}

		return Max;
	}

	public Integer MaxColum(nodoDispersa cabeceraColumna) {
		int Max = -1;
		nodoDispersa actual = cabeceraColumna;
		while (actual != null) {
			if (actual.j > Max) {
				Max = actual.j;
			}
			actual = actual.abajo;
		}

		return Max;
	}

	public void imprimirFila(nodoDispersa cabeceraFila) {
		nodoDispersa actual = cabeceraFila;
		while (actual != null) {
			System.out.print("[" + actual.i + " , " + actual.j + " INFO: " + actual.info + "] ");
			actual = actual.siguiente;
		}
		System.out.println("\n");
	}

	public void imprimirColumna(nodoDispersa cabeceraColumna) {
		nodoDispersa actual = cabeceraColumna;
		while (actual != null) {
			System.out.println(actual.i + " , " + actual.j + " INFO: " + actual.info);
			actual = actual.abajo;
		}
		System.out.println("\n");
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
			ProcessBuilder pb;

			pb = new ProcessBuilder("dot", "-Tpng", "-o", "Grafico\\" + name + ".png", "Grafico\\" + name + ".dot");
			pb.redirectErrorStream(true);
			pb.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openimg(String name) {
		try {
			String url = "Grafico\\" + name + ".png";
			System.out.println(url);
			ProcessBuilder p = new ProcessBuilder();
			p.command("cmd.exe", "/c", url);
			p.start();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}

	}
}

class nodoDispersa {
	nodoDispersa arriba, abajo, anterior, siguiente = null;
	Object info;
	Integer i, j;

	public nodoDispersa(Integer i, Integer j, Object info) {
		this.info = info;
		this.i = i;
		this.j = j;
	}

}