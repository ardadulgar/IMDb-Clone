package com.project.imdb_clone.repository;
import com.project.imdb_clone.entity.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieRepository extends JpaRepository<Movie, Long>,
        JpaSpecificationExecutor<Movie> {  // ← bunu ekle
    // mevcut metodlar...
}