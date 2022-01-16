package com.theatre.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.theatre.exceptions.SeatsEnoughException;
import com.theatre.mappers.SeatsMapper;
import com.theatre.mappers.SessionMapper;
import com.theatre.mappers.TicketsMapper;
import com.theatre.models.SeatsDTO;
import com.theatre.models.SessionDTO;
import com.theatre.models.TicketsDTO;
import com.theatre.services.SeatsService;

@Repository
public class TicketsDAOImpl implements TicketsDAO {
	private JdbcTemplate jdbcTemplate;
	@Autowired
	SeatsService seatsService;

	public TicketsDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<TicketsDTO> getAllTickets() {
		List<TicketsDTO> list = this.jdbcTemplate
				.query("select * from  tickets t inner join sessions s on t.session_id=s.session_id \n"
						+ "inner join performances p on s.PERFORMANCE_id=p.performance_id \n"
						+ "inner join seats ss on t.seat_id=ss.seat_id\n"
						+ "inner join halls h on ss.hall_id=h.hall_id", new TicketsMapper());
		return list;
	}

	@Override
	public List<TicketsDTO> getAllTicketsForPerformance(long performanceId) {
		List<TicketsDTO> list = this.jdbcTemplate.query(
				"select * from  tickets t inner join sessions s on t.session_id=s.session_id \n"
						+ "inner join performances p on s.PERFORMANCE_id=p.performance_id \n"
						+ "inner join seats ss on t.seat_id=ss.seat_id\n"
						+ "inner join halls h on ss.hall_id=h.hall_id where p.performance_id=?",
				new Object[] { performanceId }, new TicketsMapper());
		return list;
	}

	@Override
	public List<TicketsDTO> getAllTicketsForSession(long sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TicketsDTO> getAllTicketsForHall(long sessionId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean deleteTicket(long id) {
		int i = this.jdbcTemplate.update("delete from tickets where ticket_id=?", new Object[] { id });
		return i > 0;
	}

	@Override
	public boolean deleteAllPerformanceTickets(long performanceId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAllSessionTickets(long performanceId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAllHallTickets(long performanceId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getSeatId(long id) {

		List<Long> res = this.jdbcTemplate.query("select * from tickets where ticket_id=?", new Object[] { id },
				new RowMapper<Long>() {

					@Override
					public Long mapRow(ResultSet rs, int arg1) throws SQLException {

						return rs.getLong("SEAT_ID");
					}
				});
		return res.isEmpty() ? -1 : res.get(0).longValue();
	}

	@Override
	public boolean editTickets(long oldHallId, TicketsDTO ticket) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTicket(TicketsDTO ticketsDTO) {
		int i = this.jdbcTemplate.update("update tickets set price = ? where ticket_id = ? and seat_id = ?",
				new Object[] { ticketsDTO.getPrice(), ticketsDTO.getId(), ticketsDTO.getSeatId() });
		return i > 0;
	}

	@Override
	public TicketsDTO getTicketById(long id) {
		List<TicketsDTO> ticketsDTO = this.jdbcTemplate.query(
				"select * from  tickets t inner join sessions s "
						+ "on t.session_id=s.session_id inner join performances p "
						+ "on s.PERFORMANCE_id=p.performance_id inner join seats ss " + "on t.seat_id=ss.seat_id "
						+ "inner join halls h on ss.hall_id=h.hall_id where t.ticket_id=?",
				new Object[] { id }, new TicketsMapper());
		return ticketsDTO == null || ticketsDTO.size() == 0 ? null : ticketsDTO.get(0);
	}

	@Override
	public boolean addTicket(long seatId, TicketsDTO ticketsDTO) {
		int i=-1;
		if (seatId >= 0) {
			i = this.jdbcTemplate.update("insert into tickets (SESSION_ID,SEAT_ID,PRICE) values (?,?,?)",
					ticketsDTO.getSessionId(), seatId, ticketsDTO.getPrice());
		}

		return i > 0;
	}

	@Override
	public boolean doesTicketExistsForSeat(long seatId,long sessionId) {
		List<Long> ticketId = this.jdbcTemplate.query("select * from tickets where seat_id = ? and session_id=?",
				new Object[] { seatId,sessionId }, new RowMapper<Long>() {

					@Override
					public Long mapRow(ResultSet rs, int rowNum) throws SQLException {

						return rs.getLong("seat_id");
					}
				});
		return ticketId !=null && !ticketId.isEmpty();
	}
	

}
