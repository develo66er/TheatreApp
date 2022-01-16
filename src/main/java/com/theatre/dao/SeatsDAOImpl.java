package com.theatre.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.theatre.mappers.SeatsMapper;
import com.theatre.models.SeatsDTO;
import com.theatre.models.TicketsDTO;

@Repository
public class SeatsDAOImpl implements SeatsDAO {
	private JdbcTemplate jdbcTemplate;

	public SeatsDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<SeatsDTO> getSeatsForHall(long hallId) {
		List<SeatsDTO> list = this.jdbcTemplate.query("select * from seats where hall_id = ?", new Object[] {hallId},new SeatsMapper());
		return list;
	}

	@Override
	public boolean updateSeat(TicketsDTO ticket) {
		int i = this.jdbcTemplate.update("update seats set row_number = ?, seat_number=? where seat_id=? and hall_id=?",new Object[] {ticket.getRowNumber(),ticket.getSeatNumber(),ticket.getSeatId(),ticket.getHallId()});
		return i>0;
	}

	@Override
	public long doesSeatExists(long hallId, int rowNumber, int seatNumber) {
		List<Long> seats = this.jdbcTemplate.query("select * from seats where HALL_ID=? and ROW_NUMBER=? and SEAT_NUMBER=?",
					new Object[] { hallId, rowNumber,seatNumber },
					new RowMapper<Long>() {
						@Override
						public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getLong("seat_id");
						}
					});
		
		
		return seats != null && !seats.isEmpty()?seats.get(0).longValue():-1;
	}

	@Override
	public long insertSeat(long hallId, int rowNumber, int seatNumber) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection
					.prepareStatement("insert into seats (HALL_ID,ROW_NUMBER,SEAT_NUMBER) values (?,?,?)");
			ps.setLong(1, hallId);
			ps.setInt(2, rowNumber);
			ps.setInt(3, seatNumber);

			return ps;
		}, keyHolder);

		return  keyHolder.getKey()==null?-1:keyHolder.getKey().longValue();
	}
	
	
}
