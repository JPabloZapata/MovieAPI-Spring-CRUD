package com.prueba.movies.service.impl;

import com.prueba.movies.exception.MovieNotFoundException;
import com.prueba.movies.models.entity.Movie;
import com.prueba.movies.models.repository.MovieRepository;
import com.prueba.movies.service.iface.MovieService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAll() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }

    @Override
    public Optional<Movie> getById(int id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            return optionalMovie;
        }else{
            return null;
        }
    }

    @Override
    public Movie create(Movie movie) {
        movie.setFechaCreacion(LocalDateTime.now());
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(int id, Movie updatedMovie) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie existingMovie = optionalMovie.get();
            existingMovie.setTitulo(updatedMovie.getTitulo());
            existingMovie.setGenero(updatedMovie.getGenero());
            existingMovie.setDirector(updatedMovie.getDirector());
            existingMovie.setFechaCreacion(LocalDateTime.now());
            existingMovie.setAnioLanzamiento(updatedMovie.getAnioLanzamiento());
            existingMovie.setDuracion(updatedMovie.getDuracion());
            return movieRepository.save(existingMovie);
        } else {
            throw new RuntimeException("No se encontró ninguna película con el ID: " + id);
        }
    }

    @Override
    public void delete(int id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            movieRepository.deleteById(id);
        } else {
            throw new MovieNotFoundException("La película con el ID " + id + " no se encontró y no se pudo eliminar.");
        }
    }
}
