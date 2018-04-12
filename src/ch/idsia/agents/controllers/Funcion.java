package ch.idsia.agents.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Funcion {
	Instancia[] situaciones;
	LinkedList<Instancia>[] pertenece;
	String path;
	
	public Funcion() {
		//todo: Inicializar los atributos
	}
	
	/**
	 * devuelve la posicion donde se encuenta la situcacion mas parecida y ademas la guarda en la estructura de datos
	 */
	public int pertenecia(Instancia ins) {//todo
		return -1;
	}
	/**
	 * devuelve la situacion qeu se parezca más en la lista que se ha pasado como parámetro
	 */
	public Instancia similitud(Instancia ins, Instancia[] list) {//todo
		return -1;
	}
	/**
	 * devuelve el valor que ha salido del resultado de aplicar la fórmula
	 */
	static public int evaluacion(Instancia ins) {//todo
		return -1;
	}
	
	public void indexar(String path) throws IOException {
		// rellenar el pertenece que es la estructura donde vamo s a indexar
		this.path = path;
		//abrimos el fichero para su lectura
		BufferedReader br = new BufferedReader(new FileReader(path));     
		if (br.readLine() == null) {
			br.close();
			System.out.println("Error al indexar, archivo vacio");
		}
		//Llegamos a la zona de datos, que es la que nos importa 
		String line = "";
		while(!line.contains("@data"))line = br.readLine();
		line = br.readLine(); 
		//leemos datos 
		line = br.readLine();
		int contador = 0;
		while(line != null) {
			Instancia ins = new Instancia();
			String valor = "";
			for (char c : line.toCharArray()) {
				if(c!=',')valor = valor + c;
				else {
					contador++;
					switch (contador) {//todo añadir la posicion de los parametros que nos interesan y guardarlo en ins
					case 0:
						
						break;
					}
					valor = "";
				}
			}
			ins.right_jump=valor;
			
			pertenece[pertenecia(ins, situaciones)].add(ins);
			line = br.readLine();
		}
		System.out.println("Indexado completado:");
		for (int i = 0; i < pertenece.length; i++) {
			System.out.println("Coincidencias con situacion "+i+": "+pertenece[i].size());
		}
		System.out.println("___________________________________________________________");

	}
	
	
}
