package storage;

public class ListaDG {

	Nodo_Simple primero;

	public ListaDG() {
		this.primero = null;
	}

	public void conexion(int inicio,int fin,int peso) {
		agregar_conexion(inicio,fin,peso);
		agregar_conexion(fin,inicio,peso);
	}
	
	public void agregar_conexion(int indice,int finalizacion, int peso) {
		if (isNone() == false) {
			Nodo_Simple actual = primero;
			while (actual != null && actual.id != indice) {
				actual = actual.siguiente;
				if (actual == null) {
					// System.out.println("No se encontro el dato: " + data);
					break;
				}
			}
			if (actual != null && actual.id == indice) {
				actual.insert(indice, finalizacion, peso);
				// System.out.println("Dato encontrado: " + data);
			}
		}
	}
	
	
	public void insert(int id, String departamento, String nombre, Boolean sucursal) {
		Nodo_Simple new_node = new Nodo_Simple(id, departamento, nombre, sucursal);
		if (isNone()) {
			this.primero = new_node;
		} else {
			Nodo_Simple actual = this.primero;

			if (!Search_existe(id)) {
				while (actual.siguiente != null) {
					actual = actual.siguiente;
				}
				actual.siguiente = new_node;
			} else {
				System.out.println("Ya existe el nodo");
			}
		}
	}

	public Boolean Search_existe(int id) {

		if (isNone() == false) {
			Nodo_Simple actual = primero;
			while (actual != null && actual.id != id) {
				actual = actual.siguiente;
				if (actual == null) {
					// System.out.println("No se encontro el dato: " + data);
					return false;
				}
			}
			if (actual != null && actual.id == id) {
				// System.out.println("Dato encontrado: " + data);
				return true;
			}
		}
		return false;
	}

	public void showList() {
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
			while (actual != null) {
				 System.out.print( actual.nombre+ "["+actual.id+"]");
				 actual.imprimir();
				 System.out.println();
				// actual.nombre + " , " + actual.sucursal);
				actual = actual.siguiente;
			}
		}
	}
	
	public void Imprimir() {
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
			while (actual != null) {
				// System.out.println(actual.id + " , " + actual.departamento + " , " +
				// actual.nombre + " , " + actual.sucursal);
				actual = actual.siguiente;
			}
		}
	}

	public Boolean isNone() {
		return this.primero == null;
	}

	class Nodo_Simple {

		Nodo_Simple siguiente;
		public int id;
		public String departamento, nombre;
		public Boolean sucursal;
		ENodo inicio = null;

		public Nodo_Simple(int id, String departamento, String nombre, Boolean sucursal) {
			this.siguiente = null;
			this.id = id;
			this.departamento = departamento;
			this.nombre = nombre;
			this.sucursal = sucursal;
		}

		// metodo de insertar en la sublista
		public void insert(int actual, int ivex, int peso) {
			ENodo nuevo = new ENodo(ivex, peso);

			if (inicio == null) {
				inicio = nuevo;
			} else {
				ENodo aux = inicio;
				if (!Search(nuevo.ivex)) {
					while (true) {
						if (aux.siguiente == null) {
							aux.siguiente = nuevo;
							break;
						}
						aux = aux.siguiente;
					}
				} else {
					// System.out.println("Enlace ya existe: " + actual + " -> " + nuevo.ivex);
				}
			}
		}

		public Boolean Search(int data) {

			if (inicio != null) {
				ENodo actual = inicio;
				while (actual != null && actual.ivex != data) {
					actual = actual.siguiente;
					if (actual == null) {
						// System.out.println("No se encontro el dato: " + data);
						return false;
					}
				}
				if (actual != null && actual.ivex == data) {
					// System.out.println("Dato encontrado: " + data);
					return true;
				}
			}

			return false;
		}

		// imprimir la sublista
		public void imprimir() {
			ENodo aux = inicio;
			while (aux != null) {
				System.out.print("->" + "[" + aux.ivex + "]");
				aux = aux.siguiente;
			}
		}

	}

	// Clase para realizar las conexiones entre los nodos
	private class ENodo {

		int ivex;// posicion del nodo conectado
		ENodo siguiente;// nodo siguiete en la sublista
		int peso;

		// constructor de la clase
		public ENodo(int ivex, int peso) {
			this.ivex = ivex;
			this.siguiente = null;
			this.peso = peso;
		}
	}

}