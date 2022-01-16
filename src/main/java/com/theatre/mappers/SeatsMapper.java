package com.theatre.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.theatre.models.SeatsDTO;

public class SeatsMapper implements RowMapper<SeatsDTO> {

	@Override
	public SeatsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SeatsDTO seatsDTO = new SeatsDTO();
		seatsDTO.setId(rs.getLong("SEAT_ID"));
		seatsDTO.setHallId(rs.getLong("HALL_ID"));
		seatsDTO.setRowNumber(rs.getInt("ROW_NUMBER"));	
		seatsDTO.setSeatNumber(rs.getInt("SEAT_NUMBER"));		
		return seatsDTO;
	}

}
