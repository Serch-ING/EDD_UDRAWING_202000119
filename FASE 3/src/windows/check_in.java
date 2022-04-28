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
		lblNewLabel_2_2_1.setBounds(96, 165, 117, 14);
		contentPane.add(lblNewLabel_2_2_1);
		
		textField_Nuevo_Password = new JTextField();
		textField_Nuevo_Password.setColumns(10);
		textField_Nuevo_Password.setBounds(202, 162, 237, 20);
		contentPane.add(textField_Nuevo_Password);
		
		JButton btnRegistrar = new JButton("Registrar");
		
		btnRegistrar.setBounds(271, 246, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton Button_Back = new JButton("Regresar");
		
		Button_Back.setBounds(10, 403, 89, 23);
		contentPane.add(Button_Back);
		
		textField_passwordrepeat = new JTextField();
		textField_passwordrepeat.setColumns(10);
		textField_passwordrepeat.setBounds(202, 196, 237, 20);
		contentPane.add(textField_passwordrepeat);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Repertir contrase\u00F1a:");
		lblNewLabel_2_2_1_1.setBounds(64, 199, 133, 14);
		contentPane.add(lblNewLabel_2_2_1_1);
		
		JLabel lblNewLabel = new JLabel("Registro  -- Nuevo Cliente");
		lblNewLabel.setBounds(10, 11, 203, 14);
		contentPane.add(lblNewLabel);
		
		
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
					String Password = textField_Nuevo_Password.getText();
					String Password_repeat = textField_passwordrepeat.getText();

					if (!(DPI.equals("") | Name.equals("") | Password.equals("")|| Password_repeat.equals(""))) {
						
						if (Password.equals(Password_repeat)) {
							
							Long DPI_number = Long.valueOf(DPI);
							//Clients cliente_temp = new Clients(Name, Password, DPI);
							JOptionPane.showMessageDialog(null, "Se ah ingresado DPI: " + DPI);
							//storage.InsertClients(cliente_temp, DPI_number);
							
						}else {
							JOptionPane.showMessageDialog(null, "La passwords no coinciden");
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos correspondinets");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un digito en el DPI");
				}
				
			}
		});
	}
}
