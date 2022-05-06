package storage;

import java.io.FileWriter;
import java.io.PrintWriter;
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
	
	
	public void Graficar(LinkedList<String> cadenas_Arboles_Merkle) {
		String dot = "";
		
		dot = "digraph L {\r\n"
				+ "node[shape=note fillcolor=\"#A181FF\" style =filled fontsize=\"100pt\"]\r\n"
				+ "subgraph cluster_p{\r\n"
				+ "    label= \" Cola impresora Blaco y Negro \"\n";
				
	
		for (int i = 0; i < tabla.length; i++) {

			
			if( tabla[i]!= null) {
				dot+="Nodo" + i + "[label=\"["  + i + "]\ndata: DPI:" +  tabla[i].DPI + " \nName: " + tabla[i].nombre + " " + tabla[i].apellido + "\",fillcolor=\"#81FFDA\"]";
				
			
			}else {
				
				dot+="Nodo" + i + "[label=\"["  + i + "]\",fillcolor=\"#81FFDA\"]";
			
			}
		
		}
		
		
		for (int i = 0; i < tabla.length; i++) {

			
			if((i+1) <  tabla.length) {
				dot+="Nodo" + i + "->Nodo" + (i+1)+ ";\n";
			}
				
				
			
			
		
		}
		dot+="}}";
	
		
		
		System.out.println(dot);
		cadenas_Arboles_Merkle.add("Lista_Mensajeros");
		generate_grapgh("Lista_Mensajeros",dot);
		
	}
	
	private void generate_grapgh(String name, String dot) {
		try {
			Create_File("Grafico\\" + name + ".dot", dot);
			ProcessBuilder pb;
			pb = new ProcessBuilder("dot", "-Tpng", "-o", "Grafico\\" + name + ".png", "Grafico\\" + name + ".dot");
			pb.redirectErrorStream(true);
			pb.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void Create_File(String route, String contents) {

		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(route);
			pw = new PrintWriter(fw);
			pw.write(contents);
			pw.close();
			fw.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (pw != null)
				pw.close();
		}

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
