package objects;

import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Bloque {
	int Index,Nonce;
	String TimesTamp;
	LinkedList<transaccion> Data;
	String PreviusHash;
	String RootMerkle;
	String Hash;
	
	public Bloque(int Index,String TimesTamp, int Nonce,LinkedList<transaccion> Data,String PreviusHash,String RootMerkle,String Hash ) {
		this.Index=Index;
		this.Nonce=Nonce;
		this.Data=Data;
		this.PreviusHash=PreviusHash;
		this.RootMerkle=RootMerkle;
		this.Hash=Hash;	
		this.TimesTamp=TimesTamp;
	}
	
	@SuppressWarnings("unchecked")
	public void GenerarJson() {
		try {
			//JSONObject padre = new JSONObject();

			JSONObject myObject = new JSONObject();

			// Cadenas de texto básicas
			myObject.put("INDEX", Index);
			myObject.put("TIMESTAMP", TimesTamp);
			myObject.put("NONCE", Nonce);
			
			JSONArray Array = new JSONArray();
			
			for (transaccion transaccion : Data) {
				Array.add(transaccion.RegresarJSON());
			}
			
			myObject.put("DATA", Array);
			myObject.put("PREVIUSHASH", PreviusHash);	
			myObject.put("ROOTMERKLE", RootMerkle);	
			myObject.put("HASH", Hash);	
				
			// Generar cadena de texto JSON

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			String originalJson = myObject.toString();
			JsonNode tree = objectMapper.readTree(originalJson);
			String formattedJson = objectMapper.writeValueAsString(tree);

			System.out.println("Nuevo");
			System.out.println(formattedJson);
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

	}
	
}
