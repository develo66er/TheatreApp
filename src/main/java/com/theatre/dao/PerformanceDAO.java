package com.theatre.dao;

import java.util.List;

import com.theatre.models.PerformanceDTO;

public interface PerformanceDAO {
		
	public List<PerformanceDTO> getAllPerformances();
	public boolean addPerformance(PerformanceDTO performance);
	public boolean deletePerformanceById(long id);
	public boolean editPerformance(PerformanceDTO performance);
	public PerformanceDTO getPerformanceById(long id);


}
