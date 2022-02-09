package lists;

public class Simple_Windows {

	Nodo_Simple primero;

	public Simple_Windows() {
		this.primero = null;
	}

	public void insert(Object info) {
		Nodo_Simple new_node = new Nodo_Simple(info);
		if (isNone()) {
			this.primero = new_node;
		} else {
			new_node.next = this.primero;
			this.primero = new_node;
		}
	}

	public void showList() {
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
			while (actual != null) {
				System.out.println(actual.info);
				actual = actual.next;
			}
		}
	}

	public void Search(Object data) {
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
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
	
	/*
	public void Delete(Object data) {
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
			Nodo_Simple anterior = null;

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

class Nodo_Simple {

	Nodo_Simple next;
	Object info;

	public Nodo_Simple(Object info) {
		this.next = null;
		this.info = info;
	}
}
