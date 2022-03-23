package storage;

import java.io.FileWriter;
import java.io.PrintWriter;


public class MatrizDispersa {
	public nodoDispersa raiz;

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

	public void Graficar() {
		String dot = "";
		dot += "digraph L{\n";
		dot += "node[shape=note fillcolor=\"#A181FF\" style =filled]\n";
		dot += "subgraph cluster_p{\n    edge[style = \"bold\", dir= \"both\"]\n";
		dot+="label= \"Sergie Daniel Arizandieta Yol - 202000119\"";
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
						dot += "Fila" + fila.j + "[label=\"" + fila.j + "\" group=" + (fila.i+2) + "];\n";
						temp += "Fila" + fila.j + ";";
					} else if (fila.j == -1) {
						dot += "Columna" + fila.i + "[label=\"" + fila.i + "\" group=" + (fila.i+2) + "];\n";
						temp += "Columna" + fila.i + ";";
					} else {
						dot += "nodo" + fila.i + "_" + fila.j + "[label=\"" + fila.info + "\" group=" + (fila.i+2) + "];\n";
						temp += "nodo" + fila.i + "_" + fila.j + ";";
					}

				} else {
					temp += "raiz;";
				}

				fila = fila.siguiente;
			}
			String rank = "\n {rank=same;" + temp + "}\n";
			dot += rank;
			//System.out.println("\n");
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
							dot += "nodo" + fila.i + "_" + fila.j + "->nodo" + fila.siguiente.i + "_" + fila.siguiente.j + "\n";
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
		dot+= "\n}}";
		//System.out.println(dot);
		Draw_Graphiz("Matriz Dispersa",dot);
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
		dot+="label= \"Sergie Daniel Arizandieta Yol - 202000119\"";
		dot += "bgcolor = \"#FF7878\"\n";
		dot += "raiz[label = \"F/C\" fillcolor=\"#FFD581\" group=1]\n";
		String temp = "";
		
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
					if(fila.j == -1 && fila.i == -1) {
						if (fila.siguiente != null) {
							dot += "raiz->Fila0"  + "\n";
						}
						if (fila.abajo != null) {
							dot += "raiz->Columna0"  + "\n";
						}
					}else {
						
					}
					
					
					//System.out.print("[" + fila.i + " , " + fila.j + " INFO: " + fila.info + "] ");
					
					
					if (fila.j == -1) {
						validado = true;
					} else {
						fila = fila.siguiente;
					}
				}

				if (validado) {
					if (fila.siguiente != null) {
						if (fila.i + contador != fila.siguiente.i) {
							//System.out.print("[" + (fila.i + contador) + " , " + fila.j + " INFO: " + fila.info + "] ");
							
							dot+= "Columna" + (fila.i + contador ) + "[ fillcolor=\"#098AA2\" ];\n";
									
							dot += "Columna" + (fila.i + contador  )  +"->Columna" + (fila.i + contador + 1) + "\n";
							
							temp+="Columna" + (fila.i + contador ) + ";";
							contador++;
						} else {
							
							dot+= "Columna" + (fila.i + contador ) + "[ fillcolor=\"#098AA2\" ];\n";
							temp+="Columna" + (fila.i + contador) + ";";
							String rank = "\n {rank=same;raiz;" + temp + "}\n";
							temp="";
							dot+=rank;
							
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
							//System.out.println("[" + columna.i + " , " + (columna.j + contadorC) + " INFO: Row]\n");
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

		
		dot+= "\n}}";
		System.out.println(dot);
		//Draw_Graphiz("Matriz Dispersa NEW",dot);
		//openimg("Matriz Dispersa");
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
	
	public void Draw_Graphiz(String name,String dot) {

		try {
	
			Create_File("Grafico\\" + name + ".dot", dot);
			ProcessBuilder pb;
			
			pb = new ProcessBuilder("dot", "-Tpng", "-o", "Grafico\\" + name + ".png","Grafico\\" + name + ".dot");
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