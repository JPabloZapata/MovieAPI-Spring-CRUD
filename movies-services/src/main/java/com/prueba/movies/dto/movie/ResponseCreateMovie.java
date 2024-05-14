package com.prueba.movies.dto.movie;

public class ResponseCreateMovie {
    private boolean isMovieSaved;

    public boolean isIsMovieSaved() {
        return isMovieSaved;
    }

    public void setIsMovieSaved(boolean isMovieSaved) {
        this.isMovieSaved = isMovieSaved;
    }

    public ResponseCreateMovie(boolean isMovieSaved) {
        this.isMovieSaved = isMovieSaved;
    }
}
