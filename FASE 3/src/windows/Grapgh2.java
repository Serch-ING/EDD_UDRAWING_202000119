package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Grapgh2 extends JFrame {
	int ancho, alto =0;
	
	public Grapgh2(String ruta) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1460, 800);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1434, 712);
		getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 434, 261);
		scrollPane.setViewportView(lblNewLabel);
		
		ImageIcon imagen = new ImageIcon(ruta);
		lblNewLabel.setIcon(imagen);
		
		JButton btnNewButton = new JButton("+");
		ancho = imagen.getIconWidth();
		alto =imagen.getIconHeight();
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ancho += 50;
				alto += 50;
				lblNewLabel.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(ancho,alto, Image.SCALE_SMOOTH)));
			
			}
		});
		btnNewButton.setBounds(534, 727, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("-");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ancho -= 50;
				alto -= 50;
				lblNewLabel.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(ancho,alto, Image.SCALE_SMOOTH)));
			}
		});
		btnNewButton_1.setBounds(706, 727, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Salir");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(989, 727, 89, 23);
		getContentPane().add(btnNewButton_1_1);
	}
	
	

}
