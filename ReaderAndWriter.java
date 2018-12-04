import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ReaderAndWriter {

	public static String read(String filePath) {
		 File file = new File(filePath); 
		 Scanner sc;
		 String s = "";
		try {
			sc = new Scanner(file);
			 while (sc.hasNextLine()) { 
					s+=sc.nextLine();
				 } 
			 sc.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		 if(s.equals(""))
			 s= "0";
		
		 return s;
	}
	
	
	public static void write(String filePath, String content) {
			File file = new File(filePath);
		
			
			
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
			
			
		    
			    BufferedWriter writer;
				try {
					writer = new BufferedWriter(new FileWriter(file));
					writer.write(content);
					writer.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		    
		   
		    
		
	}
	
	public static void createDirectory(String folderName) {
		File dir = new File(folderName);
		if(!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	public static boolean learnedExists(String path) {
		File dir = new File(path + "learned");
			
		return dir.exists();
	}
	
	
	public static int filesInDir(String path) {
		File dir = new File(path);
		return dir.listFiles().length;
	}
	public static boolean fileExists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}
	
	/**
	 * in this case gets the folders in learing and returns avg
	 * @param filePathRead the base file path
	 * @param amountRan the amount read
	 * @return avg values
	 */
	public static ArrayList<Double> readLearned(String filePathRead, int amountRan) {
		
		File folder = new File(filePathRead + "learning/"+0+"/roads");
		
		
		
		
		
		
		
		ArrayList<Double> sum = new ArrayList<Double>();
		
		
		ArrayList<Double> avg = new ArrayList<Double>();	
		
		HashMap<Integer, ArrayList<Double>> roads = readLearnedRoads(filePathRead, amountRan);
		
		
		for(int i =0; i < roads.size();i++) {
			sum.add(0.0);	
		}
		
		//gets the sum of all the values for each road
		
		for(int i = 0; i < roads.size(); i++) {
			
			
			for(int j = 0; j < amountRan; j++) {
				
				
				
				sum.set(i, sum.get(i) + roads.get(i).get(j));
				
			}
			
		}
		
		
		//gets the avg
		for(int i = 0; i < sum.size(); i++) {
			avg.add(((double)sum.get(i))/amountRan);
			
		}
	    
	   return avg;
	    
	
}
	
	/**
	 * in this case gets the folders in learing and returns the standar deviation
	 * @param filePathRead the base file path
	 * @param amountRan the amount read
	 * @return avg values
	 */
	public static ArrayList<Double> stdOfEachRoad(String filePathRead, int amountRan) {
		
		
		
		ArrayList<Double> std = new ArrayList<Double>();
		
		ArrayList<Double> sum = new ArrayList<Double>();
		
		ArrayList<Double> avg = readLearned(filePathRead, amountRan);	
		
		
		HashMap<Integer, ArrayList<Double>> roads = readLearnedRoads(filePathRead, amountRan);
		
		
		for(int i =0; i < roads.size();i++) {
			sum.add(0.0);	
		}
		
		for(int i =0; i < avg.size(); i++) {
			
			for(int j = 0; j < roads.get(i).size(); j++) {
				sum.set(i, sum.get(i) + Math.pow(avg.get(i)-roads.get(i).get(j), 2));
			}
			
		}
		
		
		for(int i =0; i < avg.size(); i++) {
			
			double bessel = roads.get(i).size()-1;
			double before = sum.get(i) / bessel;
			std.add(Math.sqrt(before));
		}
		
	    
	   return std;
	    
	
}
	
	
	
	
	
	
	
public static HashMap<Integer, ArrayList<Double>> readLearnedRoads(String filePathRead, int amountRan) {
		
		File folder = new File(filePathRead + "learning/"+0+"/roads/");
		int amtRoad = folder.listFiles().length;
		
			
		
		HashMap<Integer, ArrayList<Double>> roads = new HashMap<Integer, ArrayList<Double>>();
		
		//reads the files for the road
		for(int j = 0; j < amtRoad; j++) {
				
			
			
			ArrayList<Double> a;
			
			if(roads.get(j) == null) {
				a = new ArrayList<Double>();
				roads.put(j, a);
			}
			else {
				a = roads.get(j);
			}
			
			for(int i = 0; i < amountRan; i++){
				
					double readVal = Double.parseDouble(read(filePathRead + "learning/"+i+"/roads/" + j +".txt"));
					
					a.add(readVal);
					roads.put(j, a);
					
			}	
				
				
		}
		
		
	    
	   return roads;
	    
	
}
	
	public static void readSoundFile(String fileName) {
		File f = new File(fileName);
		try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);
            clip.open(inputStream);
            clip.start(); 
        } catch (Exception e) {
            System.out.println("error");
            System.out.println(new File(fileName).exists());
        }
	}
	
	
}

