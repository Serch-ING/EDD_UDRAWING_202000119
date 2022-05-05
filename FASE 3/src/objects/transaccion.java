package objects;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class transaccion {
	String Sede, Destino, datetime, cliente, mensajero;
	public boolean nulo;
	public String aux;

	public transaccion(String Sede, String Destino, String datetime, String cliente, String mensajero) {
		this.Sede = Sede;
		this.Destino = Destino;
		this.datetime = datetime;
		this.cliente = cliente;
		this.mensajero = mensajero;
	}
	
	public String Data() {
		String data;
		data = this.Sede + " , " + this.Destino + " , " + this.datetime + " , " + this.cliente + " , " + this.mensajero;
		return data;
	}

	@SuppressWarnings("unchecked")
	public void GenerarJson() {
		try {
			JSONObject padre = new JSONObject();

			JSONObject myObject = new JSONObject();

			// Cadenas de texto básicas
			myObject.put("Sede", Sede);
			myObject.put("Destino", Destino);
			myObject.put("datetime", datetime);
			myObject.put("cliente", cliente);
			myObject.put("mensajero", mensajero);			
			
			
			padre.put("DATA", myObject);

		
			// Generar cadena de texto JSON
			System.out.print(padre);

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			String originalJson = padre.toString();
			JsonNode tree = objectMapper.readTree(originalJson);
			String formattedJson = objectMapper.writeValueAsString(tree);

			System.out.println("Nuevo");
			System.out.println(formattedJson);
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

	}
	
	@SuppressWarnings("unchecked")
	public JSONObject RegresarJSON() {
		try {
			

			JSONObject myObject = new JSONObject();

			// Cadenas de texto básicas
			myObject.put("Sede", Sede);
			myObject.put("Destino", Destino);
			myObject.put("datetime", datetime);
			myObject.put("cliente", cliente);
			myObject.put("mensajero", mensajero);			
			
			
			
			return myObject;
		
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		

	}
}
