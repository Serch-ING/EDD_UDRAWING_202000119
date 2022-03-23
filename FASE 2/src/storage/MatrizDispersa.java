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
			nuevo.siguiente = actual;
			nuevo.anterior = actual.anterior;
			actual.anterior.siguiente = nuevo;
			actual.anterior = nuevo;
		} else {
			actual.siguiente = nuevo;
			nuevo.anterior = actual;
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

	public void Grapgh() {
		DOT = "";
		DOT += "digraph L{\n";
		DOT += "node[shape=note fillcolor=\"#A181FF\" style =filled]\n";
		DOT += "subgraph cluster_p{\n    edge[style = \"bold\", dir= \"both\"]\n";
		DOT += "label= \"Sergie Daniel Arizandieta Yol - 202000119\"";
		DOT += "bgcolor = \"#FF7878\"\n";
		DOT += "raiz[label = \"F/C\" fillcolor=\"#FFD581\" group=0]\n";

		int files = MaxFile(this.raiz);
		int columns = MaxColum(this.raiz);

		if (files != -1) {
			String temp = "";
			for (int i = 0; i <= files; i++) {
				DOT += "Columna" + i + "[ fillcolor=\"#098AA2\" group=" + (i + 1) + " ];\n";
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
				DOT += "Fila" + i + "[ fillcolor=\"#098AA2\" group=0 ];\n";
				if (i == 0) {
					DOT += "raiz->Fila0;\n";
				}
				if (i + 1 <= columns) {
					DOT += "Fila" + (i) + "->Fila" + (i + 1) + "\n";
				}
			}
		}

		System.out.println(DOT);
		System.out.println(files + " x " + columns);
	}

	public void Files(nodoDispersa cabeceraFila) {
		String temp= "";
		nodoDispersa actual = cabeceraFila;
		nodoDispersa Sig = null;
		if (actual != null) {
			Sig = actual.abajo;
		}
		if (!(actual.j == -1)) {

			while (actual != null) {
				if (!(actual.i == -1)) {
					
					if(actual.anterior != null) {
						System.out.print("Nodo" + actual.i+ "_" + actual.j + "[label=\"" + actual.info + "\" group=" + (actual.i + 1) + "];\n");
						
						if(actual.anterior.i==-1) {
							System.out.print("Fila" +actual.anterior.j + "->Nodo"+ actual.i + "_" + actual.j +";\n");
							temp+="Fila" + +actual.anterior.j + ";";
						}else {
							System.out.print("Nodo" + actual.anterior.i + "_" + actual.anterior.j + "->Nodo"+ actual.i + "_" + actual.j +";\n");
							temp+="Nodo" + actual.anterior.i + "_" + actual.anterior.j + ";";
						}
						
						if(actual.arriba.j==-1) {
							System.out.print("Columna" +actual.arriba.i + "->Nodo"+ actual.i + "_" + actual.j +";\n");
							
						}else {
							System.out.print("Nodo" + actual.arriba.i + "_" + actual.arriba.j + "->Nodo"+ actual.i + "_" + actual.j +";\n");
							//temp+="Nodo" + actual.arriba.i + "_" + actual.arriba.j + ";";
						}
						
						temp+="Nodo" + actual.i + "_" + actual.j + ";";
						//System.out.print("[" + actual.i + " , " + actual.j + " INFO: " + actual.info + "] ");
					}
					
					//System.out.print("[" + actual.i + " , " + actual.j + " INFO: " + actual.info + "] ");
				}
				
				
				actual = actual.siguiente;
			}
			
			System.out.println("\n");
		}
		if(!temp.equals("")) {
			String rank = "{rank=same;" + temp + "}\n";
			System.out.println(rank);
		}
		
		if (Sig != null) {
			Files(Sig);
		}

	}

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

	public void Graficar() {
		String dot = "";
		dot += "digraph L{\n";
		dot += "node[shape=note fillcolor=\"#A181FF\" style =filled]\n";
		dot += "subgraph cluster_p{\n    edge[style = \"bold\", dir= \"both\"]\n";
		dot += "label= \"Sergie Daniel Arizandieta Yol - 202000119\"";
		dot += "bgcolor = \"#FF7878\"\n";
		dot += "raiz[label = \"F/C\" fillcolor=\"#FFD581\" group=1]\n";

		nodoDispersa fila;
		nodoDispersa columna = raiz;

		while (columna != null) {
			fila = columna;
			String temp = "";
			while (fila != null) {

				if (!(fila.i == -1 && fila.j == -1)) {

					if (fila.i == -1) {
						dot += "Fila" + fila.j + "[label=\"" + fila.j + "\" group=" + (fila.i + 2) + "];\n";
						temp += "Fila" + fila.j + ";";
					} else if (fila.j == -1) {
						dot += "Columna" + fila.i + "[label=\"" + fila.i + "\" group=" + (fila.i + 2) + "];\n";
						temp += "Columna" + fila.i + ";";
					} else {
						dot += "nodo" + fila.i + "_" + fila.j + "[label=\"" + fila.info + "\" group=" + (fila.i + 2)
								+ "];\n";
						temp += "nodo" + fila.i + "_" + fila.j + ";";
					}

				} else {
					temp += "raiz;";
				}

				fila = fila.siguiente;
			}
			String rank = "\n {rank=same;" + temp + "}\n";
			dot += rank;
			// System.out.println("\n");
			columna = columna.abajo;
		}

		columna = raiz;
		while (columna != null) {
			fila = columna;

			while (fila != null) {

				if (!(fila.i == -1 && fila.j == -1)) {

					if (fila.i == -1) {
						if (fila.siguiente != null) {
							dot += "Fila" + fila.j + "->nodo" + fila.siguiente.i + "_" + fila.siguiente.j + "\n";
						}
						if (fila.abajo != null) {
							dot += "Fila" + fila.j + "->Fila" + fila.abajo.j + "\n";
						}

					} else if (fila.j == -1) {

						if (fila.abajo != null) {
							dot += "Columna" + fila.i + "->nodo" + fila.abajo.i + "_" + fila.abajo.j + "\n";
						}
						if (fila.siguiente != null) {
							dot += "Columna" + fila.i + "->Columna" + fila.siguiente.i + "\n";
						}

					} else {

						if (fila.abajo != null) {
							dot += "nodo" + fila.i + "_" + fila.j + "->nodo" + fila.abajo.i + "_" + fila.abajo.j + "\n";
						}
						if (fila.siguiente != null) {
							dot += "nodo" + fila.i + "_" + fila.j + "->nodo" + fila.siguiente.i + "_" + fila.siguiente.j
									+ "\n";
						}
					}

				} else {
					if (fila.siguiente != null) {
						dot += "\nraiz->Fila" + fila.siguiente.i + "\n";
					}
					if (fila.abajo != null) {
						dot += "\nraiz->Columna" + fila.abajo.j + "\n";
					}
				}

				fila = fila.siguiente;
			}
			columna = columna.abajo;
		}
		dot += "\n}}";
		// System.out.println(dot);
		Draw_Graphiz("Matriz Dispersa", dot);
		openimg("Matriz Dispersa");
	}

	public void RecorrerCompleto() {
		nodoDispersa fila;
		nodoDispersa columna = raiz;
		Boolean validado = false;
		Integer contador = 1;
		Boolean validadoC = false;
		Integer contadorC = 1;

		while (columna != null) {
			validado = false;
			fila = columna;

			while (fila != null) {

				if (validado == false) {
					System.out.print("[" + fila.i + " , " + fila.j + " INFO: " + fila.info + "] ");
					if (fila.j == -1) {
						validado = true;
					} else {
						fila = fila.siguiente;
					}
				}

				if (validado) {
					if (fila.siguiente != null) {
						if (fila.i + contador != fila.siguiente.i) {
							System.out.print("[" + (fila.i + contador) + " , " + fila.j + " INFO: " + fila.info + "] ");
							contador++;
						} else {
							contador = 1;
							validado = false;
							fila = fila.siguiente;
						}
					} else {
						fila = fila.siguiente;
					}
				}

			}
			System.out.println("\n");
			while (true) {
				if (columna.i == -1) {
					validadoC = true;
				} else {
					break;
				}

				if (validadoC) {
					if (columna.abajo != null) {
						if (columna.j + contadorC != columna.abajo.j) {
							System.out.println("[" + columna.i + " , " + (columna.j + contadorC) + " INFO: Row]\n");
							contadorC++;
						} else {
							contadorC = 1;
							validadoC = false;
							break;
						}
					} else {
						break;
					}
				}

			}

			columna = columna.abajo;
		}

	}

	public void GraficarCompleto() {
		String dot = "";
		dot += "digraph L{\n";
		dot += "node[shape=note fillcolor=\"#A181FF\" style =filled]\n";
		dot += "subgraph cluster_p{\n    edge[style = \"bold\", dir= \"both\"]\n";
		dot += "label= \"Sergie Daniel Arizandieta Yol - 202000119\"";
		dot += "bgcolor = \"#FF7878\"\n";
		dot += "raiz[label = \"F/C\" fillcolor=\"#FFD581\" group=0]\n";

		String tempRank = "";
		nodoDispersa fila;
		nodoDispersa columna = raiz;
		Boolean validado = false;
		Integer contador = 1;
		Boolean validadoC = false;
		Integer contadorC = 1;

		while (columna != null) {
			validado = false;
			fila = columna;
			Boolean validationTemp = false;
			while (fila != null) {

				if (validado == false) {
					if (fila.j == -1 && fila.i == -1) {
						if (fila.siguiente != null) {
							dot += "raiz->Fila0" + "\n";
						}
						if (fila.abajo != null) {
							dot += "raiz->Columna0" + "\n";
						}
					}

					if (fila.j == -1) {
						validado = true;

					} else {

						fila = fila.siguiente;
					}
				}

				if (validado) {
					if (fila.siguiente != null) {

						if (fila.i + contador != fila.siguiente.i) {
							validationTemp = true;

							dot += "Columna" + (fila.i + contador) + "[ fillcolor=\"#098AA2\" group="
									+ (fila.i + contador + 1) + " ];\n";

							dot += "Columna" + (fila.i + contador) + "->Columna" + (fila.i + contador + 1) + "\n";

							tempRank += "Columna" + (fila.i + contador) + ";";
							contador++;

						} else {
							dot += "Columna" + (fila.i + contador) + "[ fillcolor=\"#098AA2\"  group="
									+ (fila.i + contador + 1) + "];\n";

							if (validationTemp == false) {
								dot += "Columna" + (fila.i + contador) + "->Columna" + (fila.i + contador + 1) + "\n";
							}
							validationTemp = false;

							tempRank += "Columna" + (fila.i + contador) + ";";

							contador = 1;
							validado = false;
							fila = fila.siguiente;

						}

					} else {
						fila = fila.siguiente;
					}
				}

			}

			System.out.println("\n");
			while (true) {
				if (columna.i == -1) {
					validadoC = true;
				} else {
					break;
				}

				if (validadoC) {
					if (columna.abajo != null) {
						if (columna.j + contadorC != columna.abajo.j) {
							// System.out.println("[" + columna.i + " , " + (columna.j + contadorC) + "
							// INFO: Row]\n");

							dot += "Fila" + (columna.j + contadorC) + "[ fillcolor=\"#098AA2\" group=0 ];\n";

							if (columna.j + contadorC + 1 != columna.abajo.j) {
								dot += "Fila" + (columna.j + contadorC) + "->Fila" + (columna.j + contadorC + 1) + "\n";
							}

							contadorC++;
						} else {
							dot += "Fila" + (columna.j + contadorC - 1) + "->Fila" + (columna.j + contadorC) + "\n";

							dot += "Fila" + (columna.j + contadorC) + "[ fillcolor=\"#098AA2\" group=0 ];\n";
							contadorC = 1;
							validadoC = false;
							break;
						}
					} else {
						break;
					}
				}

			}

			columna = columna.abajo;
		}

		String rank = "\n {rank=same;raiz;" + tempRank + "}\n";
		dot += rank;
		tempRank = "";

		dot += "\n}}";
		System.out.println(dot);
		// Draw_Graphiz("Matriz Dispersa NEW",dot);
		// openimg("Matriz Dispersa");
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