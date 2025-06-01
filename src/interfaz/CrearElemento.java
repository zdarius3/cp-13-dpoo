package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;

import auxiliares.ElementoTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

import logica.Carpeta;
import logica.Fichero;

import javax.swing.border.TitledBorder;

public class CrearElemento extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtExt;


	public CrearElemento(JFrame base, final Carpeta carpeta, final ElementoTableModel e) {
		super(base, true);
		setLocationRelativeTo(base);
		setTitle("Crear elemento");
		setBounds(100, 100, 325, 260);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblElementoACrear = new JLabel("Elemento a crear:");
		lblElementoACrear.setBounds(22, 20, 141, 14);
		contentPanel.add(lblElementoACrear);

		final JSpinner spinnerTam = new JSpinner();
		spinnerTam.setBounds(131, 138, 132, 22);
		contentPanel.add(spinnerTam); 
		spinnerTam.setEnabled(false);

		final JRadioButton rdbtnFichero = new JRadioButton("Fichero");
		rdbtnFichero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnFichero.isSelected()) {
					spinnerTam.setEnabled(true);
				}
			}
		});
		rdbtnFichero.setBounds(219, 16, 72, 23);
		contentPanel.add(rdbtnFichero);

		final JRadioButton rdbtnCarpeta = new JRadioButton("Carpeta");
		rdbtnCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnCarpeta.isSelected()) {
					spinnerTam.setEnabled(false);
				}
			}
		});
		rdbtnCarpeta.setBounds(131, 16, 72, 23);
		rdbtnCarpeta.setSelected(true);
		contentPanel.add(rdbtnCarpeta);

		ButtonGroup grupoBtn = new ButtonGroup();
		grupoBtn.add(rdbtnFichero);
		grupoBtn.add(rdbtnCarpeta);

		final JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(35, 73, 141, 14);
		contentPanel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(131, 70, 132, 22);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);

		final JLabel lblExtensin = new JLabel("Extensi\u00F3n:");
		lblExtensin.setBounds(35, 108, 141, 14);
		contentPanel.add(lblExtensin);

		txtExt = new JTextField();
		txtExt.setColumns(10);
		txtExt.setBounds(131, 103, 132, 22);
		contentPanel.add(txtExt);

		JLabel lblTamao = new JLabel("Tama\u00F1o:");
		lblTamao.setBounds(35, 142, 141, 14);
		contentPanel.add(lblTamao);

		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nom = txtNombre.getText();
				String ext = txtExt.getText();
				double tam = ((Number) spinnerTam.getValue()).doubleValue();
				if (nom.replaceAll(" ", "").equals("")) {
					JOptionPane.showMessageDialog(null, "El campo de nombre no puede estar vacío.");
					lblNombre.setForeground(Color.red);
				}
				else if (ext.replaceAll(" ", ""). equals("")) {
					JOptionPane.showMessageDialog(null, "El campo de extensión no puede estar vacío.");
					lblExtensin.setForeground(Color.red);
				}
				else {
					if(rdbtnFichero.isSelected()) {
						carpeta.addElemento(new Fichero(nom, ext, LocalDate.now(), tam));
					}
					else {
						carpeta.addElemento(new Carpeta(nom, ext, LocalDate.now()));
					}
					JOptionPane.showMessageDialog(null, "Elemento creado satisfactoriamente.");
					e.cargarDatos(carpeta);
					e.fireTableDataChanged();
					dispose();
				}
			}
		});
		btnCrear.setBounds(49, 185, 89, 23);
		contentPanel.add(btnCrear);

		JButton btnCancelar = new JButton("Cancelar");		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNombre.setText("");
				txtExt.setText("");
				spinnerTam.setValue((double) 0);
			}
		});
		btnCancelar.setBounds(175, 185, 89, 23);
		contentPanel.add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 45, 281, 128);
		contentPanel.add(panel);
	}
}
