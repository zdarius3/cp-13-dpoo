package auxiliares;

import java.util.ArrayList;

import logica.*;

import javax.swing.table.DefaultTableModel;

public class FicheroTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	public FicheroTableModel(ArrayList<Fichero> ficheros) {
		String[] columnas = {"Nombre", "Extensión", "Fecha de modificación", "Tamaño"};
		this.setColumnIdentifiers(columnas);
		cargarDatos(ficheros);
	}

	public void cargarDatos(ArrayList<Fichero> ficheros) {	
		this.setRowCount(0);
		for (Fichero e: ficheros) {
			Object[] nuevaLinea = new Object[]{e.getNombre(), e.getExtension(), e.getFechaActualizacion(), e.getTamanno() + " kb"};
			addRow(nuevaLinea);
		}
	}
	
	@Override //para que no se pueda editar directamente
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}


