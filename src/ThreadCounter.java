/**
 * ThreadCounter-klasse.
 * 
 * Deze klasse implementeert een teller die het maximum aantal lopende Quicksort-threads begrenst.
 * 
 * @author Milan de Graaf (3117308),
 *         Laurens Holst (0122173)
 */
public class ThreadCounter {

	int threadCount = 0; 	//De variabele voor het huidige aantal runnende threads
	int threadMax = 4;		//De variabele voor het maximum aantal runnende threads
	
	/**
	 * Verkrijgt een threadnummer, en wacht als het aantal threads aan het maximum zit.
	 * @return Het threadnummer.
	 */
	public synchronized int get() {
		while (threadCount >= threadMax) {
			try {
				this.wait();
			} catch(Exception e) {
			}
		}
		return threadCount++;
	}
	
	/**
	 * Geeft de thread vrij.
	 */
	public synchronized void release() {
		threadCount--;
		this.notify();
	}
	
	/**
	 * Deze methode doet voor een aantal threads een notify().
	 * Hierdoor hoeven we geen gebruik te maken van een notifyAll().
	 * @param count Aantal threads om te notifien.
	 */
	public synchronized void notifyCount(int count) {
		for (int i = 0; i < count; i++) {
			this.notify();
		}
	}
	
}
