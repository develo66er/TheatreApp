package com.theatre.models;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

public class SessionDTO {
	private long id;
	private long performanceId;
	private Timestamp startDateTime;
	private int duration;
	private PerformanceDTO performance;
	private  String oldPerformanceId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPerformanceId() {
		return performanceId;
	}
	public void setPerformanceId(long performanceId) {
		this.performanceId = performanceId;
	}
	public Timestamp getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public PerformanceDTO getPerformance() {
		return performance;
	}
	public void setPerformance(PerformanceDTO performance) {
		this.performance = performance;
	}
	public String getOldPerformanceId() {
		return oldPerformanceId;
	}
	public void setOldPerformanceId(String oldPerformanceId) {
		this.oldPerformanceId = oldPerformanceId;
	}
	
}
