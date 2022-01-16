package com.theatre.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.theatre.models.SessionDTO;

public class SessionMapper implements RowMapper<SessionDTO> {

	@Override
	public SessionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setId(rs.getLong("SESSION_ID"));
		sessionDTO.setPerformanceId(rs.getLong("PERFORMANCE_ID"));
		sessionDTO.setStartDateTime(rs.getTimestamp("SESSION_START_DATETIME"));
		sessionDTO.setDuration(rs.getInt("SESSION_DURATION"));
		return sessionDTO;
	}

}
