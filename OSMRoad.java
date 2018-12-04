public class OSMRoad{
   
   //Add the fileds here 
      
	private OSMNode[] nodes = {};
	private double weight;
	private int num;
	public OSMRoad(OSMNode[] ns, double val, int num) {
   //Constructor 
      
      nodes = ns;
      weight = val;
      this.num = num;
	}
   
	public OSMRoad() {
		
	}

	public int getNum() {
		return num;
	}
	
	
	
	public OSMNode getNode(int nth) {
   //Returns the Nth node of the road
      if(nth < nodes.length){
         return nodes[nth];
      }
      else{
         return null;
      }
   
			
	}

	public int getNumNodes() {
   //Returns number of nodes in the road
		return nodes.length;
	}

	
	public boolean containsNode(OSMNode n) {
   //Checks if node n exist in this road or not
		for(OSMNode i: nodes){
         if(i.equals(n)){
            return true;
         }
      }
      
      return false;
	}

	public OSMNode intersects(OSMRoad r) {
   //Returns the intersection node of this road and road r
   
      if(this == null || r == null){
         return null;
      }
      
		for(int i = 0; i < r.getNumNodes(); i++){
         if(this.containsNode(r.getNode(i))){
            return r.getNode(i);
         }
      
      }
      return null;
	}

	public OSMNode[] adjacentNodes(OSMNode n) {
   /*Optional method
   Returning a list of adjacent nodes by n in this road
   */
      
		return new OSMNode[]{};
      

   }

	public double getWidth() {
		// TODO Auto-generated method stub
		return this.weight;
	}
}