/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;


public class RamaB {

    boolean hoja;//identificar si es una hoja
    int contador;//identificar la cantidad de elementos que tiene la rama
    public NodoB primero;

    public RamaB() {
        this.primero = null;
        this.hoja = true;
        this.contador = 0;
    }

    public void insertar(NodoB nuevo) {
        if (primero == null) {
            //primero en la lista
            primero = nuevo;
            contador++;
        } else {
            //recorrer e insertar
            NodoB aux = primero;
            while (aux != null) {
                if (aux.id.compareTo(nuevo.id) == 0) {//------------->ya existe en el arbol
                    System.out.println("El ID " + nuevo.id + " ya existe");
                    JOptionPane.showMessageDialog(null,"El DPI " + nuevo.id + " ya existe");
                    break;
                } else {
                    if ( aux.id.compareTo(nuevo.id) == 1)  {
                        if (aux == primero) {//------------->insertar al inicio
                            aux.anterior = nuevo;
                            nuevo.siguiente = aux;
                            //ramas del nodo
                            aux.izquierda = nuevo.derecha;
                            nuevo.derecha = null;

                            primero = nuevo;
                            contador++;
                            break;
                        } else {//------------->insertar en medio;
                            nuevo.siguiente = aux;
                            //ramas del nodo
                            aux.izquierda = nuevo.derecha;
                            nuevo.derecha = null;

                            nuevo.anterior = aux.anterior;
                            aux.anterior.siguiente = nuevo;
                            aux.anterior = nuevo;
                            contador++;
                            break;
                        }
                    } else if (aux.siguiente == null) {//------------->insertar al final
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
    
	public void print_start_Cleintes(NodoB primero) {
		
		Queue<NodoB> cola_nodos = new LinkedList<NodoB>();
		cola_nodos.offer(primero);

		while (cola_nodos.peek() != null) {
			imprimircompletoCleintes(cola_nodos.poll(), cola_nodos);
		}
	}

	public void imprimircompletoCleintes(NodoB primero, Queue<NodoB> cola_nodos) {

		NodoB aux = primero;

		while (aux != null) {

			String temp = (aux.siguiente == null) ? aux.cliente.Name + "\n" : aux.cliente.Name + ", ";
			System.out.print(temp);
			

			if (aux.izquierda != null) {
				cola_nodos.offer(aux.izquierda.primero);
			}

			if (aux.derecha != null && aux.siguiente == null ) {
				cola_nodos.offer(aux.derecha.primero);
			}
		
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

			String temp = (aux.siguiente == null) ? aux.id + "\n" : aux.id + ", ";
			System.out.print(temp);
			

			if (aux.izquierda != null) {
				cola_nodos.offer(aux.izquierda.primero);
			}

			if (aux.derecha != null && aux.siguiente == null ) {
				cola_nodos.offer(aux.derecha.primero);
			}
		
			aux = aux.siguiente;

		}
	}
	


	public void asdadasdd(NodoB primero,Long id) {

		NodoB aux = primero;

		while (aux != null) {

			System.out.println(aux.id + ", ");
			if(aux.id == id) {
				System.out.println("ENCONTRADO:" + id);
			}
			
			if (aux.izquierda != null) {
				asdadasdd(aux.izquierda.primero,id);
			}

			if (aux.derecha != null && aux.siguiente == null ) {
				asdadasdd(aux.derecha.primero,id);
			}
		
			aux = aux.siguiente;
		}
	}

}
