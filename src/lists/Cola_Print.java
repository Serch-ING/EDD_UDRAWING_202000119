package lists;

import java.util.concurrent.ThreadLocalRandom;


public class Cola_Print {
	public static ThreadLocalRandom tlr = ThreadLocalRandom.current();

	Nodo_Cola_Print primero;
	public int pasosTotales;

	public Cola_Print(int pasosTotales) {
		this.primero = null;
		this.pasosTotales = pasosTotales;
	}

	public void enqueue(int id_cliente) {

		Nodo_Cola_Print new_node = new Nodo_Cola_Print(id_cliente);
		if (isNone()) {
			this.primero = new_node;
		} else {
			Nodo_Cola_Print actual = this.primero;

			while (actual.next != null) {

				actual = actual.next;
			}

			actual.next = new_node;
		}
	}

	public void printing(Circular_Doble_espera Waiting_clients) {
		if (isNone() == false) {
			this.primero.pasos += 1;
			if (this.primero.pasos == this.pasosTotales) {
				Boolean lleva_color;
				String tipo = "";
				if (this.pasosTotales == 1) {
					tipo = "Blanco y negro";
					lleva_color = false;
				} else {
					tipo = "color";
					lleva_color = true;
				}
				Waiting_clients.Give_img(this.primero.id_cliente, lleva_color);
				System.out.println("Se entrego imagen: " + tipo + " a cliente " + this.primero.id_cliente);
				Dequeue_last();
			}
		}
	}

	public void Dequeue_last() {
		if (isNone()) {
			//System.out.println("cola vacia");
		} else {
			Nodo_Cola_Print anterior = this.primero;
			this.primero = this.primero.next;
			anterior = null;
		}

	}

	public void Peek() {
		System.out.println(this.primero.id_cliente);

	}

	public void Dequeue() {
		Nodo_Cola_Print anterior = this.primero;
		this.primero = this.primero.next;

	}
	
	/*
	public Boolean Dequeue_posibility() {
		if (isNone() == true) {
			System.out.println("cola vacia");
			return false;
		} else {
			return true;
		}
	}*/

	public void showList() {
		if (isNone() == false) {
			Nodo_Cola_Print actual = this.primero;
			while (actual != null) {
				// System.out.println(actual.info+" 1");
				String color = "";
				if (this.pasosTotales == 1) {
					color = "Blanco negro";
				} else if (this.pasosTotales == 2) {
					color = "Color";
				}
				System.out.println("id: " + actual.id_cliente + " TIpo: " + color + " pasos: " + this.pasosTotales);
				actual = actual.next;
			}
		}
	}

	/*
	 * public void Search(Object data) { if (isNone() == false) {
	 * Nodo_Cola_Reception actual = this.primero; while (actual != null &&
	 * actual.cliente != data) { actual = actual.next; if (actual == null) {
	 * System.out.println("No se encontro el dato: " + data); break; } } if (actual
	 * != null && actual.cliente == data) { System.out.println("Dato encontrado: " +
	 * data); } } }
	 * 
	 * public void Delete(Object data) { if (isNone() == false) {
	 * Nodo_Cola_Reception actual = this.primero; Nodo_Cola_Reception anterior =
	 * null;
	 * 
	 * while (actual != null && actual.cliente != data) { anterior = actual; actual
	 * = actual.next; }
	 * 
	 * if (anterior == null) { this.primero = actual.next;
	 * System.out.println("Se elimino el dato: " + data); } else if (actual != null)
	 * { anterior.next = actual.next; actual.next = null;
	 * System.out.println("Se elimino el dato: " + data); } else {
	 * System.out.println("No se encontro el dato a eliminar: " + data); } } }
	 */

	public Boolean isNone() {
		return this.primero == null;
	}

	public class Nodo_Cola_Print {

		public Nodo_Cola_Print next;
		public int id_cliente;
		public int pasos;

		public Nodo_Cola_Print(int id_cliente) {
			this.next = null;
			this.id_cliente = id_cliente;
			this.pasos = 0;
		}
	}

}
