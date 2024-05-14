package com.prueba.movies.models.repository;

import com.prueba.movies.models.entity.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {}