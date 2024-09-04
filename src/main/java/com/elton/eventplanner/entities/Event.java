package com.elton.eventplanner.entities;

import java.util.Date;
import java.util.Objects;

public class Event {
	
	private Long id;
	private String name;
	private Date date;
	private String local;
	private String description;
	
	private Adm adm;
	
	public Event() {
		
	}

	public Event(Long id, String name, Date date, String local, String description, Adm adm) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.local = local;
		this.description = description;
		this.adm = adm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Adm getAdm() {
		return adm;
	}

	public void setAdm(Adm adm) {
		this.adm = adm;
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
		Event other = (Event) obj;
		return Objects.equals(id, other.id);
	}
}
