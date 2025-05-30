package logica;

import java.time.LocalDate;

public abstract class Elemento {
	private String nombre;
	private String extension;
	private LocalDate fechaActualizacion;
	
	public Elemento(String nombre, String extension, LocalDate fechaActualizacion) {
		setNombre(nombre);
		setExtension(extension);
		setFechaActualizacion(fechaActualizacion);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public LocalDate getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDate fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	//inciso c
	public abstract double getTamanno();
}
