package windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.crypto.bcrypt.BCrypt;

import objects.Clients;
import objects.Mensajero;
import objects.viaje;
import storage.NodoB;
import storage.Storage;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Admin_Module extends JFrame {

	private JPanel contentPane;
	private JTextField textField_DPI_search;
	private JTextField textField_Name_Modify;
	private JTextField textField_Password_Modify;
	private JTextField textField_deleteclient;
	private JTextField textField_id_searching;
	private JTable table;
	private JTable table_2;
	private JTable table_3;
	private JTextField textField_Nuevo_DPI;
	private JTextField textField_Nuevo_Name;
	private JTextField textField_Nuevo_Password;
	private JTextField textField_passwordrepeat;
	private JTextField textField_user;
	private JTextField textField_cellphone;
	private JTextField textField_direction;
	private JTextField textField_idmunicipio;
	private JTextField textField_mail;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnSalir;

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Admin_Module frame = new
	 * Admin_Module(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	public Admin_Module(Storage storage) {
		String[] Colums_table1 = { "DPI", "Nombre", "Password", "Cantidad de imagenes", "Cantidad de capas",
				"Cantidad de Albumes" };
		String[] Colums_table2 = { "Album", "Imagenes" };
		String[] Colums_table3 = { "Cliente", "DPI", "Total de imagenes" };

		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.JSON", "JSON");
		fc.setFileFilter(filter);
		fc.setCurrentDirectory(new File("./Test"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1526, 913);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 41, 1490, 822);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Carga Masivas", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ruta:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);

		JLabel label_ruta = new JLabel("Null");
		label_ruta.setForeground(Color.BLACK);
		label_ruta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_ruta.setBounds(48, 11, 1127, 14);
		panel.add(label_ruta);

		JButton Button_Search = new JButton("Buscar Archivo");

		Button_Search.setBounds(10, 36, 123, 23);
		panel.add(Button_Search);

		TextArea textOut = new TextArea();
		textOut.setEditable(false);
		textOut.setBounds(312, 72, 569, 442);
		panel.add(textOut);

		JButton Button_LoadClients = new JButton("Cargar clientes");

		Button_LoadClients.setBounds(10, 69, 123, 23);
		panel.add(Button_LoadClients);

		JLabel lblNewLabel_4 = new JLabel("Clientes JSON ingresados");
		lblNewLabel_4.setBounds(312, 50, 171, 14);
		panel.add(lblNewLabel_4);

		JButton Button_LoadMensajeros = new JButton("Cargar mensajeros");

		Button_LoadMensajeros.setBounds(10, 106, 165, 23);
		panel.add(Button_LoadMensajeros);

		Button_LoadMensajeros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!label_ruta.getText().equals("Null")) {

					textOut.setText(ReadJsonMensajeros(label_ruta.getText(), storage));

				} else {
					JOptionPane.showMessageDialog(null, "Seleccionar un archivo");
				}
			}
		});

		Button_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fc.showOpenDialog(Button_Search) == JFileChooser.APPROVE_OPTION) {
					try {
						textOut.setText(" ");

						label_ruta.setText(fc.getSelectedFile().toString());

					} catch (Exception e2) {

					}
				} else {
					label_ruta.setText("Null");
				}
				// DPI:2299062130101\nName: Sergie Daniel\nPassword: 1234
			}
		});

		Button_LoadClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!label_ruta.getText().equals("Null")) {

					textOut.setText(ReadJson(label_ruta.getText(), storage));

				} else {
					JOptionPane.showMessageDialog(null, "Seleccionar un archivo");
				}

			}
		});

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Operaciones de clientes", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Operaciones disponibles");
		lblNewLabel_1.setBounds(27, 11, 190, 14);
		panel_1.add(lblNewLabel_1);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(27, 36, 836, 461);
		panel_1.add(tabbedPane_1);

		JPanel panel_4 = new JPanel();
		tabbedPane_1.addTab("Insertar nuevo cliente", null, panel_4, null);
		panel_4.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(28, 0, 624, 437);
		panel_4.add(contentPane_1);

		textField_Nuevo_DPI = new JTextField();
		textField_Nuevo_DPI.setColumns(10);
		textField_Nuevo_DPI.setBounds(202, 51, 237, 20);
		contentPane_1.add(textField_Nuevo_DPI);

		JLabel lblNewLabel_2_1 = new JLabel("DPI:");
		lblNewLabel_2_1.setBounds(132, 51, 46, 14);
		contentPane_1.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Nombre cliente:");
		lblNewLabel_2_2.setBounds(96, 85, 117, 14);
		contentPane_1.add(lblNewLabel_2_2);

		textField_Nuevo_Name = new JTextField();
		textField_Nuevo_Name.setColumns(10);
		textField_Nuevo_Name.setBounds(202, 82, 237, 20);
		contentPane_1.add(textField_Nuevo_Name);

		JLabel lblNewLabel_2_2_1 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_2_2_1.setBounds(96, 289, 117, 14);
		contentPane_1.add(lblNewLabel_2_2_1);

		textField_Nuevo_Password = new JTextField();
		textField_Nuevo_Password.setColumns(10);
		textField_Nuevo_Password.setBounds(202, 286, 237, 20);
		contentPane_1.add(textField_Nuevo_Password);

		JButton btnRegistrar = new JButton("Registrar");

		btnRegistrar.setBounds(283, 363, 89, 23);
		contentPane_1.add(btnRegistrar);

		textField_passwordrepeat = new JTextField();
		textField_passwordrepeat.setColumns(10);
		textField_passwordrepeat.setBounds(202, 320, 237, 20);
		contentPane_1.add(textField_passwordrepeat);

		JLabel lblNewLabel_2_2_1_1_1 = new JLabel("Repertir contrase\u00F1a:");
		lblNewLabel_2_2_1_1_1.setBounds(64, 323, 133, 14);
		contentPane_1.add(lblNewLabel_2_2_1_1_1);

		JLabel lblNewLabel_5 = new JLabel("Registro  -- Nuevo Cliente");
		lblNewLabel_5.setBounds(10, 11, 203, 14);
		contentPane_1.add(lblNewLabel_5);

		textField_user = new JTextField();
		textField_user.setColumns(10);
		textField_user.setBounds(202, 110, 237, 20);
		contentPane_1.add(textField_user);

		JLabel lblNewLabel_2_2_2_1 = new JLabel("usuario:");
		lblNewLabel_2_2_2_1.setBounds(129, 113, 117, 14);
		contentPane_1.add(lblNewLabel_2_2_2_1);

		textField_cellphone = new JTextField();
		textField_cellphone.setColumns(10);
		textField_cellphone.setBounds(202, 185, 237, 20);
		contentPane_1.add(textField_cellphone);

		JLabel lblNewLabel_2_2_2_1_1 = new JLabel("telefono:");
		lblNewLabel_2_2_2_1_1.setBounds(129, 188, 127, 14);
		contentPane_1.add(lblNewLabel_2_2_2_1_1);

		JLabel lblNewLabel_2_2_2_1_1_1 = new JLabel("direccion:");
		lblNewLabel_2_2_2_1_1_1.setBounds(10, 230, 117, 14);
		contentPane_1.add(lblNewLabel_2_2_2_1_1_1);

		textField_direction = new JTextField();
		textField_direction.setColumns(10);
		textField_direction.setBounds(72, 227, 542, 20);
		contentPane_1.add(textField_direction);

		JLabel lblNewLabel_2_2_2_1_2 = new JLabel("ID municipio:");
		lblNewLabel_2_2_2_1_2.setBounds(129, 258, 127, 14);
		contentPane_1.add(lblNewLabel_2_2_2_1_2);

		textField_idmunicipio = new JTextField();
		textField_idmunicipio.setColumns(10);
		textField_idmunicipio.setBounds(202, 255, 237, 20);
		contentPane_1.add(textField_idmunicipio);

		textField_mail = new JTextField();
		textField_mail.setColumns(10);
		textField_mail.setBounds(202, 141, 237, 20);
		contentPane_1.add(textField_mail);

		JLabel lblNewLabel_2_2_2_2 = new JLabel("correo:");
		lblNewLabel_2_2_2_2.setBounds(129, 144, 117, 14);
		contentPane_1.add(lblNewLabel_2_2_2_2);
		
		JButton btnBuscarYModificar_1 = new JButton("Buscar y modificar");
		btnBuscarYModificar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String DPI = textField_Nuevo_DPI.getText();
					String Name = textField_Nuevo_Name.getText();

					String user = textField_user.getText();
					String mail = textField_mail.getText();
					String cellphone = textField_cellphone.getText();
					String direction = textField_direction.getText();
					String idmunicipio = textField_idmunicipio.getText();

					String Password = textField_Nuevo_Password.getText();
					String Password_repeat = textField_passwordrepeat.getText();

					if (!(DPI.equals("") | Name.equals("") | Password.equals("") || Password_repeat.equals(""))) {

						if (Password.equals(Password_repeat)) {
							int idmunicipie = Integer.valueOf(idmunicipio);
						
							JOptionPane.showMessageDialog(null, "Se ah ingresado DPI: " + DPI);
							NodoB usar = storage.RegresarCLeinte(user);
							if (usar != null) {
								JOptionPane.showMessageDialog(null, "Datos del cleinte: \n" + usar.cliente.Name + "\n" +usar.cliente.correo + "\n" +  usar.cliente.telefono+ "\n" + usar.cliente.direccion +"\n" +usar.cliente.id_municipio );
								
								usar.cliente.Name = Name;
								usar.cliente.correo = mail;
								usar.cliente.telefono = cellphone;
								usar.cliente.direccion = direction;
								usar.cliente.id_municipio = idmunicipie;
								usar.cliente.Password = Password;
								
								JOptionPane.showMessageDialog(null, "Datos del cliente actulizado: \n" + usar.cliente.Name + "\n" +usar.cliente.correo + "\n" +  usar.cliente.telefono+ "\n" + usar.cliente.direccion +"\n" +usar.cliente.id_municipio );
								
								
								
								
							} else {
								JOptionPane.showMessageDialog(null, "Usuario no encontrado");
							
							}

						} else {
							JOptionPane.showMessageDialog(null, "La passwords no coinciden");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos correspondinets");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un digito en el DPI e ID municipio");
				}
			}
		});
		btnBuscarYModificar_1.setBounds(459, 363, 155, 23);
		contentPane_1.add(btnBuscarYModificar_1);

		JPanel panel_5 = new JPanel();
		tabbedPane_1.addTab("Modificar cliente", null, panel_5, null);
		panel_5.setLayout(null);

		JLabel lblNewLabel_2_1_1 = new JLabel("DPI:");
		lblNewLabel_2_1_1.setBounds(51, 34, 46, 14);
		panel_5.add(lblNewLabel_2_1_1);

		textField_DPI_search = new JTextField();
		textField_DPI_search.setColumns(10);
		textField_DPI_search.setBounds(121, 34, 237, 20);
		panel_5.add(textField_DPI_search);

		JButton btnBuscarYModificar = new JButton("Buscar y modificar");

		btnBuscarYModificar.setBounds(176, 306, 155, 23);
		panel_5.add(btnBuscarYModificar);

		JLabel lblNewLabel_2_2_2 = new JLabel("Nombre cliente:");
		lblNewLabel_2_2_2.setBounds(15, 117, 117, 14);
		panel_5.add(lblNewLabel_2_2_2);

		textField_Name_Modify = new JTextField();
		textField_Name_Modify.setColumns(10);
		textField_Name_Modify.setBounds(121, 114, 237, 20);
		panel_5.add(textField_Name_Modify);

		JLabel lblNewLabel_2_2_1_1 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_2_2_1_1.setBounds(15, 142, 117, 14);
		panel_5.add(lblNewLabel_2_2_1_1);

		textField_Password_Modify = new JTextField();
		textField_Password_Modify.setColumns(10);
		textField_Password_Modify.setBounds(121, 142, 237, 20);
		panel_5.add(textField_Password_Modify);

		JLabel lblNewLabel_2_1_1_2 = new JLabel("Datos del cliente a modificar:  ");
		lblNewLabel_2_1_1_2.setBounds(15, 92, 202, 14);
		panel_5.add(lblNewLabel_2_1_1_2);

		JPanel panel_6 = new JPanel();
		tabbedPane_1.addTab("Eliminar cliente", null, panel_6, null);
		panel_6.setLayout(null);

		textField_deleteclient = new JTextField();
		textField_deleteclient.setColumns(10);
		textField_deleteclient.setBounds(136, 39, 237, 20);
		panel_6.add(textField_deleteclient);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("DPI:");
		lblNewLabel_2_1_1_1.setBounds(66, 39, 46, 14);
		panel_6.add(lblNewLabel_2_1_1_1);

		JButton Button_Delete_Client = new JButton("Eliminar");

		Button_Delete_Client.setBounds(211, 83, 89, 23);
		panel_6.add(Button_Delete_Client);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Arbol de clientes", null, panel_2, null);
		panel_2.setLayout(null);

		JPanel panel_7 = new JPanel();
		panel_7.setBounds(10, 45, 1465, 738);
		panel_2.add(panel_7);
		panel_7.setLayout(null);

		JLabel lblNewLabel_img = new JLabel("");
		lblNewLabel_img.setBounds(10, 11, 1445, 716);
		panel_7.add(lblNewLabel_img);

		JLabel lblNewLabel_3 = new JLabel("Arbol de Clientes");
		lblNewLabel_3.setBounds(10, 20, 116, 14);
		panel_2.add(lblNewLabel_3);

		JButton Button_btree = new JButton("Generar");

		Button_btree.setBounds(136, 16, 131, 23);
		panel_2.add(Button_btree);

		JButton Button_btree_1 = new JButton("Visualizar");

		Button_btree_1.setBounds(594, 16, 131, 23);
		panel_2.add(Button_btree_1);

		JLabel lblNewLabel_3_1 = new JLabel("Nombre del archivo:");
		lblNewLabel_3_1.setBounds(277, 20, 144, 14);
		panel_2.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_1_1 = new JLabel("Admin_Btree_Clients");
		lblNewLabel_3_1_1.setBounds(431, 20, 153, 14);
		panel_2.add(lblNewLabel_3_1_1);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Reportes", null, panel_3, null);
		panel_3.setLayout(null);

		JComboBox<String> comboBox_estrucgenerates = new JComboBox<String>();
		comboBox_estrucgenerates.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Listar clientes", "Buscar cliente por id" }));
		comboBox_estrucgenerates.setBounds(10, 51, 206, 22);
		panel_3.add(comboBox_estrucgenerates);

		textField_id_searching = new JTextField();
		textField_id_searching.setBounds(226, 52, 143, 20);
		panel_3.add(textField_id_searching);
		textField_id_searching.setColumns(10);

		String[] rows__table1 = new String[0];

		JButton btnNewButton_1 = new JButton("Generar reporte");

		btnNewButton_1.setBounds(379, 51, 138, 23);
		panel_3.add(btnNewButton_1);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.LIGHT_GRAY);
		panel_8.setBounds(527, 26, 948, 757);
		panel_3.add(panel_8);
		panel_8.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Informacion del cliente  buscado");
		lblNewLabel_2.setBounds(10, 11, 200, 14);
		panel_8.add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 934, 72);
		panel_8.add(scrollPane);

		table = new JTable();
		table.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, }, Colums_table1));

		table.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(125, 133, 689, 613);
		panel_8.add(scrollPane_1);

		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(new Object[][] { { null, null }, }, Colums_table2));

		scrollPane_1.setViewportView(table_2);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.LIGHT_GRAY);
		panel_9.setBounds(10, 105, 507, 678);
		panel_3.add(panel_9);
		panel_9.setLayout(null);

		JLabel lblNewLabel_2_3 = new JLabel("Clientes del arbol B por niveles");
		lblNewLabel_2_3.setBounds(10, 11, 200, 14);
		panel_9.add(lblNewLabel_2_3);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 36, 487, 631);
		panel_9.add(scrollPane_2);

		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, }, Colums_table3));
		scrollPane_2.setViewportView(table_3);

		JLabel lblNewLabel_2_4 = new JLabel("DPI buscado");
		lblNewLabel_2_4.setBounds(225, 37, 200, 14);
		panel_3.add(lblNewLabel_2_4);

		JPanel panel_10 = new JPanel();
		tabbedPane.addTab("Sitema de bloackchain", null, panel_10, null);
		panel_10.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Modificar tiempo de generacion de bloques color car numero en  segundos");
		lblNewLabel_6.setBounds(21, 11, 560, 14);
		panel_10.add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel("*Por defecto estaa en 180 s = 3 min");
		lblNewLabel_6_1.setBounds(21, 31, 448, 14);
		panel_10.add(lblNewLabel_6_1);

		textField = new JTextField();
		textField.setBounds(31, 56, 216, 20);
		panel_10.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Actualizar Tiempo");

		btnNewButton.setBounds(28, 87, 139, 23);
		panel_10.add(btnNewButton);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(31, 258, 216, 20);
		panel_10.add(textField_1);

		JLabel lblNewLabel_6_1_1 = new JLabel("*Por defecto estaa en 4 ceros \"0000\" ");
		lblNewLabel_6_1_1.setBounds(21, 233, 448, 14);
		panel_10.add(lblNewLabel_6_1_1);

		JLabel lblNewLabel_6_2 = new JLabel("Modificar el numero de ceros para la prueba de trabajo");
		lblNewLabel_6_2.setBounds(21, 213, 448, 14);
		panel_10.add(lblNewLabel_6_2);

		JButton btnNewButton_2 = new JButton("Actualizar Ceros");

		btnNewButton_2.setBounds(28, 289, 139, 23);
		panel_10.add(btnNewButton_2);

		JButton btnVerBlockchain = new JButton("Ver Blockchain");

		btnVerBlockchain.setBounds(1336, 7, 139, 23);
		panel_10.add(btnVerBlockchain);

		JButton GenerarBloque = new JButton("Generar Bloque");

		GenerarBloque.setBounds(1336, 55, 139, 23);
		panel_10.add(GenerarBloque);
		
		JComboBox<String> combo_Trasacciones = new JComboBox<String>();
		combo_Trasacciones.setModel(new DefaultComboBoxModel(storage.Cadenas_Arboles_Merkle.toArray()));
		combo_Trasacciones.setBounds(1107, 136, 368, 22);
		panel_10.add(combo_Trasacciones);
		
		JButton Grafos_rutas = new JButton("Ver grafo");
		
		Grafos_rutas.setBounds(1206, 233, 269, 23);
		panel_10.add(Grafos_rutas);
		
		JButton btnNewButton_3 = new JButton("Clientes con mas pedidos top 10");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clientes= "";
				Collections.sort(storage.clientes_temp);
				
				int contar = 0;
				for (Clients string : storage.clientes_temp) {
					if(contar== 10) {
						break;
					}else {
						clientes += string.Name + " -> " + string.Cantidad_pedidos + "\n";
					}
					contar++;
				}
				JOptionPane.showMessageDialog(null, "Top 10 con mas pedidos:\n " + clientes);
			}
		});
		btnNewButton_3.setBounds(31, 369, 281, 23);
		panel_10.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("mensajeros con mas entregas top 10");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clientes= "";
				Collections.sort(storage.mensajeros_temp);
				
				int contar = 0;
				for (Mensajero string : storage.mensajeros_temp) {
					if(contar== 10) {
						break;
					}else {
						clientes += string.nombre + " " + string.apellido + " -> " + string.cantidad_entregas + "\n";
					}
					contar++;
				}
				JOptionPane.showMessageDialog(null, "Top 10 mensjeros con mas entegas:\n " + clientes);
			}
		});
		btnNewButton_3_1.setBounds(31, 414, 281, 23);
		panel_10.add(btnNewButton_3_1);
		
		JButton btnNewButton_3_1_1 = new JButton("Viajes mas largos top 10");
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vaijes= "";
				Collections.sort(storage.viajes_temp);
				
				int contar = 0;
				for (viaje string : storage.viajes_temp) {
					if(contar== 10) {
						break;
					}else {
						vaijes += string.inicio + " a " + string.finalizancion + " Total: " + string.total + " minutos \n";
					}
					contar++;
				}
				JOptionPane.showMessageDialog(null, "Top 10 vijaes mas largos:\n " + vaijes);
				
			}
		});
		btnNewButton_3_1_1.setBounds(31, 463, 281, 23);
		panel_10.add(btnNewButton_3_1_1);
		
		JPanel panel_11 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_11, null);
		/// Butons
		/// ------------------------------------------------------------------------------------
		Grafos_rutas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name =  combo_Trasacciones.getSelectedItem().toString();
				String ruta = "./Grafico/" + name + ".png";
				System.out.println(ruta);
				Grapgh2 grafico = new Grapgh2(ruta);
				grafico.setVisible(true);
			}
		});

		GenerarBloque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storage.Generar_Arbol_Merkle();
			}
		});

		btnVerBlockchain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storage.blockchain.recorrido_bloques();
				String name =  "BLoackChain_" + storage.contador_bloques;
				String ruta = "./Grafico/" + name + ".png";
				System.out.println(ruta);
				Grapgh2 grafico = new Grapgh2(ruta);
				grafico.setVisible(true);
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int no_ceros = Integer.valueOf(textField_1.getText());
					JOptionPane.showMessageDialog(null, "Se actualizo la cantidad de ceros para la prueba de trabajo");
					storage.cantidad_ceros = no_ceros;

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se ingreso un dato erroeno");
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int timepo = Integer.valueOf(textField.getText());
					JOptionPane.showMessageDialog(null, "Se actulizo el tiempo de generacion de bloques");
					storage.timepo_app = 0;
					storage.tiempo_bloque = timepo;

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se ingreso un dato erroeno");
				}

			}
		});

		JButton Button_closesesion = new JButton("Cerrar sesion");

		Button_closesesion.setBounds(775, 23, 131, 23);
		contentPane.add(Button_closesesion);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storage.Generar_Arbol_Merkle();
				  System.exit(0);
			}
		});
		btnSalir.setBounds(1361, 11, 139, 23);
		contentPane.add(btnSalir);

		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String DPI = textField_Nuevo_DPI.getText();
					String Name = textField_Nuevo_Name.getText();

					String user = textField_user.getText();
					String mail = textField_mail.getText();
					String cellphone = textField_cellphone.getText();
					String direction = textField_direction.getText();
					String idmunicipio = textField_idmunicipio.getText();

					String Password = textField_Nuevo_Password.getText();
					String Password_repeat = textField_passwordrepeat.getText();

					if (!(DPI.equals("") | Name.equals("") | Password.equals("") || Password_repeat.equals(""))) {

						if (Password.equals(Password_repeat)) {
							int idmunicipie = Integer.valueOf(idmunicipio);
							Long DPI_number = Long.valueOf(DPI);
							Clients cliente_temp = new Clients(DPI, Name, user, mail, Password, cellphone, direction,
									idmunicipie);
							JOptionPane.showMessageDialog(null, "Se ah ingresado DPI: " + DPI);

							if (storage.ClinteExite(user)) {
								storage.InsertClients(cliente_temp, DPI_number);
							} else {
								JOptionPane.showMessageDialog(null, "Usuario ya existe");
								System.out.println("Usuario ya existe");
							}

						} else {
							JOptionPane.showMessageDialog(null, "La passwords no coinciden");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos correspondinets");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un digito en el DPI e ID municipio");
				}

			}
		});

		Button_Delete_Client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String DPI = textField_deleteclient.getText();
					Long DPI_int = Long.valueOf(DPI);

					// System.out.println(DPI_int);
					storage.RemovingClient(DPI_int);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un digito en el DPI");
					System.out.println(e2);
				}
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (comboBox_estrucgenerates.getSelectedItem() == "Listar clientes") {
						Queue<Clients> info;
						info = storage.ClientesB.print_start(storage.ClientesB.raiz.primero);
						Object[][] list = new Object[info.size()][3];
						int contador = 0;

						while (info.peek() != null) {
							Clients temp = info.poll();

							list[contador][0] = temp.Name;
							list[contador][1] = temp.DPI;

							Object no_images = temp.AVLImages.cantidad_images();
							list[contador][2] = no_images;

							contador++;
						}
						table_3.setModel(new DefaultTableModel(list, Colums_table3));

					} else if (comboBox_estrucgenerates.getSelectedItem() == "Buscar cliente por id") {
						Long id = Long.valueOf(textField_id_searching.getText());

						NodoB nodotemp = storage.ClientesB.buscar_start(storage.ClientesB.raiz.primero, id);
						System.out.println("\n\n------Registro--------");

						Object DPI = nodotemp.cliente.DPI;
						Object name = nodotemp.cliente.Name;
						Object password = nodotemp.cliente.Password;

						Object no_capas = nodotemp.cliente.ABBCapas.cantidad_images();
						System.out.println("No capas: " + no_capas);

						Object no_images = nodotemp.cliente.AVLImages.cantidad_images();
						System.out.println("No iamges: " + no_images);

						nodotemp.cliente.Album_list.cantidad_albums();
						Object no_albums = nodotemp.cliente.Album_list.albums.size();

						System.out.println("Canrida de albums: " + no_albums);

						Object[][] data_albums = nodotemp.cliente.Album_list.data_toshow();
						
						table.setModel(new DefaultTableModel(
								new Object[][] { { DPI, name, BCrypt.hashpw((String) password, BCrypt.gensalt(10)), no_images, no_capas, no_albums }, },
								Colums_table1));
						table_2.setModel(new DefaultTableModel(data_albums, Colums_table2));
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un digito en el DPI");
					table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
							Colums_table1));
					table_2.setModel(new DefaultTableModel(new Object[][] { { null, null }, }, Colums_table2));
					table_3.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, }, Colums_table3));
					System.out.println(e2);
				}

			}
		});

		btnBuscarYModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String DPI = textField_DPI_search.getText();
					String name = textField_Name_Modify.getText();
					String password = textField_Password_Modify.getText();
					Long DPI_long = Long.valueOf(DPI);
					storage.modifyClient(DPI_long, name, password);

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un digito en el DPI");
					System.out.println(e2);
				}
			}
		});

		Button_btree_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = "Admin_Btree_Clients";
				String ruta = "Grafico\\" + name + ".png";
				System.out.println(name);
				ImageIcon imagen = new ImageIcon(ruta);
				lblNewLabel_img.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(lblNewLabel_img.getWidth(),
						lblNewLabel_img.getHeight(), Image.SCALE_SMOOTH)));
			}
		});

		Button_btree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				storage.ClientesB.draw_start(storage.ClientesB.raiz.primero, "Admin_Btree_Clients");
				JOptionPane.showMessageDialog(null, "Se creo grafo: arbol B de clientes");
			}
		});

		Button_closesesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login(storage);
				login.setVisible(true);
				dispose();
			}
		});
	}

	public String ReadJson(String ruta, Storage storage) {

		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(ruta)) {
			String temp = "";
			Object obj = jsonParser.parse(reader);

			JSONArray jsonList = (JSONArray) obj;
			// System.out.println(jsonList + "\n");

			for (Object object : jsonList) {

				JSONObject data = (JSONObject) object;
				System.out.println(data);

				String dpi = ((String) data.get("dpi"));
				Long DPI_Long = Long.valueOf(dpi);
				System.out.println(DPI_Long);

				String name = (String) data.get("nombre_cliente");
				System.out.println(name);

				String user = (String) data.get("usuario");
				System.out.println(user);

				String mail = (String) data.get("correo");
				System.out.println(mail);

				String password = (String) data.get("password");
				System.out.println(password);

				String cellphone = (String) data.get("telefono");
				// System.out.println(cellphone);

				String direction = (String) data.get("direccion");
				// System.out.println(direction);

				String municipie = String.valueOf(data.get("id_municipio"));
				int municipie_int = Integer.valueOf(municipie);
				// System.out.println(municipie_int);

				Clients client_new = new Clients(dpi, name, user, mail, password, cellphone, direction, municipie_int);

				if (dpi != null && name != null && user != null && mail != null && password != null && cellphone != null
						&& direction != null && municipie_int > -1) {
					temp += object + "\n\n";
					storage.InsertClients(client_new, DPI_Long);
					storage.clientes_temp.add(client_new);
				} else {
					System.out.println("datos incompletos");
				}
			}
			// storage.showClients();

			System.out.println("El archivo se ingreso correctamente");
			return temp;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Ocurrio un error en la lectura del JSON");
			System.out.println(e);
			return null;
		}
	}

	public String ReadJsonMensajeros(String ruta, Storage storage) {

		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(ruta)) {
			String temp = "";
			Object obj = jsonParser.parse(reader);

			JSONArray jsonList = (JSONArray) obj;
			// System.out.println(jsonList + "\n");

			for (Object object : jsonList) {

				JSONObject data = (JSONObject) object;
				// System.out.println(data);

				String dpi = ((String) data.get("dpi"));
				Long DPI_Long = Long.valueOf(dpi);
				// System.out.println(DPI_Long);

				String name = (String) data.get("nombres");
				// System.out.println(name);

				String apellidos = (String) data.get("apellidos");
				// System.out.println(apellidos);

				String tipo_licencia = (String) data.get("tipo_licencia");
				// System.out.println(tipo_licencia);

				String genero = (String) data.get("genero");
				// System.out.println(genero);

				String telefono = (String) data.get("telefono");
				// System.out.println(telefono);

				String direccion = (String) data.get("direccion");
				// System.out.println(direccion);

				Mensajero mensajero_new = new Mensajero(DPI_Long, name, apellidos, tipo_licencia, genero, telefono,
						direccion);

				if (dpi != null && name != null && apellidos != null && tipo_licencia != null && genero != null
						&& telefono != null && direccion != null) {
					temp += object + "\n\n";
					storage.TablaHash_Mesajeros.insertar(mensajero_new);
					storage.mensajeros_temp.add(mensajero_new);

				} else {
					System.out.println("datos incompletos");
				}

			}
			storage.TablaHash_Mesajeros.mostar();
			storage.TablaHash_Mesajeros.Graficar(storage.Cadenas_Arboles_Merkle);

			System.out.println("El archivo se ingreso correctamente");
			return temp;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Ocurrio un error en la lectura del JSON");
			System.out.println(e);
			return null;
		}
	}
}
