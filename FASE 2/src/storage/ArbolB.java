
package storage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

import objects.Clients;

public class ArbolB {
	RamaB RamaIz;
	RamaB RamaDer;
	RamaB RamaRemove;
	NodoB NodeRemove;
	NodoB PadreDer;
	NodoB PadreIz;

	public String dot = "";
	public String Grap = "";
	public String Connectios = "";
	Queue<String> names = new LinkedList<String>();
	Queue<Clients> info = new LinkedList<Clients>();

	int orden_arbol = 5;
	public RamaB raiz;

	public ArbolB() {
		this.raiz = null;
	}

	public void insertar(Long id, Clients cliente) {
		NodoB nodo = new NodoB(id, cliente);

		if (raiz == null) {
			raiz = new RamaB();
			raiz.insertar(nodo);
		} else {
			NodoB obj = insertar_en_rama(nodo, raiz);

			if (obj != null) {
				// si devuelve algo el metodo de insertar en rama quiere decir que creo una
				// nueva rama, y se debe insertar en el arbol
				obj.cliente = cliente;
				raiz = new RamaB();
				raiz.insertar(obj);
				raiz.hoja = false;
			}
		}
	}

	private NodoB insertar_en_rama(NodoB nodo, RamaB rama) {
		if (rama.hoja) {
			rama.insertar(nodo);

			if (rama.contador == orden_arbol) {
				// si ya se insertaron todos los elementos posibles se debe dividir la rama
				return dividir(rama);
			} else {
				return null;
			}
		} else {
			NodoB temp = rama.primero;
			do {
				if (nodo.id.compareTo(temp.id) == 0) {
					return null;
				} else if (nodo.id.compareTo(temp.id) == -1) {
					NodoB obj = insertar_en_rama(nodo, temp.izquierda);
					if (obj instanceof NodoB) {
						rama.insertar((NodoB) obj);
						if (rama.contador == orden_arbol) {
							return dividir(rama);
						}
					}
					return null;
				} else if (temp.siguiente == null) {
					NodoB obj = insertar_en_rama(nodo, temp.derecha);
					if (obj instanceof NodoB) {
						rama.insertar((NodoB) obj);
						if (rama.contador == orden_arbol) {
							return dividir(rama);
						}
					}
					return null;
				}
				temp = (NodoB) temp.siguiente;
			} while (temp != null);
		}
		return null;
	}

	private NodoB dividir(RamaB rama) {
		Long val = Long.valueOf(-999);
		Clients cliente = null;
		NodoB temp, Nuevito;
		NodoB aux = rama.primero;
		RamaB rderecha = new RamaB();
		RamaB rizquierda = new RamaB();

		int cont = 0;
		while (aux != null) {
			cont++;
			// implementacion para dividir unicamente ramas de 4 nodos
			if (cont < 3) {
				temp = new NodoB(aux.id, aux.cliente);
				temp.izquierda = aux.izquierda;
				if (cont == 2) {
					temp.derecha = aux.siguiente.izquierda;
				} else {
					temp.derecha = aux.derecha;
				}
				// si la rama posee ramas deja de ser hoja
				if (temp.derecha != null && temp.izquierda != null) {
					rizquierda.hoja = false;
				}

				rizquierda.insertar(temp);

			} else if (cont == 3) {
				val = aux.id;
				cliente = aux.cliente;
			} else {
				temp = new NodoB(aux.id, aux.cliente);
				temp.izquierda = aux.izquierda;
				temp.derecha = aux.derecha;
				// si la rama posee ramas deja de ser hoja
				if (temp.derecha != null && temp.izquierda != null) {
					rderecha.hoja = false;
				}
				rderecha.insertar(temp);
			}
			aux = aux.siguiente;
		}
		Nuevito = new NodoB(val, cliente);
		Nuevito.derecha = rderecha;
		Nuevito.izquierda = rizquierda;
		return Nuevito;
	}

//Propios-----------------------------------------------------------------------------------------------------------------------------------------
	public Queue<Clients> print_start(NodoB primero) {
		info = new LinkedList<Clients>();

		System.out.println("Recorrer por niveles");
		Queue<NodoB> cola_nodos = new LinkedList<NodoB>();
		cola_nodos.offer(primero);

		while (cola_nodos.peek() != null) {
			imprimircompleto(cola_nodos.poll(), cola_nodos);
		}

		return info;
	}

	public void imprimircompleto(NodoB primero, Queue<NodoB> cola_nodos) {

		NodoB aux = primero;

		while (aux != null) {

			String temp = (aux.siguiente == null) ? aux.id + "\n" : aux.id + ", ";
			System.out.print(temp);
			info.offer(aux.cliente);

			if (aux.izquierda != null) {
				cola_nodos.offer(aux.izquierda.primero);
			}

			if (aux.derecha != null && aux.siguiente == null) {
				cola_nodos.offer(aux.derecha.primero);
			}
			aux = aux.siguiente;
		}
	}

	public void buscar(NodoB primero, Long id, String name, String Password) {
		NodoB temp = buscar_start(primero, id);

		if (temp != null) {
			JOptionPane.showMessageDialog(null, "Se encontro cliente: \nDPI: " + temp.id + "\nNombre: "
					+ temp.cliente.Name + "\nPassword: " + temp.cliente.Password);

			if (!(name.equals(""))) {
				temp.cliente.Name = name;
			}

			if (!(Password.equals(""))) {
				temp.cliente.Password = Password;
			}

			JOptionPane.showMessageDialog(null, "Se modifico cliente! \nDPI: " + temp.cliente.DPI + "\nNombre: "
					+ temp.cliente.Name + "\nPassword: " + temp.cliente.Password);
		} else {
			JOptionPane.showMessageDialog(null, "No se encontro el DPI a modificar");
		}
	}

	public NodoB buscar_start(NodoB primero, Long id) {

		Queue<NodoB> cola_nodos = new LinkedList<NodoB>();
		cola_nodos.offer(primero);

		while (cola_nodos.peek() != null) {

			NodoB temp = buscarcompleto(cola_nodos.poll(), cola_nodos, id);
			if (temp != null) {
				return temp;
			}
		}
		return null;
	}

	public NodoB buscarcompleto(NodoB primero, Queue<NodoB> cola_nodos, Long id) {

		NodoB aux = primero;

		while (aux != null) {
			Long temp = aux.id;
			if (temp.compareTo(id) == 0) {
				return aux;
			}

			if (aux.izquierda != null) {
				cola_nodos.offer(aux.izquierda.primero);
			}

			if (aux.derecha != null && aux.siguiente == null) {
				cola_nodos.offer(aux.derecha.primero);
			}

			aux = aux.siguiente;

		}
		return null;
	}

	public void generate_grapgh(String name, String dot) {
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

	public void draw_start(NodoB primero, String name) {
		names = new LinkedList<String>();
		info = new LinkedList<Clients>();
		dot = "";
		Connectios = "";
		Grap = "";
		Grap += "digraph structs {\n";
		Grap += "  bgcolor = \"#E3FFFA\"\n";
		Grap += " node [shape=Mrecord fillcolor=\"#FFE3FF\" style =filled];\n";
		if (primero != null) {
			Queue<NodoB> cola_nodos = new LinkedList<NodoB>();
			cola_nodos.offer(primero);

			while (cola_nodos.peek() != null) {
				drawing(cola_nodos.poll(), cola_nodos);
			}

			String[] files = dot.split("\n");

			for (String i : files) {

				Grap += "struct" + names.poll() + "[label=\"{{";

				String[] columns = i.split(",");
				int temp = columns.length;

				for (String j : columns) {
					Clients clienttemp = info.poll();
					String display = "DPI:" + j + "\\nName:" + clienttemp.Name;
					Grap += (temp - 1 == 0) ? display + "}" : display + "|";
					temp--;
				}

				Grap += "|<here>}\"];\n";
			}
		} else {
			Grap += "struct1517940428[label=\"{{Vacio}|<here>}\"];\n";
		}
		Grap += "\n" + Connectios;
		Grap += "}";
		// System.out.println(Grap);
		generate_grapgh(name, Grap);
	}

	public void drawing(NodoB primero, Queue<NodoB> cola_nodos) {
		NodoB aux = primero;
		String data = String.valueOf(primero.hashCode());
		names.offer(data);

		while (aux != null) {

			dot += (aux.siguiente == null) ? aux.id + "\n" : aux.id + ", ";
			info.offer(aux.cliente);

			if (aux.izquierda != null) {
				Connectios += "struct" + data + "->struct" + aux.izquierda.primero.hashCode() + ";\n";
				cola_nodos.offer(aux.izquierda.primero);

			}

			if (aux.derecha != null && aux.siguiente == null) {
				Connectios += "struct" + data + "->struct" + aux.derecha.primero.hashCode() + ";\n";
				cola_nodos.offer(aux.derecha.primero);
			}

			aux = aux.siguiente;
		}
	}

	public void buscartoRomove(RamaB raiz, Long id) {
		PadreDer = null;
		PadreIz = null;
		NodeRemove = null;
		RamaIz = null;
		RamaDer = null;
		RamaRemove = null;

		Removecompleto(raiz.primero, id, raiz, raiz.primero);

		if (NodeRemove != null) {

			if (RamaRemove.hoja) {

				ReducirRama(NodeRemove);
				if (RamaRemove != this.raiz) {
					if (RamaRemove.contador < 2) {
						reordenarNodos();
					}
				}

			} else {

				System.out.println("NO ES HOJA");
				System.out.println("Es hoja: " + RamaRemove.hoja);
				System.out.println("Contador  " + RamaRemove.contador);

				System.out.println("\n\nEliminando\n\n");

			}

			JOptionPane.showMessageDialog(null, "Se elimino cliente: \nDPI: " + NodeRemove.id + "\nNombre: "
					+ NodeRemove.cliente.Name + "\nPassword: " + NodeRemove.cliente.Password);
		} else {
			JOptionPane.showMessageDialog(null, "No se encontro el DPI a eliminar");
		}
	}

	public void ReducirRama(NodoB temp) {

		System.out.println("Es hoja: " + RamaRemove.hoja);
		System.out.println("Contador  " + RamaRemove.contador);

		System.out.println("\n/////////Eliminando//////////");
		Removing(temp);

	}
	
	public void Removing(NodoB temp) {
		System.out.println("Hojas: " + RamaRemove.contador);
		System.out.println("Hoja id:" + temp.id);
		System.out.println("Primero: " + RamaRemove.primero.id + "\n");

		if (RamaRemove.primero == temp) {
			if (RamaRemove.contador == 1) {
				RamaRemove.primero = null;
			} else {
				RamaRemove.primero = temp.siguiente;
				RamaRemove.primero.anterior = null;
			}

		} else {

			if (temp.siguiente == null) {
				temp.anterior.siguiente = null;
			} else {
				temp.anterior.siguiente = temp.siguiente;
				temp.siguiente.anterior = temp.anterior;
				temp = null;
			}
		}


		RamaRemove.contador--;
		System.out.println("\nSe elimino");
		System.out.println("Hojas: " + RamaRemove.contador);
		System.out.println("Primero: " + RamaRemove.primero.id + "\n");
		print_start(this.raiz.primero);

	}


	public void reordenarNodos() {
		boolean validacion = false;
		System.out.println("///ReordenarNodeos///");
		if (RamaIz != null) {
			System.out.println("Iz: " + RamaIz.primero.id);

			if (RamaIz.contador > 2) {
				validacion = true;
				System.out.println("En izquierda hay espacio" +RamaIz.contador );
				PrestarIzquierda();
			} else {
				System.out.println("En izquierda NO hay espacio");
			}

		} else {
			System.out.println("No iz");
		}

		if (!validacion) {
			if (RamaDer != null) {
				System.out.println("Derecho: " + RamaDer.primero.id);

				if (RamaDer.contador > 2) {
					validacion = true;
					System.out.println("En derecha hay espacio: " + RamaDer.contador);
					PrestarDerecha();
				} else {
					System.out.println("En derecha NO hay espacio");
				}

			} else {
				System.out.println("No der");
			}
		}

		if (!validacion) {
			rebalanceo();
		}
	}

	public void rebalanceo() {
		System.out.println("\n//////Rebalanceo////////");
		System.out.println("Raiz: " + RamaRemove.primero.id);
		
		if (RamaIz != null) {
			System.out.println("Izquierda: "+ RamaIz.primero.id);
		}
 
		if (RamaDer != null) {
			System.out.println("Derecho: " + RamaDer.primero.id);
		}
		
		if (RamaIz != null) {

			NodoB ultimo = null;
			NodoB actual = RamaIz.primero;

			while (actual != null) {
				if (actual.siguiente == null) {
					ultimo = actual;
				}
				actual = actual.siguiente;
			}

			if (PadreIz.anterior != null) {
				PadreIz.anterior.siguiente = PadreIz.siguiente;
			}


			if (PadreIz.siguiente != null) {
				PadreIz.siguiente.anterior = PadreIz.anterior;
			}
			

			PadreIz.izquierda.contador += 2;

			if(PadreIz.siguiente != null) {
				PadreIz.siguiente.izquierda = RamaIz;
			}else if (PadreIz.anterior != null) {
				PadreIz.anterior.derecha = RamaIz;
			}
			
			if (raiz.primero == PadreIz) {
				raiz.primero = PadreIz.siguiente;
			}

			PadreIz.izquierda = null;
			PadreIz.derecha = null;
			PadreIz.anterior = null;
			PadreIz.siguiente = RamaRemove.primero;
			ultimo.siguiente = PadreIz;
			PadreIz.anterior = ultimo.siguiente;
			
			NodoB aux = RamaIz.primero;
			
			while (aux!= null) {
				System.out.println("," + aux.id);
				aux =aux.siguiente;
			}
			
			print_start(this.raiz.primero);

		} else {
			System.out.println("Por derecha");
		}

	}

	public void PrestarDerecha() {
		System.out.println("Separador: " + PadreDer.id);

		// guardando encabezado
		NodoB temp = PadreDer.derecha.primero;
		PadreDer.derecha.contador--;

		// cambiando encabezado
		PadreDer.derecha.primero = PadreDer.derecha.primero.siguiente;
		PadreDer.derecha.primero.anterior = null;

		// modificando el ex primero
		temp.siguiente = PadreDer.siguiente;
		temp.anterior = PadreDer.anterior;
		temp.derecha = PadreDer.derecha;
		temp.izquierda = PadreDer.izquierda;

		// validando que sea raiz
		if (raiz.primero == PadreDer) {
			raiz.primero = temp;
		}

		// borrando enlaces de los padres
		if (PadreDer.anterior != null) {
			PadreDer.anterior.siguiente = temp;
		}

		if (PadreDer.siguiente != null) {
			PadreDer.siguiente.anterior = temp;
		}

		// borando ramas del padre
		PadreDer.siguiente = null;
		PadreDer.derecha = null;
		PadreDer.izquierda = null;

		// colocar el temp en la pagina desbalanceada
		if (RamaRemove.primero == NodeRemove) {
			RamaRemove.primero = NodeRemove.siguiente;
			RamaRemove.primero.anterior = null;
		}

		RamaRemove.primero.siguiente = PadreDer;
		PadreDer.anterior = RamaRemove.primero;

	}

	public void PrestarIzquierda() {
		System.out.println("Separador: " + PadreIz.id);

		// guardando encabezado
		NodoB temp = null;
		NodoB aux = PadreIz.izquierda.primero;

		while (aux != null) {

			if (aux.siguiente == null) {
				temp = aux;
			}

			aux = aux.siguiente;
		}

		PadreIz.izquierda.contador--;

		// limpiando nodos anteriores izquierdo
		temp.anterior.siguiente = null;

		// modificando el ex primero
		temp.siguiente = PadreIz.siguiente;
		temp.anterior = PadreIz.anterior;
		temp.derecha = PadreIz.derecha;
		temp.izquierda = PadreIz.izquierda;

		// validando que sea raiz
		if (raiz.primero == PadreIz) {
			raiz.primero = temp;
		}

		// borrando enlaces de los padres
		if (PadreIz.anterior != null) {
			PadreIz.anterior.siguiente = temp;
		}

		if (PadreIz.siguiente != null) {
			PadreIz.siguiente.anterior = temp;
		}

		// borando ramas del padre
		PadreIz.anterior = null;
		PadreIz.derecha = null;
		PadreIz.izquierda = null;
		PadreIz.siguiente = null;

		// colocar el temp en la pagina desbalanceada
		PadreIz.siguiente = RamaRemove.primero;
		RamaRemove.primero = PadreIz;
		RamaRemove.primero.siguiente.anterior = RamaRemove.primero;

	}


	public String Removecompleto(NodoB primero, Long id, RamaB raiz, NodoB padre) {

		NodoB aux = primero;

		while (aux != null) {

			Long temp = aux.id;
			if (temp.compareTo(id) == 0) {
				NodeRemove = aux;
				RamaRemove = raiz;

				if (padre.izquierda == raiz) {
					if (padre.siguiente != null) {
						RamaDer = padre.siguiente.izquierda;
						PadreDer = padre.siguiente;
					}else {
						RamaDer = padre.derecha;
						PadreDer = padre;
					}
					
					

					if (padre.anterior != null) {
						RamaIz = padre.anterior.izquierda;
						PadreIz = padre.anterior;
					} else {
						RamaIz = null;
					}

				} else if (padre.derecha == raiz) {
					RamaIz = padre.izquierda;
					PadreIz = padre;
					RamaDer = null;
				}

				return null;
			}

			if (aux.izquierda != null) {

				Removecompleto(aux.izquierda.primero, id, aux.izquierda, aux);
			}

			if (aux.derecha != null && aux.siguiente == null) {

				Removecompleto(aux.derecha.primero, id, aux.derecha, aux);
			}

			aux = aux.siguiente;

		}

		return null;
	}

}
