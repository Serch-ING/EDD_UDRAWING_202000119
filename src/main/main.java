package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class main {

	public static void main(String[] args) {
		welcome();

	}
	
	public static void welcome() {
		System.out.println("====Bienvenido a UDRAWING====\nAntes de iniciar la simulació se debe agregar los paramtros iniciales:"
				+ "\n•Carga masiva de clientes\r\n"
				+ "•Cantidad de ventanillas");
		
		System.out.println("\n---Ingresa ruta---");
		guardar();
	}
	
	private static void guardar() {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("test.json")) {
			Object obj = jsonParser.parse(reader);
			//JSONArray personasList = (JSONArray) obj;
			System.out.println("EL archivo contiene el siguiente J5ON: ");
			System.out.println(obj);
			
		
			/*
			JSONObject pop = (JSONObject) obj;
				
			
			
			JSONObject persona = (JSONObject) pop.get("Cliente1");
			
		    System.out.println("PERSONA DENTRO DEL JSON:");
		    
		    String id = (String) persona.get("id_cliente");
		    System.out.println( "id_cliente:" + id);
		    
		    String name = (String) persona.get("nombre_cliente");
		    System.out.println( "nombre_cliente:" +name);
		    
		    String color = (String) persona.get("img_color");
		    System.out.println( "img_color:"+color);
		    
		    String negro = (String) persona.get("img_bw");
		    System.out.println("img_bw:"+negro);
		    */
			Gson gson = new Gson();
			Object objectObs = gson.fromJson(new FileReader("test.json"), Object.class);
			System.out.println(objectObs);

			
		    JsonObject  object = new JsonObject();
		    ArrayList<Integer> codes = new ArrayList<Integer>();
		    
		    
		    for (Map.Entry<String,JsonElement> entry : object.entrySet()) {
		    	JsonArray array = entry.getValue().getAsJsonObject().get("unterfeld").getAsJsonArray();
		        for (JsonElement codeHolder : array) {
		            codes.add(codeHolder.getAsJsonObject().getAsJsonPrimitive("code").getAsInt());
		        }
		    }
		    
		    

		    /*for (Map.Entry<String,JsonElement> entry : object.entrySet()) {
		        JsonArray array = entry.getValue().getAsJsonObject().getAsJsonArray("unterfeld");
		        for (JsonElement codeHolder : array) {
		            codes.add(codeHolder.getAsJsonObject().getAsJsonPrimitive("code").getAsInt());
		        }*/
			
			//for (Object persona : personasList) {
				//mostrarInformacionPersona((JSONObject) persona);
			//}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private static void mostrarInformacionPersona(JSONObject jsonobject) {
		   	JSONObject persona = (JSONObject) jsonobject.get("Cliente2");
		    System.out.println("PERSONA DENTRO DEL JSON:");
		    
		    String id = (String) persona.get("id_cliente");
		    System.out.println( "id_cliente:" + id);
		    
		    String name = (String) persona.get("nombre_cliente");
		    System.out.println( "nombre_cliente:" +name);
		    
		    String color = (String) persona.get("img_color");
		    System.out.println( "img_color:"+color);
		    
		    String negro = (String) persona.get("img_bw");
		    System.out.println("img_bw:"+negro);
		    		
		    
		   }
	
	public void menu() {
		
		/*
		Parámetros iniciales
		a. Carga masiva de clientes
		b. Cantidad de ventanillas
		2. Ejecutar paso
		3. Estado en memoria de las estructuras
		4. Reportes
		5. acerca de ←datos del estudiante
		6. Salir*/

		
	}

}
