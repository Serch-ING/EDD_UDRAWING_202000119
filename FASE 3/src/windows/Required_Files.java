package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import objects.Clients;
import objects.Lugares;
import storage.ListaDG;
import storage.Storage;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;

public class Required_Files extends JFrame {

	private JPanel contentPane;


	public Required_Files(Storage storage) {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.JSON", "JSON");
		fc.setFileFilter(filter);
		fc.setCurrentDirectory(new File("./Test"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1222, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Button_Search = new JButton("Buscar Archivo");
		
		Button_Search.setBounds(10, 36, 123, 23);
		contentPane.add(Button_Search);
		
		JLabel lblNewLabel = new JLabel("Ruta:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel label_ruta = new JLabel("Null");
		label_ruta.setForeground(Color.BLACK);
		label_ruta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_ruta.setBounds(48, 11, 1127, 14);
		contentPane.add(label_ruta);
		
		JButton Button_LoadPlaces = new JButton("Cargar Lugares");

		Button_LoadPlaces.setBounds(10, 69, 123, 23);
		contentPane.add(Button_LoadPlaces);
		
		JButton Button_LoadRutes = new JButton("Cargar Rutas");
		Button_LoadRutes.setEnabled(false);

		Button_LoadRutes.setBounds(10, 106, 123, 23);
		contentPane.add(Button_LoadRutes);
		
		JButton Button_Next = new JButton("Continuar con la aplicacion");
		Button_Next.setEnabled(false);
		
		Button_Next.setBounds(382, 229, 325, 23);
		contentPane.add(Button_Next);
		
		/*Botones ---------------------------------------------------------------------------*/
		Button_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fc.showOpenDialog(Button_Search) == JFileChooser.APPROVE_OPTION) {
					try {
					
						label_ruta.setText(fc.getSelectedFile().toString());

					} catch (Exception e2) {

					}
				} else {
					label_ruta.setText("Null");
				}
			}
		});
		
		Button_LoadPlaces.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!label_ruta.getText().equals("Null")) {
					
					if(ReadPlaces(label_ruta.getText(), storage) != null) {
						Button_LoadPlaces.setEnabled(false);
						Button_LoadRutes.setEnabled(true);
					}
				
				} else {
					JOptionPane.showMessageDialog(null, "Seleccionar un archivo");
				}
			}
		});
		
		Button_LoadRutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!label_ruta.getText().equals("Null")) {
					
					if(ReadRutas(label_ruta.getText(), storage)!= null) {
						Button_LoadRutes.setEnabled(false);
						Button_Next.setEnabled(true);
					}
				
				} else {
					JOptionPane.showMessageDialog(null, "Seleccionar un archivo");
				}
			}
		});
		
		Button_Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login frame = new Login(storage);
				frame.setVisible(true);
			}
		});
		
	}
	
	public String ReadPlaces(String ruta, Storage storage) {
		ListaDG lista= new ListaDG();
		
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(ruta)) {
			String temp = null;
			Object obj = jsonParser.parse(reader);
			
			JSONObject js = (JSONObject) obj;
			JSONArray arr = (JSONArray) js.get("Lugares");
			
			for (Object object : arr) {
				JSONObject data = (JSONObject) object;
				//System.out.println(data);
				
				
				String id = String.valueOf(data.get("id"));
				int id_int = Integer.valueOf(id);
				//System.out.println(id_int);
				
								
				String departamento = (String) data.get("departamento");
				//System.out.println(departamento);
				
				String nombre = (String) data.get("nombre");
				//System.out.println(nombre);
				
				String sn_sucursal = ((String) data.get("sn_sucursal")).toUpperCase();
				sn_sucursal= sn_sucursal.replace(" ", "");
				//System.out.println(sn_sucursal);
				
				Boolean validacion = false;
				
				if(sn_sucursal.equals("NO")) {
					validacion = false;
					
				}else if(sn_sucursal.equals("SI")) {
					validacion = true;
					
				}else {
					int varible = Integer.valueOf("error");
				}
				
				//System.out.println(validacion);
				
				Lugares lugares = new Lugares(id_int, departamento, nombre, validacion);
				storage.LugaresFacil.add(lugares);
				
				storage.LLenando_Lista(id_int, departamento, nombre, validacion);
				temp+=data;
			}
			

			System.out.println("El archivo se ingreso correctamente");
			storage.Lista_adyacente.showList();
			return temp;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Ocurrio un error en la lectura del JSON");
			System.out.println(e);
			return null;
		}
	}
	
	public String ReadRutas(String ruta, Storage storage) {

		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(ruta)) {
			String temp = null;
			Object obj = jsonParser.parse(reader);
			
			JSONObject js = (JSONObject) obj;
			JSONArray arr = (JSONArray) js.get("Grafo");
			
			for (Object object : arr) {
				JSONObject data = (JSONObject) object;
				//System.out.println(data);
				
				
				String Inicio = String.valueOf(data.get("inicio"));
				int Inicio_int = Integer.valueOf(Inicio);
				//System.out.println(Inicio_int);
				
				String FIN = String.valueOf(data.get("final"));
				int FIN_int = Integer.valueOf(FIN);
				//System.out.println(FIN_int);
				
				String Peso = String.valueOf(data.get("peso"));
				int Peso_int = Integer.valueOf(Peso);
				//System.out.println(Peso_int);
				
				storage.Conexion_Lista(Inicio_int, FIN_int, Peso_int);
				temp+=data;
			}
			
			System.out.println("El archivo se ingreso correctamente");
			storage.Lista_adyacente.showList();
			return temp;
			
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Ocurrio un error en la lectura del JSON");
			System.out.println(e);
			return null;
		}
	}
	
}




