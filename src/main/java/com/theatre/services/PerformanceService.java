package com.theatre.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theatre.dao.PerformanceDAO;
import com.theatre.dao.SessionDAO;
import com.theatre.models.PerformanceDTO;

@Service
public class PerformanceService {
	@Autowired
	private PerformanceDAO performanceDAO;
	@Autowired
	private SessionDAO sessionDAO;
	
	@Transactional
	public List<PerformanceDTO> getPerformanceLIst() {
		return performanceDAO.getAllPerformances();
	}
	@Transactional
	public PerformanceDTO getPerformanceById(long id) {
		return performanceDAO.getPerformanceById(id);
	}
	@Transactional
	public boolean addPerformance(PerformanceDTO performance) {
		return performanceDAO.addPerformance(performance);
	}
	@Transactional
	public boolean deletePerformance(long id) {
		sessionDAO.deleteAllPerformanceSessions(id);
		return performanceDAO.deletePerformanceById(id);
	}
	public boolean editPerformance(PerformanceDTO performance) {
		return performanceDAO.editPerformance(performance);
	}
}
