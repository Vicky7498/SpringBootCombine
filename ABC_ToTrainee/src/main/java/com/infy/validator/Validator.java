package com.infy.validator;

import com.infy.dto.TeamMemberDTO;
import com.infy.exception.AbcException;

public class Validator {

	public static void validate(TeamMemberDTO teamMember) throws AbcException {
		if (!validateEmployeeId(teamMember.getEmployeeId())) {
			throw new AbcException("Validator.INVALID_EMPLOYEEID");
		}
	}

	public static Boolean validateEmployeeId(Integer employeeId) throws AbcException {
		String emoIdPattern = "^\\d{6}$";
		String empIdString = Integer.toString(employeeId);
		if (empIdString.matches(emoIdPattern)) {
			return true;
		} else {
			return false;
		}
	}
}
