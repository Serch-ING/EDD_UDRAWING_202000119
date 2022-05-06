package objects;

import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import storage.Merkle_tree;

public class Bloque {
	public int Index, Nonce;
	public String TimesTamp;
	public LinkedList<transaccion> Data;
	public String PreviusHash;
	public String RootMerkle;
	public String Hash;

	public Bloque(int Index, String TimesTamp,int Nonce ,LinkedList<transaccion> Data, String PreviusHash, String RootMerkle,String Hash) {
		this.Index = Index;
		this.Data = Data;
		this.PreviusHash = PreviusHash;
		this.RootMerkle = RootMerkle;
		this.TimesTamp = TimesTamp;
		this.Hash=Hash;
		this.Nonce =Nonce;
	}

	@SuppressWarnings("unchecked")
	public void GenerarJson() {
		try {
			// JSONObject padre = new JSONObject();

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
			
			FileWriter file = new FileWriter("./blockchain/Bloques/" + Index + "_" + TimesTamp+".json");
			file.write(formattedJson);
			file.flush();
			file.close();

			
			
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

	}

	public void Generar_Hash(Merkle_tree arbole_merkle, int no_ceros) {
		Nonce = 0;
	
		while (true) {
			Boolean SalirWhile = true;
			String text = Index + TimesTamp + PreviusHash + RootMerkle + Nonce;
			String hash = arbole_merkle.GenerarHash(text);

			char[] caracteres = hash.toCharArray();

			for (int i = 0; i < no_ceros; i++) {
				if (caracteres[i] == '0') {
					continue;
				} else {
					SalirWhile = false;
					Nonce++;
					break;
				}
			}
			if(caracteres[no_ceros] != '0' && SalirWhile) {
				Hash = hash;
				break;
			}else {
				Nonce++;
				continue;
			}

		}
	}

}
