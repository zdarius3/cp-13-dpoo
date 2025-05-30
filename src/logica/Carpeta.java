package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Carpeta extends Elemento {
	private ArrayList<Elemento> elementos;

	public Carpeta(String nombre, String extension, LocalDate fechaActualizacion) {
		super(nombre, extension, fechaActualizacion);
		elementos = new ArrayList<Elemento>();
	}

	public ArrayList<Elemento> getElementos() {
		return elementos;
	}

	public void addElemento(Elemento e) {
		elementos.add(e);
	}

	//inciso c
	@Override
	public double getTamanno() { 
		double total = 0;
		for (Elemento e: elementos) {
			total += e.getTamanno();
		}
		return total;
	}

	//inciso d
	public int contarFicherosEnCarpetaEsp(String extFichero, String nomCarpeta, String extCarpeta) {
		int cantFicheros = 0;
		Carpeta encontrada = buscarPrimeraCarpeta(nomCarpeta, extCarpeta);

		for (Elemento e2: encontrada.getElementos()) {
			if (e2 instanceof Fichero) {
				if (e2.getExtension().equals(extFichero)) {
					cantFicheros++;
				}
			}
		}
		return cantFicheros;
	}

	public Carpeta buscarPrimeraCarpeta(String nombre, String extension) {
		Carpeta encontrada = new Carpeta("null", "null", LocalDate.now());
		for (Elemento e: elementos) {
			if (e instanceof Carpeta) {
				if (e.getExtension().equals(extension) &&  e.getNombre().equals(nombre)) {
					encontrada = (Carpeta) e;
				}
			}
		}
		return encontrada;
	}

	//inciso e
	public ArrayList<Fichero> getFicherosMenorTam() {
		ArrayList<Fichero> ficherosMenorTam = new ArrayList<Fichero>();
		double menorTam = Double.MAX_VALUE;
		for (Elemento e: elementos) {
			if (e instanceof Fichero) {
				if (((Fichero)e).getTamanno() < menorTam) {
					ficherosMenorTam.clear();
					ficherosMenorTam.add((Fichero)e);
					menorTam = ((Fichero)e).getTamanno();
				}
				else if (((Fichero)e).getTamanno() == menorTam) {
					ficherosMenorTam.add((Fichero)e);
				}
			}
		}
		return ficherosMenorTam;
	}

	//inciso f
	public ArrayList<Fichero> getTodosFicherosMenorTam2() {
		ArrayList<Fichero> ficherosMenorTam = new ArrayList<Fichero>();
		double menorTam = Double.MAX_VALUE;
		for (Elemento e: elementos) {
			if (e instanceof Fichero) {
				if (((Fichero)e).getTamanno() < menorTam) {
					ficherosMenorTam.clear();
					ficherosMenorTam.add((Fichero)e);
					menorTam = ((Fichero)e).getTamanno();
				}
				else if(((Fichero)e).getTamanno() == menorTam) {
					ficherosMenorTam.add((Fichero)e);
				}
			}
			else {
				ArrayList<Fichero> ficherosTemp = ((Carpeta)e).getTodosFicherosMenorTam();
				for (Fichero f: ficherosTemp) {
					ficherosMenorTam.add(f);
				}
			}
		}
		return ficherosMenorTam;
	}


	//inciso f mal
	public ArrayList<Fichero> getTodosFicherosMenorTam() {
		ArrayList<Fichero> ficherosMenorTam = new ArrayList<Fichero>();

		ArrayList<Fichero> todosFicheros = getTodosLosFicheros();
		double menorTam = buscarMenorTamFichero();

		for (Fichero f: todosFicheros) {
			if (f.getTamanno() == menorTam) {
				ficherosMenorTam.add(f);
			}
		}
		return ficherosMenorTam;
	}

	public double buscarMenorTamFichero() {
		double menorTam = Double.MAX_VALUE;
		for (Fichero f: getTodosLosFicheros()) {
			if (f.getTamanno() < menorTam) {
				menorTam = f.getTamanno();
			}
		}
		return menorTam;
	}

	public ArrayList<Fichero> getTodosLosFicheros() {
		ArrayList<Fichero> todosFicheros = new ArrayList<Fichero>();
		ArrayList<Fichero> ficherosTemp = new ArrayList<Fichero>();
		for (Elemento e: elementos) {
			if (e instanceof Fichero) {
				todosFicheros.add((Fichero)e);
			}
			else {
				ficherosTemp = ((Carpeta)e).getTodosLosFicheros();
				for(Fichero f: ficherosTemp) {
					todosFicheros.add(f);
				}
			}
		}
		return todosFicheros;
	}
}


