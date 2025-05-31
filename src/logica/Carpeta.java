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
		Carpeta encontrada = buscarPrimeraCarpeta(elementos, nomCarpeta, extCarpeta);

		if (encontrada != null) {
			for (Elemento e2: encontrada.getElementos()) {
				if (e2 instanceof Fichero) {
					if (e2.getExtension().equals(extFichero)) {
						cantFicheros++;
					}
				}
			}
		}
		else {
			throw new NullPointerException("No se encontró una carpeta así");
		}
		return cantFicheros;
	}

	private Carpeta buscarPrimeraCarpeta(ArrayList<Elemento> elementos, String nombre, String extension) {
		boolean encont = false;
		Carpeta aux = null;
		Carpeta encontrada = null;
		Carpeta encontradaInterior = null;
		int i = 0;
		while(!encont && i < elementos.size()) {
			if (elementos.get(i) instanceof Carpeta) {
				aux = (Carpeta) elementos.get(i);
				if (aux.getNombre().equals(nombre) && aux.getExtension().equals(extension)) {
					encontrada = aux;
					encont = true;
				}
				if (!encont) {
					encontradaInterior = buscarPrimeraCarpeta(aux.getElementos(), nombre, extension);
					if (encontradaInterior != null) {
						encontrada = encontradaInterior;
					}
				}
			}
			i++;
		}
		return encontrada;
	}


	//inciso e
	public ArrayList<Fichero> getFicherosMenorTam() {
		ArrayList<Fichero> ficherosMenorTam = new ArrayList<Fichero>();
		double menorTam= Double.MAX_VALUE;
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
	public ArrayList<Fichero> getTodosFicherosMenorTam() {
		ArrayList<Fichero> ficherosMenorTam = new ArrayList<Fichero>();
		double menorTam[] = {Double.MAX_VALUE};
		getFicherosMenorTamEnCarpeta(this, ficherosMenorTam, menorTam);
		return ficherosMenorTam;
	}

	private ArrayList<Fichero> getFicherosMenorTamEnCarpeta(Carpeta carpeta, ArrayList<Fichero> ficherosMenorTam, 
			double[] menorTam) {
		for (Elemento e: carpeta.elementos) {
			if (e instanceof Fichero) {
				if (((Fichero)e).getTamanno() < menorTam[0]) {
					ficherosMenorTam.clear();
					ficherosMenorTam.add((Fichero)e);
					menorTam[0] = ((Fichero)e).getTamanno();
				}
				else if(((Fichero)e).getTamanno() == menorTam[0]) {
					ficherosMenorTam.add((Fichero)e);
				}
			}
			else {
				getFicherosMenorTamEnCarpeta((Carpeta)e, ficherosMenorTam, menorTam);
			}
		}
		return ficherosMenorTam;
	}



	/*               inciso f mal
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
	 */

	//para la interfaz
	private ArrayList<Fichero> getTodosLosFicheros() {
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

	public ArrayList<String> getExtTodosLosFicheros() {
		ArrayList<String> extensiones = new ArrayList<String>();
		boolean yaContiene;
		for (Fichero f: getTodosLosFicheros()) {
			yaContiene = false;
			for(String s: extensiones) {
				if (s.equals(f.getExtension())) {
					yaContiene = true;
				}
			}
			if(!yaContiene) {
				extensiones.add(f.getExtension());
			}
		}
		return extensiones;
	}

	public ArrayList<String> getNombresTodasCarpetas() {
		ArrayList<String> nombres = new ArrayList<String>();
		getNombresCarpetasEnCarpeta(this, nombres);
		return nombres;
	}

	private ArrayList<String> getNombresCarpetasEnCarpeta(Carpeta carpeta, ArrayList<String> nombres) {
		boolean yaContiene;
		for (Elemento e: carpeta.getElementos()) {
			if (e instanceof Carpeta) {
				yaContiene = false;
				for(String s: nombres) {
					if (s.equals(((Carpeta)e).getNombre())) {
						yaContiene = true;
					}
				}
				if(!yaContiene) {
					nombres.add(((Carpeta)e).getNombre());
				}
				getNombresCarpetasEnCarpeta((Carpeta)e, nombres);
			}
		}
		return nombres;
	}

	public ArrayList<String> getExtTodasCarpetas() {
		ArrayList<String> extensiones = new ArrayList<String>();
		getExtCarpetasEnCarpeta(this, extensiones);
		return extensiones;
	}

	private ArrayList<String> getExtCarpetasEnCarpeta(Carpeta carpeta, ArrayList<String> extensiones) {
		boolean yaContiene;
		for (Elemento e: carpeta.getElementos()) {
			if (e instanceof Carpeta) {
				yaContiene = false;
				for(String s: extensiones) {
					if (s.equals(((Carpeta)e).getExtension())) {
						yaContiene = true;
					}
				}
				if(!yaContiene) {
					extensiones.add(((Carpeta)e).getExtension());
				}
				getExtCarpetasEnCarpeta((Carpeta)e, extensiones);
			}
		}
		return extensiones;
	}
}


