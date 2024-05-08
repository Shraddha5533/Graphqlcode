package com.movieexampleApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieexampleApplication.entity.Movie;
import com.movieexampleApplication.service.MovieService;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@RestController
@RequestMapping("/graphql")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService)
    {
        this.movieService = movieService;
    }

    @GraphQLQuery(name = "movies",description = "Get all movies")
    public ResponseEntity<List<Movie>> getAllMovies() 
    {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/save")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie)
    {
        movieService.addMovie(movie);
        return ResponseEntity.ok("Movie Added");
    }

    @GraphQLQuery(name = "movieById", description = "Get a movie by ID")
    public ResponseEntity<Movie> getMovieById(@GraphQLArgument(name = "id") Double id)
    {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) 
        {
            return ResponseEntity.ok(movie);
        } else 
        {
            return ResponseEntity.notFound().build();
        }
    }
}
