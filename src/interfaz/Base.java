package interfaz;

import logica.*;

import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

import auxiliares.ElementoTableModel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class Base extends JFrame {
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Base() {
		Carpeta raiz = new Carpeta("Raíz", ".fdr", LocalDate.now());
		final Sistema sistema = new Sistema(500, raiz);

		Carpeta c1 = new Carpeta("carpeta1", ".crp", LocalDate.now());
		c1.addElemento(new Fichero("fichero1", ".txt", LocalDate.now(), 5.0));
		c1.addElemento(new Fichero("fichero2", ".txt", LocalDate.now(), 25.0));

		Carpeta c2 = new Carpeta("carpeta2", ".fdr", LocalDate.now());
		Carpeta c2interior = new Carpeta("carpeta2.1", ".fdr", LocalDate.now());
		c2.addElemento(new Fichero("fichero3", ".docx", LocalDate.now(), 150.0));
		c2.addElemento(new Fichero("fichero5", ".txt", LocalDate.now(), 20.0)); //para probar el inciso d
		c2.addElemento(new Fichero("fichero6", ".txt", LocalDate.now(), 20.0));
		c2interior.addElemento(new Fichero ("fichero4", ".xls", LocalDate.now(), 20));
		c2.addElemento(c2interior);

		sistema.addElemento(c1);
		sistema.addElemento(c2);
		sistema.addElemento(new Fichero("fichero7", ".txt", LocalDate.now(), 50.0));
		sistema.addElemento(new Fichero("fichero8", ".txt", LocalDate.now(), 5.0));
		sistema.addElemento(new Fichero("fichero9", ".txt", LocalDate.now(), 5.0));
		sistema.addElemento(new Fichero("fichero10", ".txt", LocalDate.now(), 150.0));
		
		setTitle("Ra\u00EDz del sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JScrollPane panelElem = new JScrollPane();
		panelElem.setBounds(10, 39, 539, 375);
		contentPane.add(panelElem);
		//crear tabla custom
		final ElementoTableModel modeloElem = new ElementoTableModel(sistema.getRaiz());
		final JTable tabla = new JTable(modeloElem);
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
		            JTable tablaOrig = (JTable) evt.getSource();
		            int fila = tablaOrig.getSelectedRow();

		            Elemento elemento = ((ElementoTableModel) tablaOrig.getModel()).getElemento(fila, sistema.getRaiz());

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
		
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 549, 21);
		contentPane.add(menuBar);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JMenuItem mntmCantidadTotalDe = new JMenuItem("Cantidad total de kilobytes en el sistema");
		mntmCantidadTotalDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "La cantidad total de kilobytes en el sistema es de: " + 
			sistema.getTamTotal() + " kilobytes");
			}
		});
		mnReportes.add(mntmCantidadTotalDe);
		
		JMenuItem mntmCantidadDeElementos = new JMenuItem("Cantidad de elementos de una extensi\u00F3n dada en una carpeta espec\u00EDfica");
		mntmCantidadDeElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PedirDatosReporte2 p = new PedirDatosReporte2(sistema);
				p.setVisible(true);
			}
		});
		mnReportes.add(mntmCantidadDeElementos);
		
		JMenuItem mntmElementosDeMenor = new JMenuItem("Ficheros de menor tama\u00F1o en la ra\u00EDz del sistema");
		mntmElementosDeMenor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FicherosMenorTamEnRaiz fichMenor = new FicherosMenorTamEnRaiz(Base.this, sistema);
				fichMenor.setVisible(true);
			}
		});
		mnReportes.add(mntmElementosDeMenor);
		
		JMenuItem mntmElementosDeMenor_1 = new JMenuItem("Ficheros de menor tama\u00F1o en todo el sistema");
		mntmElementosDeMenor_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FicherosMenorTamEnTodo fichMenor = new FicherosMenorTamEnTodo(Base.this, sistema);
				fichMenor.setVisible(true);
			}
		});
		mnReportes.add(mntmElementosDeMenor_1);
		
		JMenu mnInformacin = new JMenu("Informaci\u00F3n");
		menuBar.add(mnInformacin);
		
		JMenuItem mntmcmoUsar = new JMenuItem("\u00BFC\u00F3mo usar?");
		mntmcmoUsar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Aquí te explico cómo usar el programita");
			}
		});
		mnInformacin.add(mntmcmoUsar);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Este es el proyecto de la calse práctica #13 de DPOO"
						+ ", hecho por Damian Romero Alvarez del grupo IF-13.");
			}
		});
		mnInformacin.add(mntmAcercaDe);
		
		JButton btnNewButton = new JButton("Copiar");
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.setFocusable(false);
		btnNewButton.setBounds(122, 425, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCopiar = new JButton("Mover");
		btnCopiar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCopiar.setFocusable(false);
		btnCopiar.setBounds(234, 425, 89, 23);
		contentPane.add(btnCopiar);
		
		JButton btnNewButton_1 = new JButton("Editar");
		btnNewButton_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBounds(349, 425, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnEditar = new JButton("Eliminar");
		btnEditar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEditar.setFocusable(false);
		btnEditar.setBounds(460, 425, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setFocusable(false);
		btnCrear.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCrear.setBounds(10, 425, 89, 23);
		contentPane.add(btnCrear);
		
		
		
		
		
		
		
/*
				//inciso d
				String extFichero = JOptionPane.showInputDialog(null, "Introduzca la extensión del archivo a buscar: ");
				if (!extFichero.replaceAll(" ", "").equals("") && extFichero != null) {
					String nomCarpeta = JOptionPane.showInputDialog(null, "Introduzca el nombre de la carpeta en la cual buscar: ");
					String extCarpeta = JOptionPane.showInputDialog(null, "Introduzca la extensión de la carpeta en la cual buscar: ");
					System.out.println("La cantidad de ficheros con extension " + extFichero + " en la primera carpeta encontrada"
							+ " con extensión " + extCarpeta + " y nombre " + nomCarpeta + " es de: " +
							sistema.getCantFicherosPrimCarpeta(extFichero, nomCarpeta, extCarpeta));
				}
				System.out.println("");
*/
				
	}
}
