package storage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JOptionPane;

import objects.Nodes_Colors;
import storage.Arbol_AVL.Node;



public class Arbol_Binario {
	public String temp;
	public Nodo_ABB raiz;
	public int contador = 0;

	public Arbol_Binario() {
		raiz = null;
	}
	
	String dot = "";
	
	public String DrawGraph_return(Nodo_ABB root) {
		dot += "\n\nnode[style = filled fillcolor=\"#F788DF\"];\n";
		GenerarArbol(root);

		//System.out.println(dot);
		return dot;
	}

	public void DrawGraph(Nodo_ABB root,String name) {

		dot = "digraph G {\n";
		dot += "nodesep=0; \n";
		dot += "ranksep=0.4;\n";
		dot += "node[style = filled fillcolor=\"#F788DF\"];\n";
		

		GenerarArbol(root);

		dot += "}";

		//System.out.println(dot);
		generate_grapgh(name,dot);
	}
	

	public void GenerarArbol(Nodo_ABB actual) {
		if (actual.izquierda != null) {
			dot += ("	NodoABB" + actual.dato + "[ label=\"" + actual.dato + "\"  ];\n");
			dot += ("	NodoABB" + actual.izquierda.dato + "[ label=\"" + actual.izquierda.dato + "\"];\n");
			dot += "NodoABB" + actual.dato + "->NodoABB" + actual.izquierda.dato + "\n";
			GenerarArbol(actual.izquierda);
		} else {

			dot += "Invisible" + actual.hashCode() + "[style=invis];\n";
			dot += "NodoABB" + actual.dato + "-> Invisible" + actual.hashCode() + "[arrowsize=0 style= invisible] \n";
		}
		
		if (actual.derecha != null) {
			dot += ("	NodoABB" + actual.dato + "[ label=\"" + actual.dato + "\"];\n");
			dot += ("	NodoABB" + actual.derecha.dato + "[ label=\"" + actual.derecha.dato + "\" ];\n");
			dot += "NodoABB" + actual.dato + "->NodoABB" + actual.derecha.dato + "\n";
			GenerarArbol(actual.derecha);
		} else {
			dot += "Invisible" + actual.hashCode() + "[style=invis];\n";
			dot += "NodoABB" + actual.dato + "-> Invisible" + actual.hashCode() + "[arrowsize=0 style= invisible] \n";
		}
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

	public void busquedaListColors(int busqueda, MatrizDispersa temp_Matriz) {
		LinkedList<Nodes_Colors> temp = new LinkedList<Nodes_Colors>();
		
		temp= existeListColor(this.raiz, busqueda);
		
		if(temp!= null) {
			//System.out.println("Se encontro: " + busqueda);
			for (Nodes_Colors nodes_Colors : temp) {
				temp_Matriz.insertarNodo(nodes_Colors.columna, nodes_Colors.fila,  nodes_Colors.color);
			}
		}
	}

	private LinkedList<Nodes_Colors> existeListColor(Nodo_ABB n, int busqueda) {
		if (n == null) {
			return null;
		}
		if (n.getDato() == busqueda) {
			return n.Nodos;
		} else if (busqueda < n.getDato()) {
			return existeListColor(n.getIzquierda(), busqueda);
		} else {
			return existeListColor(n.getDerecha(), busqueda);
		}

	}

	public void insertar(int dato,LinkedList<Nodes_Colors> Nodos_new) {
		if (this.raiz == null) {
			this.raiz = new Nodo_ABB(dato,Nodos_new);
		} else {
			this.insertar(this.raiz, dato,Nodos_new);
		}
	}

	private void insertar(Nodo_ABB padre, int dato ,LinkedList<Nodes_Colors> Nodos_new) {
		if (dato > padre.getDato()) {
			if (padre.getDerecha() == null) {
				padre.setDerecha(new Nodo_ABB(dato,Nodos_new));
			} else {
				this.insertar(padre.getDerecha(), dato,Nodos_new);
			}
		}else if(dato == padre.getDato() ) {
			JOptionPane.showMessageDialog(null, "Id de imagen repetida");
			//padre.Nodos = Nodos_new;
		} else {
			if (padre.getIzquierda() == null) {
				padre.setIzquierda(new Nodo_ABB(dato,Nodos_new));
			} else {
				this.insertar(padre.getIzquierda(), dato,Nodos_new);
			}
		}
	}
	
	
	public  void Niveles(Nodo_ABB root) {
		Nodo_ABB tempnode = root;
		Queue<Nodo_ABB> queue = new LinkedList<Nodo_ABB>();
		List<Nodo_ABB> list = new LinkedList<Nodo_ABB>();
		queue.add(tempnode);
		
		while(!queue.isEmpty()) {
			tempnode = queue.remove();
			list.add(tempnode);
			if(tempnode.izquierda!= null) {
				queue.add(tempnode.izquierda);
			}
			if(tempnode.derecha != null) {
				queue.add(tempnode.derecha);
			}
		}
		
		for(Nodo_ABB tn :list) {
			System.out.print(tn.dato+",");
		}
	}
	
	public  List<Integer> NivelesRetorno(Nodo_ABB root) {
		Nodo_ABB tempnode = root;
		Queue<Nodo_ABB> queue = new LinkedList<Nodo_ABB>();
		List<Integer> List_int = new LinkedList<Integer>();
		queue.add(tempnode);
		
		while(!queue.isEmpty()) {
			tempnode = queue.remove();
			List_int.add(tempnode.dato);
			if(tempnode.izquierda!= null) {
				queue.add(tempnode.izquierda);
			}
			if(tempnode.derecha != null) {
				queue.add(tempnode.derecha);
			}
		}
		

		return List_int;
	}


	public void Profundidad(Nodo_ABB root) {
		 Stack <Nodo_ABB> stack = new Stack <Nodo_ABB> ();
		 List <Nodo_ABB> list = new LinkedList <Nodo_ABB> (); 
		 Nodo_ABB tempnode  = root;
		 stack.push (tempnode); 
		
		 while (! stack.isEmpty ()) {
			tempnode = stack.pop();
			list.add(tempnode);
			if(tempnode.derecha != null) {
				 stack.push (tempnode.derecha);
			}
			
			if(tempnode.izquierda != null) {
				stack.push(tempnode.izquierda);
			}
			
		}
		
		for(Nodo_ABB tn :list) {
			System.out.print(tn.dato+",");
		}
	}

	
	public void PrintNiveles(Nodo_ABB root) {
		int height =0;
		
		if(root.izquierda!=null) {
			height++;
		}
		
		if(root.derecha!=null) {
			height++;
		}
	

		List<Nodo_ABB> current = new ArrayList<Nodo_ABB>(), next = new ArrayList<Nodo_ABB>();

		current.add(root);

		for (int i = 0; i <= height; i++) {

			for (Nodo_ABB n : current) {
				if (n != null) {

					System.out.printf("%d, ", n.dato);

					next.add(n.izquierda);
					next.add(n.derecha);
				}
			}

			current = next;
			next = new ArrayList<Nodo_ABB>();
		}
		System.out.println();
	}


	private void preorden(Nodo_ABB n) {
		if (n != null) {
			n.imprimirDato();
			preorden(n.getIzquierda());
			preorden(n.getDerecha());
		}
	}

	private void inorden(Nodo_ABB n) {
		if (n != null) {
			inorden(n.getIzquierda());
			n.imprimirDato();
			inorden(n.getDerecha());
		}
	}
	
	private void postorden(Nodo_ABB n) {
		if (n != null) {
			postorden(n.getIzquierda());
			postorden(n.getDerecha());
			n.imprimirDato();
		}
	}
	
	public void recorridoLimitado(int capas) {
		this.temp= "";
		this.contador = capas;
	}

	
	public void preordenLimited(Nodo_ABB n, MatrizDispersa temp_Matriz) {
		if (n != null ) {

			n.imprimirDatoLimited(temp_Matriz);
			preordenLimited(n.getIzquierda(),temp_Matriz);
			preordenLimited(n.getDerecha(),temp_Matriz);
		}
	}

	public void inordenLimited(Nodo_ABB n, MatrizDispersa temp_Matriz) {
		if (n != null ) {
			
			inordenLimited(n.getIzquierda(),temp_Matriz);
			n.imprimirDatoLimited(temp_Matriz);
			inordenLimited(n.getDerecha(),temp_Matriz);
		}
	}
	
	public void postordenLimited(Nodo_ABB n, MatrizDispersa temp_Matriz) {
		if (n != null) {

			postordenLimited(n.getIzquierda(),temp_Matriz);
			postordenLimited(n.getDerecha(),temp_Matriz);
			n.imprimirDatoLimited(temp_Matriz);
			
		}
	}
	
	public void MyInOrden(Nodo_ABB n) {
		LinkedList<Nodo_ABB> lista = new LinkedList<Nodo_ABB>();
		Nodo_ABB curr = raiz;
		while (!(lista.isEmpty()) || curr != null) {
			if (curr != null) {
				lista.push(curr);
				curr = curr.getIzquierda();
			} else {
				curr = lista.pop();
				System.out.println(curr.getDato());
				curr = curr.getDerecha();
			}
		}

	}


	
	public void preorden() {
		this.preorden(this.raiz);
	}

	public void inorden() {
		this.inorden(this.raiz);
	}

	public void postorden() {
		this.postorden(this.raiz);
	}
	

	class Nodo_ABB {
		int dato;
		private Nodo_ABB izquierda, derecha;
		public LinkedList<Nodes_Colors> Nodos  = new LinkedList<Nodes_Colors>();
	
		public Nodo_ABB(int dato,LinkedList<Nodes_Colors> Nodos) {
			this.dato = dato;
			this.Nodos = Nodos;
			this.izquierda = this.derecha = null;
		}

		public int getDato() {
			return dato;
		}

		public Nodo_ABB getIzquierda() {
			return izquierda;
		}

		public void setIzquierda(Nodo_ABB izquierda) {
			this.izquierda = izquierda;
		}

		public Nodo_ABB getDerecha() {
			return derecha;
		}

		public void setDerecha(Nodo_ABB derecha) {
			this.derecha = derecha;
		}
		
		public void imprimirDatoLimited(MatrizDispersa temp_Matriz) {
			if(contador>0) {
				contador--;
				temp+= "->" + getDato() ;
				
				for (Nodes_Colors nodes_Colors : Nodos) {
					temp_Matriz.insertarNodo(nodes_Colors.columna, nodes_Colors.fila,  nodes_Colors.color);
				}
				
			}
		}
		
		public void imprimirDato() {
			System.out.println(this.getDato());
		}
	}
	
/*
	public static void main(String[] argumentos) {
		System.out.println("Sergie Daniel Arizandieta Yol");
		Arbol_Binario arbol = new Arbol_Binario();
		arbol.insertar(0, null);
		arbol.insertar(1, null);
		System.out.println("Recorriendo inorden: (RECURSIVO)");
		arbol.inorden();
		System.out.println("Testing");
		System.out.println(arbol.existe(1));
		
		//System.out.println("Recorriendo propio inorden: (ITERATIVA)");
		//arbol.MyInOrden(arbol.raiz);

	}
	
	*/
}
