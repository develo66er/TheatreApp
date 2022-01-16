package com.theatre.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theatre.dao.SessionDAO;
import com.theatre.models.SessionDTO;

@Service
public class SessionService {
	@Autowired
	private SessionDAO sessionDAO;
	
	@Transactional
	public List<SessionDTO> getSessionsList() {
		return sessionDAO.getAllSessions();
	}
	
	@Transactional
	public List<SessionDTO> getPerformanceSessionsList(long performanceId) {
		return sessionDAO.getAllSessionsForPerformance(performanceId);
	}
	
	@Transactional
	public boolean addSession(SessionDTO session) {
		return sessionDAO.addSession(session);
	}
	@Transactional
	public boolean deleteSession(long id) {
		return sessionDAO.deleteSession(id);
	}
	@Transactional
	public boolean deleteallPerformanceSessions(long performanceId) {
		return sessionDAO.deleteAllPerformanceSessions(performanceId);
	}
	@Transactional
	public boolean editSession(long oldPerformanceId, SessionDTO session) {
		return sessionDAO.editSession(oldPerformanceId,session);
	}
}
