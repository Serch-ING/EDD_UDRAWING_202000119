package storage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.ls.LSOutput;

import com.google.gson.JsonArray;

import objects.Bloque;
import objects.Clients;
import objects.Lugares;
import objects.Mensajero;
import objects.transaccion;
import storage.Merkle_tree.nodo_merkle;

public class Storage implements Runnable {
	public Simple_Bloques blockchain = new Simple_Bloques();
	
	int contador_bloques = 0;
	public int cantidad_ceros = 4;

	public int tiempo_bloque = 180;
	public int timepo_app = 0;

	public LinkedList<transaccion> Lista_transacciones = new LinkedList<transaccion>();
	

	private Merkle_tree arbole_merkle = new Merkle_tree();
	public ArbolB ClientesB = new ArbolB();
	public ListaDG Lista_adyacente = new ListaDG();
	public LinkedList<Lugares> LugaresFacil = new LinkedList<Lugares>();

	public Clients clientJoin;
	public TablaHash TablaHash_Mesajeros = new TablaHash();

	public void initilize() {
		Clients new_client = new Clients("3", "Sergie", "serch", "sergie@gmail.com", "123", "+502xxxxxxxx", "planes",
				9);
		Long id = Long.valueOf("3");
		ClientesB.insertar(id, new_client);
		System.out.println("INICIANDO");
		LlearArchivos();
		

	}

	// ----------------------FASE 3---------------------------------------
	
	public void LlearArchivos() {
		File folder = new File("./blockchain/Bloques/");
		findAllFilesInFolder(folder);
	}
	
	public void findAllFilesInFolder(File folder) {
	
		
		
		JSONParser jsonParser = new JSONParser();
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				
				System.out.println("\n");
				try (FileReader reader = new FileReader(file)) {
					LinkedList<transaccion> Data = new LinkedList<transaccion>();
					
					Object obj = jsonParser.parse(reader);

					JSONObject jsonobject = (JSONObject) obj;
					//System.out.println(jsonobject + "\n");
					
					JSONArray DATA = ((JSONArray) jsonobject.get("DATA"));
					//System.out.println(DATA);
					
					for (Object object : DATA) {
						JSONObject data = (JSONObject) object;
						

						String cliente = ((String) data.get("cliente"));
						//System.out.println(cliente);
						
						String mensajero = ((String) data.get("mensajero"));
						//System.out.println(mensajero);
						
						String datetime = ((String) data.get("datetime"));
						//System.out.println(datetime);
						
						
						String Sede = ((String) data.get("Sede"));
						//System.out.println(Sede);
						
						
						String Destino = ((String) data.get("Destino"));
						//System.out.println(Destino);
						
						transaccion trasaccion_temp = new transaccion(Sede, Destino, datetime, cliente, mensajero);
						Data.add(trasaccion_temp);
						
					}
					
					//Object Index = ( jsonobject.get("TIMESTAMP"));
					
					String TIMESTAMP = ((String) jsonobject.get("TIMESTAMP"));
					//System.out.println(TIMESTAMP);
					
					String ROOTMERKLE = ((String) jsonobject.get("ROOTMERKLE"));
					//System.out.println(ROOTMERKLE);
					

					String PREVIUSHASH = ((String) jsonobject.get("PREVIUSHASH"));
					//System.out.println(PREVIUSHASH);
					
					Object INDEX = (Object) jsonobject.get("INDEX");
					int INDEX_int = ((Long) INDEX).intValue();
					//System.out.println(INDEX_int);
					
					Object NONCE = (Object) jsonobject.get("NONCE");
					int NONCE_int = ((Long) NONCE).intValue();
					System.out.println(NONCE_int);
					
					String HASH = ((String) jsonobject.get("HASH"));
					//System.out.println(HASH);
					
					System.out.println("FINALIAZDOA");
					System.out.println("\n");
					
					
					Bloque bloque_temp = new Bloque(INDEX_int, TIMESTAMP,NONCE_int, Data, PREVIUSHASH, ROOTMERKLE,HASH);
					//bloque_temp.GenerarJson();
					
					
					blockchain.insert(bloque_temp);
					contador_bloques = blockchain.Control_indices();
				} catch (Exception e) {
					System.out.println("Error");
					System.out.println(e);
				}
				
				
			} else {
				findAllFilesInFolder(file);
			}
		}
	}

	// -------------------------------------------------------------------
	public void LLenando_Lista(int id_int, String departamento, String nombre, Boolean validacion) {
		Lista_adyacente.insert(id_int, departamento, nombre, validacion);
	}

	public void Conexion_Lista(int inicio, int fin, int peso) {
		Lista_adyacente.conexion(inicio, fin, peso);
	}

	public void InsertClients(Clients client_new, Long dPI_Long) {

		ClientesB.insertar(dPI_Long, client_new);
		// List_clients.add(client_new);
	}

	public void showClients() {
		ClientesB.raiz.print_start(ClientesB.raiz.primero);
		System.out.println("------------------------------");
		ClientesB.raiz.print_start_Cleintes(ClientesB.raiz.primero);
		System.out.println("------------------------------");
		/*
		 * for (Clients clients : List_clients) { System.out.println("Cleinte DPI: " +
		 * clients.DPI + " Nombre: " + clients.Name + " contrase;a: " +
		 * clients.Password); }
		 */
	}

	public void modifyClient(Long id, String name, String password) {
		ClientesB.buscar(ClientesB.raiz.primero, id, name, password);

	}

	public void RemovingClient(Long id) {
		ClientesB.buscartoRomove(ClientesB.raiz, id);
	}

	public Boolean SerchClient(String ususario, String password) {
		try {

			NodoB nodotemp = ClientesB.buscar_start_string(ClientesB.raiz.primero, ususario);

			if (nodotemp.cliente.usuario.equals(ususario) && nodotemp.cliente.Password.equals(password)) {
				clientJoin = nodotemp.cliente;
				JOptionPane.showMessageDialog(null, "Bienvenido: " + clientJoin.Name);
				return true;
			}

			clientJoin = null;
			return false;

			/*
			 * for (Clients clients : List_clients) { if (name.equals(clients.Name) &&
			 * password.equals(clients.Password)) { clientJoin = clients; return true; } }
			 */

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "error en busqueda de usuario");
			clientJoin = null;
			return false;

		}
	}

	public Boolean ClinteExite(String ususario) {
		try {

			NodoB nodotemp = ClientesB.buscar_start_string(ClientesB.raiz.primero, ususario);

			if (nodotemp == null) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public Clients ClientJoin() {
		return clientJoin;
	}

	@Override
	public void run() {
		try {
			while (true) {
				timepo_app++;

				System.out.println(timepo_app);

				Thread.sleep(250);

				if (timepo_app >= tiempo_bloque) {
					System.out.println("PASARON 3 MINUTOS -- SE GENERO BLOQUE");
					Generar_Arbol_Merkle();
					
					timepo_app = 0;
				}

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Generar_Arbol_Merkle() {
		JOptionPane.showMessageDialog(null, "Se generará arbol Merkle y bloque");
		nodo_merkle Raiz;

		if (Lista_transacciones.size() < 1) {
			Raiz = arbole_merkle.GenerarNodoInicio("-1", "-1");
		} else {
			Raiz = arbole_merkle.principal(Lista_transacciones);
		}

		String timeStamp = new SimpleDateFormat("dd-MM-yyyy__HH_mm_ss").format(Calendar.getInstance().getTime());
		Raiz.DrawGraph(Raiz, "Merkle_" + contador_bloques);

		String PreviusHash = "";
		String hash = "";

		if (!blockchain.isNone()) {
			PreviusHash = blockchain.Ultimo_Hash_bloque();
		}

		Bloque Bloque_generado = new Bloque(contador_bloques, timeStamp,0, Lista_transacciones, PreviusHash, Raiz.hash, "");
		Bloque_generado.Generar_Hash(arbole_merkle,cantidad_ceros);

		Bloque_generado.GenerarJson();

		blockchain.insert(Bloque_generado);
		Lista_transacciones = new LinkedList<transaccion>();
		contador_bloques++;
		JOptionPane.showMessageDialog(null, "Se generaro arbol Merkle y bloque");
	}

}
