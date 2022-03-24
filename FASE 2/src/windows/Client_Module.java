package windows;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import objects.Clients;
import objects.Nodes_Colors;

import javax.swing.JTabbedPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import storage.*;

public class Client_Module extends JFrame {

	private JPanel contentPane;

	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Storage storage = new Storage();
					Client_Module frame = new Client_Module(storage);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/


	public Client_Module(Storage storage,Clients cliente) {
		//JOptionPane.showMessageDialog(null, "Bienvenido: " + cliente.Name + " DPI: " + cliente.DPI );
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.JSON", "JSON");
		fc.setFileFilter(filter);
		fc.setCurrentDirectory(new File("./Test"));
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1108, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 41, 1072, 518);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Cargas masivas", null, panel, null);
		panel.setLayout(null);

		JButton Button_Load = new JButton("Cargar archivo");
		
		Button_Load.setBounds(10, 69, 122, 23);
		panel.add(Button_Load);

		JLabel lblNewLabel = new JLabel("Ruta:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 117, 46, 14);
		panel.add(lblNewLabel);

		JLabel label_ruta = new JLabel("Null");
		label_ruta.setForeground(Color.BLACK);
		label_ruta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_ruta.setBounds(48, 117, 569, 14);
		panel.add(label_ruta);

		JButton Button_loadData = new JButton("Cargar datos");

		Button_loadData.setBounds(10, 160, 122, 23);
		panel.add(Button_loadData);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Capas", "Imagenes", "Albums" }));
		comboBox.setBounds(10, 24, 122, 22);
		panel.add(comboBox);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("Cerrar sesion");

		btnNewButton.setBounds(964, 11, 118, 23);
		contentPane.add(btnNewButton);

		// buttons
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login(storage);
				login.setVisible(true);
				dispose();
			}
		});
		
		Button_Load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fc.showOpenDialog(Button_Load) == JFileChooser.APPROVE_OPTION) {
					try {
						label_ruta.setText(fc.getSelectedFile().toString());

					} catch (Exception e2) {

					}
				} else {
					label_ruta.setText("Null");
				}
			}
		});
		
		Button_loadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (label_ruta.getText() != "Null") {
					if (comboBox.getSelectedItem() == "Capas") {
						ReadLayers(label_ruta.getText(),storage,cliente);
						
					} else if (comboBox.getSelectedItem() == "Imagenes") {
						ReadImages(label_ruta.getText(),storage,cliente);
						
					} else if (comboBox.getSelectedItem() == "Albums") {
						ReadAlbums(label_ruta.getText(),storage,cliente);
						
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe cargar un archivo");
				}

			}
		});
	}
	
	
	public void ReadLayers(String ruta,Storage storage,Clients cliente) {
		Integer id;
		LinkedList<Nodes_Colors> Nodos ;
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(ruta)) {
			
			Object obj = jsonParser.parse(reader);
			JSONArray jsonList = (JSONArray) obj;
			
			for (Object object : jsonList) {
			
				JSONObject data = (JSONObject) object;
				//System.out.println(data);
				
				
				Object id_obj = (Object) data.get("id_capa");
				
				id = ((Long) id_obj).intValue();
				//System.out.println( id);
				
				JSONArray pixelesList = (JSONArray) data.get("pixeles");
				
				Nodos  = new LinkedList<Nodes_Colors>();
				
				//MatrizDispersa temp_Matriz = new MatrizDispersa();
				for (Object object2 : pixelesList) {
					
					
					JSONObject data2 = (JSONObject) object2;
					//System.out.println(data2);
					
					Object colum_obj = (Object) data2.get("columna");
					
					Object file_obj = (Object) data2.get("fila");
					
					String color = (String) data2.get("color");
					//System.out.println( color);
					
					Integer colum = ((Long) colum_obj).intValue();
					//System.out.println( colum);
					
					Integer file = ((Long) file_obj).intValue();
					//System.out.println( file);
					
					Nodes_Colors nodo_temp = new Nodes_Colors(file,colum,color);
					Nodos.add(nodo_temp);
					
					//temp_Matriz.insertarNodo(colum, file,  color);			
				}
				//temp_Matriz.Grapgh("MD_" + id);
				
				cliente.ABBCapas.insertar(id, Nodos);

			}
			
			//cliente.ABBCapas.inorden();
	
			System.out.println("El archivo se ingreso correctamente");
		
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Ocurrio un error en la lectura del JSON");

		}
	}
	
	public void ReadImages(String ruta,Storage storage,Clients cliente) {
		Integer id ;
		LinkedList<Integer> capas_list ;
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader(ruta)) {
			
			Object obj = jsonParser.parse(reader);
			JSONArray jsonList = (JSONArray) obj;
			
			for (Object object : jsonList) {
			
				JSONObject data = (JSONObject) object;
				//System.out.println(data);
				
				
				Object id_obj = (Object) data.get("id");
				
				id =((Long) id_obj).intValue();
				//System.out.println( id);
				
				JSONArray  capas = (JSONArray ) data.get("capas");
				//System.out.println(capas);
				
				capas_list  = new LinkedList<Integer>();
				
				for (Object object2 : capas) {
					Integer capa_no =((Long) object2).intValue();
					//System.out.println(capa_no);
					capas_list.add(capa_no);
				}
			
				
				cliente.AVLImages.root = cliente.AVLImages.insert(cliente.AVLImages.root, id, capas_list);
			}
			
			//cliente.AVLImages.print(cliente.AVLImages.root);
		
			System.out.println("El archivo se ingreso correctamente");
		
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Ocurrio un error en la lectura del JSON");

		}
	}
	
	public void ReadAlbums(String ruta,Storage storage,Clients cliente) {

		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(ruta)) {
			
			Object obj = jsonParser.parse(reader);
			JSONArray jsonList = (JSONArray) obj;
			
			for (Object object : jsonList) {
			
				JSONObject data = (JSONObject) object;
				//System.out.println(data);
				
				
				String name = (String) data.get("nombre_album");
				System.out.println( name);
				
				JSONArray images = (JSONArray) data.get("imgs");
				//System.out.println(images);
				
				if(cliente.Album_list.SearchValidacion(name) == false) {
					cliente.Album_list.insert(name);
					System.out.println("Se agrego");
				}
							
				cliente.Album_list.insernews(cliente.Album_list.SearchValidacionNode(name),images);
				
				/*for (Object object2 : images) {
					Integer img_no =((Long) object2).intValue();
					System.out.println(img_no);
					
				}*/
			
			}
	
			System.out.println("El archivo se ingreso correctamente");
		
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Ocurrio un error en la lectura del JSON");

		}
	}
	
	
}
