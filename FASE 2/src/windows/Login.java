package windows;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	String passwordAdmin = "EDD2022", userAdmin = "admin";

	private JPanel contentPane;
	private JTextField UserText;
	private JPasswordField passwordField;
	private JLabel lblContrasea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(104, 95, 60, 14);
		contentPane.add(lblNewLabel);

		UserText = new JTextField();
		UserText.setBounds(174, 94, 152, 20);
		contentPane.add(UserText);
		UserText.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(174, 125, 152, 20);
		contentPane.add(passwordField);

		lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContrasea.setBounds(82, 126, 82, 14);
		contentPane.add(lblContrasea);

		JButton Button_join = new JButton("Ingresar");

		Button_join.setBounds(237, 176, 89, 23);
		contentPane.add(Button_join);

		// Botenes
		Button_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String password = String.valueOf(passwordField.getPassword());
					String User = UserText.getText();

					if (User.equals(userAdmin) && password.equals(passwordAdmin)) {
						JOptionPane.showMessageDialog(null, "Ingreso");
						Admin_Module admin_view = new Admin_Module();
						admin_view.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Credenciales erroneas");
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error");
				}

			}
		});

	}
}
