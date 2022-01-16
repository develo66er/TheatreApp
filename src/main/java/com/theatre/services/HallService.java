package com.theatre.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theatre.dao.HallDAO;
import com.theatre.models.HallDTO;

@Service
public class HallService {
	@Autowired
	private HallDAO hallDAO;
	@Transactional
	public List<HallDTO> getAllHalls() {
		return hallDAO.getAllHalls();
	}
}
