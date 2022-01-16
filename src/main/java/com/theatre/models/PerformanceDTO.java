package com.theatre.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerformanceDTO {
	private long id;
	private String name;
	private Date startDate;
	private Date endDate;
	private List<SessionDTO> sessions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void addAllSessions(List<SessionDTO> sessionsList) {
		this.sessions = new ArrayList<SessionDTO>();
		this.sessions.addAll(sessionsList);
	}
	public List<SessionDTO> getAllSessions(){
		if(this.sessions==null) return null;
		List<SessionDTO> copy=new ArrayList<SessionDTO>(this.sessions.size());
		Collections.copy(copy, this.sessions);
		return copy;
	}
}
