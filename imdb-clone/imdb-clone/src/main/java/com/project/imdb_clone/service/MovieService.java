package com.project.imdb_clone.service;

import com.project.imdb_clone.dto.MovieDto;

import java.util.List;

public interface MovieService {
    MovieDto createMovie(MovieDto moviedto);

    MovieDto getMovieById(Long movieId);

    List<MovieDto> getAllMovies();

    MovieDto updateMovie(Long movieId, MovieDto updatedMovie);

    void deleteMovie(Long movieId);
}
