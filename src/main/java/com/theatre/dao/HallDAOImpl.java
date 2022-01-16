package com.theatre.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.theatre.mappers.HallMapper;
import com.theatre.models.HallDTO;

@Repository
public class HallDAOImpl implements HallDAO {
	private JdbcTemplate jdbcTemplate;

	public HallDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<HallDTO> getAllHalls() {
		List<HallDTO> list = this.jdbcTemplate.query("select * from halls", new HallMapper());
		return list;
	}

}
