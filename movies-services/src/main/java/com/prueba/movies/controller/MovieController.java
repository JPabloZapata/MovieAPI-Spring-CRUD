package com.prueba.movies.controller;

import com.prueba.movies.dto.base.BaseResponse;
import com.prueba.movies.dto.movie.ResponseCreateMovie;
import com.prueba.movies.dto.movie.ResponseDeleteMovie;
import com.prueba.movies.exception.MovieNotFoundException;
import com.prueba.movies.models.entity.Movie;
import com.prueba.movies.service.iface.MovieService;
import com.prueba.movies.util.ErrorMessages;
import com.prueba.movies.util.Utils;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    
    @Autowired
    public MovieService movieService;
    
    @GetMapping("/getAll")
    public BaseResponse<List<Movie>> getAllMovies(){
        BaseResponse<List<Movie>> response = new BaseResponse<>();
        try {
            List<Movie> movies = movieService.getAll();
            if (!movies.isEmpty()) {
                response.setEstado("Success");
                response.setResultado(movies);
            } else {
                response.setEstado("Error");
                response.setDescripcionError(ErrorMessages.SEARCH_MOVIES_EMPTY);
            }
        } catch (Exception e) {
            response.setEstado("Error");
            response.setDescripcionError(ErrorMessages.SEARCH_MOVIES_ERROR);
        }
        return response;
    }
    
    @GetMapping("/getById/{id}")
    public BaseResponse<Movie> getMovieById(@PathVariable int id){
        BaseResponse<Movie> response = new BaseResponse<>();
        try {
            Optional<Movie> movieOptional = movieService.getById(id);
            if (movieOptional != null) {
                response.setEstado("Success");
                response.setResultado(movieOptional.get());
            } else {
                response.setEstado("Error");
                response.setDescripcionError(ErrorMessages.SEARCH_MOVIES_NOT_FOUND);
            }
        } catch (Exception e) {
            response.setEstado("Error");
            response.setDescripcionError(ErrorMessages.SEARCH_MOVIES_ERROR);
        }
        return response;
    }
    
    @PostMapping("/create")
    public BaseResponse<ResponseCreateMovie> createMovie(@Valid @RequestBody Movie movie, BindingResult result){
        BaseResponse<ResponseCreateMovie> response = new BaseResponse<>();
        if (result.hasErrors()) {
            return Utils.getResponseFailed(result);
        }
        try {
            Movie createdMovie = movieService.create(movie);
            if (createdMovie != null) {
                response.setEstado("Success");
                response.setResultado(new ResponseCreateMovie(true));
            } else {
                response.setEstado("Error");
                response.setDescripcionError(ErrorMessages.SEARCH_MOVIES_NOT_SAVED);
                response.setResultado(new ResponseCreateMovie(false));
            }
        } catch (Exception e) {
            response.setEstado("Error");
            response.setDescripcionError("Message: " + e.getMessage());
            response.setResultado(new ResponseCreateMovie(false));
        }
        return response;
    }

    @PutMapping("/update/{id}")
    public BaseResponse<Movie> updateMovie(@PathVariable int id, @Valid @RequestBody Movie updatedMovie, BindingResult result){
        BaseResponse<Movie> response = new BaseResponse<>();
        if (result.hasErrors()) {
            return Utils.getResponseFailed(result);
        }
        try {
            Movie updatedMovieResult = movieService.update(id, updatedMovie);
            if (updatedMovieResult != null) {
                response.setEstado("Success");
                response.setResultado(updatedMovieResult);
            } else {
                response.setEstado("Error");
                response.setDescripcionError(ErrorMessages.UPDATE_MOVIE_ERROR);
            }
        } catch (Exception e) {
            response.setEstado("Error");
            response.setDescripcionError("Message: " + e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<ResponseDeleteMovie> deleteMovie(@PathVariable int id){
        BaseResponse<ResponseDeleteMovie> response = new BaseResponse<>();
        try {
            movieService.delete(id);
            response.setEstado("Success");
            response.setResultado(new ResponseDeleteMovie(true));
        } catch (MovieNotFoundException ex) {
            response.setEstado("Error");
            response.setDescripcionError(ex.getMessage());
            response.setResultado(new ResponseDeleteMovie(false));
        }catch (Exception e) {
            response.setEstado("Error");
            response.setDescripcionError(ErrorMessages.DELETE_MOVIE_ERROR + " con ID " + id + ": " + e.getMessage());
            response.setResultado(new ResponseDeleteMovie(false));
        }
        return response;
    }
}
