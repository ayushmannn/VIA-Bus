import java.util.ArrayList;

public class Chauffeur extends Person implements java.io.Serializable {
	private Vehicle vehicle;
	private String mobile;
	private String employeeId;
	private boolean available;
	private boolean onTrip;
	private boolean inTransit;
	private ArrayList<String> wishVehicle, wishService;
	private ArrayList<Schedule> schedule;
	private static final long serialVersionUID = 6529685098267757690L;
	
	public Chauffeur(String firstName, String lastName, String address)  {
		super(firstName, lastName, address);
		schedule = new ArrayList<Schedule>();
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;

	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isOnTrip() {
		return onTrip;
	}

	public void setOnTrip(boolean onTrip) {
		this.onTrip = onTrip;
	}

	public boolean isInTransit() {
		return inTransit;
	}

	public void setInTransit(boolean inTransit) {
		this.inTransit = inTransit;
	}

	public ArrayList<String> getWishVehicle() {
		return wishVehicle;
	}

	public void setWishVehicle(ArrayList<String> wishVehicle) {
		this.wishVehicle = wishVehicle;
	}

	public ArrayList<String> getWishService() {
		return wishService;
	}

	public void setWishService(ArrayList<String> wishService) {
		this.wishService = wishService;
	}

	public ArrayList<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(ArrayList<Schedule> schedule) {
		this.schedule = schedule;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Chauffeur copy() {
		Chauffeur chauffeurCopy = new Chauffeur(this.firstName, this.lastName, this.address);
		chauffeurCopy.vehicle = this.vehicle;
		chauffeurCopy.mobile = this.mobile;
		chauffeurCopy.employeeId = this.employeeId;
		chauffeurCopy.available = this.available;
		chauffeurCopy.onTrip = this.onTrip;
		chauffeurCopy.inTransit = this.inTransit;
		chauffeurCopy.wishVehicle = this.wishVehicle;
		chauffeurCopy.wishService = this.wishService;
		chauffeurCopy.schedule = this.schedule;
		return chauffeurCopy;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chauffeur other = (Chauffeur) obj;
		if (available != other.available)
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (inTransit != other.inTransit)
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (onTrip != other.onTrip)
			return false;
		if (schedule == null) {
			if (other.schedule != null)
				return false;
		} else if (!schedule.equals(other.schedule))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		if (wishService == null) {
			if (other.wishService != null)
				return false;
		} else if (!wishService.equals(other.wishService))
			return false;
		if (wishVehicle == null) {
			if (other.wishVehicle != null)
				return false;
		} else if (!wishVehicle.equals(other.wishVehicle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString();
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////
	


}
