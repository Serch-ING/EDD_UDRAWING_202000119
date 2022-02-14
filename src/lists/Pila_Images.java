package lists;
//list of images
public class Pila_Images {

	Nodo_Pila_Imgs primero;
	//contructor
	public Pila_Images() {
		this.primero = null;
	}
	//add new data to the list
	public void Push(int ClienteId, Boolean LlevaColor) {
		Nodo_Pila_Imgs new_node = new Nodo_Pila_Imgs(ClienteId, LlevaColor);
		if (isNone()) {
			this.primero = new_node;
		} else {
			new_node.next = this.primero;
			this.primero = new_node;
		}
	}
	
	//show the first data
	public void Peek() {
		System.out.println(this.primero.ClienteId);
	}

	//show all the list
	public void showList() {
		if (isNone() == false) {
			Nodo_Pila_Imgs actual = this.primero;
			while (actual != null) {
				System.out.println(actual.ClienteId);
				actual = actual.next;
			}
		}
	}
		
	//creste the text od graphivz
	public String Text_Graphivz() {
		StringBuilder dot = new StringBuilder();
		String rank="";
		
		String nombresNodos = "";
		String conexiones = "";
		Nodo_Pila_Imgs aux = this.primero;
		while (aux != null) {
			if(aux.LlevaColor) {
				nombresNodos += "Nodo" + aux.hashCode() + "[label=\"" + "IMG C" + "\",fillcolor=\"#81FFDA\"]\n";
			}else {
				nombresNodos += "Nodo" + aux.hashCode() + "[label=\"" + "IMG BN" + "\",fillcolor=\"#81FFDA\"]\n";
			}
			
			if (aux.next != null)
				conexiones += String.format("Nodo%d -> Nodo%d\n", aux.hashCode(), aux.next.hashCode());
			aux = aux.next;
			
		}
		dot.append(nombresNodos);
		dot.append(conexiones);
		return dot.toString();
	}
	
	//add the segment rank of text graphviz
	public String Text_Graphivz_rank() {
		String rank ="";
		if (isNone() == false) {
			Nodo_Pila_Imgs actual = this.primero;
			while (actual != null) {
				rank += ",Nodo" + actual.hashCode();
				actual = actual.next;
			}
		}
		return rank;
	}

	//get ot the list with old funcionality
	public void Pop_last() {
		if (isNone()) {
			//System.out.println("cola vacia");
		} else {
			Nodo_Pila_Imgs anterior = this.primero;
			this.primero = this.primero.next;
			anterior = null;
		}
	}

	//geo out the list new funcionality
	public void Pop() {
		if (isNone()) {
			System.out.println("cola vacia");
		} else {
			Nodo_Pila_Imgs anterior = this.primero;
			this.primero = this.primero.next;
			anterior = null;
		}
	}

	//send data to de printer
	public void Pop_to_printer(Nodo_Simple_Windows windown,Cola_Print bw, Cola_Print color) {
		if (isNone()) {
			System.out.println("cola vacia");

		} else {

			while (this.primero != null) {

				if (this.primero.LlevaColor) {
					color.enqueue(this.primero.ClienteId);
				} else {
					bw.enqueue(this.primero.ClienteId);
				}
				this.primero = this.primero.next;

			}
			System.out.println("Imagenes enviadas de ventanilla: " + windown.noVentanilla + " a impresoras" );
			//System.out.println("Blanco y negro");
			//bw.showList();
			//System.out.println("Color");
			//color.showList();
		}
	}
	//verify is none
	public Boolean isNone() {
		return this.primero == null;
	}

	/*
	 * public void Search(Object data) { if (isNone() == false) { Nodo_Pila_Imgs
	 * actual = this.primero; while (actual != null && actual.info != data) { actual
	 * = actual.next; if (actual == null) {
	 * System.out.println("No se encontro el dato: " + data); break; } } if (actual
	 * != null && actual.info == data) { System.out.println("Dato encontrado: " +
	 * data); } } }
	 */
}
//node of list
class Nodo_Pila_Imgs {

	Nodo_Pila_Imgs next;
	int ClienteId;
	Boolean LlevaColor;
	// Color = True, BW = false
	//constructor
	public Nodo_Pila_Imgs(int ClienteId, Boolean LlevaColor) {
		this.next = null;

		this.ClienteId = ClienteId;
		this.LlevaColor = LlevaColor;
	}
}
