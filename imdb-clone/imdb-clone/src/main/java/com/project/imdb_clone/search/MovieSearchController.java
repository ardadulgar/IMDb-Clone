package com.project.imdb_clone.search;

import com.project.imdb_clone.entity.Movie;
import com.project.imdb_clone.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies/search")
@RequiredArgsConstructor
public class MovieSearchController {

    private final MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<Page<Movie>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(defaultValue = "0")    int page,
            @RequestParam(defaultValue = "10")   int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc")  String direction
    ) {
        List<String> allowedSortFields = List.of("name", "genre", "rating");
        if (!allowedSortFields.contains(sortBy)) {
            sortBy = "name";
        }

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Movie> result = movieRepository.findAll(
                MovieSpecification.filter(name, genre),
                pageable
        );

        return ResponseEntity.ok(result);
    }
}