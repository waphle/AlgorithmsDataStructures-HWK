
public class Plane {
	
	public enum OperationType {
		Unknown,
		Landing,
		Tackoff
	}
	
	// Constructor
	public Plane() {
		Id = -1;
		airlineNo = "";
		opType = OperationType.Unknown;
		waitingTime = 0;
	}
	
	public Plane(int id, String no, OperationType opt) {
		Id = id;
		airlineNo = no;
		opType = opt;
		waitingTime = 0;
	}
	
	// Methods
	int getId() {
		return Id;
	}
	
	OperationType getOptType() {
		return opType;
	}
	
	String getAirlineNo() {
		return airlineNo;
	}
	
	int getWaitingTime() {
		return waitingTime;
	}
	
	void setWaitingTime(int time) {
		waitingTime = time;
	}
	
	void increaseWaitingTime(int time) {
		waitingTime += time;
	}
	
	// Data
	private int Id;
	private String airlineNo;
	private OperationType opType;
	private int waitingTime;
}
