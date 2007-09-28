
public class ThreadCounter {

	int threadCount = 0;
	int threadMax = 4;
	
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
	}
	
}
