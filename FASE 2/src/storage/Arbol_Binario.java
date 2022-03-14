package storage;

import java.util.LinkedList;

public class Arbol_Binario {

	Nodo_ABB raiz;

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

	public void insertar(int dato) {
		if (this.raiz == null) {
			this.raiz = new Nodo_ABB(dato);
		} else {
			this.insertar(this.raiz, dato);
		}
	}

	private void insertar(Nodo_ABB padre, int dato) {
		if (dato > padre.getDato()) {
			if (padre.getDerecha() == null) {
				padre.setDerecha(new Nodo_ABB(dato));
			} else {
				this.insertar(padre.getDerecha(), dato);
			}
		} else {
			if (padre.getIzquierda() == null) {
				padre.setIzquierda(new Nodo_ABB(dato));
			} else {
				this.insertar(padre.getIzquierda(), dato);
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

		public Nodo_ABB(int dato) {
			this.dato = dato;
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
		arbol.insertar(1);
		arbol.insertar(2);
		arbol.insertar(3);
		arbol.insertar(4);
		arbol.insertar(0);
		System.out.println("Recorriendo inorden: (RECURSIVO)");
		arbol.inorden();
		System.out.println("Recorriendo propio inorden: (ITERATIVA)");
		arbol.MyInOrden(arbol.raiz);

	}

}
