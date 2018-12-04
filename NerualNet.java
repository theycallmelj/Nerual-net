import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class NerualNet{

	
	
	private int sizeOfHiddenLayer;
	private int numOfHiddenLayers;
	
	private ArrayList<OSMNode> firstNodes =new ArrayList<OSMNode>();
	
	
	
	private HashMap<Integer,OSMNode[]> hiddenNodes = new HashMap<Integer,OSMNode[]>();
	
	private OSMNode[] outputNodes;
	
	
	private ArrayList<OSMRoad> firstRoad = new ArrayList<OSMRoad>();
	
	private HashMap<Integer,ArrayList<OSMRoad>>  hiddenRoad = new HashMap<Integer,ArrayList<OSMRoad>>();
	
	
	private ArrayList<OSMRoad> secondRoad = new ArrayList<OSMRoad>();
	
	
	private TwoThousandFourtyEight board;
	
	
	/**
	 * 
	 * @param par1 size of each layer
	 * @param par2 number of each layer 
	 * @param par3 board
	 * @param par4 learning mode
	 * @param par5 File path
	 * @param par6 index
	 */
	public NerualNet(int par1, int par2, TwoThousandFourtyEight par3, boolean par4, String par5, int par6) {
		
		setSizeOfHiddenLayer(par1);
		setNumOfHiddenLayers(par2);
		
		board = par3;
		Random rand = new Random();
		
		ReaderAndWriter.createDirectory(par5+"/learning/"+par6+"/"+"roads");
				
		
		outputNodes = new OSMNode[4];
		int[][] a = par3.getBoard();
		int h = 0;
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				firstNodes.add(h, new OSMNode((double)a[i][j]/par3.getHighestTile(), h, "input"));
				h++;
			}
			
		}
		
		
		if(!par4) {
			int b = 16;
			OSMNode[] nodes = new OSMNode[par1];
			for(int i = 0; i < par2; i++) {
				nodes =new OSMNode[par1];
				
				for(int j = 0; j < par1; j++) {
					double val = 0;
					
					nodes[j] =new OSMNode(val, b, "hidden");
					b++;
				}
				hiddenNodes.put(i, nodes);
			}
			
			for(int i = 0; i < 4; i++) {
				double val = 0;
				outputNodes[i] =  new OSMNode(val, b+i, "output");
			}
			
			int RoadNum = 0;
			for(OSMNode i: firstNodes) {
				
				
				for(OSMNode j: hiddenNodes.get(0)) {
					OSMNode[] nd = new OSMNode[2];
					nd[0] = i;
					nd[1] = j;
					double val= Double.parseDouble(ReaderAndWriter.read(par5+"learned/roads/"+RoadNum+".txt"));
					firstRoad.add(new OSMRoad(nd,val/Ai2048.maxRand, RoadNum));
					RoadNum++;
					
				}
			}
			
			//Hidden Layers input
			for(int i = 1; i < hiddenNodes.size() - 1; i++) {
				
				ArrayList<OSMRoad> roads = new ArrayList<OSMRoad>();
				//System.out.println(roads.size());
				
				for(OSMNode nodelayer2 : hiddenNodes.get(i)) {
					for(OSMNode nodelayer1 : hiddenNodes.get(i-1)) {
						OSMNode[] nd = new OSMNode[2];
						nd[0] = nodelayer1;
						nd[1] = nodelayer2;
						double val= Double.parseDouble(ReaderAndWriter.read(par5+"learned/roads/"+RoadNum+".txt"));
						roads.add(new OSMRoad(nd,val/Ai2048.maxRand, RoadNum));
						RoadNum++;
					}
				}
				//System.out.println(roads.size());
				hiddenRoad.put(i, roads);
					//System.out.println(val);
				
			}
			
			
			
			for(OSMNode i: hiddenNodes.get(hiddenNodes.size() -1)) {
				
				
				for(OSMNode j: outputNodes) {
					OSMNode[] nd = new OSMNode[2];
					nd[0] = i;
					nd[1] = j;
					double val= Double.parseDouble(ReaderAndWriter.read(par5+"learned/roads/"+RoadNum+".txt"));
					secondRoad.add(new OSMRoad(nd,val/Ai2048.maxRand, RoadNum));
					
					//System.out.println(val);
					RoadNum++;
				}
			}
		
		}
		else {
			
			int b = 16;
			OSMNode[] nodes = new OSMNode[par1];
			for(int i = 0; i < par2; i++) {
				nodes =new OSMNode[par1];
				
				for(int j = 0; j < par1; j++) {
					double val = 0;
					
					nodes[j] =new OSMNode(val, b, "hidden");
					b++;
				}
				hiddenNodes.put(i, nodes);
			}
			
			for(int i = 0; i < 4; i++) {
				double val = 0;
				outputNodes[i] =  new OSMNode(val, b+i, "output");
			}
			
			int RoadNum = 0;
			for(OSMNode i: firstNodes) {
				
				
				for(OSMNode j: hiddenNodes.get(0)) {
					OSMNode[] nd = new OSMNode[2];
					nd[0] = i;
					nd[1] = j;
					int val= rand.nextInt(Ai2048.maxRand);
					ReaderAndWriter.write(par5+"/learning/"+par6+"/"+"roads"+"/"+RoadNum +".txt", String.valueOf(val));
					firstRoad.add(new OSMRoad(nd,val/Ai2048.maxRand, RoadNum));
					RoadNum++;
					
				}
			}
			
			//Hidden Layers input
			for(int i = 1; i < hiddenNodes.size() - 1; i++) {
				
				ArrayList<OSMRoad> roads = new ArrayList<OSMRoad>();
				//System.out.println(roads.size());
				
				for(OSMNode nodelayer2 : hiddenNodes.get(i)) {
					for(OSMNode nodelayer1 : hiddenNodes.get(i-1)) {
						OSMNode[] nd = new OSMNode[2];
						nd[0] = nodelayer1;
						nd[1] = nodelayer2;
						int val= rand.nextInt(Ai2048.maxRand);
						ReaderAndWriter.write(par5+"/learning/"+par6+"/"+"roads"+"/"+RoadNum +".txt", String.valueOf(val));
						roads.add(new OSMRoad(nd,val/Ai2048.maxRand, RoadNum));
						RoadNum++;
					}
				}
				//System.out.println(roads.size());
				hiddenRoad.put(i, roads);
					//System.out.println(val);
				
			}
			
			
			
			for(OSMNode i: hiddenNodes.get(hiddenNodes.size() -1)) {
				
				
				for(OSMNode j: outputNodes) {
					OSMNode[] nd = new OSMNode[2];
					nd[0] = i;
					nd[1] = j;
					int val= rand.nextInt(Ai2048.maxRand);
					ReaderAndWriter.write(par5+"/learning/"+par6+"/"+"roads"+"/"+RoadNum +".txt", String.valueOf(val));
					secondRoad.add(new OSMRoad(nd,val/Ai2048.maxRand, RoadNum));
					RoadNum++;
					//System.out.println(val);
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * 0 = left, 1 = right, 2= up, 3 =down
	 */
	public int makeDecision() {
		
		double[] valuesAtHiddenNodes = new double[hiddenNodes.get(0).length];
		
		
		
		for(int i = 0; i < hiddenNodes.get(0).length; i++) {
			for(int j = 0; j < firstNodes.size(); j++) {
				
				//double val = firstNodes.get(j).getLon()*hiddenNodes.get(i).getLon();
				double roadVal= firstNodes.get(j).getLon() * getFirstRoad(firstNodes.get(j),hiddenNodes.get(0)[i]).getWidth();
				valuesAtHiddenNodes[i] += roadVal;
			}
			hiddenNodes.get(0)[i].setLon(valuesAtHiddenNodes[i]);
		}
		
		double[] valuesAtHiddenNodesMutilayer = new double[sizeOfHiddenLayer];
		for(int x = 1; x < hiddenNodes.size() - 1; x++) {
			
			for(OSMNode i : hiddenNodes.get(x)) {
				for(OSMNode j : hiddenNodes.get(x-1)) {
					
					//double val = firstNodes.get(j).getLon()*hiddenNodes.get(i).getLon();
					
					
					double priorval = j.getLon();
					double val = 0.1;
					if(getHiddenRoad(j,i) != null) {
						val = getHiddenRoad(j,i).getWidth();
					}
					double roadVal= priorval  * val;
					
					valuesAtHiddenNodesMutilayer[x] += roadVal;
					
				}
				hiddenNodes.get(x)[x].setLon(valuesAtHiddenNodesMutilayer[x]);
				
			}
		}
		
		
		
		HashMap<Integer, Double>valuesAtOutputNodes = new HashMap<Integer, Double>();
		for(int i = 0;i < outputNodes.length; i++) {
			valuesAtOutputNodes.put(i,0.0);
		}
		
		for(int i = 0; i < outputNodes.length; i++) {
			for(int j = 0; j < hiddenNodes.size()-1; j++) {
				double val = valuesAtHiddenNodes[j];
				
				double val2 = getSecondRoad(hiddenNodes.get(hiddenNodes.size()-1)[j] , outputNodes[i]).getWidth();
				
				valuesAtOutputNodes.put(i, val*val2);
			}
		}
		double max = 0;
		int index = 0;
		
		
		for(int i = 0; i< valuesAtOutputNodes.size(); i++) {
			if(valuesAtOutputNodes.get(i) > max) {
				max = valuesAtOutputNodes.get(i);
				
				index = i;
			}
			
			//System.out.println(valuesAtOutputNodes.get(i));
		}
		
		return index;
	}
	
	public OSMRoad getFirstRoad(OSMNode n1, OSMNode n2) {
		int i =0;
		while(!firstRoad.get(i).containsNode(n1) && !firstRoad.get(i).containsNode(n2)) {
			i++;
		}
		
		return firstRoad.get(i);
	}
	
	public OSMRoad getHiddenRoad(OSMNode n1, OSMNode n2) {
		
		int i;
		
		//System.out.println("HERE" + hiddenRoad.get(0).length);
		for(i = 1; i< hiddenRoad.size(); i++) {
			for(OSMRoad j : hiddenRoad.get(i)){
				if(j.containsNode(n1) && j.containsNode(n2))
					return j;
					
			}
			
		}
		return null;
	}
	
	public OSMRoad getSecondRoad(OSMNode n1, OSMNode n2) {
		int i =0;
		while(!secondRoad.get(i).containsNode(n1) && !secondRoad.get(i).containsNode(n2)) {
			i++;
		}
		
		return secondRoad.get(i);
	}


	public int getNumOfHiddenLayers() {
		return numOfHiddenLayers;
	}




	public void setNumOfHiddenLayers(int numOfHiddenLayers) {
		this.numOfHiddenLayers = numOfHiddenLayers;
	}




	public int getSizeOfHiddenLayer() {
		return sizeOfHiddenLayer;
	}

	public void move(TwoThousandFourtyEight b) {
		
		
	switch(makeDecision()) {
		case 0:
			b.left();
		case 1:
			b.right();
		case 2:
			b.up();
		case 3:
			b.down();
	
	}
	ArrayList<OSMNode> ns = new ArrayList<OSMNode>();
	int k =0;
	for(int i = 0; i < board.getBoard().length; i++) {
		for(int j = 0; j < board.getBoard()[i].length; j++) {
			ns.add(new OSMNode(b.getBoard()[i][j], k, "input"));
			k++;
		}
	}	
		
		
		
	}

	
	

	public void setSizeOfHiddenLayer(int sizeOfHiddenLayer) {
		this.sizeOfHiddenLayer = sizeOfHiddenLayer;
	}

	public void setInputNodes(ArrayList<OSMNode> nodes) {
		this.firstNodes = nodes;
	}


	public OSMNode[] getOutputNodes() {
		return outputNodes;
	}
	
	public HashMap<Integer, OSMNode[]> getHiddenNodes() {
		return hiddenNodes;
	}
	public ArrayList<OSMNode> getInputNodes() {
		return firstNodes;
	}

	public TwoThousandFourtyEight getBoard() {
		return board;
	}
	public void setBoard(TwoThousandFourtyEight b) {
		board = b;
	}
}
