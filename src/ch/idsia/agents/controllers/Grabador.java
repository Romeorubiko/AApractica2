package ch.idsia.agents.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;

public class Grabador {
	
	/*Necesita grabrt:
	1-Toda la informacion del estado en el que se encuentra Mario en ese tick concreto.
	2-El valor de las celdas alrededor de Mario devuelta por getMergedObservationZ(). Todas las celdas
	deben ir en la misma lnea, sin saltos de lnea.
	3-El numero de monedas recogidas y enemigos eliminados hace 5 ticks (aparte de incluir en la lnea tambien
	el numero de monedas y enemigos del tick actual)
	*/
	static final String output = "ejemplo.arff";
	static int detalle = 1;
	static int ticks = 0;
	static int saltoSeguido=0;
	
	static Queue<Integer> hace24reward = new LinkedList<Integer>();
	static Queue<Integer> hace24distance = new LinkedList<Integer>();
	static Queue<boolean[]> action24 = new LinkedList<boolean[]>();
	static Queue<byte[][]> hace24merge = new LinkedList<byte[][]>();
	
	static void grabar(MarioEnvironment e, boolean[] action, FileWriter fichero) {
		hace24reward.add(e.getIntermediateReward());
		hace24distance.add(e.getEvaluationInfo().distancePassedCells);
		action24.add(action.clone());
		hace24merge.add(e.getMergedObservationZZ(detalle, detalle));
		if(ticks < 24) {
			ticks++;
			}
		else {
			action = action24.poll();
			Queue tempList = new LinkedList<>(hace24reward);
			
			PrintWriter pw = new PrintWriter(fichero);
			
			byte[][] temp = hace24merge.poll();
			for (int i = 5; i < 10; i++) {
				for (int j = 10; j < 13; j++) {
					pw.print(temp[i][j]+",");
				}
			}
			pw.print(hace24reward.peek()+",");
			pw.print(nearestCoin(temp)+",");
			pw.print(nearestCreature(temp)+",");
			
			pw.print(hace24distance.peek()+",");

//			pw.print(e.getEvaluationInfo().totalNumberOfCoins-e.getEvaluationInfo().coinsGained+",");
//			pw.print(e.getEvaluationInfo().totalNumberOfCreatures-e.getEvaluationInfo().killsTotal+",");
			
			pw.print(saltoSeguido+",");
			
			if (temp[10][9]!=0)pw.print("true,");
			else pw.print("false,");
			
			for (int i = 0; i < 5; i++) tempList.poll();
			pw.print(tempList.poll()+",");
			for (int i = 0; i < 5; i++) tempList.poll();
			pw.print(tempList.poll()+",");
			for (int i = 0; i < 11; i++) tempList.poll();
			pw.print(tempList.poll()+",");
			hace24reward.poll();
			
			tempList = new LinkedList<>(hace24distance);
			for (int i = 0; i < 5; i++) tempList.poll();
			pw.print(tempList.poll()+",");
			for (int i = 0; i < 5; i++) tempList.poll();
			pw.print(tempList.poll()+",");
			for (int i = 0; i < 11; i++) tempList.poll();
			pw.print(tempList.poll()+",");
			hace24distance.poll();
			
			/*pw.print(action[Mario.KEY_LEFT]+",");
			pw.print(action[Mario.KEY_DOWN]+",");
			pw.print(action[Mario.KEY_SPEED]+",");
			pw.print(action[Mario.KEY_UP]+",");*/
			
//			//predicciones
//			//n+6
//			pw.print((int)(1.0013*e.getIntermediateReward()-27.2653)+",");
//			pw.print((int)(-3.474*(e.getEvaluationInfo().totalNumberOfCoins-e.getEvaluationInfo().coinsGained)
//				-57.75*(e.getEvaluationInfo().totalNumberOfCreatures-e.getEvaluationInfo().killsTotal)
//				+3648.3821)+",");
//			//n+12
//			pw.print((int)(-3.4591*(e.getEvaluationInfo().totalNumberOfCoins-e.getEvaluationInfo().coinsGained)
//			-57.6444*(e.getEvaluationInfo().totalNumberOfCreatures-e.getEvaluationInfo().killsTotal)
//			+3649.5482)+",");
//			pw.print((int)((1.0013*e.getIntermediateReward()-27.2653)*0.998+10.0473)+",");
//			//n+24
//			pw.print((int)(1.0002*e.getIntermediateReward()-1.6031)+",");
//			pw.print((int)(-3.192*(e.getEvaluationInfo().totalNumberOfCoins-e.getEvaluationInfo().coinsGained)
//				-57.2895*(e.getEvaluationInfo().totalNumberOfCreatures-e.getEvaluationInfo().killsTotal)
//				+3643.8188)+",");
//			pw.print((int)(((1.0013*e.getIntermediateReward()-27.2653)*0.998+10.0473)*0.9948+21.1029)+",");
//			
			pw.print(e.getMarioMode()+",");
			
			Instancia ins = new Instancia();
			//Todo añadir los atributos necesarios para que la instancia se evalue
			pw.print(Funcion.evaluacion(ins)+",");	
			
			if (action[Mario.KEY_RIGHT])pw.print(1);
			else pw.print(0);
			if (action[Mario.KEY_JUMP])pw.print(1);
			else pw.print(0);
			
			if(action[Mario.KEY_JUMP])saltoSeguido++;
			else saltoSeguido=0;
			pw.println();
			ticks ++; 
		}
		
	}
	
	static int nearestCoin(byte[][] e) {
		byte[][] temp = e;
		if(temp[9][10]==2)return 1;
		else if(temp[8][10]==2||temp[10][10]==2||temp[9][11]==2)return 2;
		else if(temp[11][10]==2||temp[10][11]==2||temp[9][12]==2||temp[8][11]==2||temp[7][10]==2)return 3;
		else if(temp[12][10]==2||temp[11][11]==2||temp[10][12]==2||temp[8][12]==2||temp[7][11]==2||temp[6][10]==2||temp[9][14]==2)return 4;
		else if(temp[13][10]==2||temp[12][11]==2||temp[11][12]==2||temp[10][13]==2||temp[9][14]==2||temp[9][13]==2||temp[7][12]==2||temp[6][11]==2||temp[5][10]==2)return 5;
		else if(temp[13][11]==2||temp[12][12]==2||temp[11][13]==2||temp[10][14]==2||temp[9][15]==2||temp[9][14]==2||temp[7][13]==2||temp[6][12]==2||temp[5][14]==2)return 6;
		else if(temp[13][12]==2||temp[12][13]==2||temp[11][14]==2||temp[10][15]==2||temp[8][15]==2||temp[7][14]==2||temp[6][13]==2||temp[5][15]==2)return 7;
		else if(temp[13][13]==2||temp[12][14]==2||temp[11][15]==2||temp[7][15]==2||temp[6][14]==2||temp[5][13]==2)return 8;
		else if(temp[13][14]==2||temp[12][15]==2||temp[6][15]==2||temp[5][14]==2)return 9;
		else if(temp[13][15]==2||temp[5][15]==2)return 10;
		return 11;
	}
	
	static int nearestCreature(byte[][] e) {
		byte[][] temp = e;
		if(temp[9][10]==80)return 1;
		else if(temp[8][10]==80||temp[10][10]==80||temp[9][11]==80)return 2;
		else if(temp[11][10]==80||temp[10][11]==80||temp[9][12]==80||temp[8][11]==80||temp[7][10]==80)return 3;
		else if(temp[12][10]==80||temp[11][11]==80||temp[10][12]==80||temp[8][12]==80||temp[7][11]==80||temp[6][10]==80||temp[9][14]==80)return 4;
		else if(temp[13][10]==80||temp[12][11]==80||temp[11][12]==80||temp[10][13]==80||temp[9][14]==80||temp[9][13]==80||temp[7][12]==80||temp[6][11]==80||temp[5][10]==80)return 5;
		else if(temp[13][11]==80||temp[12][12]==80||temp[11][13]==80||temp[10][14]==80||temp[9][15]==80||temp[9][14]==80||temp[7][13]==80||temp[6][12]==80||temp[5][14]==80)return 6;
		else if(temp[13][12]==80||temp[12][13]==80||temp[11][14]==80||temp[10][15]==80||temp[8][15]==80||temp[7][14]==80||temp[6][13]==80||temp[5][15]==80)return 7;
		else if(temp[13][13]==80||temp[12][14]==80||temp[11][15]==80||temp[7][15]==80||temp[6][14]==80||temp[5][13]==80)return 8;
		else if(temp[13][14]==80||temp[12][15]==80||temp[6][15]==80||temp[5][14]==80)return 9;
		else if(temp[13][15]==80||temp[5][15]==80)return 10;
		return 11;
	}
	
	
	static void borrarUltimaLinea(String path){
		try {
 
        File inFile = new File(path);	
        
        if (!inFile.isFile()) {
            System.out.println("no hay archivo");
            return;
        }
 
        //Construct the new file that will later be renamed to the original filename.
        File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
 
        BufferedReader br = new BufferedReader(new FileReader(path));
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
 
        String line = br.readLine();
        String next = br.readLine();
 
        //Read from the original file and write to the new
        //unless content matches data to be removed.
        while ((next) != null) {
        		
                pw.println(line);
                pw.flush();
                line = next;
                next = br.readLine();
                
        }
        
        pw.close();
        br.close();
 
        //Delete the original file
        if (!inFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }
 
        //Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(inFile)){
            System.out.println("Could not rename file");
 
        }
    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
	}
	
	static void cabeceraWeka(FileWriter path, String id) {
		PrintWriter pw = new PrintWriter(path);
		pw.println("@relation " + id);
		pw.println();
		
		for (int i = 5; i < 10; i++) {
			for (int j =10; j < 13; j++) {
				pw.println("@attribute mergeObsZZ_"+i+"_"+j+" {-85,-24,-60,-62,0,2,3,25,80,93}");
			}
		}
		pw.println("@attribute intermediateReward numeric");
		pw.println("@attribute nearestCoin numeric");
		pw.println("@attribute nearestCreature numeric");
		pw.println("@attribute distancePassedCells numeric");
//		pw.println("@attribute coinsOnScreen numeric");
//		pw.println("@attribute creaturesOnScreen numeric");
		pw.println("@attribute saltoSeguido numeric");
		pw.println("@attribute marioOnGround {true,false}");
		/*pw.println("@attribute accion_LEFT {true,false}");
		pw.println("@attribute accion_DOWN {true,false}");
		pw.println("@attribute accion_SPEED {true,false}");
		pw.println("@attribute accion_UP {true,false}");*/
		pw.println("@attribute reward6 numeric");
		pw.println("@attribute reward12 numeric");
		pw.println("@attribute reward24 numeric");
		pw.println("@attribute distance6 numeric");
		pw.println("@attribute distance12 numeric");
		pw.println("@attribute distance24 numeric");
		/*pw.println("@attribute P1n6 numeric");
		pw.println("@attribute P2n6 numeric");
		pw.println("@attribute P2n12 numeric");
		pw.println("@attribute P3n12 numeric");
		pw.println("@attribute P1n24 numeric");
		pw.println("@attribute P2n24 numeric");
		pw.println("@attribute P3n24 numeric");*/
		pw.println("@attribute marioMode numeric");
		pw.println("@attribute evaluacion numeric");
		
		pw.println("@attribute RIGHT_JUMP {00,01,10,11}");
		pw.println();
		pw.println("@data");
		
	}

}
	
	
