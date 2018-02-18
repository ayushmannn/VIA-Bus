

public class Route implements java.io.Serializable {

	private String departure;
	private String destination;
	private Time duration;
	private int distance;
	private static final long serialVersionUID = 6529685098267757690L;

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Route() {
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public Time getDuration() {
		return duration;
	}

	public Route copy() {
		Route routeCopy = new Route();
		routeCopy.departure = this.departure;
		routeCopy.destination = this.destination;
		routeCopy.distance = this.distance;
		routeCopy.duration = this.duration;
		return routeCopy;
	}
	
	@Override
	public String toString() {
		return departure + "-" + destination;
	}
	// --------------------------------------
}