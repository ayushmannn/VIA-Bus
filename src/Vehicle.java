public class Vehicle implements java.io.Serializable
{
   
   private String location;
   private String registration;
   private String model;
   private String type;
   private int capacity;
	private static final long serialVersionUID = 6529685098267757690L;
   //-----------------------------------------------
   public Vehicle()
   {

   }
   
   
   //-----------------------------------------------
   public String getLocation()
   {
      return location;
   }
   public void setLocation(String location)
   {
      this.location = location;
   }
   public String getRegistration()
   {
      return registration;
   }
   public void setRegistration(String registration)
   {
      this.registration = registration;
   }
   public String getModel()
   {
      return model;
   }
   public void setModel(String model)
   {
      this.model = model;
   }
   public String getType()
   {
      return type;
   }
   public void setType(String type)
   {
      this.type = type;
   }
   public int getCapacity()
   {
      return capacity;
   }
   public void setCapacity(int capacity)
   {
      this.capacity = capacity;
   }
   
	public Vehicle copy() {
		Vehicle vehicleCopy = new Vehicle();
		vehicleCopy.location = this.location;
		vehicleCopy.registration = this.registration;
		vehicleCopy.model = this.model;
		vehicleCopy.type = this.type;
		vehicleCopy.capacity = this.capacity;
		return vehicleCopy;
	}
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (capacity != other.capacity)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (registration == null) {
			if (other.registration != null)
				return false;
		} else if (!registration.equals(other.registration))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return model;
	}
   //------------------------------------------------
   
}
