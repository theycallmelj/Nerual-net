
public class Timer extends Thread{
	@Override
	public void run() {
		try {
			//sleep(300000);
			this.currentThread().sleep(300000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			
		}
		ReaderAndWriter.readSoundFile("time.wav");
	}
}
