/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

import objects.Clients;

/**
 *
 * @author Alex Rose
 */
public class ArbolB {

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
	public Queue<Clients>  print_start(NodoB primero) {
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

			if (aux.derecha != null && aux.siguiente == null ) {
				cola_nodos.offer(aux.derecha.primero);
			}
		
			aux = aux.siguiente;

		}
	}
	
	

	public void buscar(NodoB primero, Long id, String name, String Password) {
		NodoB temp = buscar_start(primero, id);

		if (temp != null) {
			JOptionPane.showMessageDialog(null, "Se encontro cliente: \nDPI: " + temp.id + "\nNombre: "+ temp.cliente.Name + "\nPassword: " + temp.cliente.Password);

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
		Grap= "";
		Grap += "digraph structs {\n";
		Grap += "  bgcolor = \"#E3FFFA\"\n";
		Grap += " node [shape=Mrecord fillcolor=\"#FFE3FF\" style =filled];\n";

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
				String display = "DPI:" + j + "\\nName:" + clienttemp.Name  ;
				Grap += (temp - 1 == 0) ? display + "}" : display + "|";
				temp--;
			}

			Grap += "|<here>}\"];\n";
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

}
