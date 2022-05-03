package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import objects.Clients;
import objects.Lugares;
import storage.Storage;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Delivery extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField_Id_sucursal;
	private JTextField textField_DPI_mesanjero;
	private JTable table_1;
	private JTable table_2;

	/**
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delivery frame = new Delivery();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 * Create the frame.
	 */
	public Delivery(Storage storage, Clients cliente) {
		String[] Colums_table1 = { "ID", "Departamento", "Nombre"};
		String[] Colums_table2 = { "Imagenes" };
		String[] Colums_table3 = {"DPI", "Nombre", "Apellidos","Tipo de licencia", "genero","direccion","telefono"};

		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1522, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 49, 369, 575);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		Object[][] new_list_table = new Object[storage.LugaresFacil.size()][4];
		int contador_temp_2 = 0;
		for (Lugares datos : storage.LugaresFacil) {
			
			if(datos.validacion) {
				new_list_table[contador_temp_2][0] = datos.id_int ;
				new_list_table[contador_temp_2][1] = datos.departamento;
				new_list_table[contador_temp_2][2] = datos.nombre;
				contador_temp_2++;
			}
			
		}
		table.setModel(new DefaultTableModel(new_list_table, Colums_table1));
		//table.setModel(new DefaultTableModel(new Object[][] { { null, null,null }, }, Colums_table1));
		
		
		scrollPane_1.setViewportView(table);
		
		textField_Id_sucursal = new JTextField();
		textField_Id_sucursal.setBounds(477, 123, 236, 20);
		contentPane.add(textField_Id_sucursal);
		textField_Id_sucursal.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ingrese Id de la sucursal ");
		lblNewLabel.setBounds(477, 98, 132, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblFechaDeSolicitud = new JLabel("Fecha de solicitud: ");
		lblFechaDeSolicitud.setBounds(477, 154, 132, 14);
		contentPane.add(lblFechaDeSolicitud);
		
		JLabel label_date = new JLabel("xx/xx/xxxx");
		label_date.setBounds(619, 154, 132, 14);
		contentPane.add(label_date);
		
		JLabel lblLugarDeDestino = new JLabel("Lugar de destino: Departamento no.");
		lblLugarDeDestino.setBounds(477, 179, 199, 14);
		contentPane.add(lblLugarDeDestino);
		
		JLabel label_direccion = new JLabel("xxx");
		label_direccion.setBounds(681, 179, 132, 14);
		contentPane.add(label_direccion);
		
		JButton btnRealizarPedido = new JButton("Realizar pedido");
		btnRealizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRealizarPedido.setBounds(644, 593, 132, 23);
		contentPane.add(btnRealizarPedido);
		
		JLabel lblIngreseDpiDel = new JLabel("Ingrese DPI del mensajero deseado");
		lblIngreseDpiDel.setBounds(477, 212, 236, 14);
		contentPane.add(lblIngreseDpiDel);
		
		textField_DPI_mesanjero = new JTextField();
		textField_DPI_mesanjero.setColumns(10);
		textField_DPI_mesanjero.setBounds(477, 237, 236, 20);
		contentPane.add(textField_DPI_mesanjero);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(895, 49, 601, 575);
		contentPane.add(scrollPane_1_1);
		
		table_1 = new JTable();
		//table_1.setModel(new DefaultTableModel(new Object[][] { { null, null,null,null,null,null,null }, }, Colums_table3));
		Object[][] lista_mensajeros = storage.TablaHash_Mesajeros.retornarTable();
		table_1.setModel(new DefaultTableModel(lista_mensajeros, Colums_table3));
		
		scrollPane_1_1.setViewportView(table_1);
		
		JLabel lblSucursales = new JLabel("Sucursales:");
		lblSucursales.setBounds(10, 24, 132, 14);
		contentPane.add(lblSucursales);
		
		JLabel lblMensajeros = new JLabel("Mensajeros:");
		lblMensajeros.setBounds(895, 18, 132, 14);
		contentPane.add(lblMensajeros);
		
		JScrollPane scrollPane_1_1_1 = new JScrollPane();
		scrollPane_1_1_1.setBounds(477, 296, 301, 286);
		contentPane.add(scrollPane_1_1_1);
		
		table_2 = new JTable();
		Object[][] new_list_img = new Object[cliente.imgstoPrint.size()][1];
		int contador_temp = 0;
		for (String datos : cliente.imgstoPrint) {
			new_list_img[contador_temp][0] = datos;
			contador_temp++;
		}
		//table_2.setModel(new DefaultTableModel(new Object[][] { { null }, }, Colums_table2));
		table_2.setModel(new DefaultTableModel(new_list_img, Colums_table2));
		
		scrollPane_1_1_1.setViewportView(table_2);
		
		JLabel lblListadoDeImagenes = new JLabel("Listado de imagenes");
		lblListadoDeImagenes.setBounds(477, 279, 236, 14);
		contentPane.add(lblListadoDeImagenes);
		
		JButton btnClear = new JButton("Limpiar lista");
				btnClear.setBounds(477, 593, 132, 23);
		contentPane.add(btnClear);
		
		JButton butn_regresar = new JButton("Regresar");
		
		butn_regresar.setBounds(747, 15, 132, 23);
		contentPane.add(butn_regresar);
		
		///BUtons----------------------------------------------------------------
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.imgstoPrint.clear();
				JOptionPane.showMessageDialog(null, "Se limpio lista ");
				table_2.setModel(new DefaultTableModel(new Object[][] { { null }, }, Colums_table2));

				
			}
		});

		
		butn_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client_Module clientes = new Client_Module(storage, cliente);
				clientes.setVisible(true);
				dispose();
			}
		});
	}
}
