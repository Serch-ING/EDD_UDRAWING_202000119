package storage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.LinkedList;

import objects.transaccion;
import storage.Arbol_Binario.Nodo_ABB;

public class Merkle_tree {
	
	public nodo_merkle principal(LinkedList<transaccion> Lista_transiciones) {
		
		
		LinkedList<String> List = new LinkedList<String>();
		for (transaccion string : Lista_transiciones) {
			List.add(string.Data());
		}
		
		nodo_merkle Raiz = Inicial(List);
		System.out.println("----TERMINO-----");
		System.out.println(Raiz.hash);
		System.out.println(Raiz.hijos);
		
		//Raiz.DrawGraph(Raiz, "GRAFO");
	
		return	Raiz;
	}
	
	private nodo_merkle Inicial(LinkedList<String> List) {
		//boolean iterar = true;
		LinkedList<nodo_merkle> padres = new LinkedList<nodo_merkle>() ;
		
		while (List.peek() != null) {
			padres.add(SubArboles(List));
		}
		
		while (padres.size() > 1 ) {
			LinkedList<nodo_merkle> temporal = new LinkedList<nodo_merkle>() ;
			CrearCopia(temporal,padres);
			padres = new LinkedList<nodo_merkle>() ;
			
			while (true) {
				nodo_merkle arbol1;
				if(temporal.peek() != null) {
					 arbol1 = temporal.pop();
				}else {
					break;
				}
				
				nodo_merkle arbol2 = null;
				if(temporal.peek() != null) {
					arbol2 = temporal.pop();
				}

				
				padres.add(GenerarArboles(arbol1,arbol2));
				
			}
		}
		
		return padres.pop();
	}
	
	private nodo_merkle GenerarArboles(nodo_merkle arbol1, nodo_merkle arbol2) {
		if(arbol2 == null) {
			LinkedList<String> temp = new LinkedList<String>() ;
			
			for (int i = 0; i < arbol1.hijos; i++) {
				temp.add("-1");
			}
			
			arbol2 = Inicial(temp);
		}
		
		nodo_merkle padre = new nodo_merkle();
		padre.hijo1_merkle = arbol1;
		padre.hijo2_merkle = arbol2;
		padre.hash = GenerarHash(arbol1.hash + arbol2.hash);
		padre.hijos = arbol1.hijos + arbol2.hijos;
		
		return padre;
		
	}

	
	
	private nodo_merkle SubArboles(LinkedList<String> List) {
		String nodo1 = null;
		String nodo2 = null;
		
		if(List.peek() != null) {
			nodo1 = List.pop();
		}else {
			return null;
		}
		
		if(List.peek() != null) {
			 nodo2 = List.pop();
		}else {
			nodo2 = "-1";
		}
	    
		return GenerarNodoInicio(nodo1,nodo2);
	}
				
	public nodo_merkle GenerarNodoInicio(String nodo1,String nodo2) {
		nodo_merkle nodo_merkle_1 = new nodo_merkle();
		nodo_merkle nodo_merkle_2 = new nodo_merkle();
		
		nodo_merkle_1.hash = GenerarHash(nodo1);
		nodo_merkle_1.padre_original = true;
		nodo_merkle_1.hijo_base = nodo1;
	
		nodo_merkle_2.hash = GenerarHash(nodo2);
		nodo_merkle_2.padre_original = true;
		nodo_merkle_2.hijo_base = nodo2;
		
		nodo_merkle padre = new nodo_merkle();
		padre.hash = GenerarHash(nodo_merkle_1.hash + nodo_merkle_2.hash);
		padre.hijo1_merkle = nodo_merkle_1;
		padre.hijo2_merkle = nodo_merkle_2;
		padre.hijos = 2;
		
		return padre;
		
	}
	
	
	
	private void CrearCopia(LinkedList<nodo_merkle> temporal, LinkedList<nodo_merkle> padres) {
		for (nodo_merkle nodo_merkle : padres) {
			temporal.add(nodo_merkle);
		}
	
	}
	
	public String GenerarHash(String text) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(text.getBytes("utf8"));
			String hash = String.format("%064x", new BigInteger(1, digest.digest()));
			return hash;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public class nodo_merkle {
		
		String dot="";
		boolean padre_original = false;
		
		int hijos;
		nodo_merkle hijo1_merkle,hijo2_merkle;
		String hash;
		
		String hijo_base;
		
		public void DrawGraph(nodo_merkle root,String name) {

			dot = "digraph G {\n";
			dot += "nodesep=3; \n";
			dot += "ranksep=1;\n";
			dot += "node[shape=box style = filled fillcolor=\"#F788DF\"];\n";
			

			GenerarArbol(root);

			dot += "}";

			//System.out.println(dot);
			generate_grapgh(name,dot);
		}
		
		public void GenerarArbol(nodo_merkle actual) {
			dot += ("	NodoABB" + actual.hashCode() + "[ label=\"" + actual.hash + "\"  ];\n");
			
			if(actual.padre_original) {
				dot += ("	NodoABB" + (actual.hashCode() + actual.hash ) + "[ label=\"" + actual.hijo_base + "\"  ];\n");
				dot += "NodoABB" + actual.hashCode() + "->NodoABB" + (actual.hashCode()+ actual.hash) + "\n";
			}else {
				if (actual.hijo1_merkle != null) {
					
					dot += ("	NodoABB" + actual.hijo1_merkle.hashCode() + "[ label=\"" + actual.hijo1_merkle.hash + "\"];\n");
					dot += "NodoABB" + actual.hashCode() + "->NodoABB" + actual.hijo1_merkle.hashCode() + "\n";
					GenerarArbol(actual.hijo1_merkle);
				} 
				
				if (actual.hijo2_merkle != null) {
					
					dot += ("	NodoABB" + actual.hijo2_merkle.hashCode() + "[ label=\"" + actual.hijo2_merkle.hash + "\" ];\n");
					dot += "NodoABB" + actual.hashCode() + "->NodoABB" + actual.hijo2_merkle.hashCode() + "\n";
					GenerarArbol(actual.hijo2_merkle);
				} 
			}
		}
		
		private void generate_grapgh(String name, String dot) {
			try {
				Create_File("Grafico\\" + name + ".dot", dot);
				ProcessBuilder pb;
				pb = new ProcessBuilder("dot", "-Tpng", "-o", "Grafico\\" + name + ".png", "Grafico\\" + name + ".dot");
				pb.redirectErrorStream(true);
				pb.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private void Create_File(String route, String contents) {

			FileWriter fw = null;
			PrintWriter pw = null;
			try {
				fw = new FileWriter(route);
				pw = new PrintWriter(fw);
				pw.write(contents);
				pw.close();
				fw.close();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			} finally {
				if (pw != null)
					pw.close();
			}

		}

		
	}
	

	
	

}
