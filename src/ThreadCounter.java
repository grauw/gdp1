
public class ThreadCounter {

	int threadCount = 0;
	int threadMax = 100;
	
	public synchronized int get() {
		if (threadCount >= threadMax) {
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
	
}
