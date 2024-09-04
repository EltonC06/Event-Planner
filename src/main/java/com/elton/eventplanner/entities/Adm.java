package com.elton.eventplanner.entities;

import java.util.Objects;

public class Adm {
	
	private Long id;
	private String password;
	
	public Adm() {
		
	}

	public Adm(Long id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adm other = (Adm) obj;
		return Objects.equals(id, other.id);
	}
}
