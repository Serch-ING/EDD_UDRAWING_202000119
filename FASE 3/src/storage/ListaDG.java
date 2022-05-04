package storage;

import java.util.Collections;
import java.util.LinkedList;
import com.fasterxml.jackson.databind.deser.impl.NullsAsEmptyProvider;

public class ListaDG {
	public int cantidad;
	public Nodo_Simple primero;

	public ListaDG() {
		this.primero = null;
	}

	public void conexion(int inicio, int fin, int peso) {
		agregar_conexion(inicio, fin, peso);
		agregar_conexion(fin, inicio, peso);
	}

	public void agregar_conexion(int indice, int finalizacion, int peso) {
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
			cantidad++;
		} else {
			Nodo_Simple actual = this.primero;

			if (!Search_existe(id)) {
				while (actual.siguiente != null) {
					actual = actual.siguiente;
				}
				actual.siguiente = new_node;
				cantidad++;
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
				System.out.print(actual.nombre + "[" + actual.id + "]");
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

	// Metodo ruta mas corta
	// ----------------------------------------------------------------------------------------
	public void CaminoMasCorto(int start, int end) {
		System.out.println("Comenzando desmadre");
		Nodo_Simple inicio = Search_id(start);
		Nodo_Simple finalizancion = Search_id(end);

		if (inicio.sucursal) {

			System.out.println("Inicio: " + inicio.nombre + " id: " + inicio.id);
			System.out.println("Inicio: " + finalizancion.nombre + " id: " + finalizancion.id);

			LinkedList<Nodo_Simple> visitados = new LinkedList<Nodo_Simple>();
			LinkedList<Distancias_nodos> distancias = new LinkedList<Distancias_nodos>();
			LinkedList<Padres> padres = new LinkedList<Padres>();
			LinkedList<Distancias_nodos> cola = new LinkedList<Distancias_nodos>();

			// visitados.add(inicio);
			distancias.add(new Distancias_nodos(inicio, 0));
			padres.add(new Padres(null, inicio));
			cola.add(new Distancias_nodos(inicio, 0));

			comenzando_recorrido(inicio, finalizancion, visitados, distancias, padres, cola);
			
			System.out.println("====================================");
			System.out.println("Logrado recorrido mas corto");
			
			
			
			
			Nodo_Simple padre = null;
			
			
			
			for (Padres datos : padres) {
				if(datos.hijo == finalizancion) {
					padre = datos.Padre;
				}
			}
			
			System.out.println("El padre de: "+ finalizancion.id + " es: " + padre.id);
			
			
			while(padre!= null) {
				
				Nodo_Simple padre_buscado = null;
				
				for (Padres datos : padres) {
					if(datos.hijo == padre) {
						padre_buscado = datos.Padre;
					}
				}
				
				if(padre_buscado!= null) {
					System.out.println("El padre de " + padre.id + " es: " + padre_buscado.id);

					
				}else {
					System.out.println(" La raiz se encontro");

				}
				
				padre = padre_buscado;
				
			
				//padre = padre_buscado;
			}
			
		} else {
			System.out.println("Error");
		}

	}

	public void comenzando_recorrido(Nodo_Simple inicio, Nodo_Simple finalizancion, LinkedList<Nodo_Simple> visitados,
			LinkedList<Distancias_nodos> distancias, LinkedList<Padres> padres, LinkedList<Distancias_nodos> cola) {

		while (cola.peek() != null) {
			Distancias_nodos actual = cola.pop();
			
			if (actual.nodo == finalizancion) {
				System.out.println("MetodoCulminado: " + actual.nodo.id);
				break;
			} else {

				visitados.add(actual.nodo);
				ENodo aux = actual.nodo.inicio;
				
				
				while (aux != null) {
					Boolean visitar = true;
					
					for (Nodo_Simple nodos : visitados) {
						if(nodos.id == aux.ivex) {
							visitar = false;
						}
					}
					
					if(visitar) {
						Nodo_Simple nodo_sublista = Search_id(aux.ivex);
						Distancias_nodos nueva_posible_distancia = new Distancias_nodos(nodo_sublista,actual.distancia + aux.peso);
						Agregar_A_Lista(distancias, nueva_posible_distancia, cola,actual,padres);
					}
					

					aux = aux.siguiente;
				}
			}
		}
	}

	public void Agregar_A_Lista(LinkedList<Distancias_nodos> distancias, Distancias_nodos nueva_posible_distancia,LinkedList<Distancias_nodos> cola, Distancias_nodos actual, LinkedList<Padres> padres) {
		boolean no_encontrado = true;
		for (Distancias_nodos distancias_nodos : distancias) {
			if (distancias_nodos.nodo == nueva_posible_distancia.nodo) {
				no_encontrado = true;
				if (distancias_nodos.distancia > nueva_posible_distancia.distancia) {
					distancias_nodos.distancia = nueva_posible_distancia.distancia;
					Actualizar_cola(cola, nueva_posible_distancia);
					actulizar_padre(padres,nueva_posible_distancia,actual);
				}
				break;
			}
		}

		if (no_encontrado) {
			distancias.add(nueva_posible_distancia);
			Agregar_cola(cola, nueva_posible_distancia);
			Agregar_padre(padres,nueva_posible_distancia,actual);

		}
	}

	public void Actualizar_cola(LinkedList<Distancias_nodos> cola, Distancias_nodos nueva_posible_distancia) {
		for (Distancias_nodos distancias_nodos : cola) {
			if (distancias_nodos.nodo == nueva_posible_distancia.nodo) {
				distancias_nodos.distancia = nueva_posible_distancia.distancia;
				Collections.sort(cola);
				break;
			}
		}
	}

	public void Agregar_cola(LinkedList<Distancias_nodos> cola, Distancias_nodos nueva_posible_distancia) {
		cola.add(nueva_posible_distancia);
		Collections.sort(cola);
	}
	
	public void actulizar_padre( LinkedList<Padres> padres,Distancias_nodos nueva_posible_distancia, Distancias_nodos actual) {
		for (Padres datos : padres) {
			if(datos.hijo == nueva_posible_distancia.nodo) {
				datos.Padre = actual.nodo;
			}
		}
	}
	
	public void Agregar_padre( LinkedList<Padres> padres,Distancias_nodos nueva_posible_distancia, Distancias_nodos actual) {
		Padres padres_nuevos = new Padres(actual.nodo, nueva_posible_distancia.nodo);
		padres.add(padres_nuevos);
	}


	private class Padres {

		Nodo_Simple Padre;
		Nodo_Simple hijo;

		// constructor de la clase
		public Padres(Nodo_Simple Padre, Nodo_Simple hijo) {
			this.Padre = Padre;
			this.hijo = hijo;

		}
	}

	private class Distancias_nodos implements Comparable<Distancias_nodos> {

		Nodo_Simple nodo;
		int distancia;

		// constructor de la clase
		public Distancias_nodos(Nodo_Simple nodo, int distancia) {
			this.distancia = distancia;
			this.nodo = nodo;

		}

		// Override the compareTo() method
		@Override
		public int compareTo(Distancias_nodos s) {
			if (distancia > s.distancia) {
				return 1;
			} else if (distancia == s.distancia) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	public Nodo_Simple Search_id(int id) {

		if (isNone() == false) {
			Nodo_Simple actual = primero;
			while (actual != null && actual.id != id) {
				actual = actual.siguiente;
				if (actual == null) {
					// System.out.println("No se encontro el dato: " + data);
					return null;
				}
			}
			if (actual != null && actual.id == id) {
				// System.out.println("Dato encontrado: " + data);
				return actual;
			}
		}
		return null;
	}
	// -------------------------------------------------------------------------------------------

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
				// System.out.print("->" + "[" + aux.ivex + "]{"+aux.peso+"}");
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