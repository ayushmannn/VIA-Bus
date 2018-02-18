import java.util.ArrayList;
import java.util.Date;


public class Passenger extends Person implements java.io.Serializable
{
   private Date dateBirth;
   private String email;
   private boolean newsletter;
   private ArrayList<String> specialServices;
   private static final long serialVersionUID = 6529685098267757690L;
	
   public Passenger(String firstName, String lastName, String address)
   {
      super(firstName, lastName, address);
      specialServices = new ArrayList<String>();
   }

   public ArrayList<String> getSpecialServices() {
	return specialServices;
}

public void setSpecialServices(ArrayList<String> specialServices) {
	this.specialServices = specialServices;
}

public Date getDateBirth()
   {   
      return dateBirth;
   }

   public void setDateBirth(Date dateBirth)
   {
      this.dateBirth = dateBirth;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public boolean isNewsletter()
   {
      return newsletter;
   }

   public void setNewsletter(boolean newsletter)
   {
      this.newsletter = newsletter;
   }

   
	public String reservationsToString() {
		String all ="";
		for (int i = 0; i < specialServices.size(); i++)
			if (i + 1 != specialServices.size())
				all += specialServices.get(i) + ", ";
			else
				all += specialServices.get(i);
		return all;
	}
   
	public Passenger copy() {
		Passenger passengerCopy = new Passenger(this.firstName, this.lastName, this.address);
		passengerCopy.dateBirth = this.dateBirth;
		passengerCopy.email = this.email;
		passengerCopy.newsletter = this.newsletter;
		passengerCopy.specialServices = this.specialServices;
		return passengerCopy;
	}
}
