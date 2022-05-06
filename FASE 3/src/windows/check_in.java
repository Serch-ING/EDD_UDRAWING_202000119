package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.Clients;
import storage.Storage;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class check_in extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Nuevo_DPI;
	private JTextField textField_Nuevo_Name;
	private JTextField textField_Nuevo_Password;
	private JTextField textField_passwordrepeat;
	private JTextField textField_user;
	private JTextField textField_cellphone;
	private JTextField textField_direction;
	private JTextField textField_idmunicipio;
	private JTextField textField_mail;
	
	public check_in(Storage storage) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_Nuevo_DPI = new JTextField();
		textField_Nuevo_DPI.setColumns(10);
		textField_Nuevo_DPI.setBounds(202, 91, 237, 20);
		contentPane.add(textField_Nuevo_DPI);
		
		JLabel lblNewLabel_2_1 = new JLabel("DPI:");
		lblNewLabel_2_1.setBounds(132, 91, 46, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Nombre cliente:");
		lblNewLabel_2_2.setBounds(96, 125, 117, 14);
		contentPane.add(lblNewLabel_2_2);
		
		textField_Nuevo_Name = new JTextField();
		textField_Nuevo_Name.setColumns(10);
		textField_Nuevo_Name.setBounds(202, 122, 237, 20);
		contentPane.add(textField_Nuevo_Name);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_2_2_1.setBounds(96, 329, 117, 14);
		contentPane.add(lblNewLabel_2_2_1);
		
		textField_Nuevo_Password = new JTextField();
		textField_Nuevo_Password.setColumns(10);
		textField_Nuevo_Password.setBounds(202, 326, 237, 20);
		contentPane.add(textField_Nuevo_Password);
		
		JButton btnRegistrar = new JButton("Registrar");
		
		btnRegistrar.setBounds(283, 403, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton Button_Back = new JButton("Regresar");
		
		Button_Back.setBounds(10, 403, 89, 23);
		contentPane.add(Button_Back);
		
		textField_passwordrepeat = new JTextField();
		textField_passwordrepeat.setColumns(10);
		textField_passwordrepeat.setBounds(202, 360, 237, 20);
		contentPane.add(textField_passwordrepeat);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Repertir contrase\u00F1a:");
		lblNewLabel_2_2_1_1.setBounds(64, 363, 133, 14);
		contentPane.add(lblNewLabel_2_2_1_1);
		
		JLabel lblNewLabel = new JLabel("Registro  -- Nuevo Cliente");
		lblNewLabel.setBounds(10, 11, 203, 14);
		contentPane.add(lblNewLabel);
		
		textField_user = new JTextField();
		textField_user.setColumns(10);
		textField_user.setBounds(202, 150, 237, 20);
		contentPane.add(textField_user);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("usuario:");
		lblNewLabel_2_2_2.setBounds(129, 153, 117, 14);
		contentPane.add(lblNewLabel_2_2_2);
		
		textField_cellphone = new JTextField();
		textField_cellphone.setColumns(10);
		textField_cellphone.setBounds(202, 225, 237, 20);
		contentPane.add(textField_cellphone);
		
		JLabel lblNewLabel_2_2_2_1 = new JLabel("telefono:");
		lblNewLabel_2_2_2_1.setBounds(129, 228, 127, 14);
		contentPane.add(lblNewLabel_2_2_2_1);
		
		JLabel lblNewLabel_2_2_2_1_1 = new JLabel("direccion:");
		lblNewLabel_2_2_2_1_1.setBounds(10, 270, 117, 14);
		contentPane.add(lblNewLabel_2_2_2_1_1);
		
		textField_direction = new JTextField();
		textField_direction.setColumns(10);
		textField_direction.setBounds(72, 267, 542, 20);
		contentPane.add(textField_direction);
		
		JLabel lblNewLabel_2_2_2_1_2 = new JLabel("ID municipio:");
		lblNewLabel_2_2_2_1_2.setBounds(129, 298, 127, 14);
		contentPane.add(lblNewLabel_2_2_2_1_2);
		
		textField_idmunicipio = new JTextField();
		textField_idmunicipio.setColumns(10);
		textField_idmunicipio.setBounds(202, 295, 237, 20);
		contentPane.add(textField_idmunicipio);
		
		textField_mail = new JTextField();
		textField_mail.setColumns(10);
		textField_mail.setBounds(202, 181, 237, 20);
		contentPane.add(textField_mail);
		
		JLabel lblNewLabel_2_2_2_2 = new JLabel("correo:");
		lblNewLabel_2_2_2_2.setBounds(129, 184, 117, 14);
		contentPane.add(lblNewLabel_2_2_2_2);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storage.Generar_Arbol_Merkle();
				  System.exit(0);
			}
		});
		btnSalir.setBounds(475, 24, 139, 23);
		contentPane.add(btnSalir);
		
		
		//buttons--------------------------------------------------------
		Button_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login(storage);
				login.setVisible(true);
				dispose();
			}
		});
		
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

					if (!(DPI.equals("") | Name.equals("") | Password.equals("")|| Password_repeat.equals(""))) {
						
						if (Password.equals(Password_repeat)) {
							int idmunicipie = Integer.valueOf(idmunicipio);
							Long DPI_number = Long.valueOf(DPI);
							Clients cliente_temp = new Clients(DPI,Name,user,mail,Password,cellphone,direction,idmunicipie);
							JOptionPane.showMessageDialog(null, "Se ah ingresado DPI: " + DPI);
							
							if(storage.ClinteExite(user)) {
								storage.InsertClients(cliente_temp, DPI_number);
							}else {
								JOptionPane.showMessageDialog(null, "Usuario ya existe");
								System.out.println("Usuario ya existe");
							}
							
						}else {
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
	}
}
