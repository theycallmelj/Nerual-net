import javax.swing.JOptionPane;

public class Speak extends Thread{
	public static int interation = 0;
	
	@Override
	public void run() {
			
	
		//String ans = JOptionPane.showInputDialog("I might be a while. Want to play ticktacktoe? Yes/no");
		
		
		//2Timer t= new Timer();
	
		while(AiMain2048.going) {
			
			Play.play();
			/**Thing t= new Thing();
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			**/
			
		}
	
	
	}
	
	
	
}
