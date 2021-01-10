package com.ejbank.api.payload;

public class UserPayload {
	private final String firstname;
	private final String lastname;

	public UserPayload(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

}
