package storage;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import objects.Clients;
import storage.Arbol_Binario.Nodo_ABB;

public class Arbol_AVL {
	public Queue<Node> temp_list = new LinkedList<Node>();
	public int no_nodos = 0;
	public Node buscado = null;
	public Node root = null;
	public int MaxId = 0;
	
	public class Node {

		private Node left, right;
		private int height = 1;
		public int value;
		public LinkedList<Integer> capas_list = new LinkedList<Integer>();
		public Arbol_Binario ABBCapas_self = new Arbol_Binario();

		private Node(int val, LinkedList<Integer> capas_list) {
			this.value = val;
			this.capas_list = capas_list;
		}

	}

	private int height(Node N) {
		if (N == null)
			return 0;
		return N.height;
	}
	
	

	public Node insert(Node node, int value, LinkedList<Integer> capas_list) {
		if (value > MaxId) {
			MaxId = value;
		}
		if (node == null) {
			Node Nuwvo = new Node(value, capas_list);
			ABBtree_sub(Nuwvo);
			return (Nuwvo);
		}

		if (value < node.value) {
			node.left = insert(node.left, value, capas_list);
		} else if (value == node.value) {

			JOptionPane.showMessageDialog(null, "Id de capa repetida: " + value);
			// node.capas_list = capas_list;
		} else {
			node.right = insert(node.right, value, capas_list);
		}

		node.height = Math.max(height(node.left), height(node.right)) + 1;

		int balance = getBalance(node);

		if (balance > 1 && value < node.left.value)
			return rightRotate(node);

		if (balance < -1 && value > node.right.value)
			return leftRotate(node);

		if (balance > 1 && value > node.left.value) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		if (balance < -1 && value < node.right.value) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		return node;
	}

	public void ABBtree_sub(Node node) {

		for (Integer i : node.capas_list) {
			node.ABBCapas_self.insertar(i, null);
		}

		// node.ABBCapas_self.Niveles(node.ABBCapas_self.raiz);
	}

	private Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;

		x.right = y;
		y.left = T2;

		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;

		return x;
	}

	private Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;

		y.left = x;
		x.right = T2;

		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;

		return y;
	}

	private int getBalance(Node N) {
		if (N == null)
			return 0;
		return height(N.left) - height(N.right);
	}

	public int cantidad_images() {
		no_nodos = 0;
		contar(root);
		return no_nodos;
	}

	public void contar(Node root) {
		if (root != null) {
			contar(root.left);
			no_nodos++;
			System.out.printf("%d ", root.value);
			contar(root.right);
		}
	}

	public void preOrder(Node root) {
		if (root != null) {
			preOrder(root.left);
			System.out.printf("%d ", root.value);
			preOrder(root.right);
		}
	}
	
	public void voltera(Node root) {
		if (root != null) {
			voltera(root.left);
			voltera(root.right);
			
			Node temp = root.left;
			root.left = root.right;
			root.right = temp;
				
			
		}
	}
	
	
	public Node[]  recolecdata() {
		temp_list = new LinkedList<Node>();

		alldata(this.root);
		int size = temp_list.size();
		Node[] new_list = new Node[size];
		int contador = 0;
		
		while (temp_list.peek() != null) {
			new_list[contador] = temp_list.poll();
			contador++;
		}
		
		 int n = new_list.length;  
		 Node temp_switch;  
		
		 for(int i=0; i < n; i++){  
			 for(int j=1; j < (n-i); j++){  
                
				 if(new_list[j-1].capas_list.size() < new_list[j].capas_list.size()){  
					
					 temp_switch = new_list[j-1];  
					 new_list[j-1] = new_list[j];  
					 new_list[j] = temp_switch;    
				 } 
             }           
         }  
		
		return new_list;
	}
	
	public void alldata(Node n) {
		if (n != null) {
			alldata(n.left);
			alldata(n.right);
			temp_list.offer(n);
			//n.imprimirDato();
		}
	}

	public void seraching(int id, Clients cliente, MatrizDispersa temp_Matriz) {
		this.buscado = null;
		Search(this.root, id);
		List<Integer> List_int = new LinkedList<Integer>();

		if (this.buscado != null) {
			if (this.buscado.ABBCapas_self.raiz != null) {
				List_int = this.buscado.ABBCapas_self.NivelesRetorno(this.buscado.ABBCapas_self.raiz);

				cliente.ABBCapas.temp = "";
				for (Integer tn : List_int) {
					cliente.ABBCapas.busquedaListColors(tn, temp_Matriz);
					cliente.ABBCapas.temp += "  -> " + tn;
				}
			}
		}
	}
	
	public boolean validandoALbum(int id) {
		this.buscado = null;
		Search(this.root, id);
		
		if (this.buscado != null) {
			return true;
		}else{
			return false;
		}
	}

	public void Search(Node root, int id) {
		if (root != null) {
			Search(root.left, id);
			if (id == root.value) {
				this.buscado = root;
			}

			Search(root.right, id);
		}
	}

	private Node minValueNode(Node node) {
		Node current = node;

		while (current.left != null)
			current = current.left;
		return current;
	}

	public Node deleteNode(Node root, int value) {

		if (root == null) {
			
			return root;
		}
		if (value < root.value)
			root.left = deleteNode(root.left, value);

		else if (value > root.value)
			root.right = deleteNode(root.right, value);

		else {

			if ((root.left == null) || (root.right == null)) {

				Node temp;
				if (root.left != null)
					temp = root.left;
				else
					temp = root.right;

				if (temp == null) {
					temp = root;
					root = null;
				} else
					root = temp;

				temp = null;
			} else {

				Node temp = minValueNode(root.right);

				root.value = temp.value;

				root.right = deleteNode(root.right, temp.value);
			}
			JOptionPane.showMessageDialog(null, "Se elimino la imagen: "+value);
		}

		if (root == null)
			return root;

		root.height = Math.max(height(root.left), height(root.right)) + 1;

		int balance = getBalance(root);

		if (balance > 1 && getBalance(root.left) >= 0)
			return rightRotate(root);

		if (balance > 1 && getBalance(root.left) < 0) {
			root.left = leftRotate(root.left);
			JOptionPane.showMessageDialog(null, "ROTACION DOBLE");
			return rightRotate(root);
		}

		if (balance < -1 && getBalance(root.right) <= 0)
			return leftRotate(root);

		if (balance < -1 && getBalance(root.right) > 0) {
			root.right = rightRotate(root.right);
			JOptionPane.showMessageDialog(null, "ROTACION DOBLE");
			return leftRotate(root);
		}

		return root;
	}

	public void print(Node root) {

		if (root == null) {
			System.out.println("(XXXXXX)");
			return;
		}

		int height = root.height, width = (int) Math.pow(2, height - 1);

		List<Node> current = new ArrayList<Node>(1), next = new ArrayList<Node>(2);
		current.add(root);

		final int maxHalfLength = 4;
		int elements = 1;

		StringBuilder sb = new StringBuilder(maxHalfLength * width);
		for (int i = 0; i < maxHalfLength * width; i++)
			sb.append(' ');
		String textBuffer;

		for (int i = 0; i < height; i++) {

			sb.setLength(maxHalfLength * ((int) Math.pow(2, height - 1 - i) - 1));

			textBuffer = sb.toString();

			for (Node n : current) {

				System.out.print(textBuffer);

				if (n == null) {

					System.out.print("        ");
					next.add(null);
					next.add(null);

				} else {

					System.out.printf("(%6d)", n.value);
					next.add(n.left);
					next.add(n.right);

				}

				System.out.print(textBuffer);

			}

			System.out.println();

			if (i < height - 1) {

				for (Node n : current) {

					System.out.print(textBuffer);

					if (n == null)
						System.out.print("        ");
					else
						System.out.printf("%s      %s", n.left == null ? " " : "/", n.right == null ? " " : "\\");

					System.out.print(textBuffer);

				}

				System.out.println();

			}

			elements *= 2;
			current = next;
			next = new ArrayList<Node>(elements);

		}
	}

	String dot = "";

	public void DrawGraph_Binary(Node root, String name, int id) {

		dot = "digraph G {\n";
		dot += "nodesep=0; \n";
		dot += "ranksep=0.4;\n";
		dot += "node[style = filled fillcolor=\"#88E1F7\"];\n";

		GenerarArboGrapgh(root);

		if (search_value(id)) {
			dot += this.buscado.ABBCapas_self.DrawGraph_return(this.buscado.ABBCapas_self.raiz);
			
			dot += "NodoAVL" + this.buscado.value + "->NodoABB" + this.buscado.ABBCapas_self.raiz.dato+ "[label=\"CAPAS,RAIZ\"]\n";
		} else {
			dot += "NodoAVL" + this.buscado.value + "->NULL[label=\"CAPAS,RAIZ\" fillcolor=\"pink\"]\n";
		}

		dot += "}";

		// System.out.println(dot);
		generate_grapgh(name, dot);
	}

	public boolean search_value(int id) {
		this.buscado = null;
		Search(this.root, id);

		if (this.buscado.ABBCapas_self.raiz != null) {
			return true;
		} else {
			return false;
		}
	}

	public void DrawGraph(Node root, String name) {

		dot = "digraph G {\n";
		dot += "nodesep=0; \n";
		dot += "ranksep=0.4;\n";
		dot += "node[style = filled fillcolor=\"#88E1F7\"];\n";

		GenerarArboGrapgh(root);

		dot += "}";

		// System.out.println(dot);
		generate_grapgh(name, dot);
	}

	public void GenerarArboGrapgh(Node actual) {

		dot += ("	NodoAVL" + actual.value + "[ label=\"" + actual.value + "\"  ];\n");
		if (actual.left != null) {

			dot += ("	NodoAVL" + actual.left.value + "[ label=\"" + actual.left.value + "\"];\n");
			dot += "NodoAVL" + actual.value + "->NodoAVL" + actual.left.value + "\n";
			GenerarArboGrapgh(actual.left);
		} else {

			dot += "Invisible" + actual.hashCode() + "[style=invis];\n";
			dot += "NodoAVL" + actual.value + "-> Invisible" + actual.hashCode() + "[arrowsize=0 style= invisible] \n";
		}

		if (actual.right != null) {

			dot += ("	NodoAVL" + actual.right.value + "[ label=\"" + actual.right.value + "\" ];\n");
			dot += "NodoAVL" + actual.value + "->NodoAVL" + actual.right.value + "\n";
			GenerarArboGrapgh(actual.right);
		} else {
			dot += "Invisible" + actual.hashCode() + "[style=invis];\n";
			dot += "NodoAVL" + actual.value + "-> Invisible" + actual.hashCode() + "[arrowsize=0 style= invisible] \n";
		}
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

	public void PrintNiveles(Node root) {

		int height = root.height;

		List<Node> current = new ArrayList<Node>(), next = new ArrayList<Node>();

		current.add(root);

		for (int i = 0; i < height; i++) {

			for (Node n : current) {
				if (n != null) {

					System.out.printf("%d, ", n.value);

					next.add(n.left);
					next.add(n.right);
				}
			}

			current = next;
			next = new ArrayList<Node>();
		}
		System.out.println();
	}

	public void Espejo() {

	}

	public void PrintNivelesSeparados(Node root) {

		if (root == null) {
			System.out.println("(XXXXXX)");
			return;
		}

		int height = root.height;

		List<Node> current = new ArrayList<Node>(1), next = new ArrayList<Node>(2);
		current.add(root);

		int elements = 1;

		for (int i = 0; i < height; i++) {

			for (Node n : current) {
				if ((n != null)) {
					next.add(null);
					next.add(null);

				} else {

					System.out.printf("%d, ", n.value);

					next.add(n.left);
					next.add(n.right);
				}
			}

			System.out.println();

			elements *= 2;
			current = next;
			next = new ArrayList<Node>(elements);
		}
	}
	
    public static <T> void main(String args[]) {
    	LinkedList<Integer> capas_list = new LinkedList<Integer>();
    	capas_list.add(1);
        Arbol_AVL t = new Arbol_AVL();
		
        
        int[ ] edad = {50,8,9,30,11,1,15,5,33,88};  
		
		for (int i : edad) {
			t.root = t.insert(t.root, i, capas_list);
		}
		
		//t.root = t.deleteNode(t.root, 33);
		//t.PrintNiveles(t.root);
		t.print(t.root);
		System.out.println("\n");
		t.voltera(t.root);
		System.out.println("\n");
		t.print(t.root);
		//t.PrintNiveles(t.root);
		System.out.println("/////////////////////");
		
			
	

     }
    }
