package storage;

import java.util.LinkedList;

import objects.Nodes_Colors;



public class Arbol_Binario {

	public Nodo_ABB raiz;

	public Arbol_Binario() {
		raiz = null;
	}

	public boolean existe(int busqueda) {
		return existe(this.raiz, busqueda);
	}

	private boolean existe(Nodo_ABB n, int busqueda) {
		if (n == null) {
			return false;
		}
		if (n.getDato() == busqueda) {
			return true;
		} else if (busqueda < n.getDato()) {
			return existe(n.getIzquierda(), busqueda);
		} else {
			return existe(n.getDerecha(), busqueda);
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
			padre.Nodos = Nodos_new;
		} else {
			if (padre.getIzquierda() == null) {
				padre.setIzquierda(new Nodo_ABB(dato,Nodos_new));
			} else {
				this.insertar(padre.getIzquierda(), dato,Nodos_new);
			}
		}
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

	private void postorden(Nodo_ABB n) {
		if (n != null) {
			postorden(n.getIzquierda());
			postorden(n.getDerecha());
			n.imprimirDato();
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
		private int dato;
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

		public void imprimirDato() {
			System.out.println(this.getDato());
		}
	}
	

	public static void main(String[] argumentos) {
		System.out.println("Sergie Daniel Arizandieta Yol");
		Arbol_Binario arbol = new Arbol_Binario();
		arbol.insertar(0, null);
		arbol.insertar(1, null);
		System.out.println("Recorriendo inorden: (RECURSIVO)");
		arbol.inorden();
		System.out.println("Recorriendo propio inorden: (ITERATIVA)");
		arbol.MyInOrden(arbol.raiz);

	}
	
}
