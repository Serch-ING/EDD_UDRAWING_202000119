package functionalities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import lists.Cola_Reception;
import lists.Simple_Windows;


public class ReadMasiveData {
	public ThreadLocalRandom tlr = ThreadLocalRandom.current();
	public Cola_Reception Cola_Recepcion;
	public Simple_Windows Simpe_Ventanas;

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//welcom message
	public void welcome() {
		System.out.println(
				"====Bienvenido a UDRAWING====\nAntes de iniciar la simulación se debe agregar los parametros iniciales:"
						+ "\n•Carga masiva de clientes\r\n" + "•Cantidad de ventanillas");
		Load_json();
		load_windows();
		
		
	}
	
	//usar lad doc.json
	public void Load_json() {
		try {
			System.out.println("\n---Ingresa ruta del archivo---");
			String ruta = br.readLine();
			ReadJson(ruta);
		} catch (IOException e) {
			System.out.println("Ocurrio un error en el ingreso de ruta");
		}
	}

	//read the json
	public void ReadJson(String ruta) {

		JSONParser jsonParser = new JSONParser();
		ObjectMapper objectMapper = new ObjectMapper();

		try (FileReader reader = new FileReader(ruta)) {

			Object obj = jsonParser.parse(reader);
			JSONObject Jobj = (JSONObject) obj;

			Path fileName = Path.of(ruta);
			String actual = Files.readString(fileName);

			Cola_Recepcion = new Cola_Reception();

			@SuppressWarnings("unchecked")
			Map<String, String> map = objectMapper.readValue(actual, Map.class);
			for (Map.Entry<String, String> entry : map.entrySet()) {

				// System.out.println("Key : " + entry.getKey());
				JSONObject persona = (JSONObject) Jobj.get(entry.getKey());

				String id = (String) persona.get("id_cliente");
				// System.out.println("id_cliente:" + id);

				String name = (String) persona.get("nombre_cliente");
				// System.out.println("nombre_cliente:" + name);

				String color = (String) persona.get("img_color");
				// System.out.println("img_color:" + color);

				String negro = (String) persona.get("img_bw");
				// System.out.println("img_bw:" + negro + "\n");
				Cola_Recepcion.enqueue(Integer.valueOf(id), name, Integer.valueOf(color), Integer.valueOf(negro));
			}

			System.out.println("El archivo se ingreso correctamente");
			// Cola_Recepcion.Generate_Random();
			// Cola_Recepcion.showList();

		} catch (FileNotFoundException e) {
			System.out.println("\nNo se encontro el documento buscado");
			Load_json();
			/*
			 * e.printStackTrace(); } catch (IOException e) { //e.printStackTrace(); } catch
			 * (ParseException e) { //e.printStackTrace();
			 */
		} catch (Exception e) {
			System.out.println("\nSe ingreso un archivo con la estructura distinta establecida");
			Load_json();
			// e.printStackTrace();
		}
	}

	//load list of windows
	public void load_windows() {
		try {
			System.out.println("\n---Ingresa la cantidad de ventanillas---");
			String noWindows = br.readLine();

			if (isNum(noWindows)) {
				if (Integer.valueOf(noWindows) < 1) {
					System.out.println("El numero de ventanillas minimo es 1");
					load_windows();
				} else {
					Create_windows(Integer.valueOf(noWindows));
				}

			} else
				load_windows();
		} catch (IOException e) {
			System.out.println("Ocurrio un error en el ingreso de numero de ventanillas");
		}
	}

	//create the list of windows
	public void Create_windows(Integer noWindows) {
		Simpe_Ventanas = new Simple_Windows();

		for (int i = noWindows; i > 0; i--) {
			Simpe_Ventanas.insert(i);
		}		
	}

	
	//verify if its num
	public Boolean isNum(String data) {
		try {
			Integer ruta = Integer.valueOf(data);
			return true;
		} catch (Exception e) {
			System.out.println("Ocurrio un error en el tipo de dato");
			return false;
		}
	}

}
