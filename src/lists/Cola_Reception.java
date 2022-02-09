package lists;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Cola_Reception {
	public static ThreadLocalRandom tlr = ThreadLocalRandom.current();
	
	Nodo_Cola_Reception primero;

	public Cola_Reception() {
		this.primero = null;
	}

	public void insert(int id, String name, int img_color, int img_bw) {
		client cliente = new client(id, name, img_color, img_bw);

		Nodo_Cola_Reception new_node = new Nodo_Cola_Reception(cliente);
		if (isNone()) {

			if (id == -1) {
				new_node.info.id = 1;
			}

			this.primero = new_node;
		} else {

			int idAnterior = 2;
			Nodo_Cola_Reception actual = this.primero;

			while (actual.next != null) {

				actual = actual.next;
				if (id == -1) {
					idAnterior = actual.info.id;
				}
			}

			if (id == -1) {
				new_node.info.id = idAnterior + 1;
			}

			actual.next = new_node;
		}
	}
	
	public void Generate_Random() {
		int imgs = tlr.nextInt(1, 4 + 1);
		int img_color = 0;
		int img_bw = 0;
		int aux;
		for (int i = 0; i < imgs; i++) {
			aux = tlr.nextInt(3, 4 + 1);
			if (aux % 2 == 0) {
				img_color += 1;
			} else {
				img_bw += 1;
			}
		}

		String[] names = { "Reid", "Brady", "Lorene", "Randi", "Eal", "Dene", "Karry", "Astrix", "Davina", "Ellsworth",
				"Dorey", "Sanderson", "Marylynne", "Zeke", "Stu", "Fidelity", "Ludvig", "Glenn", "Phylis", "Adlai",
				"Corbet", "Theodora", "Travus", "Dannel", "Delora", "Paulette", "Haskel", "Clovis", "Peder", "Edwina",
				"Helli", "Zachary", "Godiva", "Sabrina", "Sheffield", "Karry" };

		String[] lastnames = { "Sowersby", "Stebles", "Vittore", "Rivalant", "Plummer", "Leon", "Ickovits", "Tayspell",
				"Scading", "McElrath", "Bricknall", "Plesing", "Bagshawe", "Pinnion", "Killby", "Gange", "Fust",
				"Sindell", "Lawrenceson", "Dimbylow", "Misson", "Rudolf", "Aldin", "McCafferky", "Stirton", "Meagher",
				"Liepmann", "Ramelot", "Selvester", "Montague", "Meneghelli", "Korneichik", "Peever", "Flanagan",
				"Martinolli" };

		int noName = tlr.nextInt(0, names.length);
		int noLastnames = tlr.nextInt(0, lastnames.length);

		String Name = names[noName] + " " + lastnames[noLastnames];

		insert(-1, Name, img_color, img_bw);
	}

	public void Out() {
		if (isNone()) {	
			System.out.println("cola vacia");
		}else {
			Nodo_Cola_Reception anterior = this.primero;
			this.primero = this.primero.next;
			anterior = null;
		}
		
	}

	public void showList() {
		if (isNone() == false) {
			Nodo_Cola_Reception actual = this.primero;
			while (actual != null) {
				//System.out.println(actual.info+" 1");
				System.out.println("id: " + actual.info.id + " Name: " + actual.info.name + " color: " + actual.info.img_color+ " ByN: " + actual.info.img_bw);
				actual = actual.next;
			}
		}
	}

	/*public void Search(Object data) {
		if (isNone() == false) {
			Nodo_Cola_Reception actual = this.primero;
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
			Nodo_Cola_Reception actual = this.primero;
			Nodo_Cola_Reception anterior = null;

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

	public class client {

		int id, img_color, img_bw;
		String name;

		public client(int id, String name, int img_color, int img_bw) {
			this.id = id;
			this.name = name;
			this.img_color = img_color;
			this.img_bw = img_bw;
		}

	}

	public class Nodo_Cola_Reception {

		Nodo_Cola_Reception next;
		client info;

		public Nodo_Cola_Reception(client info) {
			this.next = null;
			this.info = info;
		}
	}

}
