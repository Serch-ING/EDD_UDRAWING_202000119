package lists;

import object.client;

public class Circular_Doble_espera {
	Nodo_Doble_waiting_clients primero;
	Nodo_Doble_waiting_clients ultimo;

	public Circular_Doble_espera() {
		
		this.primero = null;
		this.ultimo = null;
	}

	public void insert(client client) {
		Nodo_Doble_waiting_clients new_node = new Nodo_Doble_waiting_clients(client);
		if (isNone()) {
			this.primero = new_node;
			this.ultimo = new_node;
		} else {
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
				System.out.println(actual.client.id);
				actual = actual.next;
			} while (actual != this.primero);
		}
	}

	public void Search(int idClient) {

		if (isNone() == false) {
			Nodo_Doble_waiting_clients actual = this.primero;
			Boolean Encontrado = true;
			do {
				if (actual.client.id == idClient) {
					// System.out.println("Dato encontrado: " + idClient);
					Encontrado = false;
					break;
				} else {
					actual = actual.next;
				}
			} while (actual != this.primero);

			if (Encontrado) {
				System.out.println("No se encontro el dato: " + idClient);
			}

		} else {
			System.out.println("Vacio");
		}
	}

	public void exit_of_system() {
		Nodo_Doble_waiting_clients actual = this.primero;
		if (isNone() == false) {
			do {
				if (actual.client.img_bwTotal == actual.client.img_bw
						&& actual.client.img_colorTotal == actual.client.img_color) {
					Delete(actual.client.id);
				}
				actual = actual.next;
				if(isNone() == true) {
					break;
				}
			} while (actual != this.primero);
		}
	}

	public void Give_img(int idClient, Boolean Lleva_color) {

		if (isNone() == false) {
			Nodo_Doble_waiting_clients actual = this.primero;
			Boolean Encontrado = true;
			do {
				if (actual.client.id == idClient) {
					// System.out.println("Dato encontrado: " + idClient);
					if (Lleva_color) {
						actual.client.img_color += 1;
					} else {
						actual.client.img_bw += 1;
					}

					Encontrado = false;
					break;
				} else {
					actual = actual.next;
				}
			} while (actual != this.primero);

			if (Encontrado) {
				System.out.println("No se encontro el dato: " + idClient);
			}

		} else {
			System.out.println("Vacio");
		}
	}

	public void Delete(int idClient) {
		if (isNone() == false) {

			Nodo_Doble_waiting_clients actual = this.primero;
			Nodo_Doble_waiting_clients anterior = this.ultimo;
			if (this.primero == this.ultimo) { 
				if (actual.client.id == idClient) {
					System.out.println("Cliente: " + actual.client.id + " salio del sistema last");
					this.primero = null;
					this.ultimo = null;
				}
			} else {
				
				do {
					if (actual.client.id == idClient) {
						if (actual == this.primero) {
							this.primero = this.primero.next;
							this.ultimo.next = this.primero;
							this.primero.previous = this.ultimo;
						}
						if (actual == ultimo) {
							this.ultimo = anterior;
							this.primero.previous = this.ultimo;
							this.ultimo.next = this.primero;
						} else {
							anterior.next = actual.next;
							actual.next.previous = anterior;
						}
						System.out.println("Cliente: " + actual.client.id + " salio del sistema");
					}
					anterior = actual;
					actual = actual.next;
				} while (actual != this.primero);

			}
			
		}

	}

	public Boolean isNone() {
		return this.primero == null;
	}
	
}

class Nodo_Doble_waiting_clients {

	Nodo_Doble_waiting_clients next, previous;
	client client;

	public Nodo_Doble_waiting_clients(client client) {
		this.next = this;
		this.previous = this;
		this.client = client;
	}

}