package logica;

import java.time.LocalDate;

public class Fichero extends Elemento {
	private double tamanno;

	public Fichero(String nombre, String extension, LocalDate fechaActualizacion, double tamanno) {
		super(nombre, extension, fechaActualizacion);
		setTamanno(tamanno);
	}

	//inciso c
	@Override 
	public double getTamanno() {
		return tamanno;
	}

	public void setTamanno(double tamanno) {
		this.tamanno = tamanno;
	}
	

}
