package interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import logica.Fichero;
import logica.Sistema;

import auxiliares.FicheroTableModel;

public class FicherosMenorTamEnRaiz extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public static void main(JFrame base, Sistema sistema) {
		try {
			FicherosMenorTamEnRaiz dialog = new FicherosMenorTamEnRaiz(base, sistema);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FicherosMenorTamEnRaiz(JFrame base, Sistema sistema) {
		super(base, "Elementos de menor tamaño", true);
		setTitle("Ficheros de menor tama\u00F1o en la ra\u00EDz del sistema");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane ficheros = new JScrollPane();
			ficheros.setBounds(10, 11, 414, 239);
			contentPanel.add(ficheros);
			
			ArrayList<Fichero> mostrar = sistema.getFicherosMenorTamEnRaiz();
			final FicheroTableModel modeloFich = new FicheroTableModel(mostrar);
			final JTable tabla= new JTable(modeloFich);
			ficheros.setViewportView(tabla);
			contentPanel.add(ficheros);
		}
	}

}
