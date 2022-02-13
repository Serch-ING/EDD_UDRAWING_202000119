package functionalities;

import java.util.Scanner;

import lists.Circular_Doble_espera;
import lists.Cola_Print;

public class general {
	public Cola_Print printer_bw = new Cola_Print(1);
	public Cola_Print printer_color = new Cola_Print(2);
	public Circular_Doble_espera Waiting_clients = new Circular_Doble_espera();
	int Pasos=1;

	Scanner sc = new Scanner(System.in);
	ReadMasiveData Data = new ReadMasiveData();

	public void inicializar() {
		Data.welcome();
		// Data.Cola_Recepcion.showList();
		System.out.println("\n==================================");
		System.out.println("====Sistema listo para simular====\n\n");
		Menu();
		// Realizar_Paso();
	}

	public void Realizar_Paso() {
		System.out.println("\n================= Paso: " + Pasos + " =================");
		Waiting_clients.showList();
		Waiting_clients.exit_of_system();
		printing();
		window_magnament();
		Pasos +=1;
	}

	public void Random_a_cola() {
		Data.Cola_Recepcion.Generate_Random();
	}

	public void window_magnament() {
		Data.Simpe_Ventanas.recolect_img(printer_bw, printer_color,Waiting_clients);

		if (Data.Simpe_Ventanas.Search_disposition() && Data.Cola_Recepcion.Dequeue_posibility()) {
			Data.Simpe_Ventanas.insert_client(Data.Cola_Recepcion.Dequeue());
		}
		//System.out.println("no llega");

	}

	public void printing() {
		printer_bw.printing(Waiting_clients);
		printer_color.printing(Waiting_clients);
	}

	public void impresora_a_persona() {

	}

	public void Menu() {
		int option = 0;

		do {

			try {

				System.out.println("\n------------Menu------------\n");
				System.out.println("1.Ejecutar paso\n2.Estado en memoria de las estructuras\n3.Reportes"
						+ "\n4.Acerca de ←datos del estudiante\n5.Salir\n");
				option = Integer.parseInt(sc.nextLine());

				switch (option) {
				case 1:
					System.out.println("Ejecutar paso\n");
					Realizar_Paso();
					break;

				case 2:
					System.out.println("Estado de la estructuras ");

					break;

				case 3:
					System.out.println("Reportes");
					break;

				case 4:
					System.out.println("Acerca des");
					break;
				case 5:
					System.out.println("Salir");
					System.exit(1);
					break;

				default:
					System.out.println("Option fuera de los parametros\n");
					break;
				}

			} catch (Exception e) {
				option = 0;
				System.out.println("No se ingrso un numero\n");

			}

		} while (option != 5);
	}
}
