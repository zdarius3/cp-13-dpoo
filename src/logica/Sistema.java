package logica;

import java.util.ArrayList;

public class Sistema {
	private double capacidad;
	private Carpeta raiz;

	public Sistema(double capacidad, Carpeta raiz) {
		super();
		setCapacidad(capacidad);
		setRaiz(raiz);
	}

	public double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}

	public Carpeta getRaiz() {
		return raiz;
	}

	public void setRaiz(Carpeta raiz) {
		this.raiz = raiz;
	}

	public void addElemento(Elemento e) {
		raiz.addElemento(e);
	}

	//inciso c
	public double getTamTotal() {
		return raiz.getTamanno();
	}

	//inciso d
	public int getCantFicherosPrimCarpeta(String extFichero, String nomCarpeta, String extCarpeta) {
		return raiz.contarFicherosEnCarpetaEsp(extFichero, nomCarpeta, extCarpeta);
	}
	
	//inciso e
	public ArrayList<Fichero> getFicherosMenorTamEnRaiz() {
		return raiz.getFicherosMenorTam();
	}
	
	
	//inciso f
	public ArrayList<Fichero> getTodosFicherosMenorTam() {
		return raiz.getTodosFicherosMenorTam();
	}
	
	//para la interfaz
	public ArrayList<String> getExtensionesFicheros() {
		return raiz.getExtTodosLosFicheros();
	}
	
	public ArrayList<String> getExtensionesCarpetas() {
		return raiz.getExtTodasCarpetas();
	}
	
	public ArrayList<String> getNombresCarpetas() {
		return raiz.getNombresTodasCarpetas();
	}
	
	
}
