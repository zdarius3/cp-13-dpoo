package auxiliares;

import java.time.LocalDate;

import logica.*;

import javax.swing.table.DefaultTableModel;

public class ElementoTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	public ElementoTableModel(Carpeta carpeta) {
		String[] columnas = {"Nombre", "Extensión", "Tipo", "Fecha de modificación", "Tamaño"};
		this.setColumnIdentifiers(columnas);
		cargarDatos(carpeta);
	}

	public void cargarDatos(Carpeta carpeta) {	
		this.setRowCount(0);
		String tipo;
		for (Elemento e: carpeta.getElementos()) {
			if (e instanceof Carpeta) {
				tipo = "Carpeta";
			}
			else {
				tipo = "Fichero";
			}
			Object[] nuevaLinea = new Object[]{e.getNombre(), e.getExtension(), tipo, e.getFechaActualizacion(), e.getTamanno() + " kb"};
			addRow(nuevaLinea);
		}
	}
	
	public void addElemento(Object[] elemento) {
		addRow(elemento);
	}
	
	public void addFichero(String nom, String ext, LocalDate fecha, double tam) {
		Object[] nuevo = new Object[]{nom, ext, fecha, tam};
		addRow(nuevo);
	}
	
	public void addCarpeta(String nom, String ext, LocalDate fecha) {
		Object[] nuevo = new Object[]{nom, ext, fecha};
		addRow(nuevo);
	}
	
	public void eliminar(int pos){
		removeRow(pos);
	}
	
	public void modificar(int pos, String nom, String ext, LocalDate fecha, double tam){
		Object[] newRow = new Object[]{nom, ext, fecha, tam};
		setValueAt(nom, pos, 0);
		setValueAt(ext, pos, 1);
		setValueAt(fecha, pos, 2);
		setValueAt(tam, pos, 3);			
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


