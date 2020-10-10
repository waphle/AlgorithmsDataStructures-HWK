public class Runway {

	public Runway(int occupyTime) {
		occupiedTime = 0;
		occupied = false;
		maxOccupyTime = occupyTime;
	}
	
	// Methods 
	public int getOccupiedTime() {
		return occupiedTime;
	}
	
	public void increaseOccupiedTime(int t) {
		if (t <= 0) {
			return;
		}
		else if (occupiedTime + t < maxOccupyTime) {
			occupiedTime += t;
			occupied = true;
		}
		else {
			occupiedTime = 0;
			occupied = false;
		}
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public void updateOccupyState(boolean o) {
		occupied = o;
	}
	
	// Data
	private int occupiedTime;
	private boolean occupied;
	private int maxOccupyTime;
}
