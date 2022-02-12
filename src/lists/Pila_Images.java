package lists;



public class Pila_Images {

	Nodo_Pila_Imgs primero;

	public Pila_Images() {
		this.primero = null;
	}

	public void Push(Object info,int ClienteId) {
		Nodo_Pila_Imgs new_node = new Nodo_Pila_Imgs(info,ClienteId);
		if (isNone()) {
			this.primero = new_node;
		} else {
			new_node.next = this.primero;
			this.primero = new_node;
		}
	}
	
	public void Peek() {
		System.out.println(this.primero.info);
	}

	public void showList() {
		if (isNone() == false) {
			Nodo_Pila_Imgs actual = this.primero;
			while (actual != null) {
				System.out.println(actual.info);
				actual = actual.next;
			}
		}
	}
	
 /*
	public void Search(Object data) {
		if (isNone() == false) {
			Nodo_Pila_Imgs actual = this.primero;
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
	}*/

	public void Pop() {
		if (isNone()) {	
			System.out.println("cola vacia");
		}else {
			Nodo_Pila_Imgs anterior = this.primero;
			this.primero = this.primero.next;
			anterior = null;
		}
	}

	public Boolean isNone() {
		return this.primero == null;
	}
}

class Nodo_Pila_Imgs {

	Nodo_Pila_Imgs next;
	Object info;
	int  ClienteId;

	public Nodo_Pila_Imgs(Object info,int ClienteId) {
		this.next = null;
		this.info = info;
		this.ClienteId = ClienteId;
	}
}
