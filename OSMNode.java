public class OSMNode{
   
      //Add the fields of this class here 
   private long id;
   private double lon;
   private String layer;
   

	public OSMNode(long _id) {
      //Constructor 
    
     id =  _id;
     
	}

	public OSMNode(double value, long _id, String par3) {
      //Constructor 
    lon = value;
    id =  _id;
    layer = par3;
   
	}

	public double getLon() {
		return lon;
	}

	

	public long getId() {
		return id;
	}

	
	public void setNumRoads(int nr) {
   
      //***This method is optional***
      //Sets number of roads passing from that node

	}
   public void setValue(double nlon) {
      //This method sets new value to value field
      
         lon = nlon;
	}

	
	public int getNumRoads() {
      //***This method is optional***
      //This method returns num nodes paased from that node
		return 0;
	}

 	void addRoad(OSMRoad r) {
       //***This method is optional***
       //This method adds road r to the list of roads passing from this node   
 	}

	public boolean equals(OSMNode n) {
       //Checks if this node is equal to node n
		return n.getId() == this.id;
	}

	public void setLon(double d) {
		// TODO Auto-generated method stub
		this.lon = d;
		
	}
}
