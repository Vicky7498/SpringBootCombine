package com.infy.validator;

import com.infy.dto.MovieDTO;
import com.infy.exception.DreamMakersException;

public class Validator {

	public static void validate(MovieDTO movieDTO) throws DreamMakersException {
		if (!validateMovie(movieDTO)) {
			throw new DreamMakersException("Validator.INVALID_NAMES");
		}
	}

	public static Boolean validateMovie(MovieDTO movieDTO) {
		if (movieDTO.getMovieName() == null || movieDTO.getDirector().getDirectorName() == null
				|| movieDTO.getMovieName() == "" || movieDTO.getDirector().getDirectorName() == "") {
			return false;
		} else {
			return true;
		}
	}
}
