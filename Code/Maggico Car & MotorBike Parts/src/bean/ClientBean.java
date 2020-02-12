package bean;

public class ClientBean extends UserBean {

	private String iban;	
	
	public ClientBean() {
		super();
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
	
	public String toString() {
		return "[Username: " + super.getUsername() + "][email: " + super.getEmail() + "][password: " + super.getPassword() + "]" ;
	}
	
	
	
	
	
}
