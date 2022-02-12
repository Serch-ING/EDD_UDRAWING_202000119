package functionalities;

import java.util.Scanner;



public class general {
	Scanner sc = new Scanner(System.in);
	ReadMasiveData Data = new ReadMasiveData(); 

	public void inicializar() {
		Data.welcome();
		//Data.Cola_Recepcion.showList();
		System.out.println("\n==================================");
		System.out.println("====Sistema listo para simular====\n\nMenu:");
		Menu();
		//Realizar_Paso();
	}
	
	public void Realizar_Paso() {
		System.out.println("Se realizo paso");
		Cola_a_ventanillas();
	}
	public void Random_a_cola() {
		Data.Cola_Recepcion.Generate_Random();
	}
	
	public void Cola_a_ventanillas() {
		Data.Simpe_Ventanas.recolect_img();
		ventanillas_recolctando();
		
		if (Data.Simpe_Ventanas.Search_disposition() && Data.Cola_Recepcion.Dequeue_posibility()) {
			Data.Simpe_Ventanas.insert_client(Data.Cola_Recepcion.Dequeue());
		}else {
			System.out.println("no llega");
		}
		
		//Data.Cola_Recepcion.showList();
	}
	
	public void ventanillas_recolctando() {
		
	}
	
	public void ventanillas_a_impresora() {
		
	}
	
	public void impresora_a_persona() {
		
	}
	

	public void Menu() {
		int option = 0;

		do {
			
			try {
				
				System.out.println("------Menu------\n");
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

		}while (option != 5);
	}	
}
