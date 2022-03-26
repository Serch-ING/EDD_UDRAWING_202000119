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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import storage.*;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client_Module extends JFrame {

	private JPanel contentPane;
	private JTextField textField_setlayers;
	private JTextField textField_IDtreeImg;
	private JTextField textField_layers;
	private JTextField textField_idlayer;

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Storage storage = new Storage();
	 * Client_Module frame = new Client_Module(storage); frame.setVisible(true); }
	 * catch (Exception e) { e.printStackTrace(); } } }); }
	 */

	public Client_Module(Storage storage, Clients cliente) {
		// JOptionPane.showMessageDialog(null, "Bienvenido: " + cliente.Name + " DPI: "
		// + cliente.DPI );
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.JSON", "JSON");
		fc.setFileFilter(filter);
		fc.setCurrentDirectory(new File("./Test"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1700, 929);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 42, 1664, 837);
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
		label_ruta.setBounds(48, 117, 1069, 14);
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
		panel_2.setBounds(10, 11, 261, 165);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JComboBox<String> comboBox_route = new JComboBox<String>();
		comboBox_route.setBounds(10, 28, 148, 22);
		panel_2.add(comboBox_route);
		comboBox_route.setModel(new DefaultComboBoxModel<String>(new String[] { "PreOrden", "InOrden", "PostOrden" }));

		JLabel lblNewLabel_1 = new JLabel("Cantidad de capas");
		lblNewLabel_1.setBounds(10, 61, 201, 14);
		panel_2.add(lblNewLabel_1);

		textField_setlayers = new JTextField();
		textField_setlayers.setBounds(10, 84, 148, 20);
		panel_2.add(textField_setlayers);
		textField_setlayers.setColumns(10);

		JButton btnNewButton_generateRoutes = new JButton("Generar imagen");

		btnNewButton_generateRoutes.setBounds(10, 114, 148, 23);
		panel_2.add(btnNewButton_generateRoutes);

		JLabel lblNewLabel_1_1_1 = new JLabel("Por recorrido limitado");
		lblNewLabel_1_1_1.setBounds(10, 11, 181, 14);
		panel_2.add(lblNewLabel_1_1_1);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(Color.LIGHT_GRAY);
		panel_2_1.setBounds(10, 187, 261, 135);
		panel_1.add(panel_2_1);

		JLabel lblNewLabel_1_1 = new JLabel("Por arbol de imagenes");
		lblNewLabel_1_1.setBounds(10, 11, 205, 14);
		panel_2_1.add(lblNewLabel_1_1);

		textField_IDtreeImg = new JTextField();
		textField_IDtreeImg.setColumns(10);
		textField_IDtreeImg.setBounds(10, 67, 157, 20);
		panel_2_1.add(textField_IDtreeImg);

		JButton btnNewButton_generateTreeImg = new JButton("Generar imagen");

		btnNewButton_generateTreeImg.setBounds(10, 98, 157, 23);
		panel_2_1.add(btnNewButton_generateTreeImg);

		JLabel lblNewLabel_1_1_2 = new JLabel("id de imagen");
		lblNewLabel_1_1_2.setBounds(10, 38, 218, 14);
		panel_2_1.add(lblNewLabel_1_1_2);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBackground(Color.LIGHT_GRAY);
		panel_2_1_1.setBounds(10, 333, 261, 166);
		panel_1.add(panel_2_1_1);

		JLabel lblNewLabel_1_1_3 = new JLabel("Por capas");
		lblNewLabel_1_1_3.setBounds(10, 11, 219, 14);
		panel_2_1_1.add(lblNewLabel_1_1_3);

		textField_layers = new JTextField();
		textField_layers.setColumns(10);
		textField_layers.setBounds(10, 83, 156, 20);
		panel_2_1_1.add(textField_layers);

		JButton btnNewButton_generateLayers = new JButton("Generar imagen");

		btnNewButton_generateLayers.setBounds(10, 132, 156, 23);
		panel_2_1_1.add(btnNewButton_generateLayers);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("id de capas separadas ");
		lblNewLabel_1_1_2_1.setBounds(10, 36, 219, 14);
		panel_2_1_1.add(lblNewLabel_1_1_2_1);

		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("por  comas");
		lblNewLabel_1_1_2_1_1.setBounds(10, 58, 219, 14);
		panel_2_1_1.add(lblNewLabel_1_1_2_1_1);

		JComboBox<String> comboBox_IMGS = new JComboBox<String>();

		comboBox_IMGS.setBounds(380, 36, 295, 22);
		panel_1.add(comboBox_IMGS);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Imagenes para visualizar");
		lblNewLabel_1_1_1_1.setBounds(380, 11, 295, 14);
		panel_1.add(lblNewLabel_1_1_1_1);

		JButton button_show = new JButton("Mostrar");

		button_show.setBounds(472, 69, 122, 23);
		panel_1.add(button_show);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		panel_3.setBounds(780, 11, 869, 788);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		JLabel Label_img1 = new JLabel("");
		Label_img1.setBounds(10, 11, 847, 766);
		panel_3.add(Label_img1);

		// Button-----------------------------------------------------------------------------------------------------------------
		btnNewButton_generateLayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MatrizDispersa temp_Matriz = new MatrizDispersa();

					String temp = textField_layers.getText();
					int[] listlayer_no = Arrays.stream(temp.split(",")).mapToInt(Integer::parseInt).toArray();

					cliente.ABBCapas.temp = "";

					for (int i : listlayer_no) {
						cliente.ABBCapas.busquedaListColors(i, temp_Matriz);
						cliente.ABBCapas.temp += "  -> " + i;

						// System.out.println(i);
					}

					String name = cliente.DPI + "_ListaCapas_" + temp + "_";
					name += cliente.ID_IMG;
					temp_Matriz.GrapghInvisibleNewAplicacion(name);
					cliente.ID_IMG++;
					cliente.generated_images.add(name);

					updateImg(comboBox_IMGS, cliente);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se debe ingresar la estructura dicha");
				}
			}
		});

		btnNewButton_generateTreeImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MatrizDispersa temp_Matriz = new MatrizDispersa();
					int temp = Integer.valueOf(textField_IDtreeImg.getText());
					String name = cliente.DPI + "_ArbolImgs_id" + temp + "_";

					cliente.AVLImages.seraching(temp, cliente, temp_Matriz);

					name += cliente.ID_IMG;
					temp_Matriz.GrapghInvisibleNewAplicacion(name);
					cliente.ID_IMG++;
					cliente.generated_images.add(name);

					updateImg(comboBox_IMGS, cliente);

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se debe ingresar un dato");
				}
			}
		});

		btnNewButton_generateRoutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatrizDispersa temp_Matriz = new MatrizDispersa();
				try {

					if (comboBox_route.getSelectedItem() == "PreOrden") {

						int temp = Integer.valueOf(textField_setlayers.getText());

						String name = cliente.DPI + "_PreOrden_capas" + temp + "_";

						cliente.ABBCapas.recorridoLimitado(temp);
						cliente.ABBCapas.preordenLimited(cliente.ABBCapas.raiz, temp_Matriz);

						name += cliente.ID_IMG;
						temp_Matriz.GrapghInvisibleNewAplicacion(name);
						cliente.ID_IMG++;
						cliente.generated_images.add(name);

						updateImg(comboBox_IMGS, cliente);

					} else if (comboBox_route.getSelectedItem() == "InOrden") {

						int temp = Integer.valueOf(textField_setlayers.getText());

						String name = cliente.DPI + "_InOrden_capas" + temp + "_";

						cliente.ABBCapas.recorridoLimitado(temp);
						cliente.ABBCapas.inordenLimited(cliente.ABBCapas.raiz, temp_Matriz);

						name += cliente.ID_IMG;
						temp_Matriz.GrapghInvisibleNewAplicacion(name);
						cliente.ID_IMG++;
						cliente.generated_images.add(name);

						updateImg(comboBox_IMGS, cliente);

					} else if (comboBox_route.getSelectedItem() == "PostOrden") {

						int temp = Integer.valueOf(textField_setlayers.getText());

						String name = cliente.DPI + "_PostOrden_capas" + temp + "_";

						cliente.ABBCapas.recorridoLimitado(temp);
						cliente.ABBCapas.postordenLimited(cliente.ABBCapas.raiz, temp_Matriz);

						name += cliente.ID_IMG;
						temp_Matriz.GrapghInvisibleNewAplicacion(name);
						cliente.ID_IMG++;
						cliente.generated_images.add(name);

						updateImg(comboBox_IMGS, cliente);

					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se debe ingresar un dato");
				}
			}
		});

		button_show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = comboBox_IMGS.getSelectedItem().toString();
				String ruta = "Grafico\\" + name + ".png";
				System.out.println(name);
				ImageIcon imagen = new ImageIcon(ruta);
				Label_img1.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(Label_img1.getWidth(),
						Label_img1.getHeight(), Image.SCALE_SMOOTH)));
			}
		});

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Registro y eliminacion de imagenes", null, panel_4, null);
		panel_4.setLayout(null);

		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Visualizar estructuras", null, panel_5, null);
		panel_5.setLayout(null);

		JComboBox<String> comboBox_viewestruct = new JComboBox<String>();
		comboBox_viewestruct.setModel(new DefaultComboBoxModel<String>(
				new String[] { "arbol de imagenes", "arbol de capas", "Listado de albumes", "Capa","Imagen y Arbol de capas" }));
		comboBox_viewestruct.setBounds(30, 83, 148, 22);
		panel_5.add(comboBox_viewestruct);

		textField_idlayer = new JTextField();
		textField_idlayer.setBounds(30, 160, 148, 20);
		panel_5.add(textField_idlayer);
		textField_idlayer.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("id de capa a visualizar");
		lblNewLabel_2.setBounds(30, 135, 148, 14);
		panel_5.add(lblNewLabel_2);

		JButton btnGenerar = new JButton("Generar");

		btnGenerar.setBounds(30, 203, 89, 23);
		panel_5.add(btnGenerar);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBackground(Color.GRAY);
		panel_3_1.setBounds(464, 11, 1185, 788);
		panel_5.add(panel_3_1);

		JLabel Label_img2 = new JLabel("");
		Label_img2.setBounds(10, 11, 1165, 766);
		panel_3_1.add(Label_img2);
		
		JComboBox<String> comboBox_estrucgenerates = new JComboBox<String>();
		comboBox_estrucgenerates.setBounds(212, 83, 206, 22);
		panel_5.add(comboBox_estrucgenerates);
		
		JButton btnVer = new JButton("Ver");
		
		btnVer.setBounds(332, 131, 89, 23);
		panel_5.add(btnVer);

		JButton btnNewButton = new JButton("Cerrar sesion");

		btnNewButton.setBounds(964, 11, 118, 23);
		contentPane.add(btnNewButton);

		// buttons=---------------------------------------------------------------------------------------------------------------------
		
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = comboBox_estrucgenerates.getSelectedItem().toString();
				String ruta = "Grafico\\" + name + ".png";
				System.out.println(name);
				ImageIcon imagen = new ImageIcon(ruta);
				Label_img2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(Label_img2.getWidth(),Label_img2.getHeight(), Image.SCALE_SMOOTH)));
			}
		});
		
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (comboBox_viewestruct.getSelectedItem() == "arbol de imagenes") {
						
						String name = cliente.DPI + "_ArbolImagenes";
						
						cliente.AVLImages.DrawGraph(cliente.AVLImages.root,name);
						cliente.generate_struc.add(name);
						updateImg(comboBox_estrucgenerates,"Arbol de imagenes",cliente);
						
					} else if (comboBox_viewestruct.getSelectedItem() == "arbol de capas") {
						String name = cliente.DPI + "_ArbolCapas";
						
						cliente.ABBCapas.DrawGraph(cliente.ABBCapas.raiz,name);
						cliente.generate_struc.add(name);
						updateImg(comboBox_estrucgenerates,"Arbol de imagenes",cliente);

					} else if (comboBox_viewestruct.getSelectedItem() == "Listado de albumes") {
						
						String name = cliente.DPI + "_Albumes";
						
						cliente.Album_list.graph(name);
						
						cliente.generate_struc.add(name);
						updateImg(comboBox_estrucgenerates,"Arbol de imagenes",cliente);
						

					} else if (comboBox_viewestruct.getSelectedItem() == "Capa") {
						MatrizDispersa temp_Matriz = new MatrizDispersa();
						int temp = Integer.valueOf(textField_idlayer.getText());
						
						String name = cliente.DPI + "_CapaLogica_"+temp;
						cliente.ABBCapas.busquedaListColors(temp, temp_Matriz);
						temp_Matriz.GrapghInvisibleNewLogico(name);
						
						cliente.generate_struc.add(name);
						updateImg(comboBox_estrucgenerates,"Capa:" + temp,cliente);
						
					} else if (comboBox_viewestruct.getSelectedItem() == "Imagen y Arbol de capas") {
						

					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error, verificar que las estructuras tengan datos o colocar un numero en id");
					System.out.println(e);
				}
			}
		});

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
						ReadLayers(label_ruta.getText(), storage, cliente);

					} else if (comboBox.getSelectedItem() == "Imagenes") {
						ReadImages(label_ruta.getText(), storage, cliente);

					} else if (comboBox.getSelectedItem() == "Albums") {
						ReadAlbums(label_ruta.getText(), storage, cliente);

					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe cargar un archivo");
				}

			}
		});

	}
	
	public void mesageGenerateEstruu(String tipo) {
		JOptionPane.showMessageDialog(null, "Imagen generada con exito: " + tipo);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateImg(JComboBox<String> combo, String tipo,Clients cliente) {
		combo.setModel(new DefaultComboBoxModel(cliente.generate_struc.toArray()));
		mesageGenerateEstruu(tipo);
	}
	

	public void mesageGenerate(Clients cliente) {
		JOptionPane.showMessageDialog(null, "Imagen generandose con las capas Recorrido:   " + cliente.ABBCapas.temp);
		cliente.ABBCapas.temp = "";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateImg(JComboBox<String> combo, Clients cliente) {
		combo.setModel(new DefaultComboBoxModel(cliente.generated_images.toArray()));
		mesageGenerate(cliente);
	}

	public void ReadLayers(String ruta, Storage storage, Clients cliente) {
		Integer id;
		LinkedList<Nodes_Colors> Nodos;
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(ruta)) {

			Object obj = jsonParser.parse(reader);
			JSONArray jsonList = (JSONArray) obj;

			for (Object object : jsonList) {

				JSONObject data = (JSONObject) object;
				// System.out.println(data);

				Object id_obj = (Object) data.get("id_capa");

				id = ((Long) id_obj).intValue();
				// System.out.println( id);

				JSONArray pixelesList = (JSONArray) data.get("pixeles");

				Nodos = new LinkedList<Nodes_Colors>();

				// MatrizDispersa temp_Matriz = new MatrizDispersa();
				for (Object object2 : pixelesList) {

					JSONObject data2 = (JSONObject) object2;
					// System.out.println(data2);

					Object colum_obj = (Object) data2.get("columna");

					Object file_obj = (Object) data2.get("fila");

					String color = (String) data2.get("color");
					// System.out.println( color);

					Integer colum = ((Long) colum_obj).intValue();
					// System.out.println( colum);

					Integer file = ((Long) file_obj).intValue();
					// System.out.println( file);

					Nodes_Colors nodo_temp = new Nodes_Colors(file, colum, color);
					Nodos.add(nodo_temp);

					// temp_Matriz.insertarNodo(colum, file, color);
				}
				// temp_Matriz.Grapgh("MD_" + id);
				// temp_Matriz.GrapghInvisible("MD0_" + id);
				// temp_Matriz.GrapghInvisibleNew("NewMatriz_" + id);
				cliente.ABBCapas.insertar(id, Nodos);

			}

			// cliente.ABBCapas.inorden();
			JOptionPane.showMessageDialog(null, "El archivo capas se ingreso correctamente");
			System.out.println("El archivo se ingreso correctamente");

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Ocurrio un error en la lectura del JSON");

		}
	}

	public void ReadImages(String ruta, Storage storage, Clients cliente) {
		Integer id;
		LinkedList<Integer> capas_list;
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(ruta)) {

			Object obj = jsonParser.parse(reader);
			JSONArray jsonList = (JSONArray) obj;

			for (Object object : jsonList) {

				JSONObject data = (JSONObject) object;
				// System.out.println(data);

				Object id_obj = (Object) data.get("id");

				id = ((Long) id_obj).intValue();
				// System.out.println( id);

				JSONArray capas = (JSONArray) data.get("capas");
				// System.out.println(capas);

				capas_list = new LinkedList<Integer>();

				for (Object object2 : capas) {
					Integer capa_no = ((Long) object2).intValue();
					// System.out.println(capa_no);
					capas_list.add(capa_no);
				}

				cliente.AVLImages.root = cliente.AVLImages.insert(cliente.AVLImages.root, id, capas_list);
			}

			// cliente.AVLImages.print(cliente.AVLImages.root);
			JOptionPane.showMessageDialog(null, "El archivo imagenes se ingreso correctamente");
			System.out.println("El archivo se ingreso correctamente");

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Ocurrio un error en la lectura del JSON");
			System.out.println(e);

		}
	}

	public void ReadAlbums(String ruta, Storage storage, Clients cliente) {

		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(ruta)) {

			Object obj = jsonParser.parse(reader);
			JSONArray jsonList = (JSONArray) obj;

			for (Object object : jsonList) {

				JSONObject data = (JSONObject) object;
				// System.out.println(data);

				String name = (String) data.get("nombre_album");
				//System.out.println(name);

				JSONArray images = (JSONArray) data.get("imgs");
				// System.out.println(images);

				if (cliente.Album_list.SearchValidacion(name) == false) {
					cliente.Album_list.insert(name);
					//System.out.println("Se agrego");
				}

				cliente.Album_list.insernews(cliente.Album_list.SearchValidacionNode(name), images);

				/*
				 * for (Object object2 : images) { Integer img_no =((Long) object2).intValue();
				 * System.out.println(img_no);
				 * 
				 * }
				 */

			}

			JOptionPane.showMessageDialog(null, "El archivo albums se ingreso correctamente");
			System.out.println("El archivo se ingreso correctamente");

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Ocurrio un error en la lectura del JSON");

		}
	}
}
