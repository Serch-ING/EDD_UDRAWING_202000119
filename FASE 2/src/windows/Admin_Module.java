package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;

import lists.Cola_Reception;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.TextArea;

public class Admin_Module extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Module frame = new Admin_Module();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Admin_Module() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.exp", "exp");
		fc.setFileFilter(filter);
		fc.setCurrentDirectory(new File("./"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 932, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 896, 552);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Carga Masiva", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ruta:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel label_ruta = new JLabel("Null");
		label_ruta.setForeground(Color.BLACK);
		label_ruta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_ruta.setBounds(48, 11, 569, 14);
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
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Operaciones de clientes", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Arbol de clientes", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Reportes", null, panel_3, null);
		panel_3.setLayout(null);
		
		
		//Buttons
		Button_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fc.showOpenDialog(Button_Search) == JFileChooser.APPROVE_OPTION) {
					try {
						textOut.setText(" ");
						
						
						label_ruta.setText(fc.getSelectedFile().toString());
						
					} catch (Exception e2) {

					}
				} else {
					System.out.println("NO se ecogio");
				}
				
			}
		});
		
		Button_LoadClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
	
	
	public void ReadJson(String ruta) {

		JSONParser jsonParser = new JSONParser();
		ObjectMapper objectMapper = new ObjectMapper();

		try (FileReader reader = new FileReader(ruta)) {

			Object obj = jsonParser.parse(reader);
			JSONObject Jobj = (JSONObject) obj;
			



			System.out.println("El archivo se ingreso correctamente");



		} catch (Exception e) {
			
		}
	}
}
