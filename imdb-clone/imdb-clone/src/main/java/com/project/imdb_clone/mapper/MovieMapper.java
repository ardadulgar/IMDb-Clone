package com.project.imdb_clone.mapper;

import com.project.imdb_clone.dto.MovieDto;
import com.project.imdb_clone.entity.Movie;

public class MovieMapper {
    public static MovieDto mapToMovieDto(Movie movie) {
        return new MovieDto(
            movie.getId(),
            movie.getName(),
            movie.getGenre(),
            movie.getDescription(),
            movie.getRating()
        );
    }
    public static Movie mapToMovie(MovieDto moviedto) {
        return new Movie(
                moviedto.getId(),
                moviedto.getName(),
                moviedto.getGenre(),
                moviedto.getDescription(),
                moviedto.getRating()
        );
    }
}
