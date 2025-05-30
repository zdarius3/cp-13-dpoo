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
		ArrayList<Fichero> todosFicheros = getTodosLosFicheros();
		ArrayList<Fichero> ficherosMenorTam = new ArrayList<Fichero>();
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
		for (Elemento e: elementos) {
			if (e instanceof Fichero) {
				if (((Fichero)e).getTamanno() < menorTam) {
					menorTam = ((Fichero)e).getTamanno();
				}
			}
			else {
				for (Fichero f: ((Carpeta)e).getTodosLosFicheros()) {
					if (f.getTamanno() < menorTam) {
						menorTam = f.getTamanno();
					}
				}
			}
		}
		return menorTam;
	}

	public ArrayList<Fichero> getTodosLosFicheros() {
		ArrayList<Fichero> ficheros = new ArrayList<Fichero>();
		ArrayList<Fichero> ficherosTemp = new ArrayList<Fichero>();
		for (Elemento e: elementos) {
			if (e instanceof Fichero) {
				ficheros.add((Fichero)e);
			}
			else {
				ficherosTemp = ((Carpeta)e).getTodosLosFicheros();
				for(int i = 0; i < ficherosTemp.size(); i++) {
					ficheros.add(ficherosTemp.get(i));
				}
			}
		}
		return ficheros;
	}
}


