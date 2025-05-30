package Main;

import java.time.LocalDate;
import java.util.ArrayList;

import logica.*;

public class Main {

	public static void main(String[] args) {
		Carpeta raiz = new Carpeta("Raíz", ".fdr", LocalDate.now());
		Sistema sistema = new Sistema(500, raiz);
		
		Carpeta c1 = new Carpeta("carpeta1", ".fdr", LocalDate.now());
		c1.addElemento(new Fichero("fichero1", ".txt", LocalDate.now(), 20.0));
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
		
		//inciso c
		System.out.println("La cantidad total de kilobytes en el dispositivo es de " + sistema.getTamTotal() 
				+ " kilobytes");
		
		//inciso d (falta pedirle al ususario)
		System.out.println("La cantidad de ficheros con extension .txt en la primera carpeta encontrada"
				+ " con extensión fdr y nombre carpeta2 es de: " +
				sistema.getCantFicherosPrimCarpeta(".txt", "carpeta2", ".fdr"));
		
		//inciso e
		ArrayList<Fichero> fMenorTam = sistema.getFicherosMenorTamEnRaiz();
		System.out.println("Los ficheros con menor tamaño en todo el sistema son: ");
		for (Fichero f: fMenorTam) {
			System.out.println(f.getNombre() + " de tamaño " + f.getTamanno() + " kilobytes");
		}
	}

}
