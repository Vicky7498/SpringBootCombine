package com.infy.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.infy.dto.MovieDTO;
import com.infy.exception.DreamMakersException;
import com.infy.repository.MovieRepository;
import com.infy.validator.Validator;

@Service(value = "movieService")
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	Environment environment;

	public String addMovie(MovieDTO movieDTO) throws DreamMakersException {
		Validator.validate(movieDTO);
		return movieRepository.addMovie(movieDTO);
	}

	public List<MovieDTO> getMovies(String directorName) throws DreamMakersException {
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		movieList = movieRepository.getAllMovies();
		Iterator<MovieDTO> movieIterator = movieList.iterator();
		while (movieIterator.hasNext()) {
			if (movieIterator.next().getDirector().getDirectorName().equals(directorName)) {
				movieIterator.remove();
			}
		}
		if (!movieList.isEmpty()) {
			return movieList;
		} else {
			throw new DreamMakersException(environment.getProperty("Service.MOVIE_NOT_FOUND"));
		}
	}
}
