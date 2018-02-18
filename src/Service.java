import java.util.ArrayList;

public class Service implements java.io.Serializable {

	private String service;
	private Route route;
	private Chauffeur chauffeur;
	private boolean availability;
	private Schedule schedule;
	private ArrayList<Reservation> reservations;
	
	private static final long serialVersionUID = 6529685098267757690L;
	
	// --------------------------------------
	public Service() {
		
		reservations = new ArrayList<Reservation>();
	}

	// --------------------------------------

	public String getService() {
		return service;
	}


	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;

	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Service copy() {
		Service serviceCopy = new Service();
		serviceCopy.service = this.service;
		serviceCopy.route = this.route;
		serviceCopy.chauffeur = this.chauffeur;
		serviceCopy.availability = this.availability;
		serviceCopy.schedule = this.schedule;
		serviceCopy.reservations = this.reservations;
		return serviceCopy;
	}

}
