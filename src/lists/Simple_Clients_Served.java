package lists;

import java.io.FileWriter;
import java.io.PrintWriter;

import object.client;

public class Simple_Clients_Served {

	Nodo_Simple_Client_Served primero;

	public Simple_Clients_Served() {
		this.primero = null;
	}

	public void insert(client info) {//log_2(n)
		Nodo_Simple_Client_Served new_node = new Nodo_Simple_Client_Served(info);
		if (isNone()) {
			this.primero = new_node;
		} else {
			Nodo_Simple_Client_Served actual = this.primero;
			while (actual.next != null) {
				actual = actual.next;
			}
			actual.next = new_node;
		}
	}

	public void showList() {
		if (isNone() == false) {
			Nodo_Simple_Client_Served actual = this.primero;
			while (actual != null) {
				System.out.println(actual.info);
				actual = actual.next;
			}
		}
	}

	public void Search(Object data) {
		if (isNone() == false) {
			Nodo_Simple_Client_Served actual = this.primero;
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

	public Boolean isNone() {
		return this.primero == null;
	}

	class Nodo_Simple_Client_Served {

		Nodo_Simple_Client_Served next;
		client info;

		public Nodo_Simple_Client_Served(client info) {
			this.next = null;
			this.info = info;
		}
}
}
