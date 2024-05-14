package com.prueba.movies.dto.movie;

public class ResponseDeleteMovie {
    private boolean isMovieDelete;

    public ResponseDeleteMovie(boolean isMovieDelete) {
        this.isMovieDelete = isMovieDelete;
    }

    public boolean isIsMovieDelete() {
        return isMovieDelete;
    }

    public void setIsMovieDelete(boolean isMovieDelete) {
        this.isMovieDelete = isMovieDelete;
    }
    
}
