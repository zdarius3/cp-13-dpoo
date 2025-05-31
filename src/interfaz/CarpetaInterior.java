package interfaz;



import logica.*;

import java.awt.EventQueue;



import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

import auxiliares.ElementoTableModel;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarpetaInterior extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(final Carpeta carpeta) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarpetaInterior frame = new CarpetaInterior(carpeta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CarpetaInterior(final Carpeta carpeta) {
		
		setTitle("Contenido de " + carpeta.getNombre());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane panelElem = new JScrollPane();
		panelElem.setBounds(10, 11, 539, 403);
		contentPane.add(panelElem);
		//crear tabla custom
		final ElementoTableModel modeloElem = new ElementoTableModel(carpeta);
		final JTable tabla = new JTable(modeloElem);
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
		            JTable tablaOrig = (JTable) evt.getSource();
		            int fila = tablaOrig.getSelectedRow();

		            Elemento elemento = ((ElementoTableModel) tablaOrig.getModel()).getElemento(fila, carpeta);

		            if (elemento instanceof Carpeta) {
		                final JFrame framePadre = (JFrame) SwingUtilities.getWindowAncestor(tabla);		                
		                framePadre.setVisible(false);
		                JFrame frameInterior = new CarpetaInterior((Carpeta) elemento);		                
		                frameInterior.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                frameInterior.addWindowListener(new WindowAdapter() {
		                    @Override
		                    public void windowClosed(WindowEvent we) {
		                        framePadre.setVisible(true);
		                    }
		                });
		                
		                frameInterior.setLocationRelativeTo(null);
		                frameInterior.setVisible(true);
		            }
		        }
		    }
		});
		panelElem.setViewportView(tabla);
		contentPane.add(panelElem);
		
		JButton btnNewButton = new JButton("Copiar");
		btnNewButton.setFocusable(false);
		btnNewButton.setBounds(122, 427, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCopiar = new JButton("Mover");
		btnCopiar.setFocusable(false);
		btnCopiar.setBounds(234, 427, 89, 23);
		contentPane.add(btnCopiar);
		
		JButton btnNewButton_1 = new JButton("Editar");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBounds(349, 427, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnEditar = new JButton("Eliminar");
		btnEditar.setFocusable(false);
		btnEditar.setBounds(460, 427, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CrearElemento dialog = new CrearElemento(carpeta, modeloElem);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnCrear.setFocusable(false);
		btnCrear.setBounds(10, 427, 89, 23);
		contentPane.add(btnCrear);
	}
}
