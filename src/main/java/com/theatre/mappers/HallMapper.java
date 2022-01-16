package com.theatre.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.theatre.models.HallDTO;

public class HallMapper implements RowMapper<HallDTO> {

	@Override
	public HallDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		HallDTO hallDTO = new HallDTO();
		hallDTO.setId(rs.getLong("HALL_ID"));
		hallDTO.setName(rs.getString("HALL_NAME"));
		hallDTO.setRowsNumber(rs.getInt("ROWS_NUMBER"));	
		hallDTO.setSeatsNumber(rs.getInt("SEATS_NUMBER"));	
		return hallDTO;
	}

}
