package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lists.Cola_Reception;



public class main {
	 
	
	public static void main(String[] args) {
		
		System.out.println("----Lista Simple----");
		
		fuctions fuc  = new fuctions();
		
		fuc.welcome();
	
	
		
		
		

	}
}

class fuctions {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public void welcome() {
		try {
			System.out.println(
					"====Bienvenido a UDRAWING====\nAntes de iniciar la simulació se debe agregar los paramtros iniciales:"
							+ "\n•Carga masiva de clientes\r\n" + "•Cantidad de ventanillas");

			System.out.println("\n---Ingresa ruta---");

			String ruta = br.readLine();
			ReadJson(ruta);
		} catch (IOException e) {
			System.out.println("Ocurrio un error en el ingreso de ruta");
		}

	}

	private  void ReadJson(String ruta) {
		JSONParser jsonParser = new JSONParser();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try (FileReader reader = new FileReader(ruta)) {
			
			Object obj = jsonParser.parse(reader);
			JSONObject Jobj = (JSONObject) obj;

			System.out.println("EL archivo contiene el siguiente J5ON: ");

			System.out.println("PERSONA DENTRO DEL JSON:\n");
			Path fileName = Path.of("test.json");
			String actual = Files.readString(fileName);
			
			Cola_Reception Cola_Recepcion = new Cola_Reception();
			@SuppressWarnings("unchecked")
			Map<String, String> map = objectMapper.readValue(actual, Map.class);
			for (Map.Entry<String, String> entry : map.entrySet()) {

				System.out.println("Key : " + entry.getKey());

				JSONObject persona = (JSONObject) Jobj.get(entry.getKey());

				String id = (String) persona.get("id_cliente");
				System.out.println("id_cliente:" + id);

				String name = (String) persona.get("nombre_cliente");
				System.out.println("nombre_cliente:" + name);

				String color = (String) persona.get("img_color");
				System.out.println("img_color:" + color);

				String negro = (String) persona.get("img_bw");
				System.out.println("img_bw:" + negro + "\n");
				Cola_Recepcion.insert(Integer.valueOf(id),name,Integer.valueOf(color),Integer.valueOf(negro));
			}
			
			Cola_Recepcion.showList();
			
		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el documento buscado");
			welcome();
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (ParseException e) {
			//e.printStackTrace();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public void menu() {

		/*
		 * Parámetros iniciales a. Carga masiva de clientes b. Cantidad de ventanillas
		 * 2. Ejecutar paso 3. Estado en memoria de las estructuras 4. Reportes 5.
		 * acerca de ←datos del estudiante 6. Salir
		 */

		
	}

}
