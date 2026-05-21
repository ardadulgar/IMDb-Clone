package com.project.imdb_clone.controller;

import com.project.imdb_clone.dto.MovieDto;
import com.project.imdb_clone.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;

    // post movie rest apisi olusturuldu
    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {
        MovieDto savedMovie = movieService.createMovie(movieDto);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    //get movie rest apisi olusturuldu
    @GetMapping("{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("id") Long movieId) {
        MovieDto movieDto = movieService.getMovieById(movieId);
        return ResponseEntity.ok(movieDto);
    }

    //getall movie rest apisi olusturuldu
    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies(){
        List<MovieDto> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    //update movie rest apisi olusturuldu
    @PutMapping("{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable("id") Long movieId,
                                                @RequestBody MovieDto updatedMovie){
        MovieDto movieDto = movieService.updateMovie(movieId, updatedMovie);
        return ResponseEntity.ok(movieDto);
    }

    //delete movie rest apisi olusturuldu
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.ok("Film silindi basariyla :)");
    }
}

