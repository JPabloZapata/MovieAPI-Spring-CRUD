package com.prueba.movies.dto.base;

public class BaseResponse<T> {
    
    private String descripcionError;
    private String estado;
    private T resultado;
    
    public BaseResponse() {}

    public BaseResponse(String descripcionError, String estado, T resultado) {
        this.descripcionError = descripcionError;
        this.estado = estado;
        this.resultado = resultado;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public T getResultado() {
        return resultado;
    }

    public void setResultado(T resultado) {
        this.resultado = resultado;
    } 
}
