package com.theatre.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.theatre.models.PerformanceDTO;

public class PerformanceMapper implements RowMapper<PerformanceDTO> {

	@Override
	public PerformanceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PerformanceDTO performanceDTO = new PerformanceDTO();
		performanceDTO.setId(rs.getLong("PERFORMANCE_ID"));
		performanceDTO.setName(rs.getString("PERFORMANCE_NAME"));
		performanceDTO.setStartDate(rs.getDate("PERFORMANCE_START_DATE"));
		performanceDTO.setEndDate(rs.getDate("PERFORMANCE_END_DATE"));
		return performanceDTO;
	}

}
