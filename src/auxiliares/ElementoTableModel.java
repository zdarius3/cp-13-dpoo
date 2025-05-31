package auxiliares;

import logica.*;

import javax.swing.table.DefaultTableModel;

public class ElementoTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	public ElementoTableModel(Carpeta carpeta) {
		String[] columnas = {"Nombre", "Extensión", "Fecha de modificación", "Tamaño"};
		this.setColumnIdentifiers(columnas);
		cargarDatos(carpeta);
	}

	public void cargarDatos(Carpeta carpeta) {	
		this.setRowCount(0);
		for (Elemento e: carpeta.getElementos()) {
			Object[] nuevaLinea = new Object[]{e.getNombre(), e.getExtension(), e.getFechaActualizacion(), e.getTamanno() + " kb"};
			addRow(nuevaLinea);
		}
	}
	
	@Override //para que no se pueda editar directamente
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	public Elemento getElemento(int row, Carpeta carpeta) {
        if (row >= 0 && row < carpeta.getElementos().size()) {
            return carpeta.getElementos().get(row);
        } else {
            throw new IndexOutOfBoundsException("El índice de fila " + row + " está fuera de los límites.");
        }
    }
}


