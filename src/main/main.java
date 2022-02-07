package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lists.Cola_Reception;
import objects.client;


public class main {

	public static void main(String[] args) {
		
		System.out.println("----Lista Simple----");
		Cola_Reception Cola_Recepcion = new Cola_Reception();
		Cola_Recepcion.insert(1,"Sergie",2,3);
		Cola_Recepcion.insert(4,"Daniel",5,6);
	
		System.out.println("====Mostrar====");
		Cola_Recepcion.showList();
		System.out.println("====Salio de cola====");
		Cola_Recepcion.Out();
		Cola_Recepcion.showList();
		System.out.println("====Salio de cola====");
		Cola_Recepcion.Out();
		Cola_Recepcion.showList();
		
		//welcome();

	}
	
	public static void welcome() {
		System.out.println("====Bienvenido a UDRAWING====\nAntes de iniciar la simulació se debe agregar los paramtros iniciales:"
				+ "\n•Carga masiva de clientes\r\n"
				+ "•Cantidad de ventanillas");
		
		System.out.println("\n---Ingresa ruta---");
		ReadJson();
	}

	private static void ReadJson() {
		JSONParser jsonParser = new JSONParser();
		ObjectMapper objectMapper = new ObjectMapper();

		try (FileReader reader = new FileReader("test.json")) {

			Object obj = jsonParser.parse(reader);
			JSONObject Jobj = (JSONObject) obj;

			System.out.println("EL archivo contiene el siguiente J5ON: ");

			System.out.println("PERSONA DENTRO DEL JSON:\n");
			Path fileName = Path.of("test.json");
			String actual = Files.readString(fileName);

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

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
