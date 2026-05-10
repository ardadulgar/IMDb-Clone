package com.project.imdb_clone.service.impl;

import com.project.imdb_clone.dto.MovieDto;
import com.project.imdb_clone.entity.Movie;
import com.project.imdb_clone.exception.ResourceNotFoundException;
import com.project.imdb_clone.mapper.MovieMapper;
import com.project.imdb_clone.repository.MovieRepository;
import com.project.imdb_clone.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Override
    public MovieDto createMovie(MovieDto movieDto) {

        Movie movie = MovieMapper.mapToMovie(movieDto);
        Movie savedMovie = movieRepository.save(movie);

        return MovieMapper.mapToMovieDto(savedMovie);
    }

    @Override
    public MovieDto getMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Veri bulunamadi verilen id ile :" + movieId));
        return MovieMapper.mapToMovieDto(movie);
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map((movie) -> MovieMapper.mapToMovieDto(movie))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto updateMovie(Long movieId, MovieDto updatedMovie) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new ResourceNotFoundException("Film bulumnamadi verilen id ile: " + movieId)
        );

        movie.setName(updatedMovie.getName());
        movie.setGenre(updatedMovie.getGenre());
        movie.setDescription(updatedMovie.getDescription());
        movie.setRating(updatedMovie.getRating());

        Movie updatedMovieObj = movieRepository.save(movie);

        return MovieMapper.mapToMovieDto(updatedMovieObj);
    }

    @Override
    public void deleteMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new ResourceNotFoundException("Film bulumnamadi verilen id ile  : " + movieId)
        );
        movieRepository.deleteById(movieId);
    }
}
