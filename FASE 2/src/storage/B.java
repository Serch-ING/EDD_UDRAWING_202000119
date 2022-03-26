
package storage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

import objects.Clients;



public class B {
    public int orden_arbol;
    public Pagina raiz;
    public String dot="";
    public String Grap="";
    public String Connectios="";
    Queue<String> names = new LinkedList<String>();
    
    public B(int orden) {
        this.raiz = new Pagina();
        this.orden_arbol = orden;
    }
    
    public void insertar(Long id,Clients cliente) {
        NodoB nodo = new NodoB(id,cliente);
        NodoB obj = insertar_en_pagina(nodo, raiz);
        if (obj != null) {
           
            raiz = new Pagina();
            raiz.insertar(obj);
            raiz.hoja = false;
        }
    }

    private NodoB insertar_en_pagina(NodoB nodo, Pagina rama) {
        if (rama.hoja) {
            rama.insertar(nodo);
            return  (rama.contador == orden_arbol)? dividir(rama): null;
        } else {
            NodoB temp = rama.primero;
            do {
                if (nodo.id == temp.id) {
                    return null;
                } else if (nodo.id < temp.id) {
                    NodoB obj = insertar_en_pagina(nodo, temp.izquierda);
                    return validar_division(obj, rama);
                } else if (temp.siguiente == null) {
                    NodoB obj = insertar_en_pagina(nodo, temp.derecha);
                    return validar_division(obj, rama);
                }
                temp = (NodoB) temp.siguiente;
            } while (temp != null);
        }
        return null;
    }

    private NodoB validar_division(NodoB obj, Pagina rama){
        if (obj instanceof NodoB) {
            rama.insertar((NodoB) obj);
            if (rama.contador == orden_arbol) {
                return dividir(rama);
            }
        }
        return null;
    }

    
    public void generate_grapgh(String name, String dot) {
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
    
	public void draw_start(NodoB primero,String name) {
			names = new LinkedList<String>();
			dot="";
			Connectios = "";
			
			Grap +="digraph structs {\n";
			Grap +="  bgcolor = \"#E3FFFA\"\n";
			Grap +=" node [shape=Mrecord fillcolor=\"#FFE3FF\" style =filled];\n";
			
			Queue<NodoB> cola_nodos = new LinkedList<NodoB>();
			cola_nodos.offer(primero);
	
			while (cola_nodos.peek() != null) {
				drawing(cola_nodos.poll(), cola_nodos);
			}
			
			String[] files = dot.split("\n");
			
			for (String i : files) {
				
				Grap +="struct"+names.poll() +"[label=\"{{";
				
				String[] columns = i.split(",");
				int temp = columns.length;
				
				for (String j : columns) {
				
					Grap += (temp-1 == 0)? j + "}": j + "|";
					temp--;
				}
				
				Grap +="|<here>}\"];\n";	
			}

			Grap+="\n" + Connectios;
			Grap+="}";
			//System.out.println(Grap);
			generate_grapgh(name,Grap);
		}
	
	
	public void drawing(NodoB primero, Queue<NodoB> cola_nodos) {
			NodoB aux = primero;
			String data =String.valueOf(primero.hashCode());
			names.offer(data);
			
			while (aux != null) {
	
				dot += (aux.siguiente == null) ? aux.id + "\n" : aux.id + ", ";
				
				if (aux.izquierda != null) {
					Connectios += "struct" + data + "->struct"+ aux.izquierda.primero.hashCode() + ";\n";
					cola_nodos.offer(aux.izquierda.primero);
				}
	
				if (aux.derecha != null && aux.siguiente == null ) {	
					Connectios += "struct" + data + "->struct"+ aux.derecha.primero.hashCode()+";\n";
					cola_nodos.offer(aux.derecha.primero);
				}

				aux = aux.siguiente;
			}
		}
    
    private NodoB dividir(Pagina rama) {
    	Long val = (long) -999;
        NodoB temp, Nuevito;
        NodoB aux = rama.primero;
        Pagina rderecha = new Pagina();
        Pagina rizquierda = new Pagina();

        int cont = 0;
        while (aux != null) {
            cont++;
            
            if (cont < 3) {
                temp = new NodoB(aux.id, aux.izquierda,aux.siguiente.izquierda);
                
                rizquierda.hoja= !(temp.derecha != null && temp.izquierda != null);
                rizquierda.insertar(temp);
            } else if (cont == 3) {
                val = aux.id;
            } else {
                temp = new NodoB(aux.id, aux.izquierda,aux.derecha);
                
                rderecha.hoja= !(temp.derecha != null && temp.izquierda != null);
                rderecha.insertar(temp);
            }
            aux = aux.siguiente;
        }
        Nuevito = new NodoB(val,rizquierda,rderecha);
        return Nuevito;
    }
}
