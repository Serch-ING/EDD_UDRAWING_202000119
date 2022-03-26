package storage;

import java.util.LinkedList;
import java.util.Queue;

public class Pagina {
	boolean hoja;// identificar si es una hoja
	int contador;// identificar la cantidad de elementos que tiene la pagina
	public NodoB primero;

	public Pagina() {
		this.primero = null;
		this.hoja = true;
		this.contador = 0;
	}

	public void insertar(NodoB nuevo) {
		if (primero == null) {
			// primero en la lista
			primero = nuevo;
			contador++;
		} else {
			// recorrer e insertar
			NodoB aux = primero;
			while (aux != null) {
				if (aux.id == nuevo.id) {// ------------->ya existe en el arbol
					System.out.println("El ID " + nuevo.id + " ya existe");
					break;
				} else {
					if (aux.id > nuevo.id) {
						if (aux == primero) {// ------------->insertar al inicio
							aux.anterior = nuevo;
							nuevo.siguiente = aux;
							// ramas del nodo
							aux.izquierda = nuevo.derecha;
							nuevo.derecha = null;

							primero = nuevo;
							contador++;
							break;
						} else {// ------------->insertar en medio;
							nuevo.siguiente = aux;
							// ramas del nodo
							aux.izquierda = nuevo.derecha;
							nuevo.derecha = null;

							nuevo.anterior = aux.anterior;
							aux.anterior.siguiente = nuevo;
							aux.anterior = nuevo;
							contador++;
							break;
						}
					} else if (aux.siguiente == null) {// ------------->insertar al final
						aux.siguiente = nuevo;
						nuevo.anterior = aux;
						contador++;
						break;
					}
				}
				aux = aux.siguiente;
			}

		}
	}

	public void imprimir() {
		NodoB aux = this.primero;
		while (aux != null) {
			System.out.println(aux.id + ", ");
			aux = aux.siguiente;
		}
	}

	public void print_start(NodoB primero) {
		Queue<NodoB> cola_nodos = new LinkedList<NodoB>();
		cola_nodos.offer(primero);

		while (cola_nodos.peek() != null) {
			imprimircompleto(cola_nodos.poll(), cola_nodos);
		}

	}

	public void imprimircompleto(NodoB primero, Queue<NodoB> cola_nodos) {

		NodoB aux = primero;

		while (aux != null) {

			String print = (aux.siguiente == null) ? aux.id + "\n" : aux.id + ", ";
			System.out.print(print);

			if (aux.izquierda != null) {
				cola_nodos.offer(aux.izquierda.primero);
			}

			if (aux.derecha != null&&aux.siguiente == null ) {
				cola_nodos.offer(aux.derecha.primero);
			}
		
			aux = aux.siguiente;

		}
	}
}

class NodoB {
	// Valores
	int id; // --> clave
	// Apuntadores
	NodoB anterior, siguiente;
	Pagina izquierda, derecha;

	public NodoB(int id) {
		this.id = id;
		this.anterior = this.siguiente = null;
		this.izquierda = this.derecha = null;
	}

	public NodoB(int id, Pagina izquierda, Pagina derecha) {
		this.id = id;
		this.anterior = this.siguiente = null;
		this.izquierda = izquierda;
		this.derecha = derecha;
	}
}