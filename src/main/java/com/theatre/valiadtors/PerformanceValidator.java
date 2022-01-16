package com.theatre.valiadtors;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.theatre.models.PerformanceDTO;
@Service
public class PerformanceValidator implements Validator{
	@Autowired
	private Validator validator;
	@Override
	public boolean supports(Class<?> clazz) {
		
		return PerformanceDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PerformanceDTO p = (PerformanceDTO) target;
        if (p.getName()==null) {
            errors.rejectValue("name", "value.negative");
        }
        if (p.getStartDate()==null) {
            errors.rejectValue("startDate", "value.null");
        }
        if (p.getEndDate()==null) {
            errors.rejectValue("endDate", "value.null");
        }
	}

}
