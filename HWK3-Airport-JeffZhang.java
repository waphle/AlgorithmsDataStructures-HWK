import java.util.Random;

public class JeffsAirportSimu {
	private int simulationTime = 120;
	private int timeUnit = 5;
	private int runwayTime = 15;
	private int landingQueueLength = 6;
	private int takeoffQueueLength = 6;
	private int maxPlanesOneTime = 3;
	
	private Runway runway1 = new Runway(runwayTime);
	private Runway runway2 = new Runway(runwayTime);
	
	private PlaneQueueManager queueMgr = new PlaneQueueManager(landingQueueLength, maxPlanesOneTime);

	private Random randArrival = new Random(maxPlanesOneTime);
	private Random randDeparture = new Random(maxPlanesOneTime);
	
	public int nPlanesProcessed = 0;
	public int idleTime = 0;
	public int landingWait = 0;
	public int takeoffWait = 0;
	public int nLandeds = 0;
	public int nTakeoffs = 0;
	public int nRefuseds = 0;
	
	public void main(String[] args) {
		
		// Simulation loop
		int timeElapsed = 0;
		while (timeElapsed < simulationTime)
		{
			timeElapsed += timeUnit;
		}
	}

}