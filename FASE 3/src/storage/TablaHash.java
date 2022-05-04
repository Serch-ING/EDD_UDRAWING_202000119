package storage;

import java.util.LinkedList;

import objects.HashData;
import objects.Mensajero;

public class TablaHash {
	public int longitud;
	public Mensajero mensajero;

	public Mensajero tabla[];
	public int capacidad;

	public TablaHash() {
		this.longitud = 37;
		//this.longitud = 29;
		tabla = new Mensajero[longitud];
		capacidad = (int) Math.round(0.75 * longitud);

	}
	
	public Object[][] retornarTable(){
		Object[][] lista = new Object[this.longitud][7];
		
		int contador = 0;
		for (int i = 0; i < tabla.length; i++) {
			
			if(this.tabla[i] != null) {
				
				lista[contador][0]= tabla[i].DPI;
				lista[contador][1]= tabla[i].nombre;
				lista[contador][2]= tabla[i].apellido;
				lista[contador][3]= tabla[i].tipo_liciencia;
				lista[contador][4]= tabla[i].genero;
				lista[contador][5]= tabla[i].telefono;
				lista[contador][6]= tabla[i].direccion;

				contador++;
			}
			
		}
		
		
		return lista;
	}
	

	public boolean EstaLlena() {

		int contador = 0;

		for (int i = 0; i < tabla.length; i++) {
			if (this.tabla[i] != null) {
				contador++;
			}
		}

		if (contador >= capacidad) {
			return true;
		}

		return false;
	}

	public void CambiarTabla() {

		Mensajero tablanew[] = new Mensajero[this.longitud * 2];
		this.longitud *=2;
		this.capacidad = (int) Math.round(0.75 * longitud);
		
		for (int i = 0; i < tabla.length; i++) {
			tablanew[i] = this.tabla[i];
		}

		this.tabla = tablanew;
		//System.out.println("SE AMPLIO TABLA");

	}

	public void mostar() {
		for (int i = 0; i < tabla.length; i++) {

			System.out.println("Posicion " + i + " data: " + tabla[i]);

		}

		System.out.println("/////////////////////////////////////// \n");

	}

	public void insertar(Mensajero mensajero) {
		int indice = (int) (mensajero.DPI % this.longitud);
		//System.out.println("Inidce: " + indice);

		for (int i = 0; i < tabla.length; i++) {
			if (i == indice) {
				if (this.tabla[i] == null) {
					this.tabla[i] = mensajero;
					break;
				} else {
					Colision(mensajero, i, 1);
					break;
				}
			}
		}

		if (EstaLlena()) {
			CambiarTabla();
		}
		//System.out.println("Se ingeso mensajero");
	}

	public void Colision(Mensajero mensajero, int IndiceOriginal, int i) {
		//System.out.println("Colision");
		int nuevoIndice = (int) (mensajero.DPI % 7 + 1) * i;

		//System.out.println("nuevo indiice: " + nuevoIndice);

		int temp = IndiceOriginal + nuevoIndice;

		//System.out.println("suma indiice: " + (IndiceOriginal + nuevoIndice));
		
		boolean validacion = true;
		while (validacion) {

			for (int j = 0; j < tabla.length; j++) {
				if (temp == 0) {
					if (this.tabla[j] == null) {
						this.tabla[j] = mensajero;
						validacion= false;
						break;
					} else {
						i++;
						Colision(mensajero, IndiceOriginal,i );
						validacion= false;
						break;
					}
				}
				temp--;
			}
		}
	}
	
	public Mensajero retornoMensajero(Long DPI){
		
		for (int i = 0; i < tabla.length; i++) {
			if(this.tabla[i] != null) {
				if(this.tabla[i].DPI == DPI.longValue()) {
					return this.tabla[i];
				}
			}	
		}
		
		
		return null;
	}
	

}
