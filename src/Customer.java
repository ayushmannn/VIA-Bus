import java.util.Date;

public class Customer extends Person implements java.io.Serializable {
	private Date dateBirth;
	private String email;
	private boolean newsletter;
	private String affiliation = "";
	private int frequency, discount;

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	private String status;

	public Customer(String firstName, String lastName, String address) {
		super(firstName, lastName, address);
		frequency = 0;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isNewsletter() {
		return newsletter;
	}

	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public Customer copy() {
		Customer copy = new Customer(firstName, lastName, address);
		copy.dateBirth = this.dateBirth;
		copy.email = this.email;
		copy.newsletter = this.newsletter;
		copy.frequency = this.frequency;
		copy.discount = this.discount;
		copy.status = this.status;
		copy.affiliation = this.affiliation;

		return copy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return super.toString()
				+ String.format(
						"DOB: %s, Email: %s, Newsletter: %s, Frequency: %s, Discount: %s, status: %s, aff: %s",
						getDateBirth(), getEmail(), isNewsletter(),
						getFrequency(), getDiscount(), getStatus(),
						getAffiliation());
	}

}
