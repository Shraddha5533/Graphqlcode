package com.movieexampleApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieexampleApplication.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Double> 
{
	
}
