import java.util.Date;

public class Schedule implements java.io.Serializable {

	private Date dateStart;
	private Date dateEnd;
	private Time timeStart;
	private Time timeEnd;
	private Vehicle assignedVehicle;
	
	private static final long serialVersionUID = 6529685098267757690L;
	// --------------------------------------
	public Schedule(Date dateStart, Date dateEnd, Time timeStart, Time timeEnd) {
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		
	}

	// --------------------------------------
	public Date getDateStart() {
		return dateStart;
	}
	

	public Vehicle getAssignedVehicle() {
		return assignedVehicle;
	}

	public void setAssignedVehicle(Vehicle assignedVehicle) {
		this.assignedVehicle = assignedVehicle;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	public Time getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
	}

	public Time getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Time timeEnd) {
		this.timeEnd = timeEnd;
	}
	
	public Schedule copy() {
		Schedule scheduleCopy = new Schedule(this.dateStart, this.dateEnd, this.timeStart, this.timeEnd);
		scheduleCopy.assignedVehicle = this.assignedVehicle;
		return scheduleCopy;
	}

	@Override
	public String toString() {
		return "Schedule [dateStart=" + dateStart + ", dateEnd=" + dateEnd
				+ ", timeStart=" + timeStart + ", timeEnd=" + timeEnd
				+ ", assignedVehicle=" + assignedVehicle + "]";
	}
	
	
	
	// --------------------------------------

}
