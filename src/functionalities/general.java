package functionalities;

public class general {
	ReadMasiveData fuc = new ReadMasiveData();

	public void inicializar() {
		fuc.welcome();
		System.out.println("\n==================================");
		System.out.println("====Sistema listo para simular====\n\nMenu:");
		menu();
		Realizar_Paso();
	}
	
	public void Realizar_Paso() {
		fuc.Cola_Recepcion.showList();
		fuc.Simpe_Ventanas.showList();
		
		
	}
	
	public void Random_a_cola() {
		fuc.Cola_Recepcion.Generate_Random();
	}
	
	public void Cola_a_ventanillas() {
		
	}
	
	public void ventanillas_recolctando() {
		
	}
	
	public void ventanillas_a_impresora() {
		
	}
	
	public void impresora_a_persona() {
		
	}
	
	public void menu() {
		System.out.println("1.Ejecutar paso\n2.Estado en memoria de las estructuras\n3.Reportes"
				+ "\n4.Acerca de ←datos del estudiante\n5.Salir\n");
	}
	
}
