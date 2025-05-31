package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

import logica.Sistema;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PedirDatosReporte2 extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public PedirDatosReporte2(final Sistema sistema) {
		setTitle("Introduzca los datos pedidos");
		setBounds(100, 100, 570, 218);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblSeleccioneLaExtension = new JLabel("Seleccione la extensi\u00F3n de los ficheros a contar:");
		lblSeleccioneLaExtension.setBounds(21, 17, 303, 14);
		contentPanel.add(lblSeleccioneLaExtension);

		ArrayList<String> extensiones = sistema.getExtensionesFicheros();
		final JComboBox extFich = new JComboBox();
		extFich.setBounds(350, 13, 174, 20);
		contentPanel.add(extFich);
		for (int i = 0; i < extensiones.size(); i++) {
			extFich.addItem(extensiones.get(i));
		}

		JLabel lblSeleccion = new JLabel("Seleccione el nombre de la carpeta en la que buscar:");
		lblSeleccion.setBounds(21, 56, 303, 14);
		contentPanel.add(lblSeleccion);

		ArrayList<String> nombresCarp = sistema.getNombresCarpetas();
		final JComboBox nomCarp = new JComboBox();
		nomCarp.setBounds(350, 53, 174, 20);
		contentPanel.add(nomCarp);
		for (int i = 0; i < nombresCarp.size(); i++) {
			nomCarp.addItem(nombresCarp.get(i));
		}


		JLabel lblSeleccioneLaExtensin = new JLabel("Seleccione la extensi\u00F3n de la carpeta en la que buscar:");
		lblSeleccioneLaExtensin.setBounds(21, 98, 332, 14);
		contentPanel.add(lblSeleccioneLaExtensin);

		ArrayList<String> extensionesCarp = sistema.getExtensionesCarpetas();
		final JComboBox extCarp = new JComboBox();
		extCarp.setBounds(350, 95, 174, 20);
		contentPanel.add(extCarp);
		for (int i = 0; i < extensionesCarp.size(); i++) {
			extCarp.addItem(extensionesCarp.get(i));
		}

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String extensionFichero = (String)extFich.getSelectedItem();
				String nombreCarpeta = (String)nomCarp.getSelectedItem();
				String extensionCarpeta = (String)extCarp.getSelectedItem();
				try {
					int cantidad = sistema.getCantFicherosPrimCarpeta(extensionFichero, nombreCarpeta, extensionCarpeta);
					if (cantidad > 0) {
						JOptionPane.showMessageDialog(null, "La cantidad de ficheros con la extensión (" + extensionFichero + 
								") encontrados en la carpeta de nombre (" + nombreCarpeta + ") y extensión (" + extensionCarpeta +
								") es de: " + cantidad);
					}
					else {
						JOptionPane.showMessageDialog(null, "No se encontraron ficheros con las características indicadas.");
					}
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "No se encontró una carpeta con esas características.");
				}
			}
		});
		btnAceptar.setFocusable(false);
		btnAceptar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAceptar.setBounds(131, 143, 89, 23);
		contentPanel.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setFocusable(false);
		btnCancelar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancelar.setBounds(314, 143, 89, 23);
		contentPanel.add(btnCancelar);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 128, 534, 2);
		contentPanel.add(separator);
	}
}
