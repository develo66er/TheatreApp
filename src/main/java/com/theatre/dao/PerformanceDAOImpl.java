package com.theatre.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.theatre.mappers.PerformanceMapper;
import com.theatre.models.PerformanceDTO;

@Repository
public class PerformanceDAOImpl implements PerformanceDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public PerformanceDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public List<PerformanceDTO> getAllPerformances(){
		List<PerformanceDTO> list = this.jdbcTemplate.query("SELECT * FROM PERFORMANCES", new PerformanceMapper());
		return list;
	}
	
	@Override
	public boolean addPerformance(PerformanceDTO performance) {
		int i = this.jdbcTemplate.update("INSERT INTO performances (PERFORMANCE_name, PERFORMANCE_start_date, PERFORMANCE_end_date) VALUES (?, ?, ?)", performance.getName(), performance.getStartDate(),performance.getEndDate());
		return i==1;
	}
	@Override
	public boolean deletePerformanceById(long id) {
		int i = this.jdbcTemplate.update("delete from performances where performance_id=?", id);
		return i==1;
	}
	@Override
	public boolean editPerformance(PerformanceDTO performance) {
		int i = this.jdbcTemplate.update("update performances set PERFORMANCE_name=?, PERFORMANCE_start_date=?, PERFORMANCE_end_date=? where performance_id=?", performance.getName(), performance.getStartDate(),performance.getEndDate(),performance.getId());
		return i>0;
	}
	@Override
	public PerformanceDTO getPerformanceById(long id) {
		List<PerformanceDTO> list = this.jdbcTemplate.query("SELECT * FROM PERFORMANCES where performance_id=?",new Object[] {id}, new PerformanceMapper());
		if(list == null || list.isEmpty()) return null;
		return list.get(0);
	}

}
