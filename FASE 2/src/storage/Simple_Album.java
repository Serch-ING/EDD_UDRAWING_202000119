package storage;

import org.json.simple.JSONArray;

public class Simple_Album {

	Nodo_Simple primero;

	public Simple_Album() {
		this.primero = null;
	}

	public void insert(String info) {
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

	public void Search(String data) {
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

	public boolean SearchValidacion(String data) {
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
			while (actual != null && !(actual.info.equals(data))) {
				actual = actual.next;
				if (actual == null) {
					return false;

				}
			}
			if (actual != null && actual.info.equals(data)) {
				return true;

			}
		}
		return false;
	}

	public Nodo_Simple SearchValidacionNode(String data) {
		if (isNone() == false) {
			Nodo_Simple actual = this.primero;
			while (actual != null && !(actual.info.equals(data))) {
				actual = actual.next;
				if (actual == null) {
					return null;

				}
			}
			if (actual != null && actual.info.equals(data)) {
				return actual;

			}
		}
		return null;
	}

	public void insernews(Nodo_Simple actual, JSONArray data) {
		if (actual != null) {
			for (Object object2 : data) {
				Integer img_no = ((Long) object2).intValue();
				actual.Sublist.insert(img_no);
				// System.out.println(img_no);
			}
		}

	}

	public void Delete(String data) {
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
	}

	public Boolean isNone() {
		return this.primero == null;
	}
}

class Nodo_Simple {

	Nodo_Simple next;
	String info;
	Simple_Sublist_Album Sublist = new Simple_Sublist_Album();

	public Nodo_Simple(String info) {
		this.next = null;
		this.info = info;
	}
}
