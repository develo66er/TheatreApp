package com.theatre.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.theatre.models.TicketsDTO;

public class TicketsMapper implements RowMapper<TicketsDTO> {

	@Override
	public TicketsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TicketsDTO ticketsDTO = new TicketsDTO();
		ticketsDTO.setId(rs.getLong("TICKET_ID"));
		ticketsDTO.setPrice(rs.getDouble("PRICE"));
		ticketsDTO.setSession(new SessionMapper().mapRow(rs, rowNum));
		ticketsDTO.setPerformance(new PerformanceMapper().mapRow(rs, rowNum));
		ticketsDTO.setSeats(new SeatsMapper().mapRow(rs, rowNum));
		ticketsDTO.setHall(new HallMapper().mapRow(rs, rowNum));
		return ticketsDTO;
	}

}
/*
 * select * from  tickets t inner join sessions s on t.session_id=s.session_id 
inner join performances p on s.perfomance_id=p.performance_id 
inner join seats ss on t.seat_id=ss.seat_id
inner join halls h on ss.hall_id=h.hall_id
 * 
 * */
 