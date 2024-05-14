package com.prueba.movies.service.iface;

import com.prueba.movies.models.entity.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieService {
    
    List<Movie> getAll();
    Optional<Movie> getById(int id);
    Movie create(Movie movie);
    Movie update(int id, Movie updatedMovie);
    void delete(int id);
}
