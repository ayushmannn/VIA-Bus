import java.util.ArrayList;
import java.util.UUID;

public class Reservation implements java.io.Serializable {

	private int price;
	private ArrayList<Passenger> passengers;
	private Customer customer;
	private String uniqueID;
	
	private static final long serialVersionUID = 6529685098267757690L;

	public Reservation() {
		uniqueID = UUID.randomUUID().toString();
	}

	
	
	
	public String getUniqueID() {
		return uniqueID;
	}


	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}


	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Reservation copy() {
		Reservation reservationCopy = new Reservation();
		reservationCopy.price = this.price;
		reservationCopy.passengers = this.passengers;
		reservationCopy.customer = this.customer;
		return reservationCopy;
	}

}
