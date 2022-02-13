package lists;



public class Pila_Images {

	Nodo_Pila_Imgs primero;

	public Pila_Images() {
		this.primero = null;
	}

	public void Push(int ClienteId,Boolean LlevaColor) {
		Nodo_Pila_Imgs new_node = new Nodo_Pila_Imgs(ClienteId,LlevaColor);
		if (isNone()) {
			this.primero = new_node;
		} else {
			new_node.next = this.primero;
			this.primero = new_node;
		}
	}
	
	public void Peek() {
		System.out.println(this.primero.ClienteId);
	}

	public void showList() {
		if (isNone() == false) {
			Nodo_Pila_Imgs actual = this.primero;
			while (actual != null) {
				System.out.println(actual.ClienteId);
				actual = actual.next;
			}
		}
	}
	
	public void Pop_last() {
		if (isNone()) {	
			System.out.println("cola vacia");
		}else {
			Nodo_Pila_Imgs anterior = this.primero;
			this.primero = this.primero.next;
			anterior = null;
		}
	}
	
	public void Pop() {
		if (isNone()) {	
			System.out.println("cola vacia");
		}else {
			Nodo_Pila_Imgs anterior = this.primero;
			this.primero = this.primero.next;
			anterior = null;
		}
	}
	
	public void Pop_to_printer(Cola_Print bw, Cola_Print color) {
		if (isNone()) {	
			System.out.println("cola vacia");
		
		}else {
			
			while (this.primero != null) {
				
				if(this.primero.LlevaColor) {
					color.enqueue(this.primero.ClienteId);
				}else {
					bw.enqueue(this.primero.ClienteId);
				}
				this.primero = this.primero.next;
				
			}
			System.out.println("Imagenes enviadas a impresoras");
			System.out.println("Blanco y negro");
			bw.showList();
			System.out.println("Color");
			color.showList();
		}
	}

	public Boolean isNone() {
		return this.primero == null;
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
}

class Nodo_Pila_Imgs {

	Nodo_Pila_Imgs next;
	int  ClienteId;
	Boolean LlevaColor; 
	//Color = True, BW = false

	public Nodo_Pila_Imgs(int ClienteId, Boolean LlevaColor) {
		this.next = null;

		this.ClienteId = ClienteId;
		this.LlevaColor = LlevaColor;
	}
}
