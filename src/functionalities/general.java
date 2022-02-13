package functionalities;

import java.util.Scanner;

import lists.Circular_Doble_espera;
import lists.Cola_Print;
import lists.Simple_Clients_Served;

public class general {
	public Cola_Print printer_bw = new Cola_Print(1);
	public Cola_Print printer_color = new Cola_Print(2);
	public Circular_Doble_espera Waiting_clients = new Circular_Doble_espera();
	public Simple_Clients_Served Clients_Served = new Simple_Clients_Served();
	
	int Pasos=1;

	Scanner sc = new Scanner(System.in);
	ReadMasiveData Data = new ReadMasiveData();

	public void inicializar() {
		Data.welcome();
		// Data.Cola_Recepcion.showList();
		System.out.println("\n==================================");
		System.out.println("====Sistema listo para simular====");
		System.out.println("==================================\n");
		Clients_Served.Draw_Graphiz();
		Waiting_clients.Draw_Graphiz();
		Data.Simpe_Ventanas.Draw_Graphiz();
		Data.Cola_Recepcion.Draw_Graphiz();
		printer_bw.Draw_Graphiz();
		printer_color.Draw_Graphiz();
		Menu();
		
		// Realizar_Paso();
	}

	public void Realizar_Paso() {
		try {
			System.out.println("\n================= Paso: " + Pasos + " =================");
			Random_a_cola();
			Waiting_clients.exit_of_system(Pasos,Clients_Served);
			printing();
			window_magnament();
			Pasos +=1;
			
			
			
			//Genrar grafo
			Clients_Served.Draw_Graphiz();
			Waiting_clients.Draw_Graphiz();
			Data.Cola_Recepcion.Draw_Graphiz();
			Data.Simpe_Ventanas.Draw_Graphiz();
			printer_bw.Draw_Graphiz();
			printer_color.Draw_Graphiz();
		} catch (Exception e) {
			System.out.println("Hubo un problema");
		}
		
	}

	public void Random_a_cola() {
		Data.Cola_Recepcion.Generate_Random();
	}

	public void window_magnament() {
		Data.Simpe_Ventanas.recolect_img(printer_bw, printer_color,Waiting_clients);

		if (Data.Simpe_Ventanas.Search_disposition() && Data.Cola_Recepcion.Dequeue_posibility()) {
			Data.Simpe_Ventanas.insert_client(Data.Cola_Recepcion.Dequeue(),Pasos);
		}
		//System.out.println("no llega");

	}

	public void printing() {
		printer_bw.printing(Waiting_clients);
		printer_color.printing(Waiting_clients);
	}

	public void Menu() {
		int option = 0;

		do {

			try {

				System.out.println("\n-------------------Menu--------------------\n");
				System.out.println("1.Ejecutar paso\n2.Estado en memoria de las estructuras\n3.Reportes"
						+ "\n4.Acerca del estudiante\n5.Salir\n");
				option = Integer.parseInt(sc.nextLine());

				switch (option) {
				case 1:
					//System.out.println("Ejecutar paso\n");
					Realizar_Paso();
					break;

				case 2:
					System.out.println("Estado de la estructuras");
					Clients_Served.openimg();
					Waiting_clients.openimg();
					Data.Simpe_Ventanas.openimg();
					Data.Cola_Recepcion.openimg();
					printer_bw.openimg();
					printer_color.openimg();
					break;

				case 3:
					System.out.println("Reportes");
					break;

				case 4:
					System.out.println("========= Acerca de datos del estudiante =========");
					System.out.println("Universidad de San Carlos de Guatemala");
					System.out.println("Facultad de Ingenieria\nEscuela de Ciencias y Sistemas");
					System.out.println("\nNombre:Sergie Daniel Arizandieta Yol\nCarnet:202000119");
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
