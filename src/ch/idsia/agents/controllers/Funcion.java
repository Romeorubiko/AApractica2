package ch.idsia.agents.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Funcion {
	Instancia situaciones [];
	LinkedList<Instancia> pertenece [];
	String path;
	
	public Funcion() {
		initSituaciones();
		pertenece = new LinkedList[4];
		for (LinkedList<Instancia> linkedList : pertenece) {
			linkedList = new LinkedList<Instancia>();
		}
		
	}
	
	/**
	 * devuelve la posicion donde se encuenta la situcacion mas parecida y ademas la guarda en la estructura de datos
	 */
	public int pertenecia(Instancia ins) {//todo
		return 1;
	}
	/**
	 * devuelve la situacion qeu se parezca más en la lista que se ha pasado como parámetro
	 */
	public Instancia similitud(Instancia ins, Instancia[] list) {//todo
		return null;
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
					case 1:
						ins.merge5_10 = Integer.parseInt(valor);
						break;
					case 2:
						ins.merge5_11 = Integer.parseInt(valor);
						break;
					case 3:
						ins.merge5_12 = Integer.parseInt(valor);
						break;
					case 4:
						ins.merge6_10 = Integer.parseInt(valor);
						break;
					case 5:
						ins.merge6_11 = Integer.parseInt(valor);
						break;
					case 6:
						ins.merge6_12 = Integer.parseInt(valor);
						break;
					case 7:
						ins.merge7_10 = Integer.parseInt(valor);
						break;
					case 8:
						ins.merge7_11 = Integer.parseInt(valor);
						break;
					case 9:
						ins.merge7_12 = Integer.parseInt(valor);
						break;
					case 10:
						ins.merge8_10 = Integer.parseInt(valor);
						break;
					case 11:
						ins.merge8_11 = Integer.parseInt(valor);
						break;
					case 12:
						ins.merge8_12 = Integer.parseInt(valor);
						break;
					case 13:
						ins.merge9_10 = Integer.parseInt(valor);
						break;
					case 14:
						ins.merge9_11 = Integer.parseInt(valor);
						break;
					case 15:
						ins.merge9_12 = Integer.parseInt(valor);
						break;
					case 16:
						ins.reward = Integer.parseInt(valor);
						break;
					case 17:
						ins.nearestCoin = Integer.parseInt(valor);
						break;
					case 18:
						ins.nearestCreature = Integer.parseInt(valor);
						break;
					case 19:
						ins.distance = Integer.parseInt(valor);
						break;
					case 20:
						ins.saltoSeguido = Integer.parseInt(valor);
						break;
					case 21:
						ins.marioOnGorund = Boolean.parseBoolean(valor);
						break;
					case 22:
						ins.reward6 = Integer.parseInt(valor);
						break;
					case 23:
						ins.reward12 = Integer.parseInt(valor);
						break;
					case 24:
						ins.reward24 = Integer.parseInt(valor);
						break;
					case 25:
						ins.distance6 = Integer.parseInt(valor);
						break;
					case 26:
						ins.distance12 = Integer.parseInt(valor);
						break;
					case 27:
						ins.distance24 = Integer.parseInt(valor);
						break;
					case 28:
						ins.marioMode = Integer.parseInt(valor);
						break;
					case 29:
						ins.evaluacion = Integer.parseInt(valor);
						break;
					}
					valor = "";
				}
			}
			ins.right_jump=valor;
			
			pertenece[pertenecia(ins)].add(ins);
			line = br.readLine();
		}
		System.out.println("Indexado completado:");
		for (int i = 0; i < pertenece.length; i++) {
			System.out.println("Coincidencias con situacion "+i+": "+pertenece[i].size());
		}
		System.out.println("___________________________________________________________");

	}
	
	private void initSituaciones() {
		situaciones = new Instancia[4];
		
		//caso a
		Instancia temp = new Instancia();
		temp.nearestCreature = 3;
		temp.nearestCoin = 11;
		temp.merge9_10 = 80;
		temp.merge9_11 = 80;
		temp.merge9_12 = 80;
		temp.merge8_10 = 80;
		temp.merge8_11 = 80;
		temp.merge7_10 = 80;
		temp.merge5_10 = 0;
		temp.merge5_11 = 0;
		temp.merge5_12 = 0;
		temp.merge6_10 = 0;
		temp.merge6_11 = 0;
		temp.merge6_12 = 0;
		temp.merge7_11 = 0;
		temp.merge7_12 = 0;
		temp.merge8_12 = 0;
		situaciones[0] = temp;
		
		//caso b
		temp = new Instancia();
		temp.nearestCreature = 11;
		temp.nearestCoin = 3;
		temp.merge9_10 = 80;
		temp.merge9_11 = 80;
		temp.merge9_12 = 80;
		temp.merge8_10 = 80;
		temp.merge8_11 = 80;
		temp.merge7_10 = 80;
		temp.merge5_10 = 0;
		temp.merge5_11 = 0;
		temp.merge5_12 = 0;
		temp.merge6_10 = 0;
		temp.merge6_11 = 0;
		temp.merge6_12 = 0;
		temp.merge7_11 = 0;
		temp.merge7_12 = 0;
		temp.merge8_12 = 0;
		situaciones[1] = temp;

		//caso c
		temp = new Instancia();
		temp.nearestCreature = 11;
		temp.nearestCoin = 11;
		temp.merge9_10 = 80;
		temp.merge9_11 = 80;
		temp.merge9_12 = 80;
		temp.merge8_10 = 80;
		temp.merge8_11 = 80;
		temp.merge7_10 = 80;
		temp.merge5_10 = 0;
		temp.merge5_11 = 0;
		temp.merge5_12 = 0;
		temp.merge6_10 = 0;
		temp.merge6_11 = 0;
		temp.merge6_12 = 0;
		temp.merge7_11 = 0;
		temp.merge7_12 = 0;
		temp.merge8_12 = 0;
		situaciones[3] = temp;

		//caso d
		temp = new Instancia();
		temp.nearestCreature = 11;
		temp.nearestCoin = 11;
		temp.merge9_10 = -60;
		temp.merge9_11 = -60;
		temp.merge9_12 = -60;
		temp.merge8_10 = -60;
		temp.merge8_11 = -60;
		temp.merge7_10 = -60;
		temp.merge5_10 = 0;
		temp.merge5_11 = 0;
		temp.merge5_12 = 0;
		temp.merge6_10 = 0;
		temp.merge6_11 = 0;
		temp.merge6_12 = 0;
		temp.merge7_11 = 0;
		temp.merge7_12 = 0;
		temp.merge8_12 = 0;
		situaciones[4] = temp;
		
		
	}
	
	
}
