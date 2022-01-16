package com.theatre.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theatre.dao.SeatsDAO;
import com.theatre.models.SeatsDTO;

@Service
public class SeatsService {
	@Autowired
	private SeatsDAO seatsDAO;
	
	@Transactional
	public List<SeatsDTO> getSeatsForHall(long hallId) {
		return seatsDAO.getSeatsForHall(hallId);
	}
	
}
