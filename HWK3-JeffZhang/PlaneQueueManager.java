import java.util.LinkedList;
import java.util.Queue;

public class PlaneQueueManager {

	public PlaneQueueManager(int maxLandings, int maxTakeoffs) {
		landingQueueSize = maxLandings;
		takeoffQueueSize = maxTakeoffs;
	}

	// Methods
	public int getMaxLandingNum() {
		return landingQueueSize;
	}
	
	public int getMaxTakeoffNum() {
		return takeoffQueueSize;
	}
	
	public int getLandingQueueVacancyNum() {
		return landingQueueSize - landingQueue.size();
	}
	
	public int getTakeoffQueueVacancyNum() {
		return takeoffQueueSize - takeoffQueue.size();
	}
	
	public Queue<Plane> getLandingQueue() {
		return landingQueue;
	}
	
	public Queue<Plane> getTakeofQueue() {
		return takeoffQueue;
	}
	
	public boolean addToLandingQueue(Plane plane) {
		if (landingQueue.size() < landingQueueSize) {
			return landingQueue.offer(plane);
		}
		else {
			return false;
		}
	}
	
	public boolean addToTakeoffQueue(Plane plane) {
		if (takeoffQueue.size() < takeoffQueueSize) {
			return takeoffQueue.offer(plane);
		}
		else {
			return false;
		}
	}
	
	public Plane pollLandingQueue() {
		return landingQueue.poll();
	}
	
	public Plane pollTakeoffQueue() {
		return takeoffQueue.poll();
	}
	
	public Plane peekLandingQueue() {
		return landingQueue.peek();
	}
	
	public Plane peekTakeoffQueue() {
		return takeoffQueue.peek();
	}
	
	public boolean isLandingQueueFull() {
		return (landingQueue.size() == landingQueueSize);
	}
	
	public boolean isTakeoffQueueFull() {
		return (takeoffQueue.size() == takeoffQueueSize);
	}
	
	public boolean isLandingQueueEmpty() {
		return (landingQueue.size() == 0);
	}
	
	public boolean isTakeoffQueueEmpty() {
		return (takeoffQueue.size() == 0);
	}
	
	public void updateQueues(int t) {
		// Update the planes in the landing queue
		for (Plane p: landingQueue) {
			p.increaseWaitingTime(t);
		}
		
		// Update the planes in the taking off queue
		for (Plane p: takeoffQueue) {
			p.increaseWaitingTime(t);
		}
	}
	
	// Data
	private Queue<Plane> landingQueue = new LinkedList<Plane>();
	private Queue<Plane> takeoffQueue = new LinkedList<Plane>();
	private int landingQueueSize;
	private int takeoffQueueSize;
}
