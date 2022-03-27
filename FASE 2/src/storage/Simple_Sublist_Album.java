package storage;

import java.util.LinkedList;

import objects.Clients;

public class Simple_Sublist_Album {

	Nodo_Simple_Sub primero;

	public Simple_Sublist_Album() {
		this.primero = null;
	}
	
	public boolean validacion_existe(Clients cliente) {
		LinkedList<Integer> datos_Eliminar = new LinkedList<Integer> ();
		
		if (isNone() == false) {
			Nodo_Simple_Sub actual = this.primero;
			while (actual != null) {
				if(!cliente.AVLImages.validandoALbum(actual.info)) {
					datos_Eliminar.add(actual.info);
				}
				//System.out.println(actual.info);
				actual = actual.next;
			}
		}
		
		if(datos_Eliminar.size()==0) {
			return false;
		}else {
			
			for (Integer i : datos_Eliminar) {
				this.Delete(i);
			}
			
			return true;
		}	
	}
	
	public LinkedList<String> catindad_imagenes() {
		LinkedList<String> temp = new LinkedList<String>();
		if (isNone() == false) {
			Nodo_Simple_Sub actual = this.primero;
			while (actual != null) {
				temp.add(String.valueOf(actual.info));
				System.out.println(actual.info);
				actual = actual.next;
			}
			return temp;
		}
		
		return temp;
	}

	public String enlaces(int contador) {
		String temp = "";
		Nodo_Simple_Sub actual = this.primero;
		while (actual != null) {
			temp+= actual.info+ "[group="+contador+"];\n";
			
			if(actual.next!= null) {
				temp += actual.info + "->" + actual.next.info+"\n";
			}
			
			//System.out.println(actual.info);
			actual = actual.next;
		}
		
		return temp;

	}

	public void insert(int info) {
		Nodo_Simple_Sub new_node = new Nodo_Simple_Sub(info);
		if (isNone()) {
			this.primero = new_node;
		} else {
			new_node.next = this.primero;
			this.primero = new_node;
		}
	}

	public void showList() {
		if (isNone() == false) {
			Nodo_Simple_Sub actual = this.primero;
			while (actual != null) {
				System.out.println(actual.info);
				actual = actual.next;
			}
		}
	}

	public void Search(int data) {
		if (isNone() == false) {
			Nodo_Simple_Sub actual = this.primero;
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

	public void Delete(int data) {
		if (isNone() == false) {
			Nodo_Simple_Sub actual = this.primero;
			Nodo_Simple_Sub anterior = null;

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
				//System.out.println("Se elimino el dato: " + data);
			} else {
				//System.out.println("No se encontro el dato a eliminar: " + data);
			}
		}
	}

	public Boolean isNone() {
		return this.primero == null;
	}
}

class Nodo_Simple_Sub {

	Nodo_Simple_Sub next;
	int info;
	Object puntero;

	public Nodo_Simple_Sub(int info) {
		this.next = null;
		this.info = info;
	}
}
