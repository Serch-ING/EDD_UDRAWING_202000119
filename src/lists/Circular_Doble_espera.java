package lists;

public class Circular_Doble_espera {
	Nodo_Doble_waiting_clients primero;
	Nodo_Doble_waiting_clients ultimo;
	
	public Circular_Doble_espera() {
		this.primero = null;
		this.ultimo = null;
	}
	
	public void insert(Object info) {
		Nodo_Doble_waiting_clients new_node = new Nodo_Doble_waiting_clients(info);
		if (isNone()) {
			this.primero = new_node;
			this.ultimo = new_node;
		}else {
			this.ultimo.next = new_node;
			new_node.next = this.primero;
			new_node.previous = this.ultimo;
			this.ultimo = new_node;
			this.primero.previous = ultimo;
		}	
	}
	
	public void showList() {
		Nodo_Doble_waiting_clients actual = this.primero;

		if (isNone() == false) {
			do {
				System.out.println(actual.info);
				actual = actual.next;
			} while (actual != this.primero);
		}
	}
	
	public Boolean isNone() {
		return this.primero == null;
	}
}

class Nodo_Doble_waiting_clients{	
	
	Nodo_Doble_waiting_clients next, previous;
	Object info;
	
	public Nodo_Doble_waiting_clients(Object info) {
		this.next =this;
		this.previous = this;
		this.info = info;
	}
	
}