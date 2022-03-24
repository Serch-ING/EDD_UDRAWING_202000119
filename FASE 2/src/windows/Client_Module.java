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
import javax.swing.JTextField;

public class Client_Module extends JFrame {

	private JPanel contentPane;
	private JTextField textField_setlayers;
	private JTextField textField_IDtreeImg;
	private JTextField textField_layers;

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
		setBounds(100, 100, 1168, 629);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 41, 1132, 538);
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
		tabbedPane.addTab("Generar y ver imagenes", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(10, 11, 150, 165);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JComboBox<String> comboBox_route = new JComboBox<String>();
		comboBox_route.setBounds(10, 28, 122, 22);
		panel_2.add(comboBox_route);
		comboBox_route.setModel(new DefaultComboBoxModel<String>(new String[] { "PreOrden", "InOrden", "PostOrden" }));
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad de capas");
		lblNewLabel_1.setBounds(10, 61, 122, 14);
		panel_2.add(lblNewLabel_1);
		
		textField_setlayers = new JTextField();
		textField_setlayers.setBounds(10, 84, 122, 20);
		panel_2.add(textField_setlayers);
		textField_setlayers.setColumns(10);
		
		JButton btnNewButton_generateRoutes = new JButton("Generar imagen");

		btnNewButton_generateRoutes.setBounds(10, 114, 122, 23);
		panel_2.add(btnNewButton_generateRoutes);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Por recorrido limitado");
		lblNewLabel_1_1_1.setBounds(10, 11, 122, 14);
		panel_2.add(lblNewLabel_1_1_1);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(Color.LIGHT_GRAY);
		panel_2_1.setBounds(10, 187, 150, 135);
		panel_1.add(panel_2_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Por arbol de imagenes");
		lblNewLabel_1_1.setBounds(10, 11, 122, 14);
		panel_2_1.add(lblNewLabel_1_1);
		
		textField_IDtreeImg = new JTextField();
		textField_IDtreeImg.setColumns(10);
		textField_IDtreeImg.setBounds(10, 67, 122, 20);
		panel_2_1.add(textField_IDtreeImg);
		
		JButton btnNewButton_generateTreeImg = new JButton("Generar imagen");
		btnNewButton_generateTreeImg.setBounds(10, 98, 122, 23);
		panel_2_1.add(btnNewButton_generateTreeImg);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("id de imagen");
		lblNewLabel_1_1_2.setBounds(10, 38, 122, 14);
		panel_2_1.add(lblNewLabel_1_1_2);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBackground(Color.LIGHT_GRAY);
		panel_2_1_1.setBounds(10, 333, 150, 166);
		panel_1.add(panel_2_1_1);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Por capas");
		lblNewLabel_1_1_3.setBounds(10, 11, 122, 14);
		panel_2_1_1.add(lblNewLabel_1_1_3);
		
		textField_layers = new JTextField();
		textField_layers.setColumns(10);
		textField_layers.setBounds(10, 83, 122, 20);
		panel_2_1_1.add(textField_layers);
		
		JButton btnNewButton_generateLayers = new JButton("Generar imagen");
		btnNewButton_generateLayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_generateLayers.setBounds(10, 132, 122, 23);
		panel_2_1_1.add(btnNewButton_generateLayers);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("id de capas separadas ");
		lblNewLabel_1_1_2_1.setBounds(10, 36, 180, 14);
		panel_2_1_1.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("por  comas");
		lblNewLabel_1_1_2_1_1.setBounds(10, 58, 180, 14);
		panel_2_1_1.add(lblNewLabel_1_1_2_1_1);
		
		JComboBox<String> comboBox_IMGS = new JComboBox<String>();
		comboBox_IMGS.setBounds(192, 36, 122, 22);
		panel_1.add(comboBox_IMGS);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Imagenes para visualizar");
		lblNewLabel_1_1_1_1.setBounds(192, 11, 139, 14);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		JButton button_show = new JButton("Mostrar");
		button_show.setBounds(192, 69, 122, 23);
		panel_1.add(button_show);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		panel_3.setBounds(354, 11, 763, 488);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(10, 11, 743, 466);
		panel_3.add(lblNewLabel_2);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Registro y eliminacion de imagenes", null, panel_4, null);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Visualizar estructuras", null, panel_5, null);
		panel_5.setLayout(null);

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

		
		btnNewButton_generateRoutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatrizDispersa temp_Matriz = new MatrizDispersa();
				try {
					
				
					if (comboBox_route.getSelectedItem() == "PreOrden") {
						System.out.println("PreOrden");
						int temp =Integer.valueOf(textField_setlayers.getText());
						cliente.ABBCapas.recorridoLimitado(temp);
						
						
						cliente.ABBCapas.preordenLimited(cliente.ABBCapas.raiz);
				
						
						
					} else if (comboBox_route.getSelectedItem() == "InOrden") {
						System.out.println("InOrden");
						int temp =Integer.valueOf(textField_setlayers.getText());
						cliente.ABBCapas.recorridoLimitado(temp);
						
						//cliente.ABBCapas.inorden();
						cliente.ABBCapas.inordenLimited(cliente.ABBCapas.raiz,temp_Matriz);
						
						
					} else if (comboBox_route.getSelectedItem() == "PostOrden") {
						System.out.println("PostOrden");
						int temp =Integer.valueOf(textField_setlayers.getText());
						cliente.ABBCapas.recorridoLimitado(temp);
						
						
						cliente.ABBCapas.postordenLimited(cliente.ABBCapas.raiz);
						
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se debe ingresar un dato");
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
				//temp_Matriz.GrapghInvisible("MD0_" + id);
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
