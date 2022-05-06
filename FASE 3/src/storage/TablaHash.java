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
		
		dot = "digraph G { bgcolor=\"black\"\r\n"
				+ "   fontname=\"Helvetica,Arial,sans-serif\"\r\n"
				+ "  edge [fontname=\"Helvetica,Arial,sans-serif\"]\r\n"
				+ "  subgraph cluster1 {fillcolor=\"skyblue\" style=\"filled\"\r\n"
				+ "  node [shape=Msquare fillcolor=\"gold:brown\" style=\"radial\" gradientangle=180]\r\n"
				+ "  label = \" Tabla de transiciones de: CUATRO\"\r\n"
				+ "  a0 [label=<  \r\n"
				+ "  <TABLE border=\"10\" cellspacing=\"10\" cellpadding=\"10\" style=\"rounded\" gradientangle=\"315\">\r\n"
				+ "  \n";
		dot+="<TR>\n";

		dot+= " \t<TD border=\"3\" bgcolor=\"#FFF97B\">" + "indice: " + "Inicio" + "</TD>\n";
		for (int i = 0; i < tabla.length; i++) {

			System.out.println("Posicion " + i + " data: " + tabla[i]);
			
			dot+= "<TD border=\"3\" >"  + i + " data: " +  tabla[i] +  "</TD>";
			
			
		

		}
		dot+="</TR>\n";
		dot+= "\n</TABLE>>];}}";
		
		
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
