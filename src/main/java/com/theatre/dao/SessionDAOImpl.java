package com.theatre.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.theatre.mappers.SessionMapper;
import com.theatre.models.SessionDTO;

@Repository
public class SessionDAOImpl implements SessionDAO {
	private JdbcTemplate jdbcTemplate;

	public SessionDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<SessionDTO> getAllSessions() {
		List<SessionDTO> list = this.jdbcTemplate.query("SELECT * FROM SESSIONS", new SessionMapper());
		return list;
	}

	@Override
	public List<SessionDTO> getAllSessionsForPerformance(long performanceId) {
		List<SessionDTO> list = this.jdbcTemplate.query("SELECT * FROM SESSIONS where PERFORMANCE_ID=?",
				new Object[] { performanceId }, new SessionMapper());
		return list;
	}

	@Override
	public boolean addSession(SessionDTO session) {
		int i = this.jdbcTemplate.update(
				"INSERT INTO sessions (PERFORMANCE_id, session_start_datetime, session_duration) VALUES (?, ?, ?)",
				session.getPerformanceId(), session.getStartDateTime(), session.getDuration());
		return i == 1;
	}

	@Override
	public boolean deleteSession(long id) {
		int i = this.jdbcTemplate.update("delete from sessions where session_id=?", id);
		return i == 1;
	}

	@Override
	public boolean deleteAllPerformanceSessions(long performanceId) {
		int i = this.jdbcTemplate.update("delete from sessions where PERFORMANCE_id=?", performanceId);
		return i > 0;
	}

	@Override
	public boolean editSession(long oldPerformanceId, SessionDTO session) {
		
			int i = this.jdbcTemplate.update(
					"update sessions set PERFORMANCE_id = ?, session_start_datetime=?, session_duration=? where session_id=? and PERFORMANCE_id = ?",
					session.getPerformanceId(), session.getStartDateTime(), session.getDuration(), session.getId(),oldPerformanceId);

		
		return i == 1;
	}

}
