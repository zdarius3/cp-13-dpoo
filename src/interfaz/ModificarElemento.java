package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;

import auxiliares.ElementoTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.Carpeta;
import logica.Elemento;
import logica.Fichero;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class ModificarElemento extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtExt;


	public ModificarElemento(JFrame base, final Carpeta carpeta, final ElementoTableModel e, final Elemento elem) {
		super(base, true);
		setLocationRelativeTo(base);
		setTitle("Modificar elemento");
		setBounds(100, 100, 325, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		final JSpinner spinnerTam = new JSpinner();
		spinnerTam.setBounds(130, 105, 132, 22);
		contentPanel.add(spinnerTam); 
		if(elem instanceof Carpeta) {
			spinnerTam.setEnabled(false);
		}
		else {
			spinnerTam.setEnabled(true);
			spinnerTam.setValue(elem.getTamanno());
		}

		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nuevos datos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 281, 135);
		contentPanel.add(panel);
		panel.setLayout(null);

		txtNombre = new JTextField();
		txtNombre.setBounds(119, 22, 132, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setText(elem.getNombre());

		txtExt = new JTextField();
		txtExt.setBounds(119, 56, 132, 22);
		panel.add(txtExt);
		txtExt.setColumns(10);
		txtExt.setText(elem.getExtension());

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nom = txtNombre.getText();
				String ext = txtExt.getText();
				double tam = ((Number) spinnerTam.getValue()).doubleValue();
				if (nom.replaceAll(" ", "").equals("")) {
					JOptionPane.showMessageDialog(null, "El campo de nombre no puede estar vacío.");
				}
				else if (ext.replaceAll(" ", ""). equals("")) {
					JOptionPane.showMessageDialog(null, "El campo de extensión no puede estar vacío.");
				}
				else {
					if(elem instanceof Carpeta) {
						((Carpeta)elem).modificar(nom, ext);
					}
					else {
						((Fichero)elem).modificar(nom, ext, tam);
					}
					JOptionPane.showMessageDialog(null, "Elemento modificado satisfactoriamente.");
					e.cargarDatos(carpeta);
					e.fireTableDataChanged();
					dispose();
				}
			}
		});
		btnEditar.setBounds(48, 157, 89, 23);
		contentPanel.add(btnEditar);

		JButton btnCancelar = new JButton("Cancelar");		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNombre.setText("");
				txtExt.setText("");
				spinnerTam.setValue((double) 0);
			}
		});
		btnCancelar.setBounds(174, 157, 89, 23);
		contentPanel.add(btnCancelar);


		final JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(23, 25, 141, 14);
		panel.add(lblNombre);

		

		final JLabel lblExtensin = new JLabel("Extensi\u00F3n:");
		lblExtensin.setBounds(23, 61, 141, 14);
		panel.add(lblExtensin);

		JLabel lblTamao = new JLabel("Tama\u00F1o:");
		lblTamao.setBounds(23, 96, 141, 14);
		panel.add(lblTamao);
	}
}
