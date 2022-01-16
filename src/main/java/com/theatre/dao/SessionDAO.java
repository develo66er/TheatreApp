package com.theatre.dao;

import java.util.List;

import com.theatre.models.PerformanceDTO;
import com.theatre.models.SessionDTO;

public interface SessionDAO {
		
	public List<SessionDTO> getAllSessions();
	public List<SessionDTO> getAllSessionsForPerformance(long performanceId);
	public boolean addSession(SessionDTO session);
	public boolean deleteSession( long id);
	public boolean deleteAllPerformanceSessions(long performanceId);
	public boolean editSession(long oldPerformanceId, SessionDTO session);
}
