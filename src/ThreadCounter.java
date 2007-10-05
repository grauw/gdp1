
public class ThreadCounter {

	int threadCount = 0; 	//De variabele voor het huidige aantal runnende threads
	int threadMax = 4;		//De variabele voor het maximum aantal runnende threads
	
	public synchronized int get() {
		while (threadCount >= threadMax) {
			try {
				this.wait();
			} catch(Exception e) {
			}
		}
		return threadCount++;
	}
	
	public synchronized void release() {
		threadCount--;
		this.notify();
	}
	
	public synchronized void notifyCount(int count) {
		for (int i = 0; i < count; i++) {
			this.notify();
		}
		/*Deze methode hebben we gemaakt om voor een aantal threads een notify() te doen
		 *Hierdoor hoeven we geen gebruik te maken van een notifyAll()
		 * */
	}
	
}
