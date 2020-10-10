import java.util.Random;

public class JeffsAirportSimu {
	
	// Constants
	private static int simulationTime = 120;
	private static int timeUnit = 5;
	private static int runwayTime = 10;
	private static int landingQueueLength = 9;
	private static int takeoffQueueLength = 9;
	private static int maxPlanesOneTime = 1;
	
	// Resources
	private static Runway runway1 = new Runway(runwayTime);
	private static Runway runway2 = new Runway(runwayTime);
	
	private static PlaneQueueManager queueMgr = new PlaneQueueManager(landingQueueLength, takeoffQueueLength);

	private static Random randArrival = new Random();
	private static Random randDeparture = new Random();
	
	// Global variables
	public static int startHour = 7;
	public static int startMinute = 0;
	public static int nPlanesProcessed = 0;
	public static int idleTime = 0;
	public static int landingWait = 0;
	public static int takeoffWait = 0;
	public static int nLandeds = 0;
	public static int nTakeoffs = 0;
	public static int nRefuseds = 0;
	
	public static void main(String[] args) {
		
		// Simulation loop
		int timeElapsed = 0;
		int planeSequenceNum = 0;
		while (timeElapsed < simulationTime)
		{
			// Operate landing or tacking off using runway #1
			Plane [] landedPlan = new Plane[2], departedPlane = new Plane[2];
			for (Plane p: landedPlan) {
				p = new Plane();
			}
			
			for (Plane p: departedPlane) {
				p = new Plane();
			}

			if (!runway1.isOccupied()) {
				// If the landing queue is not empty, then landing one plane
				if (!queueMgr.isLandingQueueEmpty()) {
					landedPlan[0] = queueMgr.pollLandingQueue();
					landingWait += landedPlan[0].getWaitingTime();
					nLandeds++;
				}
				else { // if the landing queue is empty, then take off one plane
					if (!queueMgr.isTakeoffQueueEmpty()) {
						departedPlane[0] = queueMgr.pollTakeoffQueue();
						takeoffWait += departedPlane[0].getWaitingTime();
						nTakeoffs++;
					}
				}
			}
			
			// Operate landing or tacking off using runway #2
			if (!runway2.isOccupied()) {
				// If the landing queue is not empty, then landing one plane using runway #1
				if (!queueMgr.isLandingQueueEmpty()) {
					landedPlan[1] = queueMgr.pollLandingQueue();
					landingWait += landedPlan[1].getWaitingTime();
					nLandeds++;
				}
				else { // if the landing queue is empty, then take off one plane using runway #1
					if (!queueMgr.isTakeoffQueueEmpty()) {
						departedPlane[1] = queueMgr.pollTakeoffQueue();
						takeoffWait += departedPlane[1].getWaitingTime();
						nTakeoffs++;
					}
				}
			}
			
			// Simulate new planes
			int nLandingPlanes = randArrival.nextInt(maxPlanesOneTime + 1);
			int nTakeoffPlanes = randDeparture.nextInt(maxPlanesOneTime + 1);
			int nVacancies = queueMgr.getLandingQueueVacancyNum();
			
			// Update refused planes' record
			if (nLandingPlanes > nVacancies) {
				nRefuseds += nLandingPlanes - nVacancies;
			}
			
			// Add arriving planes to the landing queue
			if (nVacancies > 0) {
				for (int i = 0; i < nVacancies; i++, nLandingPlanes--) {
					if (nLandingPlanes > 0) {
						Plane plane = new Plane(planeSequenceNum, "", Plane.OperationType.Landing);
						queueMgr.addToLandingQueue(plane);
						planeSequenceNum++;
						nPlanesProcessed++;
					}
				}
			}
			
			// Update refused planes' record
			nVacancies = queueMgr.getTakeoffQueueVacancyNum();
			if (nTakeoffPlanes > nVacancies) {
				nRefuseds += nTakeoffPlanes - nVacancies;
			}
			
			// Add departing planes to the departure queue
			if (nVacancies > 0) {
				for (int i = 0; i < nVacancies; i++, nTakeoffPlanes--) {
					if (nTakeoffPlanes > 0) {
						Plane plane = new Plane(planeSequenceNum, "", Plane.OperationType.Tackoff);
						queueMgr.addToTakeoffQueue(plane);
						planeSequenceNum++;
						nPlanesProcessed++;
					}	
				}
			}
			
			// Update the runways' statuses
			runway1.increaseOccupiedTime(timeUnit);
			runway2.increaseOccupiedTime(timeUnit);
			
			// Update the queues
			queueMgr.updateQueues(timeUnit);
			
			// Accumulate idle time
			if (queueMgr.isLandingQueueEmpty()) {
				idleTime += timeUnit;
			}
			
			if (queueMgr.isTakeoffQueueEmpty()) {
				idleTime += timeUnit;
			}
			
			// Print statistics for the current step
			int hour = startHour + (startMinute + timeElapsed) / 60;
			int minute = (startMinute + timeElapsed) % 60;
			
			System.out.printf("The time is %2d:%2d.\n", hour, minute);
			System.out.printf("There are %d planes waiting to land.\n", queueMgr.getLandingQueue().size());
			int runwayNum = 1;
			for (Plane p: landedPlan) {
				if (null != p && p.getId() > 0) {
					System.out.printf("Plane #%2d is cleared to land using runway #%d.\n", p.getId(), runwayNum);
				}
				runwayNum++;
			}
			
			System.out.printf("There are %d planes waiting to land.\n", queueMgr.getTakeofQueue().size());
			runwayNum = 1;
			for (Plane p: departedPlane) {
				if (null != p && p.getId() > 0) {
					System.out.printf("Plane #%2d is cleared to take off using runway #%d.\n", p.getId(), runwayNum);
				}
				runwayNum++;
			}
			
			System.out.println("");
			
			timeElapsed += timeUnit;
		}
		
		// Finalize the waiting times
		for (Plane p: queueMgr.getLandingQueue()) {
			landingWait += p.getWaitingTime();
		}
		for (Plane p: queueMgr.getTakeofQueue()) {
			takeoffWait += p.getWaitingTime();
		}
		
		// Print overall simulation statistics
		System.out.printf("Total number of planes processed: %d\n", nPlanesProcessed);
		System.out.printf("Number of planes landed: %d\n", nLandeds);
		System.out.printf("Number of planes taken off: %d\n", nTakeoffs);
		System.out.printf("Number of planes refused: %d\n", nRefuseds);
		System.out.printf("Number of planes left ready to land: %d\n", queueMgr.getLandingQueue().size());
		System.out.printf("Number of planes left ready to take off: %d\n", queueMgr.getTakeofQueue().size());
		System.out.printf("Percentage of time the runway was idle: %.2f\n", (float)idleTime/(2*simulationTime));
		System.out.printf("Average wait time to land: %.1f min\n", (float)landingWait/nLandeds);
		System.out.printf("Average wait time to take off: %.1f min\n", (float)takeoffWait/nTakeoffs);
	}
}
