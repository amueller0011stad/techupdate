package de.ssc.restjpa.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bank {
    
	private long id;
	private int bankNumber;
	private String description;
	private String serverAdress;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(int bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getServerAdress() {
		return serverAdress;
	}
	public void setServerAdress(String serverAdress) {
		this.serverAdress = serverAdress;
	}
}
