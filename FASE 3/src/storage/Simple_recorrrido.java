package storage;

import javax.swing.JOptionPane;

import storage.ListaDG.Nodo_Simple;

public class Simple_recorrrido {

	Nodo_Simple_recorrido primero;

	public Simple_recorrrido() {
		this.primero = null;
	}

	public void insert(Object info, Nodo_Simple data) {
		Nodo_Simple_recorrido new_node = new Nodo_Simple_recorrido(info,data);
		if (isNone()) {
			this.primero = new_node;
		} else {
			new_node.next = this.primero;
			this.primero = new_node;
		}
	}

	public void showList() {
		if (isNone() == false) {
			Nodo_Simple_recorrido actual = this.primero;
			while (actual != null) {
				System.out.println(actual.info);
				actual = actual.next;
			}
		}
	}
	
	

	public void showList_recorrido() {
		int Contador =0;
		System.out.println("Comenzando recorrido");
		if (isNone() == false) {
			Nodo_Simple_recorrido actual = this.primero;
			while (actual != null) {
				JOptionPane.showMessageDialog(null,"Mensajero se encuntra en:\n id: " + actual.data.id  + "\nUbicacion: " + actual.data.departamento + ", " + actual.data.nombre + "\nTimpo: " + Contador + " h");
				Contador +=  Math.floor(Math.random()*(1-3+1)+3);
				System.out.print(actual.data.id + " -> " );
				actual = actual.next;
			}
			
			System.out.println("Se termino recorrido");
		}
	}


	public void Search(Object data) {
		if (isNone() == false) {
			Nodo_Simple_recorrido actual = this.primero;
			while (actual != null && actual.info != data) {
				actual = actual.next;
				if (actual == null) {
					System.out.println("No se encontro el dato: " + data);
					break;
				}
			}
			if (actual != null && actual.info == data) {
				System.out.println("Dato encontrado: " + data);
			}
		}
	}

	public void Delete(Object data) {
		if (isNone() == false) {
			Nodo_Simple_recorrido actual = this.primero;
			Nodo_Simple_recorrido anterior = null;

			while (actual != null && actual.info != data) {
				anterior = actual;
				actual = actual.next;
			}

			if (anterior == null) {
				this.primero = actual.next;
				System.out.println("Se elimino el dato: " + data);
			} else if (actual != null) {
				anterior.next = actual.next;
				actual.next = null;
				System.out.println("Se elimino el dato: " + data);
			} else {
				System.out.println("No se encontro el dato a eliminar: " + data);
			}
		}
	}

	public Boolean isNone() {
		return this.primero == null;
	}
}

class Nodo_Simple_recorrido {
	
	Nodo_Simple_recorrido next;
	Nodo_Simple data;
	Object info;
	
	//int 0 = inicio
	//int 1 = final
	//int 2 = trancursp
	String ubicacion;
	public Nodo_Simple_recorrido(Object info,Nodo_Simple data) {
		this.data =data;
		
		this.next = null;
		this.info = info;
	}
}
