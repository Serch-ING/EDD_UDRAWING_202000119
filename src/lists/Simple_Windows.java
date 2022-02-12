package lists;
import object.client;
import lists.Pila_Images;

public class Simple_Windows {

	Nodo_Simple_Windows primero;

	public Simple_Windows() {
		this.primero = null;
	}

	public void insert(int info) {
		Nodo_Simple_Windows new_node = new Nodo_Simple_Windows(info);
		if (isNone()) {
			this.primero = new_node;
		} else {
			new_node.next = this.primero;
			this.primero = new_node;
		}
	}

	public void showList() {
		if (isNone() == false) {
			Nodo_Simple_Windows actual = this.primero;
			while (actual != null) {
				System.out.println(actual.noVentanilla);
				actual = actual.next;
			}
		}
	}
	
	
	public void recolect_img() {
		if (isNone() == false) {
			Nodo_Simple_Windows actual = this.primero;
			while (actual != null) {
				if(actual.cliente != null) {
					takeimg_client(actual);
				}
			}
		}
	}
	
	public void takeimg_client(Nodo_Simple_Windows windown) {
		if(windown.cliente.img_bw > 0) {
			windown.cliente.img_bw -= 1;
			windown.PilaImg.Push(1,windown.cliente.id);
		}
	}
	

	public void insert_client(client cliente) {

		Nodo_Simple_Windows actual = this.primero;
		while (actual != null && actual.cliente != null) {
			actual = actual.next;

		}
		if (actual != null && actual.cliente == null) {
			System.out.println("Ventana libre: " + actual.noVentanilla);
			actual.cliente = cliente;
			System.out.println("Cliente: " + cliente.id + " paso a ventanilla: " + actual.noVentanilla);
		}

	}

	public Boolean Search_disposition() {
		if (isNone() == false) {
			Nodo_Simple_Windows actual = this.primero;
			while (actual != null && actual.cliente != null) {
				actual = actual.next;
				if (actual == null) {
					System.out.println(" Ventanas ocupadas");
					return false;
				}
			}
			if (actual != null && actual.cliente == null) {
				System.out.println("Ventana libre: " + actual.noVentanilla);
				return true;
				
			}
		}
		return false;
	}
	
	/*
	public void Delete(Object data) {
		if (isNone() == false) {
			Nodo_Simple_Windows actual = this.primero;
			Nodo_Simple_Windows anterior = null;

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
	}*/

	public Boolean isNone() {
		return this.primero == null;
	}
}

class Nodo_Simple_Windows {

	public Nodo_Simple_Windows next;
	public int noVentanilla;
	public client cliente;
	public Pila_Images PilaImg = new Pila_Images();	

	public Nodo_Simple_Windows(int noVentanilla) {
		this.next = null;
		this.noVentanilla = noVentanilla;
		this.cliente = null;
	}
}
