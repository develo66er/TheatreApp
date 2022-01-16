package com.theatre.filters;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.theatre.models.SessionDTO;

@Component
public class SessionFilter {
	public List<SessionDTO> filterByDate( List<SessionDTO> sessions, String date){
		System.out.println("sessionDate ----------------------------------------------------");
		 List<SessionDTO> filtered = sessions.stream().filter(s->{
			 Timestamp ts = s.getStartDateTime();
			 LocalDateTime ldt = ts.toLocalDateTime();
			 String sessionDate = String.format("%d-%d-%d", ldt.getYear(),ldt.getMonthValue(),ldt.getDayOfMonth());
			 System.out.println("sessionDate ------------"+ sessionDate);
			 System.out.println("Date ------------"+ date);

			 return sessionDate.equals(date);
		 }).collect(Collectors.toList());
		return filtered;
	}
}
